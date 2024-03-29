<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="futureStockEnabled" required="true" type="java.lang.Boolean" %>

<c:if test="${product.purchasable and product.stock.stockLevel le 0}">
	<c:set var="productStockLevel"><spring:theme code="product.variants.out.of.stock"/></c:set>
</c:if>
<c:if test="${product.stock.stockLevel gt 0}">
	<c:set var="productStockLevel">${product.stock.stockLevel}&nbsp;<spring:theme code="product.variants.in.stock"/></c:set>
</c:if>
<c:if test="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}">
	<c:set var="productStockLevel"><spring:theme code="product.variants.available"/></c:set>
</c:if>

<ycommerce:testId code="productDetails_productInStock_label">
	<p class="stock_message">${productStockLevel}</p>
</ycommerce:testId>

<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
	<c:if test="${futureStockEnabled}">
		<ycommerce:testId code="productDetails_showAvailability_label">
			<c:url value="${product.url}/futureStock" var="productfutureStockUrl"/>
			<a class="futureStockLink" href="${productfutureStockUrl}" target="_blank" title="<spring:theme code="basket.page.viewFuture"/>">
				<spring:theme code="basket.page.viewFuture"/>
			</a>
		</ycommerce:testId>
	</c:if>
</sec:authorize>
