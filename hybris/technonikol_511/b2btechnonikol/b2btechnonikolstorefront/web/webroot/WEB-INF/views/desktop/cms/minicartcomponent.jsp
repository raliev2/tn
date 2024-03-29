<%@ page trimDirectiveWhitespaces="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:url value="/cart/miniCart/${totalDisplay}" var="refreshMiniCartUrl"/>
<c:url value="/cart/rollover/${component.uid}" var="rolloverPopupUrl"/>

<script id="miniCartTemplate" type="text/x-jquery-tmpl">
/*<![CDATA[*/
	<dt><ycommerce:testId code="miniCart_items_label"><spring:theme text="items" code="cart.items" arguments="{{= totalItems}}"/></ycommerce:testId> - </dt>
	<dd><ycommerce:testId code="miniCart_total_label">
			<c:if test="${totalDisplay == 'TOTAL'}">{{= totalPrice.formattedValue}}</c:if>
			<c:if test="${totalDisplay == 'SUBTOTAL'}">{{= subTotal.formattedValue}}</c:if>
			<c:if test="${totalDisplay == 'TOTAL_WITHOUT_DELIVERY'}">{{= totalNoDelivery.formattedValue}}</c:if>
	</ycommerce:testId></dd>
/*]]>*/
</script>

<script type="text/javascript"> // set vars
var rolloverPopupUrl = '${rolloverPopupUrl}';
var refreshMiniCartUrl = '${refreshMiniCartUrl}/?';
</script>

<div>
	<c:url value="/cart" var="cartUrl"/>
    <c:choose>
        <c:when test="${totalItems == 0}">
            <a href="${cartUrl}" class="link-cart">Ваша корзина пуста</a>
        </c:when>
        <c:otherwise>
            <a href="${cartUrl}" class="link-cart">В корзине
                <product:productAmount amount="${totalItems}" cart="true" />
            </a>
        </c:otherwise>
    </c:choose>
</div>
<cart:rolloverCartPopup/>