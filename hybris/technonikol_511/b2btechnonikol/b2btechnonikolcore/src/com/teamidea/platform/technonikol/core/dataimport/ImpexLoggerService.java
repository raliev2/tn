/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport;

import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;
import de.hybris.platform.cronjob.model.JobLogModel;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.internal.service.AbstractService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.teamidea.platform.technonikol.core.dataimport.batch.utils.BatchDirectoryExtUtils;


/**
 * @author Aleksey Lubimov
 * 
 */
public class ImpexLoggerService extends AbstractService
{
	private static final Logger log = Logger.getLogger(ImpexLoggerService.class);

	private static final String STATUS_FILE_PREFIX = "status_";
	private static final String ERROR_FILE_PREFIX = "error_";
	private static final String ELEMENT_LOG = "log";
	private static final String ELEMENT_ERROR = "error";
	private static final String ELEMENT_ERROR_LINE = "line";
	private static final String ELEMENT_ERROR_DUMP = "dump";
	private static final String ELEMENT_ERROR_MESSAGE = "message";

	private String encoding;

	/**
	 * Returns the error file
	 * 
	 * @param file
	 * @return the error file
	 */
	private File getStatusFile(final File file)
	{
		return new File(BatchDirectoryExtUtils.getRelativeStatusDirectory(file), STATUS_FILE_PREFIX + file.getName() + ".txt");
	}

	/**
	 * Returns the error file
	 * 
	 * @param file
	 * @return the error file
	 */
	private File getErrorFile(final File file)
	{
		return new File(BatchDirectoryUtils.getRelativeErrorDirectory(file), ERROR_FILE_PREFIX + file.getName() + ".xml");
	}

	private XMLStreamWriter getErrorWriter(final File file, final XMLStreamWriter errorWriter)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		XMLStreamWriter result = errorWriter;
		if (result == null)
		{
			result = XMLOutputFactory.newInstance().createXMLStreamWriter(
					new OutputStreamWriter(new FileOutputStream(getErrorFile(file)), encoding));
		}
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
	public PrintWriter writeStatus(final File file, final PrintWriter statusWriter, final String message)
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

	/**
	 * Prints an error containing the reason of the error
	 * 
	 * @param file
	 * @param errorWriter
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public PrintWriter writeErrorLine(final File file, final PrintWriter errorWriter, final String message)
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
	 * 
	 * @param file
	 * @param errorWriter
	 * @param unresolvedLines
	 * @param logs
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public XMLStreamWriter writeXmlError(final File file, final XMLStreamWriter errorWriter,
			final ImpExMediaModel unresolvedLines, final List<JobLogModel> logs, final String message)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		final XMLStreamWriter result = writeXmlErrorHeader(file, errorWriter);

		// write message
		if (message != null)
		{
			writeXmlErrorMessage(result, message);
		}

		// write logs
		if (logs != null)
		{
			writeXmlErrorLog(result, logs);
		}

		// write impex dump
		if (unresolvedLines != null)
		{
			writeXmlErrorDump(result, unresolvedLines.getPreview());
		}

		writeXmlErrorTail(result);

		return result;
	}

	/**
	 * Prints an error containing the reason of the error in XML format
	 * 
	 * @param file
	 * @param errorWriter
	 * @param unresolvedLines
	 * @param logs
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public XMLStreamWriter writeXmlError(final File file, final XMLStreamWriter errorWriter,
			final ImpExMediaModel unresolvedLines, final List<JobLogModel> logs) throws UnsupportedEncodingException,
			FileNotFoundException, XMLStreamException
	{
		return writeXmlError(file, errorWriter, unresolvedLines, logs, null);
	}

	private XMLStreamWriter writeXmlErrorHeader(final File file, final XMLStreamWriter errorWriter)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		final XMLStreamWriter result = getErrorWriter(file, errorWriter);

		result.writeStartDocument();
		result.writeStartElement(ELEMENT_LOG); // log

		return result;
	}

	private void writeXmlErrorTail(final XMLStreamWriter errorWriter) throws UnsupportedEncodingException, FileNotFoundException,
			XMLStreamException
	{
		errorWriter.writeEndElement(); // log
		errorWriter.writeEndDocument();
	}

	private void writeXmlErrorMessage(final XMLStreamWriter errorWriter, final String message)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR_MESSAGE);
		errorWriter.writeCData(message);
		errorWriter.writeEndElement(); // error
	}

	private void writeXmlErrorDump(final XMLStreamWriter errorWriter, final String message) throws UnsupportedEncodingException,
			FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR_DUMP);
		errorWriter.writeCData(message);
		errorWriter.writeEndElement(); // error
	}

	private void writeXmlErrorLog(final XMLStreamWriter errorWriter, final List<JobLogModel> logs)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR);
		for (final JobLogModel log : logs)
		{
			writeXmlErrorLine(errorWriter, log.getMessage());
		}
		errorWriter.writeEndElement(); // error
	}

	private void writeXmlErrorLine(final XMLStreamWriter errorWriter, final String message) throws UnsupportedEncodingException,
			FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR_LINE);
		errorWriter.writeCData(message);
		errorWriter.writeEndElement(); // line
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding()
	{
		return encoding;
	}

	@Required
	public void setEncoding(final String encoding)
	{
		this.encoding = encoding;
	}
}
