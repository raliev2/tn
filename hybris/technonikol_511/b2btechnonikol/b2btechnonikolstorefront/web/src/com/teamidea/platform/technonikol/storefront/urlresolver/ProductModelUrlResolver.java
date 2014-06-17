/**
 *
 */

package com.teamidea.platform.technonikol.storefront.urlresolver;

import de.hybris.platform.commerceservices.url.impl.DefaultProductModelUrlResolver;


/**
 * @author Igor Bobko
 *
 */

public class ProductModelUrlResolver extends DefaultProductModelUrlResolver
{
	@Override
	protected String urlSafe(final String text)
	{
		final String result = ModelUrlResolver.translitRustoEng(text);
		return super.urlSafe(result);
	}
}
