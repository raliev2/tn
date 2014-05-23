<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:choose>
	<c:when test="${empty product.variantOptions}">
		<c:set var="allowAddToCart" value="${true}"/>
	</c:when>
	<c:otherwise>
		<c:set var="allowAddToCart" value="${false}"/>
	</c:otherwise>
</c:choose>

<form id="addToCartForm" class="add_to_cart_form" action="<c:url value="/cart/add"/>" method="post">
    <%--span class="prod_results">
        <product:productFutureAvailability product="${product}" futureStockEnabled="${futureStockEnabled}" />
    </span--%>
    <c:if test="${(product.purchasable) || (true)}">
        <label for="qty" class="g-italic">Кол-во:</label>
        <input type="text" value="1" id="qty" name="qty" class="g-input" size="3" />
    </c:if>
    <input type="hidden" name="productCodePost" value="${product.code}"/>

    <c:set var="buttonType">button</c:set>
    <c:if test="${allowAddToCart and product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
        <c:set var="buttonType">submit</c:set>
    </c:if>

    <button id="addToCartButton" type="${buttonType}" disabled="true" class="button add_to_cart_button <c:if test="${fn:contains(buttonType, 'button')}">button_disabled</c:if>">
        В корзину
    </button>

    <c:if test="${multiDimensionalProduct}" >
        <sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
            <c:url value="${product.url}/orderForm" var="productOrderFormUrl"/>
            <a href="${productOrderFormUrl}" ><spring:theme code="order.form" /></a>
        </sec:authorize>
    </c:if>

</form>
