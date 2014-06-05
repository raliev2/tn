/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.exception;

import de.hybris.platform.cronjob.model.JobLogModel;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;

import java.io.File;
import java.util.List;


/**
 * 
 */
public class ImpexImportException extends SystemException
{
	private File sourceFile;
	private ImpExMediaModel unresolvedLines;
	private List<JobLogModel> logs;

	public ImpexImportException(final String message)
	{
		super(message);
	}

	public ImpexImportException(final String message, final File sourceFile, final ImpExMediaModel unresolvedLines,
			final List<JobLogModel> logs)
	{
		super(message);
		this.sourceFile = sourceFile;
		this.unresolvedLines = unresolvedLines;
		this.logs = logs;
	}

	public ImpexImportException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public ImpexImportException(final String message, final File sourceFile, final ImpExMediaModel unresolvedLines,
			final List<JobLogModel> logs, final Throwable cause)
	{
		super(message, cause);
		this.sourceFile = sourceFile;
		this.unresolvedLines = unresolvedLines;
		this.logs = logs;
	}

	/**
	 * @param cause
	 */
	public ImpexImportException(final Throwable cause)
	{
		super(cause);
	}


	public ImpexImportException(final File sourceFile, final ImpExMediaModel unresolvedLines, final List<JobLogModel> logs,
			final Throwable cause)
	{
		super(cause);
		this.sourceFile = sourceFile;
		this.unresolvedLines = unresolvedLines;
		this.logs = logs;
	}

	/**
	 * @return the sourceFile
	 */
	public File getSourceFile()
	{
		return sourceFile;
	}

	/**
	 * @return the unresolvedLines
	 */
	public ImpExMediaModel getUnresolvedLines()
	{
		return unresolvedLines;
	}

	/**
	 * @return the logs
	 */
	public List<JobLogModel> getLogs()
	{
		return logs;
	}

}
