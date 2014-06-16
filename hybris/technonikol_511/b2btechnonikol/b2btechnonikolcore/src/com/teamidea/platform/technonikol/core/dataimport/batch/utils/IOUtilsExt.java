/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.batch.utils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.log4j.Logger;


/**
 * @author Aleksey
 * 
 */
public class IOUtilsExt
{
	private static final Logger log = Logger.getLogger(IOUtilsExt.class);

	public static void closeQuietly(final XMLStreamWriter output)
	{
		if (log.isDebugEnabled())
		{
			log.debug(">> closeQuietly(XMLStreamWriter)");
		}

		try
		{
			if (output != null)
			{
				output.close();
				if (log.isDebugEnabled())
				{
					log.debug("XMLStreamWriter closed");
				}
			}
		}
		catch (final XMLStreamException ioe)
		{
		}
	}

}
