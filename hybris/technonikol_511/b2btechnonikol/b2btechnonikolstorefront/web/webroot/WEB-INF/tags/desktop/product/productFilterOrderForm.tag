<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form"%>

<div class="prod_list">
	<c:url value="${product.url}" var="productUrl"/>
	
	<div class="thumb">
		<c:if test="${not empty product.averageRating}">
				<span class="stars large" style="display: inherit;">
				<span style="width: <fmt:formatNumber maxFractionDigits="0" value="${product.averageRating * 24}" />px;"></span>
			</span>
		</c:if>

		<product:productPrimaryImage product="${product}" format="thumbnail"/>
		<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
			<img class="promo" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
		</c:if>
	</div>
	
	<c:set var="checkBoxClass" value="js-checkbox-sku-id" />
	<c:if test="${not empty product.variantMatrix}">
		<c:set var="checkBoxClass" value="js-checkbox-base-product" />
	</c:if>

	<div class="details">
		<input type="checkbox" value="${product.code}" class="${checkBoxClass}"><c:out value='${product.code}' />
		<h2><c:out value='${product.name}' /></h2>
		<product:productListerItemPrice product="${product}" />
		
		<div id="priority1Dimensions">
			<c:forEach items="${product.variantMatrix}" var="variant">
				<c:if test="${variant.variantOption.stock.stockLevel != 0}">
					<input class="js-checkbox-sku-id" type="checkbox" name="productIdsList" id="productIdsList" base-product-code="${product.code}" value="${variant.variantOption.code }"> ${ variant.variantValueCategory.name }
				</c:if>
			</c:forEach>
		</div>
		
	</div>
</div>