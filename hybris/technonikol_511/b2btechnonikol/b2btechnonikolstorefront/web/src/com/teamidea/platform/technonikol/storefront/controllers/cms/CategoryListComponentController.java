/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.cms;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.converters.Populator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamidea.platform.technonikol.core.model.CategoryListComponentModel;
import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;


/**
 * @author Marina
 *
 */

/**
 * Controller for CMS ProductReferencesComponent.
 */
@Controller("CategoryListComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.CategoryListComponent)
public class CategoryListComponentController extends AbstractCMSComponentController<CategoryListComponentModel>
{
	@Resource(name = "categoryDataUrlResolver")
	private UrlResolver<CategoryModel> categoryDataUrlResolver;
	@Resource(name = "categoryConverter")
	private Populator<CategoryModel, CategoryData> categoryConverter;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final CategoryListComponentModel component)
	{
		final CategoryModel currentCategory = getRequestContextData(request).getCategory();
		if (currentCategory != null)
		{
			final Collection<CategoryModel> categoryModels = currentCategory.getCategories();

			final List<CategoryData> categories = new ArrayList<CategoryData>();
			for (final CategoryModel categoryModel : categoryModels)
			{
				final CategoryData data = new CategoryData();
				getCategoryConverter().populate(categoryModel, data);
				categories.add(data);
			}

			for (final CategoryData category : categories)
			{
				category.setUrl(getCategoryDataUrlResolver().resolve(category));
			}

			model.addAttribute("title", component.getTitle());
			model.addAttribute("categories", categories);
		}

	}

	/**
	 * @return the categoryDataUrlResolver
	 */
	public UrlResolver getCategoryDataUrlResolver()
	{
		return categoryDataUrlResolver;
	}

	/**
	 * @param categoryDataUrlResolver
	 *           the categoryDataUrlResolver to set
	 */
	public void setCategoryDataUrlResolver(final UrlResolver categoryDataUrlResolver)
	{
		this.categoryDataUrlResolver = categoryDataUrlResolver;
	}

	/**
	 * @return the categoryConverter
	 */
	public Populator getCategoryConverter()
	{
		return categoryConverter;
	}

	/**
	 * @param categoryConverter
	 *           the categoryConverter to set
	 */
	public void setCategoryConverter(final Populator categoryConverter)
	{
		this.categoryConverter = categoryConverter;
	}

}
