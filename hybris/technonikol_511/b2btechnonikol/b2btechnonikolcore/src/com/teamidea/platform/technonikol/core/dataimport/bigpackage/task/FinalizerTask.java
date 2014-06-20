package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.exceptions.BigPackageException;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.finalizer.Finalizer;
import com.teamidea.platform.technonikol.core.model.hotfolder.HFPackageFileModel;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class FinalizerTask extends AbstractHotFolderTask
{

	private static final Logger LOG = Logger.getLogger(FinalizerTask.class);

	private Map<String, Finalizer> finalizerMap;

	private Finalizer defaultFinalizer;

	public HotFolderPackageMessage onExecute(final HotFolderPackageMessage message)
	{
		LOG.debug("Finalizing file: " + message.getCurrentPath() + " in package: " + message.getPackageId());
		try
		{
			getTransactionTemplate().execute(new TransactionCallbackWithoutResult()
			{
				@Override
				protected void doInTransactionWithoutResult(final TransactionStatus transactionStatus)
				{
					getHotFolderPackageService().finalizeFile(message.getPackageId(), message.getSequenceNumber(), !message.isError());

					final Collection<HFPackageFileModel> fileModels = getHotFolderPackageService().getPackageFiles(
							message.getPackageId());

					final Collection<HFPackageFileModel> finishedFiles = Collections2.filter(fileModels,
							new Predicate<HFPackageFileModel>()
							{
								@Override
								public boolean apply(final HFPackageFileModel HFPackageFileModel)
								{
									return HFPackageFileModel.getProcessed();
								}
							});

					if (finishedFiles.size() == message.getFileNumbers())
					{
						LOG.debug("All files for package: " + message.getPackageId() + " has been processed, checking if any of "
								+ fileModels.size() + " is not processed successfully ");

						final Collection<HFPackageFileModel> problemFiles = Collections2.filter(fileModels,
								new Predicate<HFPackageFileModel>()
								{
									@Override
									public boolean apply(final HFPackageFileModel HFPackageFileModel)
									{
										return HFPackageFileModel.getErrors();
									}
								});

						if (CollectionUtils.isEmpty(problemFiles))
						{
							LOG.debug("All files for package: " + message.getPackageId()
									+ " has been processed successfully, can do finalization...");

							final Finalizer finalizerForType = getFinalizerMap().get(message.getPackageType());
							if (finalizerForType != null)
							{
								LOG.debug("run finalization...");
								finalizerForType.finalizeImport(message);
							}
							else if (getDefaultFinalizer() != null)
							{
								LOG.debug("run default finalization...");
								getDefaultFinalizer().finalizeImport(message);
							}

							getHotFolderPackageService().finishPackage(message.getPackageId());
						}
						else
						{
							LOG.error(problemFiles.size() + " out of " + message.getFileNumbers()
									+ " has been processed successfully, cannot do finalization, check such files");
							for (final HFPackageFileModel problemFile : problemFiles)
							{
								LOG.error("Problem file: " + problemFile.getPath());
							}
						}

					}
					else
					{
						LOG.debug(finishedFiles.size() + "out of " + message.getFileNumbers()
								+ " has been processed, no finalization yet...");
					}
				}
			});

		}
		catch (final Exception e)
		{
			LOG.error("FinalizerTask failed...", e);
			message.setError(true);
			throw new BigPackageException(message, e);
		}
		return message;
	}

	public Map<String, Finalizer> getFinalizerMap()
	{
		return finalizerMap;
	}

	public void setFinalizerMap(final Map<String, Finalizer> finalizerMap)
	{
		this.finalizerMap = finalizerMap;
	}

	public Finalizer getDefaultFinalizer()
	{
		return defaultFinalizer;
	}

	public void setDefaultFinalizer(final Finalizer defaultFinalizer)
	{
		this.defaultFinalizer = defaultFinalizer;
	}
}
