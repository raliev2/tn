/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.misc;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.technonikol.ws.stocks.MaterialsRow;
import ru.technonikol.ws.stocks.SendQueryResponse;

import com.teamidea.platform.technonikol.services.stock.DeliveryDateIntegrationService;
import com.teamidea.platform.technonikol.storefront.controllers.AbstractController;
import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;


/**
 * @author Marina
 * 
 */
@Controller
@Scope("tenant")
public class CheckInStockController extends AbstractController
{
	private static final String PRODUCT_CODE = "productCode";
	private static final String PRODUCT_COUNT = "count";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String AVAILABILITY_MESSAGE = "availabilityMessage";
	private static final String POST_DATE = "postDate";

	protected static final Logger LOG = Logger.getLogger(CheckInStockController.class);

	@Resource(name = "deliveryDateIntegrationService")
	private DeliveryDateIntegrationService deliveryDateIntegrationService;

	@RequestMapping(value = "/stock/check", method = RequestMethod.GET)
	public String checkStock(final HttpServletRequest request, final Model model)
	{
		final String productCode = request.getParameter(PRODUCT_CODE);
		final String count = request.getParameter(PRODUCT_COUNT);
		final String date = "";

		final SendQueryResponse response = deliveryDateIntegrationService.deliveryDateQueryOut(productCode, count, date);

		if (response == null || response.getReturn() == null || response.getReturn().getMaterials() == null)
		{
			LOG.debug("Error while trying to get delivery information for product with code = " + productCode);
			model.addAttribute(ERROR_MESSAGE, "Ошибка при получении информации о сроках доставки товара с артикулом " + productCode);
			return ControllerConstants.Views.Fragments.Stock.CheckStockPopup;
		}

		final List<MaterialsRow> deliveryInfo = response.getReturn().getMaterials().getRow();
		MaterialsRow productInfo = null;
		for (final MaterialsRow row : deliveryInfo)
		{
			if (StringUtils.equals(row.getEKN(), productCode))
			{
				productInfo = row;
			}
		}

		if (productInfo == null)
		{
			LOG.debug("Didn't get delivery information for product with code = " + productCode);
			model.addAttribute(ERROR_MESSAGE, "Отсутствуют данные о сроках доставки товара с артикулом " + productCode);
			return ControllerConstants.Views.Fragments.Stock.CheckStockPopup;
		}

		final String availabilityMessage = StringUtils.equals(productInfo.getCount(), "0") ? " недоступен" : (StringUtils.equals(
				productInfo.getCount(), count) ? " доступен полностью (количество: " + productInfo.getCount() + ")"
				: " доступен частично (количество: " + productInfo.getCount() + ")");

		model.addAttribute(PRODUCT_CODE, productCode);
		model.addAttribute(POST_DATE, productInfo.getDatePost());
		model.addAttribute(AVAILABILITY_MESSAGE, availabilityMessage);

		return ControllerConstants.Views.Fragments.Stock.CheckStockPopup;
	}
}
