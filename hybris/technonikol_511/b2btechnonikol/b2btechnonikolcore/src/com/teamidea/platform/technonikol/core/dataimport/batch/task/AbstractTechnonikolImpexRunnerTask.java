/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.batch.task;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.HeaderTask;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.teamidea.platform.technonikol.core.dataimport.ImpexLoggerService;
import com.teamidea.platform.technonikol.core.dataimport.exception.ImpexImportException;


/**
 * @author Aleksey Lubimov
 * 
 */
public abstract class AbstractTechnonikolImpexRunnerTask implements HeaderTask //extends AbstractImpexRunnerTask
{
	private static final Logger log = Logger.getLogger(AbstractTechnonikolImpexRunnerTask.class);

	private SessionService sessionService;
	private ImportService importService;
	private UserService userService;
	private String sessionUser;
	private ImpexLoggerService loggerService;

	//private final String encoding = CSVConstants.HYBRIS_ENCODING;

	@Override
	public BatchHeader execute(final BatchHeader header) throws UnsupportedEncodingException, FileNotFoundException,
			ImpexImportException
	{
		Assert.notNull(header);
		Assert.notNull(header.getEncoding());

		if (CollectionUtils.isNotEmpty(header.getTransformedFiles()))
		{
			final Session localSession = getSessionService().createNewSession();
			try
			{
				replaceSessionUser();
				for (final File file : header.getTransformedFiles())
				{
					processFile(file, header.getEncoding());
				}
			}
			finally
			{
				getSessionService().closeSession(localSession);
			}
		}
		return header;
	}

	private void replaceSessionUser()
	{
		final UserModel sessionUserModel = getUserService().getUserForUID(getSessionUser());
		if (sessionUserModel != null)
		{
			getUserService().setCurrentUser(sessionUserModel);
		}
	}

	/**
	 * Process an impex file using the given encoding
	 * 
	 * @param file
	 * @param encoding
	 * @throws FileNotFoundException
	 */
	protected void processFile(final File file, final String encoding) throws FileNotFoundException, ImpexImportException
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
			if (importResult.isError())
			{
				ImpExMediaModel unresolvedLines = null;
				if (importResult.hasUnresolvedLines())
				{
					log.error(importResult.getUnresolvedLines().getPreview());
					unresolvedLines = importResult.getUnresolvedLines();
				}

				throw new ImpexImportException("Impex import failed", file, unresolvedLines, importResult.getCronJob().getLogs());
			}
		}
		finally
		{
			IOUtils.closeQuietly(fis);
		}
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public ImportService getImportService()
	{
		return importService;
	}

	@Required
	public void setImportService(final ImportService importService)
	{
		this.importService = importService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the sessionUser
	 */
	public String getSessionUser()
	{
		return sessionUser;
	}

	@Required
	public void setSessionUser(final String sessionUser)
	{
		this.sessionUser = sessionUser;
	}

	/**
	 * Lookup method to return the import config
	 * 
	 * @return import config
	 */
	public abstract ImportConfig getImportConfig();


	public ImpexLoggerService getLoggerService()
	{
		return loggerService;
	}

	@Required
	public void setLoggerService(final ImpexLoggerService loggerService)
	{
		this.loggerService = loggerService;
	}
}
