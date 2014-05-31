package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.CSVConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.teamidea.platform.technonikol.core.dataimport.ImpexLoggerService;
import com.teamidea.platform.technonikol.core.dataimport.batch.utils.IOUtilsExt;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class ImpexProcessorTask extends AbstractHotFolderTask
{

	private static final Logger LOG = Logger.getLogger(ImpexProcessorTask.class);

	private SessionService sessionService;
	private ImportService importService;
	private ImportConfig importConfig;
	private ImpexLoggerService loggerService;

	private String encoding = CSVConstants.HYBRIS_ENCODING;

	public HotFolderPackageMessage onExecute(final HotFolderPackageMessage message) throws UnsupportedEncodingException,
			FileNotFoundException, XMLStreamException
	{
		Assert.notNull(message);
		Assert.notNull(message.getCurrentPath());
		final Session localSession = getSessionService().createNewSession();

		XMLStreamWriter errorWriter = null;
		final File currentFile = message.getCurrentPath().toFile();
		try (InputStream fis = new FileInputStream(currentFile))
		{
			final ImportConfig config = getImportConfig();
			final ImpExResource resource = new StreamBasedImpExResource(fis, getEncoding());
			config.setScript(resource);
			final ImportResult importResult = getImportService().importData(config);
			if (importResult.isError())
			{
				if (importResult.hasUnresolvedLines())
				{
					LOG.error(importResult.getUnresolvedLines().getPreview());
				}

				ImpExMediaModel unresolvedLines = null;
				if (importResult.hasUnresolvedLines())
				{
					LOG.error(importResult.getUnresolvedLines().getPreview());
					unresolvedLines = importResult.getUnresolvedLines();
				}

				errorWriter = loggerService.writeXmlError(currentFile, errorWriter, unresolvedLines, importResult.getCronJob()
						.getLogs(), "Impex import failed");
			}
			message.setError(importResult.isError());
			message.setImpexImportError(importResult.isError());
			return message;
		}
		catch (final IOException e)
		{
			LOG.error(e, e);
			message.setError(true);
			return message;
		}
		finally
		{
			IOUtilsExt.closeQuietly(errorWriter);
			getSessionService().closeSession(localSession);
		}
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public ImportService getImportService()
	{
		return importService;
	}

	public void setImportService(final ImportService importService)
	{
		this.importService = importService;
	}

	public ImportConfig getImportConfig()
	{
		return importConfig;
	}

	public void setImportConfig(final ImportConfig importConfig)
	{
		this.importConfig = importConfig;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(final String encoding)
	{
		this.encoding = encoding;
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
