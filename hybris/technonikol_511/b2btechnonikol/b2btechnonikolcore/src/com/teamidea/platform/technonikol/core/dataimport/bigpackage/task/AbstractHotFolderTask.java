package com.teamidea.platform.technonikol.core.dataimport.bigpackage.task;

import java.nio.file.Path;

import org.springframework.transaction.support.TransactionTemplate;

import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageMessage;
import com.teamidea.platform.technonikol.core.dataimport.bigpackage.HotFolderPackageService;


/**
 * Created by anton.gavazyuk on 14/05/14.
 */
public abstract class AbstractHotFolderTask
{

	private HotFolderPackageService hotFolderPackageService;
	private TransactionTemplate transactionTemplate;

	public HotFolderPackageService getHotFolderPackageService()
	{
		return hotFolderPackageService;
	}

	public void setHotFolderPackageService(final HotFolderPackageService hotFolderPackageService)
	{
		this.hotFolderPackageService = hotFolderPackageService;
	}

	public TransactionTemplate getTransactionTemplate()
	{
		return transactionTemplate;
	}

	public void setTransactionTemplate(final TransactionTemplate transactionTemplate)
	{
		this.transactionTemplate = transactionTemplate;
	}

	public HotFolderPackageMessage createMessage(final Path filePath)
	{
		return new HotFolderPackageMessage(filePath);
	}


}
