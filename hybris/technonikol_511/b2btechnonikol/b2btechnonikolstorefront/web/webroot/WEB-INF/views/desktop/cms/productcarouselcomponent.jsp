<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:if test="${not empty productData}">

    <div class="block-chars">
        <div class="block-chars__header">${title}</div>
            <div class="carousel-product__carousel clearfix">
            <ul>
            <c:forEach items="${productData}" var="productReference">
		<c:url value="${productReference.url}/quickView" var="productQuickViewUrl"/>
                <c:url value="${productReference.url}" var="productUrl"/>
                <li class="product-carousel__item">
                    <div class="product-carousel-item__img">
                        <a href="${productUrl}"><product:productPrimaryImage product="${productReference}" format="thumbnail"/></a>
                    </div>
                    <div class="product-carousel-item__info">
                        <div class="product-carousel-item__name">
                            <a href="${productUrl}">${productReference.name}</a>
                        </div>
                        <div class="product-carousel-item__articul">
                            <spring:theme code="page.productDetails.article"/>: <a href="${productUrl}">${productReference.code}</a>
                                <p class="product-carousel-item__manufacturer">${productReference.brand.name}</p>
                        </div>
                    </div>


                    <div class="product-carousel-item__price">
                        <c:if test="${not empty productReference.price}">
                            <spring:theme code="page.productDetails.price"/>:
                            <span class="product-carousel-item__price_green">
                            <format:fromPrice priceData="${productReference.price}"/>
                            </span>
                        </c:if>
                    </div>

                    <product:productReferenceToCart product="${productReference}"/>
                    <%--<div class="product-carousel-item__in-cart">
                        <input type="text" class="in-cart__input" value="1" />
                        <a href="javascript:void(0)" class="button">В корзину</a>
                    </div>--%>

                    <%--<div class="product-carousel-item__to-compare">
                        <c:set var="toCompareId" value="to_compare_${productReference.target.code}"/>
                        <input type="checkbox" id="${toCompareId}" /> <label for="${toCompareId}"><spring:theme code="page.productDetails.toCampare"/></label>
                    </div>--%>

                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
</c:if>

<%--

<c:if test="${not empty productData}">

<div class="scroller vertical">
	<div class="title_holder">
		<div class="title">
			<div class="title-top">
				<span></span>
			</div>
		</div>
		<h2>${title}</h2>
	</div>
	
	<c:choose>
		<c:when test="${component.popup}">
			<ul class="carousel jcarousel-skin popup">
				<c:forEach items="${productData}" var="product">
		
					<c:url value="${product.url}/quickView" var="productQuickViewUrl"/>
					<li>
						<a href="${productQuickViewUrl}" class="popup">
							<span>
								<product:productPrimaryImage product="${product}" format="thumbnail"/>
							</span>
							<h3>
									${product.name}
							</h3>
							<p>
								<format:fromPrice priceData="${product.price}"/>
							</p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="carousel jcarousel-skin">
				<c:forEach items="${productData}" var="product">
		
					<c:url value="${product.url}" var="productUrl"/>
					<li>
						<a href="${productUrl}">
							<span>
								<product:productPrimaryImage product="${product}" format="thumbnail"/>
							</span>
							<h3>
									${product.name}
							</h3>
							<p>
								<format:fromPrice priceData="${product.price}"/>
							</p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
	
</div>
</c:if>
--%>