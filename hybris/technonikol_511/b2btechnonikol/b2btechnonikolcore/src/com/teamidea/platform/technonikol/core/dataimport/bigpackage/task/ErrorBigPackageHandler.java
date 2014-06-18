/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.task.ErrorHandler;

import org.apache.log4j.Logger;
import org.springframework.integration.MessagingException;

import com.teamidea.platform.technonikol.core.dataimport.LoggerStatus;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.exceptions.BigPackageException;
import com.teamidea.platform.technonikol.core.dataimport.exception.ImpexImportException;


/**
 * @author Aleksey Lubimov
 * 
 */
public class ErrorBigPackageHandler extends ErrorHandler
{
	private static final Logger log = Logger.getLogger(ErrorBigPackageHandler.class);

	private CleanupBigPackageHelper cleanupBigPackageHelper;

	public void onError(final BigPackageException exception)
	{
		log.error("BigPackageException caught", exception);
		final HotFolderPackageMessage packageMessage = exception.getPackageMessage();
		if (packageMessage != null)
		{
			final String message = exception.getMessage() != null ? exception.getMessage()
					: exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage();
			getCleanupBigPackageHelper().cleanup(packageMessage, LoggerStatus.FATAL, message);
		}
	}

	public void onError(final ImpexImportException exception)
	{
		log.error("ImpexImportException caught", exception);
		getCleanupBigPackageHelper().cleanup(exception.getSourceFile(), LoggerStatus.FATAL, exception.getUnresolvedLines(),
				exception.getLogs(), exception.getMessage());
	}

	/**
	 * Point of entry for errors during processing routed to the error channel.
	 * 
	 * @param exception
	 */
	@Override
	public void onError(final MessagingException exception)
	{
		log.error("MessagingException exception caught", exception);
		if (exception.getCause() instanceof BigPackageException)
		{
			onError((BigPackageException) exception.getCause());
		}
		else if (exception.getCause() instanceof ImpexImportException)
		{
			onError((ImpexImportException) exception.getCause());
		}
		else
		{
			super.onError(exception);
		}
	}

	/**
	 * Point of entry for errors during processing routed to the error channel.
	 * 
	 * @param exception
	 */
	@Override
	public void onError(final IllegalStateException exception)
	{
		log.error("IllegalStateException exception caught", exception);
		if (exception.getCause() instanceof BigPackageException)
		{
			onError((BigPackageException) exception.getCause());
		}
		else if (exception.getCause() instanceof ImpexImportException)
		{
			onError((ImpexImportException) exception.getCause());
		}
		else
		{
			super.onError(exception);
		}
	}

	public CleanupBigPackageHelper getCleanupBigPackageHelper()
	{
		return cleanupBigPackageHelper;
	}

	public void setCleanupBigPackageHelper(final CleanupBigPackageHelper cleanupBigPackageHelper)
	{
		this.cleanupBigPackageHelper = cleanupBigPackageHelper;
	}

}
