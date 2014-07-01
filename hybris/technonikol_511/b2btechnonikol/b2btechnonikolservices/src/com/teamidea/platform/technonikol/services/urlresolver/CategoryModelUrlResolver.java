/**
 *
 */
package com.teamidea.platform.technonikol.services.urlresolver;

import de.hybris.platform.commerceservices.url.impl.DefaultCategoryModelUrlResolver;


/**
 * @author Igor Bobko
 *
 */
public class CategoryModelUrlResolver extends DefaultCategoryModelUrlResolver
{
	@Override
	protected String urlSafe(final String text)
	{
		final String result = ModelUrlResolver.translitRustoEng(text);
		return super.urlSafe(result);
	}
}
