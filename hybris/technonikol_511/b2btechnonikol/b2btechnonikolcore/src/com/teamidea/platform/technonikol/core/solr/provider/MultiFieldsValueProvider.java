/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * Technonikol Project. 
 *   
 */
package com.teamidea.platform.technonikol.core.solr.provider;

import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * 
 */
public class MultiFieldsValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable,
		ApplicationContextAware
{
	private FieldNameProvider fieldNameProvider;
	private CommonI18NService commonI18NService;
	private ExpressionParser parser;
	private ApplicationContext applicationContext;

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		final Collection<FieldValue> resolvedFieldValues = new ArrayList<FieldValue>();

		final String[] fieldsELValue = getFieldValuesSpringExpression(indexedProperty);

		for (final String exprValue : fieldsELValue)
		{
			final Expression parsedExpression = parser.parseExpression(exprValue);
			final StandardEvaluationContext context = new StandardEvaluationContext(model);
			context.setBeanResolver(new BeanFactoryResolver(this.applicationContext));
			context.setVariable("item", model);

			if (indexedProperty.isLocalized())
			{
				for (final LanguageModel language : indexConfig.getLanguages())
				{
					final Locale locale = commonI18NService.getLocaleForLanguage(language);
					context.setVariable("lang", locale);

					final Object value = parsedExpression.getValue(context);
					resolvedFieldValues.addAll(resolve(indexedProperty, value, language.getIsocode()));
				}
			}
			else if (indexedProperty.isCurrency())
			{
				for (final CurrencyModel currency : indexConfig.getCurrencies())
				{
					final CurrencyModel sessionCurrency = commonI18NService.getCurrentCurrency();
					try
					{
						commonI18NService.setCurrentCurrency(currency);
						context.setVariable("currency", currency);
						final Object value = parsedExpression.getValue(context);
						resolvedFieldValues.addAll(resolve(indexedProperty, value, currency.getIsocode()));
					}
					finally
					{
						commonI18NService.setCurrentCurrency(sessionCurrency);
					}
				}
			}
			else
			{
				final Object value = parsedExpression.getValue(context);
				resolvedFieldValues.addAll(resolve(indexedProperty, value, null));
			}

		}
		return resolvedFieldValues;
	}

	private String[] getFieldValuesSpringExpression(final IndexedProperty indexedProperty)
	{
		final String exprValues = indexedProperty.getValueProviderParameter();
		return exprValues.split("[|]");
	}

	/**
	 * Формируем коллекцию FieldValue
	 * 
	 * @param indexedProperty
	 * @param value
	 * @param qualifier
	 * @return
	 */
	private Collection<? extends FieldValue> resolve(final IndexedProperty indexedProperty, final Object value,
			final String qualifier)
	{
		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, qualifier);

		if (value == null)
		{
			return Collections.EMPTY_LIST;
		}

		if (value instanceof Collection)
		{
			return resolveValuesForCollection((Collection) value, fieldNames);
		}

		return getFieldValuesForFieldNames(fieldNames, value);
	}

	/**
	 * Формируем коллекцию FieldValue для коллекции значений
	 * 
	 * @param value
	 * @param fieldNames
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<FieldValue> resolveValuesForCollection(final Collection value, final Collection<String> fieldNames)
	{
		final Collection<Collection<FieldValue>> fieldValues = Collections2.transform(value,
				new Function<Object, Collection<FieldValue>>()
				{
					@Override
					@Nullable
					public Collection<FieldValue> apply(@Nullable final Object applyTo)
					{
						return getFieldValuesForFieldNames(fieldNames, applyTo);
					}
				});

		// трансфомируем вложенные коллекции в одноуровневую коллекцию 
		return Lists.newArrayList(Iterables.concat(fieldValues));
	}

	/**
	 * Формируем коллекцию FieldValue для значения
	 * 
	 * @param fieldNames
	 * @param value
	 * @return
	 */
	private Collection<FieldValue> getFieldValuesForFieldNames(final Collection<String> fieldNames, final Object value)
	{
		return Collections2.transform(fieldNames, new Function<String, FieldValue>()
		{
			@Override
			@Nullable
			public FieldValue apply(@Nullable final String fieldName)
			{
				return new FieldValue(fieldName, String.valueOf(value));
			}
		});
	}

	protected FieldNameProvider getFieldNameProvider()
	{
		return this.fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	protected CommonI18NService getCommonI18NService()
	{
		return this.commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Required
	public void setParser(final ExpressionParser parser)
	{
		this.parser = parser;
	}

	public void setApplicationContext(final ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
}
