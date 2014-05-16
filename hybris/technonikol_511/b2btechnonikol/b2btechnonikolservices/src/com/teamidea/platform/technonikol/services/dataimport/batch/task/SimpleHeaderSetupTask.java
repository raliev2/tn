/**
 * 
 */
package com.teamidea.platform.technonikol.services.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;

import java.io.File;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * @author Aleksey
 * 
 */
public class SimpleHeaderSetupTask
{
	protected String storeBaseDirectory;
	protected String catalog;
	protected boolean net;
	protected String encoding;

	public String getEncoding()
	{
		return encoding;
	}

	@Required
	public void setEncoding(final String encoding)
	{
		this.encoding = encoding;
	}

	/**
	 * Initially creates the header.
	 * 
	 * @param file
	 * @return the header
	 */
	public BatchHeader execute(final File file)
	{
		Assert.notNull(file);
		final BatchHeader header = new BatchHeader();
		header.setFile(file);
		header.setStoreBaseDirectory(storeBaseDirectory);
		header.setCatalog(catalog);
		header.setNet(net);
		header.setEncoding(encoding);
		header.addTransformedFile(file);
		return header;
	}

	public String getStoreBaseDirectory()
	{
		return storeBaseDirectory;
	}

	@Required
	public void setStoreBaseDirectory(final String storeBaseDirectory)
	{
		this.storeBaseDirectory = storeBaseDirectory;
	}

	public String getCatalog()
	{
		return catalog;
	}

	@Required
	public void setCatalog(final String catalog)
	{
		this.catalog = catalog;
	}

	public boolean isNet()
	{
		return net;
	}

	@Required
	public void setNet(final boolean net)
	{
		this.net = net;
	}
}
