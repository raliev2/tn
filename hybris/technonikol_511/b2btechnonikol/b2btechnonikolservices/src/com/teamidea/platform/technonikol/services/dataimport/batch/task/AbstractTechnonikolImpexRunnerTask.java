/**
 * 
 */
package com.teamidea.platform.technonikol.services.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.task.AbstractImpexRunnerTask;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;


/**
 * @author Aleksey
 * 
 */
public abstract class AbstractTechnonikolImpexRunnerTask extends AbstractImpexRunnerTask
{
	private static final Logger log = Logger.getLogger(AbstractTechnonikolImpexRunnerTask.class);

	/**
	 * Process an impex file using the given encoding
	 * 
	 * @param file
	 * @param encoding
	 * @throws FileNotFoundException
	 */
	@Override
	protected void processFile(final File file, final String encoding) throws FileNotFoundException
	{
		if (log.isDebugEnabled())
		{
			log.debug(">> AbstractTechnonikolImpexRunnerTask.processFile(File, String)");
		}
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
			final ImportConfig config = getImportConfig();
			final ImpExResource resource = new StreamBasedImpExResource(fis, encoding);
			config.setScript(resource);
			final ImportResult importResult = getImportService().importData(config);
			if (importResult.isError() && importResult.hasUnresolvedLines())
			{
				log.error(importResult.getUnresolvedLines().getPreview());
				throw new SystemException(importResult.getUnresolvedLines().getPreview());
			}
		}
		finally
		{
			IOUtils.closeQuietly(fis);
		}
	}

}
