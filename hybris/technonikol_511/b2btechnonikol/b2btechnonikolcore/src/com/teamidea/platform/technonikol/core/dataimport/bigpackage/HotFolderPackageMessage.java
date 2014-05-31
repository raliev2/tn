/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.bigpackage;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Aleksey
 * 
 */
public class HotFolderPackageMessage
{
	private static final Pattern FILENAME_MATCHER = Pattern.compile("(\\D+?)(\\d+?)_(\\d+?)-(\\d+?).ext.impex");

	private final String packageId;
	private final Path currentPath;
	private final String packageType;
	private final int sequenceNumber;
	private final int fileNumbers;
	private boolean isError = false;
	private boolean isImpexImportError = false;

	public HotFolderPackageMessage(final Path currentPath)
	{
		final Matcher matcher = FILENAME_MATCHER.matcher(currentPath.getFileName().toString());
		if (!matcher.matches())
		{
			throw new IllegalArgumentException("Cannot match currentPath with: " + currentPath + " pattern: "
					+ matcher.pattern().toString());
		}
		this.currentPath = currentPath;
		this.packageId = matcher.group(2);
		this.packageType = matcher.group(1);
		this.sequenceNumber = Integer.valueOf(matcher.group(3));
		this.fileNumbers = Integer.valueOf(matcher.group(4));
	}

	public String getPackageId()
	{
		return packageId;
	}

	public Path getCurrentPath()
	{
		return currentPath;
	}

	public String getPackageType()
	{
		return packageType;
	}

	public int getSequenceNumber()
	{
		return sequenceNumber;
	}

	public int getFileNumbers()
	{
		return fileNumbers;
	}

	public boolean isError()
	{
		return isError;
	}

	public void setError(final boolean isError)
	{
		this.isError = isError;
	}

	/**
	 * @return the isImpexImportError
	 */
	public boolean isImpexImportError()
	{
		return isImpexImportError;
	}

	/**
	 * @param isImpexImportError
	 *           the isImpexImportError to set
	 */
	public void setImpexImportError(final boolean isImpexImportError)
	{
		this.isImpexImportError = isImpexImportError;
	}

}
