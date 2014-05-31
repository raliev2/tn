/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.bigpackage.exceptions;

import de.hybris.platform.servicelayer.exceptions.SystemException;

import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;


/**
 * @author Aleksey Lubimov
 * 
 */
public class BigPackageException extends SystemException
{
	private HotFolderPackageMessage packageMessage;

	public BigPackageException(final String message)
	{
		super(message);
	}

	public BigPackageException(final String message, final HotFolderPackageMessage packageMessage)
	{
		super(message);
		this.packageMessage = packageMessage;
	}

	public BigPackageException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public BigPackageException(final String message, final HotFolderPackageMessage packageMessage, final Throwable cause)
	{
		super(message, cause);
		this.packageMessage = packageMessage;
	}

	/**
	 * @param cause
	 */
	public BigPackageException(final Throwable cause)
	{
		super(cause);
	}


	public BigPackageException(final HotFolderPackageMessage packageMessage, final Throwable cause)
	{
		super(cause);
		this.packageMessage = packageMessage;
	}

	/**
	 * @return the packageMessage
	 */
	public HotFolderPackageMessage getPackageMessage()
	{
		return packageMessage;
	}

}
