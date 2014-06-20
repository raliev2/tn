/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper;
import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;
import de.hybris.platform.cronjob.model.JobLogModel;
import de.hybris.platform.impex.model.ImpExMediaModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.teamidea.platform.technonikol.core.dataimport.ImpexLoggerService;
import com.teamidea.platform.technonikol.core.dataimport.LoggerStatus;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;


/**
 * @author Aleksey Lubimov
 * 
 */
public class CleanupBigPackageHelper extends CleanupHelper
{
	private static final Logger log = Logger.getLogger(CleanupBigPackageHelper.class);

	private static final String DATE_SEPARATOR = "_";

	private ImpexLoggerService loggerService;

	/**
	 * Performs the cleanup of given header
	 * 
	 * @param header
	 *           to clean up
	 * @param error
	 *           flag indicating if there was an error
	 */
	@Override
	public void cleanup(final BatchHeader header, final boolean error)
	{
		if (log.isDebugEnabled())
		{
			log.debug("Cleanup BatchHeader. hasError:" + error);
		}
		if (header != null)
		{
			try
			{
				if (header.getFile() != null)
				{
					final File movedFile = getDestFile(header.getFile(), false);
					if (!header.getFile().renameTo(movedFile))
					{
						log.warn("Could not move " + header.getFile() + " to " + movedFile);

					}
				}
				if (!error)
				{
					loggerService.writeXmlSuccessStatus(header.getFile());
				}
				else
				{
					loggerService.writeXmlStatus(header.getFile(), LoggerStatus.FATAL, null, null, "Import error");
				}
			}
			catch (final Exception e)
			{
				log.error(e, e);
			}
		}
	}

	public void cleanup(final HotFolderPackageMessage packageMessage)
	{
		try
		{
			if (!packageMessage.isImpexImportError())
			{
				cleanup(packageMessage, packageMessage.isError() ? LoggerStatus.FATAL : LoggerStatus.SUCCESS, null);
			}
			else
			{
				moveFile(packageMessage.getCurrentPath());
			}
		}
		catch (final Exception e)
		{
			log.error(e, e);
		}
	}

	public void cleanup(final HotFolderPackageMessage packageMessage, final String code, final String errorMessage)
	{
		if (log.isDebugEnabled())
		{
			log.debug("Cleanup HotFolderPackageMessage.");
		}
		if (packageMessage != null)
		{
			if (log.isDebugEnabled())
			{
				log.debug("HotFolderPackageMessage.isError:" + packageMessage.isError());
			}
			cleanupPackageMessage(packageMessage, code, errorMessage);
		}
	}

	/**
	 * @param packageMessage
	 */
	private void cleanupPackageMessage(final HotFolderPackageMessage packageMessage, final String code, final String errorMessage)
	{
		try
		{
			moveFile(packageMessage.getCurrentPath());
			if (!packageMessage.isError() && LoggerStatus.SUCCESS.equals(code))
			{
				loggerService.writeXmlSuccessStatus(packageMessage.getCurrentPath().toFile());
			}
			else
			{
				loggerService.writeXmlStatus(packageMessage.getCurrentPath().toFile(), code, null, null, errorMessage);
			}
		}
		catch (final Exception e)
		{
			log.error(e, e);
		}
	}


	/**
	 * @param packageMessage
	 * @param dirName
	 * @throws IOException
	 */
	private void moveFile(final Path currentPath) throws IOException
	{
		final StringBuilder builder = new StringBuilder(currentPath.getFileName().toString());
		if (!StringUtils.isBlank(getTimeStampFormat()))
		{
			final SimpleDateFormat sdf = new SimpleDateFormat(getTimeStampFormat(), Locale.getDefault());
			builder.append(DATE_SEPARATOR);
			builder.append(sdf.format(new Date()));
		}

		final String dirName = BatchDirectoryUtils.getRelativeArchiveDirectory(currentPath.toFile());
		Files.move(currentPath, Paths.get(dirName, builder.toString()), StandardCopyOption.REPLACE_EXISTING);
	}

	private void moveFile(final File file) throws IOException
	{
		final File movedFile = getDestFile(file, false);
		if (!file.renameTo(movedFile))
		{
			log.warn("Could not move " + file + " to " + movedFile);
		}
	}

	/**
	 * @param packageMessage
	 */
	public void cleanup(final File file, final String status, final ImpExMediaModel unresolvedLines, final List<JobLogModel> logs,
			final String message)
	{
		if (log.isDebugEnabled())
		{
			log.debug("Cleanup File. Status:" + status);
		}

		try
		{
			if (file != null)
			{
				moveFile(file);
			}
			if (LoggerStatus.SUCCESS.equals(status))
			{
				loggerService.writeXmlSuccessStatus(file);
			}
			else
			{
				loggerService.writeXmlStatus(file, status, unresolvedLines, logs, message);
			}
		}
		catch (final Exception e)
		{
			log.error(e, e);
		}
	}

	public ImpexLoggerService getLoggerService()
	{
		return loggerService;
	}

	@Required
	public void setLoggerService(final ImpexLoggerService loggerService)
	{
		this.loggerService = loggerService;
	}
}
