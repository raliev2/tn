<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:if test="${not empty productReferences}">
	<c:if test="${component.maximumNumberProducts > 0}">

        <c:set var="referenceType" value="${productReferences[0].referenceType}"/>

    <div class="block-chars">
        <div class="block-chars__header">${component.title}</div>
            <div class="carousel-product__carousel <c:if test="${referenceType != 'SIMILAR'}">carousel-product__carousel_height</c:if> clearfix">
            <ul>
            <c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
                <c:url value="${productReference.target.url}/quickView" var="productQuickViewUrl"/>
                <li class="product-carousel__item product-carousel__item_930px">
                    <div class="product-carousel-item__img product-carousel-item__img_930px">
                        <a href="${productQuickViewUrl}"><product:productPrimaryImage product="${productReference.target}" format="thumbnail"/></a>
                    </div>
                    <div class="product-carousel-item__info">
                        <c:if test="${component.displayProductTitles}">
                        <div class="product-carousel-item__name">
                            <a href="${productQuickViewUrl}">${productReference.target.name}</a>
                        </div>
                        </c:if>
                        <div class="product-carousel-item__articul">
                            <spring:theme code="page.productDetails.article"/>: <a href="${productQuickViewUrl}">${productReference.target.code}</a>
                                <p class="product-carousel-item__manufacturer">${productReference.target.manufacturer}</p>
                        </div>
                    </div>

                    <c:if test="${component.displayProductPrices}">
                    <div class="product-carousel-item__price">
                        <spring:theme code="page.productDetails.price"/>:
                        <span class="product-carousel-item__price_green">
	                    <c:if test="${productReference.target.price}">
                            <format:fromPrice priceData="${productReference.target.price}"/>
			    </c:if>
                        </span>
                    </div>
                    </c:if>
                        <c:if test="${referenceType != 'SIMILAR'}">
                            <div class="product-carousel-item__in-cart">
                         		<input type="text" class="in-cart__input" value="1" />
                         		<a href="javascript:void(0)" class="button">В корзину</a>
                         	</div>
                        </c:if>
                    <div class="product-carousel-item__to-compare">
                        <c:set var="toCompareId" value="to_compare_${productReference.target.code}"/>
                        <input type="checkbox" id="${toCompareId}" /> <label for="${toCompareId}"><spring:theme code="page.productDetails.toCampare"/></label>
                    </div>

                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
	</c:if>
</c:if>