/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.batch.utils;

import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;

import java.io.File;

import org.springframework.util.Assert;


/**
 * @author Aleksey Lubimov
 * 
 */
public class BatchDirectoryExtUtils
{
	private static final String STATUS_DIRECTORY = "status";

	private BatchDirectoryExtUtils()
	{
		//empty
	}

	/**
	 * Retrieves the relative status directory from the currently processed file
	 * 
	 * @param processedFile
	 * @return error directory path
	 */
	public static String getRelativeStatusDirectory(final File processedFile)
	{
		return BatchDirectoryUtils.verify(BatchDirectoryUtils.getRelativeBaseDirectory(processedFile) + File.separator
				+ STATUS_DIRECTORY);
	}

	/**
	 * Retrieves the relative base directory from the currently processed file
	 * 
	 * @param processedFile
	 * @return base directory path
	 */
	public static String getRelativeBaseDirectory(final File processedFile)
	{
		return processedFile.getParentFile().getParentFile().getAbsolutePath();
	}

	/**
	 * Verifies, if the given directory path exists and if not, creates and verifies the directories
	 * 
	 * @param directory
	 *           to check
	 * @return verified directory (same reference)
	 */
	public static String verify(final String directory)
	{
		Assert.hasText(directory);
		final File dir = new File(directory);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		Assert.isTrue(dir.exists());
		return directory;
	}
}
