
package com.teamidea.platform.technonikol.storefront.servlets;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CssFilter implements Filter
{
	private static final String LESS_DEFAULT_SERVLET_NAME = "LessServlet";

	private RequestDispatcher defaultRequestDispatcher;

	protected RequestDispatcher getDefaultRequestDispatcher()
	{
		return defaultRequestDispatcher;
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{
		final ServletContext servletContext = filterConfig.getServletContext();
		
		if (servletContext.getNamedDispatcher(LESS_DEFAULT_SERVLET_NAME) != null)
		{
			this.defaultRequestDispatcher = servletContext.getNamedDispatcher(LESS_DEFAULT_SERVLET_NAME);
		}
		else
		{
			throw new IllegalStateException(
					"Unable to locate the default servlet for serving static content. Please set the 'defaultServletName' property explicitly.");
		}
	}

	@Override
	public void destroy()
	{
		// No implementation
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException
	{
		getDefaultRequestDispatcher().forward(request, response);
	}
}