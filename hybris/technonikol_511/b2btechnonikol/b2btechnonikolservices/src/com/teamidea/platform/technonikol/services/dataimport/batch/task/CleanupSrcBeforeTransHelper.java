/**
 * 
 */
package com.teamidea.platform.technonikol.services.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper;

import org.apache.log4j.Logger;


/**
 * @author Aleksey
 * 
 */
public class CleanupSrcBeforeTransHelper extends CleanupHelper
{

	private static final Logger log = Logger.getLogger(CleanupSrcBeforeTransHelper.class);

	/**
	 * Performs the cleanup of given header
	 * 
	 * @param header
	 *           to clean up
	 * @param error
	 *           flag indicating if there was an error
	 */
	@Override
	public void cleanup(final BatchHeader header, final boolean error)
	{
		if (log.isDebugEnabled())
		{
			log.debug("Cleanap SOURCE before TRANSFORMED. hasError:" + error);
		}
		if (header != null)
		{
			cleanupSourceFile(header, error);
			cleanupTransformedFiles(header);
		}
	}
}
