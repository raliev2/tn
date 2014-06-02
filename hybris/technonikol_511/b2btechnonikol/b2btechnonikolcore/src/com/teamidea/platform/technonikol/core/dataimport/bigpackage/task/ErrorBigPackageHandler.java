/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.task.ErrorHandler;

import org.apache.log4j.Logger;
import org.springframework.integration.MessagingException;

import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.exceptions.BigPackageException;


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
		log.error("BigPackage exception caught", exception);
		final HotFolderPackageMessage packageMessage = exception.getPackageMessage();
		getCleanupBigPackageHelper().cleanup(packageMessage);
		writeError(packageMessage, exception.getMessage());
	}

	/**
	 * Point of entry for errors during processing routed to the error channel.
	 * 
	 * @param exception
	 */
	@Override
	public void onError(final MessagingException exception)
	{
		log.error("unexpected exception caught", exception);
		if (exception.getCause() instanceof BigPackageException)
		{
			final HotFolderPackageMessage packageMessage = ((BigPackageException) exception.getCause()).getPackageMessage();
			getCleanupBigPackageHelper().cleanup(packageMessage);
			writeError(packageMessage, exception.getMessage());
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
		log.error("unexpected exception caught", exception);
		if (exception.getCause() instanceof BigPackageException)
		{
			final HotFolderPackageMessage packageMessage = ((BigPackageException) exception.getCause()).getPackageMessage();
			getCleanupBigPackageHelper().cleanup(packageMessage);
			writeError(packageMessage, exception.getMessage());
		}
		else
		{
			super.onError(exception);
		}
	}


	/**
	 * @param exception
	 * @param packageMessage
	 */
	private void writeError(final HotFolderPackageMessage packageMessage, final String message)
	{
		try
		{
			if (!packageMessage.isImpexImportError())
			{
				getCleanupBigPackageHelper().writeError(packageMessage, message);
			}
		}
		catch (final Exception e)
		{
			log.error(e);
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
