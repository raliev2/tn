/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper;
import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.teamidea.platform.technonikol.core.dataimport.ImpexLoggerService;
import com.teamidea.platform.technonikol.core.dataimport.batch.utils.IOUtilsExt;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;


/**
 * @author Aleksey Lubimov
 * 
 */
public class CleanupBigPackageHelper extends CleanupHelper
{
	private static final Logger log = Logger.getLogger(CleanupBigPackageHelper.class);

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
			log.debug("Cleanup BatchHeader. SOURCE before TRANSFORMED. hasError:" + error);
		}
		if (header != null)
		{
			cleanupSourceFile(header, error);
			cleanupTransformedFiles(header);
		}
	}

	public void cleanup(final HotFolderPackageMessage packageMessage)
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
			cleanupPackageMessage(packageMessage);
		}
	}

	/**
	 * @param packageMessage
	 */
	private void cleanupPackageMessage(final HotFolderPackageMessage packageMessage)
	{
		final String dirName = BatchDirectoryUtils.getRelativeArchiveDirectory(packageMessage.getCurrentPath().toFile());
		try
		{
			writeStatus(packageMessage);
			Files.move(packageMessage.getCurrentPath(),
					Paths.get(dirName, packageMessage.getCurrentPath().getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);

		}
		catch (final IOException e)
		{
			log.error(e, e);
		}
	}

	public void writeError(final HotFolderPackageMessage packageMessage, final String message)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		XMLStreamWriter errorWriter = null;
		try
		{
			errorWriter = loggerService.writeXmlError(packageMessage.getCurrentPath().toFile(), errorWriter, null, null, message);
		}
		finally
		{
			IOUtilsExt.closeQuietly(errorWriter);
		}
	}

	/**
	 * @param packageMessage
	 */
	private void writeStatus(final HotFolderPackageMessage packageMessage) throws UnsupportedEncodingException,
			FileNotFoundException
	{
		PrintWriter statusWriter = null;
		try
		{
			statusWriter = loggerService.writeStatus(packageMessage.getCurrentPath().toFile(), null,
					packageMessage.isError() ? "Error" : "Ok");
		}
		finally
		{
			IOUtils.closeQuietly(statusWriter);
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
