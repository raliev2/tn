/**
 * 
 */
package com.teamidea.platform.technonikol.core.dataimport.batch.aop;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchException;
import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.aop.AspectUtils;

import org.aspectj.lang.ProceedingJoinPoint;

import com.teamidea.platform.technonikol.core.dataimport.exception.ImpexImportException;


/**
 * Aspect to wrap all exceptions in a {@link BatchException} to preserve the header.
 */
public class TechnonikoExceptionHandlerAspect
{
	/**
	 * Invokes a method and wraps all Exceptions in a {@link BatchException}.
	 * 
	 * @param pjp
	 * @return result of the invocation
	 * @throws Throwable
	 */
	public Object execute(final ProceedingJoinPoint pjp) throws Throwable
	{
		final BatchHeader header = AspectUtils.getHeader(pjp.getArgs());
		try
		{
			return pjp.proceed();
		}
		catch (final ImpexImportException e)
		{
			throw e;
		}
		catch (final Exception e)
		{
			throw new BatchException(e.getMessage(), header, e);
		}
	}
}
