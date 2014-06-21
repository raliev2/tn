<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<ycommerce:testId code="searchPage_price_label_${product.code}">
    <div class="prod-list-item__price">
        Цена:
        <!-- if product is multidimensional with different prices, show range, else, show unique price -->	        
	<span class="price-value">
        <c:choose>
            <c:when test="${product.multidimensional and (product.priceRange.minPrice.value ne product.priceRange.maxPrice.value)}">
                <format:price priceData="${product.priceRange.minPrice}"/> - <format:price priceData="${product.priceRange.maxPrice}"/>
            </c:when>
            <c:otherwise>
		<format:fromPrice priceData="${product.price}"/>
            </c:otherwise>
        </c:choose>
        </span>
    </div>
</ycommerce:testId>

<c:if test="${product.volumePricesFlag}">
	<span class="volumePricesFlag">
		<spring:theme code="text.volumePrices" var="volumePricesText"/>
		<theme:image code="img.volumePrices" alt="${volumePricesText}" title="${volumePricesText}" />
	</span>
</c:if>
