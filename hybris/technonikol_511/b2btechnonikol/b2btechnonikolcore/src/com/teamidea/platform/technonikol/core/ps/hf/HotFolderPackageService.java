package com.teamidea.platform.technonikol.core.ps.hf;


import com.google.common.collect.Sets;
import com.teamidea.platform.technonikol.core.ps.hf.model.HFPackageFileModel;
import com.teamidea.platform.technonikol.core.ps.hf.model.HFPackageModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class HotFolderPackageService extends AbstractBusinessService {

    private static final Logger LOG = Logger.getLogger(HotFolderPackageService.class);

    private FlexibleSearchService flexibleSearchService;

    public HFPackageModel registerPackage(String packageId, String packageType, int totalFiles, int sequence, Path pathToFile) {
        Assert.noNullElements(new Object[]{packageId,packageType,pathToFile});

        if (packageId == null) {
            throw new IllegalArgumentException("Package GUID cannot be empty");
        }

        HFPackageModel model = getModelService().create(HFPackageModel.class);
        model.setGuid(packageId);
        model.setType(packageType);
        model.setDateCreated(new Date());
        model.setFilesNumber(totalFiles);

        return registerNewFile(model,sequence,pathToFile);
    }

    public HFPackageModel registerNewFile(HFPackageModel packageModel, int sequenceId, Path path){
        Assert.noNullElements(new Object[]{packageModel, path});

        if(CollectionUtils.isNotEmpty(packageModel.getFiles())){
           for(HFPackageFileModel fileModel : packageModel.getFiles()){
               if(fileModel.getSequence() == sequenceId){
                   LOG.warn("For package: "+packageModel.getGuid()+" there is already file with sequence: "+sequenceId+" and status of processing: "+packageModel.getFinished());

                   if(fileModel.getErrors()){
                       LOG.warn("Reprocessing file for package: "+packageModel.getGuid()+" and sequence: "+sequenceId);
                       return packageModel;
                   } else{
                       //TODO better routing mechanism
                       throw new IllegalArgumentException("File for package: "+packageModel.getGuid()+" and sequence: "+sequenceId+" has been successfully processed on: "+ fileModel.getDateFinished()+" thus is ignored");
                   }
               }
           }
        }

        HFPackageFileModel file = getModelService().create(HFPackageFileModel.class);
        file.setDateCreated(new Date());
        file.setPath(path.toString());
        file.setPackageId(packageModel.getGuid());
        file.setSequence(sequenceId);
        file.setDateCreated(new Date());

        Set<HFPackageFileModel> files = Sets.newHashSet();
        if(CollectionUtils.isNotEmpty(packageModel.getFiles()))
            files.addAll(packageModel.getFiles());
        files.add(file);

        packageModel.setFiles(files);

        getModelService().save(packageModel);
        return packageModel;
    }

    public HFPackageModel registerNewFile(String packageId, int sequenceId, Path path) {
        Assert.noNullElements(new Object[]{packageId, path});
        HFPackageModel model = getPackage(packageId);
        return registerNewFile(model,sequenceId,path);
    }

    public HFPackageFileModel finalizeFile(String packageId, int sequence, boolean importSuccessfull) {
        Assert.notNull(packageId);

        FlexibleSearchQuery query = new FlexibleSearchQuery("select {pk} from {"+ HFPackageFileModel._TYPECODE+"} where {packageId}=?packageId and {sequence}=?sequence");
        query.addQueryParameter("packageId", packageId);
        query.addQueryParameter("sequence", sequence);
        try{
            HFPackageFileModel model = getFlexibleSearchService().searchUnique(query);
            model.setProcessed(true);
            model.setErrors(!importSuccessfull);
            model.setDateFinished(new Date());
            getModelService().save(model);
            return model;
        }catch(ModelNotFoundException ex){
            LOG.error(ex,ex);
            throw new IllegalArgumentException("Cannot identify the file for package: "+packageId+" and sequence: "+sequence);
        }
    }

    public Collection<HFPackageFileModel> getPackageFiles(String packageId) {
        Assert.noNullElements(new Object[]{packageId});
        return getPackage(packageId).getFiles();
    }

    public HFPackageModel finishPackage(String packageId) {
        HFPackageModel model = getPackage(packageId);
        model.setDateFinished(new Date());
        model.setFinished(true);
        getModelService().save(model);
        return model;
    }

    public Collection<String> getPackageTypes() {
        FlexibleSearchQuery query = new FlexibleSearchQuery("select distinct {type} from {"+HFPackageModel._TYPECODE+"}");
        return getFlexibleSearchService().<String>search(query).getResult();
    }

    public Collection<HFPackageModel> getPackages(String type) {
        Assert.notNull(type);
        FlexibleSearchQuery query = new FlexibleSearchQuery("select {pk} from {"+HFPackageModel._TYPECODE+"} where {type}=?type");
        query.addQueryParameter("type", type);
        return getFlexibleSearchService().<HFPackageModel>search(query).getResult();
    }

    public HFPackageModel getPackage(String packageId) {
        Assert.notNull(packageId);
        FlexibleSearchQuery query = new FlexibleSearchQuery("select {pk} from {"+HFPackageModel._TYPECODE+"} where {guid}=?guid");
        query.addQueryParameter("guid", packageId);
        try {
            return getFlexibleSearchService().searchUnique(query);
        } catch (ModelNotFoundException ex) {
            return null;
        }

    }


    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }


}
