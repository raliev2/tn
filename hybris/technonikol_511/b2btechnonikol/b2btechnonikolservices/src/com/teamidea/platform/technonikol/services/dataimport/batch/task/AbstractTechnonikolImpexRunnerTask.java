/**
 * 
 */
package com.teamidea.platform.technonikol.services.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.HeaderTask;
import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.CSVConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.teamidea.platform.technonikol.services.dataimport.batch.utils.BatchDirectoryExtUtils;
import com.teamidea.platform.technonikol.services.dataimport.batch.utils.IOUtilsExt;


/**
 * @author Aleksey Lubimov
 * 
 */
public abstract class AbstractTechnonikolImpexRunnerTask implements HeaderTask //extends AbstractImpexRunnerTask
{
	private static final Logger log = Logger.getLogger(AbstractTechnonikolImpexRunnerTask.class);

	private SessionService sessionService;
	private ImportService importService;

	private static final String ERROR_FILE_PREFIX = "error_";
	private static final String STATUS_FILE_PREFIX = "status_";
	private static final String ELEMENT_LOG = "log";
	private static final String ELEMENT_ERROR = "error";

	private final String encoding = CSVConstants.HYBRIS_ENCODING;

	@Override
	public BatchHeader execute(final BatchHeader header) throws UnsupportedEncodingException, FileNotFoundException,
			XMLStreamException
	{
		Assert.notNull(header);
		Assert.notNull(header.getEncoding());
		if (CollectionUtils.isNotEmpty(header.getTransformedFiles()))
		{
			final Session localSession = getSessionService().createNewSession();
			try
			{
				for (final File file : header.getTransformedFiles())
				{
					processFile(file, header.getEncoding());
				}
			}
			finally
			{
				getSessionService().closeSession(localSession);
			}
		}
		return header;
	}

	/**
	 * Process an impex file using the given encoding
	 * 
	 * @param file
	 * @param encoding
	 * @throws FileNotFoundException
	 */
	protected void processFile(final File file, final String encoding) throws UnsupportedEncodingException, FileNotFoundException,
			XMLStreamException
	{
		if (log.isDebugEnabled())
		{
			log.debug(">> AbstractTechnonikolImpexRunnerTask.processFile(File, String)");
		}
		FileInputStream fis = null;
		XMLStreamWriter errorWriter = null;
		PrintWriter statusWriter = null;
		try
		{
			fis = new FileInputStream(file);
			final ImportConfig config = getImportConfig();
			final ImpExResource resource = new StreamBasedImpExResource(fis, encoding);
			config.setScript(resource);
			final ImportResult importResult = getImportService().importData(config);
			if (importResult.isError() && importResult.hasUnresolvedLines())
			{
				log.error(importResult.getUnresolvedLines().getPreview());
				errorWriter = writeXmlError(file, errorWriter, importResult.getUnresolvedLines().getPreview());
				statusWriter = writeStatus(file, statusWriter, "Error");
			}
			else
			{
				statusWriter = writeStatus(file, statusWriter, "Ok");
			}
		}
		finally
		{
			IOUtils.closeQuietly(fis);
			IOUtilsExt.closeQuietly(errorWriter);
			IOUtils.closeQuietly(statusWriter);
		}
	}

	/**
	 * Returns the error file
	 * 
	 * @param file
	 * @return the error file
	 */
	protected File getErrorFile(final File file)
	{
		return new File(BatchDirectoryUtils.getRelativeErrorDirectory(file), ERROR_FILE_PREFIX + file.getName() + ".xml");
	}

	/**
	 * Returns the error file
	 * 
	 * @param file
	 * @return the error file
	 */
	protected File getStatusFile(final File file)
	{
		return new File(BatchDirectoryExtUtils.getRelativeStatusDirectory(file), STATUS_FILE_PREFIX + file.getName() + ".txt");
	}

	/**
	 * Prints an error containing the reason of the error
	 * 
	 * @param file
	 * @param errorWriter
	 * @param message
	 *           the message
	 * @return error writer
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	protected PrintWriter writeErrorLine(final File file, final PrintWriter errorWriter, final String message)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		PrintWriter result = errorWriter;
		if (result == null)
		{
			result = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getErrorFile(file)), encoding)));
		}
		result.print(message);
		return result;
	}

	/**
	 * Prints an error containing the reason of the error
	 * 
	 * @param file
	 * @param errorWriter
	 * @param message
	 *           the message
	 * @return error writer
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	protected XMLStreamWriter writeXmlError(final File file, final XMLStreamWriter errorWriter, final String message)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		XMLStreamWriter result = errorWriter;
		if (result == null)
		{
			result = XMLOutputFactory.newInstance().createXMLStreamWriter(
					new OutputStreamWriter(new FileOutputStream(getErrorFile(file)), encoding));
		}

		result.writeStartDocument();
		result.writeStartElement(ELEMENT_LOG); // log

		result.writeStartElement(ELEMENT_ERROR);
		result.writeCData(message);
		result.writeEndElement(); // error

		result.writeEndElement(); // log
		result.writeEndDocument();
		return result;
	}

	/**
	 * Prints an status
	 * 
	 * @param file
	 * @param statusWriter
	 * @param message
	 *           the message
	 * @return status writer
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	protected PrintWriter writeStatus(final File file, final PrintWriter statusWriter, final String message)
			throws UnsupportedEncodingException, FileNotFoundException
	{
		PrintWriter result = statusWriter;
		if (result == null)
		{
			result = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getStatusFile(file)), encoding)));
		}
		result.print(message);
		return result;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public ImportService getImportService()
	{
		return importService;
	}

	@Required
	public void setImportService(final ImportService importService)
	{
		this.importService = importService;
	}

	/**
	 * Lookup method to return the import config
	 * 
	 * @return import config
	 */
	public abstract ImportConfig getImportConfig();
}
