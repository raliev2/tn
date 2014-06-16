<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="isOrderForm" required="false" type="java.lang.Boolean" %>
<%@ attribute name="index1" required="false" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${product.url}" var="productUrl"/>

<div class="prod-list__item clearfix">
    <div class="prod-list-item__product clearfix">
        <div class="prod-list-item__img">
            <a href="${productUrl}">
                <product:productPrimaryImage product="${product}" format="thumbnail"/>
            </a>
        </div>
        <c:choose>
            <%-- Verify if products is a multidimensional product --%>
            <c:when test="${product.multidimensional}">
                <div class="search-item__about">
                    <a href="javascript:void(0)" class="search-item-about__name"><c:out value="${product.name}" /></a>
                    <p class="search-item-about__manufacturer">${product.brand.name}</p>
                    <p class="search-item-about__variants">Несколько вариантов продукта<br />доступны </p>
                </div>
                <div class="search-item__actions g-float-right">
                    <product:productListerItemPrice product="${product}" />
                    <div>
                        <a href="${productUrl}" class="button-other-products">Еще 2 товара</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="prod-list-item__about">
                    <a href="${productUrl}" class="prod-list-item-about__name"><c:out value="${product.name}" /></a>
                    <p class="prod-list-item-about__manufacturer">${product.brand.name}</p>
                    <p>Код ТН: <span class="g-strong"><a href="${productUrl}">${product.code}</a></span></p>
                </div>
                <div class="prod-list-item__actions g-float-right">
                    <product:productListerItemPrice product="${product}" />

                    <div class="stock in-stock g-float-right"><span>Есть на складе</span>
                        <div class="g-info"></div>
                    </div>
                    <div class="clearfix"></div>
                    <product:productListerItemToCart product="${product}" />
                    <%--<div class="to-cart">
                        <label for="qty${index1}">Кол-во:</label>
                        <input type="text" value="1" id="qty${index1}" name="qty" class="g-input" size="3" />
                        <a href="javascript:void(0)" class="button">В корзину</a>
                    </div>--%>
                    <div class="clearfix"></div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

