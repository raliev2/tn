package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public class CleanupTask extends AbstractHotFolderTask
{
	private static final Logger log = Logger.getLogger(CleanupTask.class);

	private CleanupBigPackageHelper cleanupBigPackageHelper;

	public HotFolderPackageMessage onExecute(final HotFolderPackageMessage message)
	{
		cleanupBigPackageHelper.cleanup(message);
		return null;
	}

	public CleanupBigPackageHelper getCleanupBigPackageHelper()
	{
		return cleanupBigPackageHelper;
	}

	@Required
	public void setCleanupBigPackageHelper(final CleanupBigPackageHelper cleanupBigPackageHelper)
	{
		this.cleanupBigPackageHelper = cleanupBigPackageHelper;
	}

}
