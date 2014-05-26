package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.log4j.Logger;

import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class CleanupTask extends AbstractHotFolderTask
{

	private static final Logger LOG = Logger.getLogger(CleanupTask.class);

	public HotFolderPackageMessage onExecute(final HotFolderPackageMessage message)
	{
		//final String dirName = message.isError() ? BatchDirectoryUtils.getRelativeErrorDirectory(message.getCurrentPath().toFile())
		//		: BatchDirectoryUtils.getRelativeArchiveDirectory(message.getCurrentPath().toFile());
		final String dirName = BatchDirectoryUtils.getRelativeArchiveDirectory(message.getCurrentPath().toFile());
		try
		{
			Files.move(message.getCurrentPath(), Paths.get(dirName, message.getCurrentPath().getFileName().toString()),
					StandardCopyOption.REPLACE_EXISTING);
		}
		catch (final IOException e)
		{
			LOG.error(e, e);
		}
		return null;
	}
}
