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

<c:url value="/cart" var="cartUrl"/>
<script type="text/plain" id="addToCartTmpl">
    <div class="cart-popup__header clearfix">
        <div class="cart-popup__mesage">{{=it.message}}</div>
        <div class="cart-popup__tocart g-float-right"><a href="${cartUrl}">Посмотреть корзину</a></div>
    </div>
    <div class=cart-popup__body>
                <div class="cart-popup-carousel__header">Также покупают:</div>
                {{=it.productReference}}
    </div>
</script>

<form id="addToCartForm" class="add_to_cart_form" action="<c:url value="/cart/add"/>" method="post">
    <c:set var="buttonType">button</c:set>
    [${allowAddToCart}][${product.purchasable}][${product.stock.stockLevelStatus.code}]
    <c:if test="${allowAddToCart and product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
        <c:set var="buttonType">submit</c:set>
    </c:if>
    <div class="to-cart">
        <label for="qty${index1}">Кол-во:</label>
        <input type="text" value="1" id="qty${index1}" name="qty" class="g-input" size="3" />
        <button type="${buttonType}" disabled="true" class="button <c:if test="${fn:contains(buttonType, 'button')}">button_disabled</c:if>">
            В корзину
        </button>
    </div>
    <input type="hidden" name="productCodePost" value="${product.code}"/>
</form>