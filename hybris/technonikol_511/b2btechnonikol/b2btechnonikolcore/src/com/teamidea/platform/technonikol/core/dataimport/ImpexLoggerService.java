/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport;

import de.hybris.platform.cronjob.model.JobLogModel;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.internal.service.AbstractService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.teamidea.platform.technonikol.core.dataimport.batch.utils.BatchDirectoryExtUtils;
import com.teamidea.platform.technonikol.core.dataimport.batch.utils.IOUtilsExt;


/**
 * @author Aleksey Lubimov
 * 
 */
public class ImpexLoggerService extends AbstractService
{
	private static final Logger log = Logger.getLogger(ImpexLoggerService.class);

	private static final String STATUS_FILE_SUFIX = "_S";
	private static final String ERROR_FILE_SUFIX = "_E";
	private static final String FILE_EXT = ".xml";

	private static final String ELEMENT_HEADER = "status";
	private static final String ELEMENT_CODE = "code";
	private static final String ELEMENT_ERROR = "error";
	private static final String ELEMENT_ERROR_LINE = "line";
	private static final String ELEMENT_ERROR_DUMP = "dump";
	private static final String ELEMENT_ERROR_MESSAGE = "message";

	private String encoding;

	private static final Pattern FILENAME_MATCHER = Pattern.compile("^(.+?)(_[FfRr]){0,1}(\\..+?)$");

	private File getStatusFile(final File file, final boolean isError)
	{
		final Matcher matcher = FILENAME_MATCHER.matcher(file.getName());
		final StringBuilder sb = new StringBuilder();
		if (matcher.matches())
		{
			sb.append(matcher.group(1)).append(isError ? ERROR_FILE_SUFIX : STATUS_FILE_SUFIX).append(matcher.group(3));
		}
		else
		{
			sb.append(FilenameUtils.removeExtension(file.getName())).append(isError ? ERROR_FILE_SUFIX : STATUS_FILE_SUFIX)
					.append(FilenameUtils.getExtension(file.getName()));
		}
		sb.append(FILE_EXT);

		return new File(BatchDirectoryExtUtils.getRelativeStatusDirectory(file), sb.toString());
	}

	private XMLStreamWriter getStatusWriter(final File file, final XMLStreamWriter errorWriter, final boolean isError)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		XMLStreamWriter result = errorWriter;
		if (result == null)
		{
			result = XMLOutputFactory.newInstance().createXMLStreamWriter(
					new OutputStreamWriter(new FileOutputStream(getStatusFile(file, isError)), encoding));
		}
		return result;
	}

	public void writeXmlSuccessStatus(final File file) throws UnsupportedEncodingException, FileNotFoundException,
			XMLStreamException
	{
		final XMLStreamWriter statusXmlWriter = null;
		try
		{
			final XMLStreamWriter result = writeXmlStatusHeader(file, statusXmlWriter, false);

			// write code
			writeXmlStatusCode(result, LoggerStatus.SUCCESS);
			writeXmlStatusTail(result);
		}
		finally
		{
			IOUtilsExt.closeQuietly(statusXmlWriter);
		}
	}

	/**
	 * 
	 * @param file
	 * @param statusXmlWriter
	 * @param code
	 * @param unresolvedLines
	 * @param logs
	 * @param message
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public void writeXmlStatus(final File file, final String code, final ImpExMediaModel unresolvedLines,
			final List<JobLogModel> logs, final String message) throws UnsupportedEncodingException, FileNotFoundException,
			XMLStreamException
	{
		final XMLStreamWriter statusXmlWriter = null;
		try
		{
			final XMLStreamWriter result = writeXmlStatusHeader(file, statusXmlWriter, true);

			// write code
			writeXmlStatusCode(result, code);

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

			writeXmlStatusTail(result);
		}
		finally
		{
			IOUtilsExt.closeQuietly(statusXmlWriter);
		}
	}

	private XMLStreamWriter writeXmlStatusHeader(final File file, final XMLStreamWriter errorWriter, final boolean isError)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		final XMLStreamWriter result = getStatusWriter(file, errorWriter, isError);

		result.writeStartDocument();
		result.writeStartElement(ELEMENT_HEADER); // header

		return result;
	}

	private void writeXmlStatusTail(final XMLStreamWriter errorWriter) throws UnsupportedEncodingException, FileNotFoundException,
			XMLStreamException
	{
		errorWriter.writeEndElement(); // header
		errorWriter.writeEndDocument();
	}

	private void writeXmlStatusCode(final XMLStreamWriter errorWriter, final String code) throws UnsupportedEncodingException,
			FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_CODE);
		errorWriter.writeCharacters(code);
		errorWriter.writeEndElement();
	}

	private void writeXmlErrorMessage(final XMLStreamWriter errorWriter, final String message)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR_MESSAGE);
		errorWriter.writeCData(message);
		errorWriter.writeEndElement();
	}

	private void writeXmlErrorDump(final XMLStreamWriter errorWriter, final String message) throws UnsupportedEncodingException,
			FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR_DUMP);
		errorWriter.writeCData(message);
		errorWriter.writeEndElement();
	}

	private void writeXmlErrorLog(final XMLStreamWriter errorWriter, final List<JobLogModel> logs)
			throws UnsupportedEncodingException, FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR);
		for (final JobLogModel log : logs)
		{
			writeXmlErrorLine(errorWriter, log.getMessage());
		}
		errorWriter.writeEndElement();
	}

	private void writeXmlErrorLine(final XMLStreamWriter errorWriter, final String message) throws UnsupportedEncodingException,
			FileNotFoundException, XMLStreamException
	{
		errorWriter.writeStartElement(ELEMENT_ERROR_LINE);
		errorWriter.writeCData(message);
		errorWriter.writeEndElement();
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
