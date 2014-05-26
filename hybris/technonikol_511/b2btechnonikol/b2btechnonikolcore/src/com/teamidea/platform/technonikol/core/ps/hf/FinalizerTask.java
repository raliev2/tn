package com.teamidea.platform.technonikol.core.ps.hf;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.teamidea.platform.technonikol.core.ps.hf.model.HFPackageFileModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import java.util.Collection;
import java.util.Map;

/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class FinalizerTask extends AbstractHotFolderTask {

    private static final Logger LOG = Logger.getLogger(FinalizerTask.class);

    private Map<String,Finalizer> finalizerMap;

    private Finalizer defaultFinalizer;

    public HotFolderPackageMessage onExecute(final HotFolderPackageMessage object) {
        LOG.debug("Finalizing file: " + object.getCurrentPath() + " in package: " + object.getPackageId());
        getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {

                getHotFolderPackageService().finalizeFile(object.getPackageId(),object.getSequenceNumber(), !object.isError());

                Collection<HFPackageFileModel> fileModels = getHotFolderPackageService().getPackageFiles(object.getPackageId());

                Collection<HFPackageFileModel> finishedFiles = Collections2.filter(fileModels, new Predicate<HFPackageFileModel>() {
                    @Override
                    public boolean apply(HFPackageFileModel HFPackageFileModel) {
                        return HFPackageFileModel.getProcessed();
                    }
                });

                if (finishedFiles.size() == object.getFileNumbers()) {
                    LOG.debug("All files for package: " + object.getPackageId() + " has been processed, checking if any of "
                            + fileModels.size() + " is not processed successfully ");

                    Collection<HFPackageFileModel> problemFiles = Collections2.filter(fileModels, new Predicate<HFPackageFileModel>() {
                        @Override
                        public boolean apply(HFPackageFileModel HFPackageFileModel) {
                            return HFPackageFileModel.getErrors();
                        }
                    });

                    if (CollectionUtils.isEmpty(problemFiles)) {
                        LOG.debug("All files for package: " + object.getPackageId() + " has been processed successfully, can do finalization...");

                        Finalizer finalizerForType = getFinalizerMap().get(object.getPackageType());
                        if(finalizerForType!=null){
                            finalizerForType.finalizeImport(object);
                        } else if(getDefaultFinalizer()!=null){
                            getDefaultFinalizer().finalizeImport(object);
                        }

                        getHotFolderPackageService().finishPackage(object.getPackageId());
                    } else {
                        LOG.error(problemFiles.size() + " out of " + object.getFileNumbers() + " has been processed successfully, cannot do finalization, check such files");
                        for (HFPackageFileModel problemFile: problemFiles){
                            LOG.error("Problem file: "+problemFile.getPath());
                        }
                    }

                } else {
                    LOG.debug(finishedFiles.size() + "out of " + object.getFileNumbers() + " has been processed, no finalization yet...");
                }

            }
        });
        return null;
    }

    public Map<String, Finalizer> getFinalizerMap() {
        return finalizerMap;
    }

    public void setFinalizerMap(Map<String, Finalizer> finalizerMap) {
        this.finalizerMap = finalizerMap;
    }

    public Finalizer getDefaultFinalizer() {
        return defaultFinalizer;
    }

    public void setDefaultFinalizer(Finalizer defaultFinalizer) {
        this.defaultFinalizer = defaultFinalizer;
    }
}
