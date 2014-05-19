<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:if test="${not empty productReferences}">
	<c:if test="${component.maximumNumberProducts > 0}">

    <div class="block-chars">
        <div class="block-chars__header">${component.title}</div>
        <div class="carousel-product__carousel clearfix">
            <ul>
            <c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
                <c:url value="${productReference.target.url}/quickView" var="productQuickViewUrl"/>
                <li class="product-carousel__item product-carousel__item_930px">
                    <div class="product-carousel-item__img product-carousel-item__img_930px">
                        <a href="${productQuickViewUrl}"><img src="${themeResourcePath}/images/products/prod1.jpg" alt="${productReference.target.name}" /></a>
                    </div>
                    <div class="product-carousel-item__info">
                        <div class="product-carousel-item__name">
                            <a href="${productQuickViewUrl}">${productReference.target.name}</a>
                        </div>
                        <div class="product-carousel-item__articul">
                            Артикул: <a href="${productQuickViewUrl}">${productReference.target.code}</a>
                            <p>ROBERTS</p>
                        </div>
                    </div>
                    <div class="product-carousel-item__price">
                        Цена: <span class="product-carousel-item__price_green">400,00&nbsp;<span class='g-rouble'>P</span></span>
                    </div>
                    <div class="product-carousel-item__to-compare">
                        <input type="checkbox" id="to_compare1" /> <label for="to_compare1"><spring:theme code="page.productDetails.toCampare"/></label>
                    </div>
                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
	</c:if>
</c:if>