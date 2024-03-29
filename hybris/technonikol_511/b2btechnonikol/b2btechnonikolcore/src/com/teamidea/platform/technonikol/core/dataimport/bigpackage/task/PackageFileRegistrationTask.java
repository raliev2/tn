package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import de.hybris.platform.core.Registry;

import java.io.File;
import java.nio.file.Path;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.exceptions.BigPackageException;
import com.teamidea.platform.technonikol.core.model.hotfolder.HFPackageModel;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class PackageFileRegistrationTask extends AbstractHotFolderTask
{

	private static final Logger LOG = Logger.getLogger(PackageFileRegistrationTask.class);

	public HotFolderPackageMessage onExecute(final File file)
	{
		Registry.activateMasterTenant();

		final Path filePath = file.toPath();
		final HotFolderPackageMessage message = createMessage(filePath);

		getTransactionTemplate().execute(new TransactionCallbackWithoutResult()
		{
			@Override
			protected void doInTransactionWithoutResult(final TransactionStatus transactionStatus)
			{
				final HFPackageModel model = getHotFolderPackageService().getPackage(message.getPackageId());

				if (model != null)
				{
					LOG.debug("Package already exists: " + message.getPackageId() + " for file: " + filePath);
					if (model.getFinished())
					{
						message.setError(true);
						throw new BigPackageException("Cannot process file for finished package.", message);
					}
					getHotFolderPackageService().registerNewFile(message.getPackageId(), message.getSequenceNumber(), filePath);
				}
				else
				{
					LOG.debug("Package: " + message.getPackageId() + " doesnt exist for file: " + filePath + " create new one...");
					getHotFolderPackageService().registerPackage(message.getPackageId(), message.getPackageType(),
							message.getFileNumbers(), message.getSequenceNumber(), filePath);
				}
			}
		});

		return message;
	}
}
