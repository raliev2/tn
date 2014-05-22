/**
 * 
 */
package com.teamidea.platform.technonikol.services.dataimport.batch.utils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


/**
 * @author Aleksey
 * 
 */
public class IOUtilsExt
{
	public static void closeQuietly(final XMLStreamWriter output)
	{
		try
		{
			if (output != null)
			{
				output.close();
			}
		}
		catch (final XMLStreamException ioe)
		{
		}
	}

}
