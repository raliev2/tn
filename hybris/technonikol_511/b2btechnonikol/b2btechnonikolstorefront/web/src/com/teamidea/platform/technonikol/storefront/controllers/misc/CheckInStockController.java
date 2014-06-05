/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.misc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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
	private static final String ROWS = "rows";

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
			model.addAttribute(ERROR_MESSAGE, "Ошибка получения данных");
			return ControllerConstants.Views.Fragments.Stock.CheckStockPopup;
		}

		final List<MaterialsRow> deliveryInfo = response.getReturn().getMaterials().getRow();
		final List<MaterialsRow> productInfo = new ArrayList<MaterialsRow>();
		for (final MaterialsRow row : deliveryInfo)
		{
			if (StringUtils.equals(row.getEKN(), productCode))
			{
				productInfo.add(row);
			}
		}

		if (CollectionUtils.isEmpty(productInfo))
		{
			LOG.debug("Didn't get delivery information for product with code = " + productCode);
			model.addAttribute(ERROR_MESSAGE, "Данные отсутствуют");
			return ControllerConstants.Views.Fragments.Stock.CheckStockPopup;
		}

		model.addAttribute(ROWS, productInfo);
		model.addAttribute(PRODUCT_COUNT, count);

		return ControllerConstants.Views.Fragments.Stock.CheckStockPopup;
	}
}
