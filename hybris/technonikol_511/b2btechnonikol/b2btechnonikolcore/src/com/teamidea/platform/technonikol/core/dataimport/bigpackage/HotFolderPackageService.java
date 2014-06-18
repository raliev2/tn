package com.teamidea.platform.technonikol.core.dataimport.bigpackage;


import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.google.common.collect.Sets;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.exceptions.BigPackageException;
import com.teamidea.platform.technonikol.core.model.hotfolder.HFPackageFileModel;
import com.teamidea.platform.technonikol.core.model.hotfolder.HFPackageModel;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class HotFolderPackageService extends AbstractBusinessService
{

	private static final Logger LOG = Logger.getLogger(HotFolderPackageService.class);

	private FlexibleSearchService flexibleSearchService;

	public HFPackageModel registerPackage(final String packageId, final String packageType, final int totalFiles,
			final int sequence, final Path pathToFile)
	{
		Assert.noNullElements(new Object[]
		{ packageId, packageType, pathToFile });

		if (packageId == null)
		{
			final HotFolderPackageMessage badMessage = new HotFolderPackageMessage(pathToFile);
			badMessage.setError(true);

			throw new BigPackageException("Package GUID cannot be empty", badMessage);
		}

		final HFPackageModel model = getModelService().create(HFPackageModel.class);
		model.setGuid(packageId);
		model.setType(packageType);
		model.setDateCreated(new Date());
		model.setFilesNumber(totalFiles);

		return registerNewFile(model, sequence, pathToFile);
	}

	public HFPackageModel registerNewFile(final HFPackageModel packageModel, final int sequenceId, final Path path)
	{
		Assert.noNullElements(new Object[]
		{ packageModel, path });

		if (CollectionUtils.isNotEmpty(packageModel.getFiles()))
		{
			for (final HFPackageFileModel fileModel : packageModel.getFiles())
			{
				if (fileModel.getSequence() == sequenceId)
				{
					LOG.warn("For package: " + packageModel.getGuid() + " there is already file with sequence: " + sequenceId
							+ " and status of processing: " + packageModel.getFinished());

					if (fileModel.getErrors())
					{
						LOG.warn("Reprocessing file for package: " + packageModel.getGuid() + " and sequence: " + sequenceId);
						return packageModel;
					}
					else
					{
						final HotFolderPackageMessage badMessage = new HotFolderPackageMessage(path);
						badMessage.setError(true);

						//TODO better routing mechanism
						throw new BigPackageException("File for package: " + packageModel.getGuid() + " and sequence: " + sequenceId
								+ " has been successfully processed on: " + fileModel.getDateFinished() + " thus is ignored", badMessage);
					}
				}
			}
		}

		final HFPackageFileModel file = getModelService().create(HFPackageFileModel.class);
		file.setDateCreated(new Date());
		file.setPath(path.toString());
		file.setPackageId(packageModel.getGuid());
		file.setSequence(sequenceId);
		file.setDateCreated(new Date());

		final Set<HFPackageFileModel> files = Sets.newHashSet();
		if (CollectionUtils.isNotEmpty(packageModel.getFiles()))
		{
			files.addAll(packageModel.getFiles());
		}
		files.add(file);

		packageModel.setFiles(files);

		getModelService().save(packageModel);
		return packageModel;
	}

	public HFPackageModel registerNewFile(final String packageId, final int sequenceId, final Path path)
	{
		Assert.noNullElements(new Object[]
		{ packageId, path });
		final HFPackageModel model = getPackage(packageId);
		return registerNewFile(model, sequenceId, path);
	}

	public HFPackageFileModel finalizeFile(final String packageId, final int sequence, final boolean importSuccessfull)
	{
		Assert.notNull(packageId);

		final FlexibleSearchQuery query = new FlexibleSearchQuery("select {pk} from {" + HFPackageFileModel._TYPECODE
				+ "} where {packageId}=?packageId and {sequence}=?sequence");
		query.addQueryParameter("packageId", packageId);
		query.addQueryParameter("sequence", sequence);
		try
		{
			final HFPackageFileModel model = getFlexibleSearchService().searchUnique(query);
			model.setProcessed(true);
			model.setErrors(!importSuccessfull);
			model.setDateFinished(new Date());
			getModelService().save(model);
			return model;
		}
		catch (final ModelNotFoundException ex)
		{
			LOG.error(ex, ex);
			throw new IllegalArgumentException("Cannot identify the file for package: " + packageId + " and sequence: " + sequence);
		}
	}

	public Collection<HFPackageFileModel> getPackageFiles(final String packageId)
	{
		Assert.noNullElements(new Object[]
		{ packageId });
		return getPackage(packageId).getFiles();
	}

	public HFPackageModel finishPackage(final String packageId)
	{
		final HFPackageModel model = getPackage(packageId);
		model.setDateFinished(new Date());
		model.setFinished(true);
		getModelService().save(model);
		return model;
	}

	public Collection<String> getPackageTypes()
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery("select distinct {type} from {" + HFPackageModel._TYPECODE + "}");
		return getFlexibleSearchService().<String> search(query).getResult();
	}

	public Collection<HFPackageModel> getPackages(final String type)
	{
		Assert.notNull(type);
		final FlexibleSearchQuery query = new FlexibleSearchQuery("select {pk} from {" + HFPackageModel._TYPECODE
				+ "} where {type}=?type");
		query.addQueryParameter("type", type);
		return getFlexibleSearchService().<HFPackageModel> search(query).getResult();
	}

	public HFPackageModel getPackage(final String packageId)
	{
		Assert.notNull(packageId);
		final FlexibleSearchQuery query = new FlexibleSearchQuery("select {pk} from {" + HFPackageModel._TYPECODE
				+ "} where {guid}=?guid");
		query.addQueryParameter("guid", packageId);
		try
		{
			return getFlexibleSearchService().searchUnique(query);
		}
		catch (final ModelNotFoundException ex)
		{
			return null;
		}

	}


	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}


}
