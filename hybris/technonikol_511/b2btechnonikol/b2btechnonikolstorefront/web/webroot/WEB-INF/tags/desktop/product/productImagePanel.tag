<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<c:if test="${fn:contains(product.url, '?sku=')}">
    <c:url value="${fn:substringBefore(product.url, '?sku=')}/zoomImages" var="productZoomImagesUrl"/>
</c:if>
<c:if test="${not fn:contains(product.url, '?sku=')}">
    <c:url value="${product.url}/zoomImages" var="productZoomImagesUrl"/>
</c:if>

<c:set value="${ycommerce:productImage(product, 'product')}" var="primaryImage"/>
<c:set value="${ycommerce:productImage(product, 'zoom')}" var="zoomImage"/>

<div class="product-img">
    <c:choose>
        <c:when test="${not empty primaryImage}">
            <c:choose>
                <c:when test="${not empty zoomImage}">
                    <div class="easyzoom">
                        <a href="${zoomImage.url}" class="productImgMainBig">
                            <product:productPrimaryImage product="${product}" format="product"/>
                        </a>
                    </div>
                    <div class="clearfix"></div>
                    <div class="open-img js-open-img_open"></div>
                    <div class="g-close" style="display:none"><a href="javascript:void(0)">Закрыть</a></div>
                </c:when>
                <c:otherwise>
                    <div class="productImgMainBig">
                        <product:productPrimaryImage product="${product}" format="product"/>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <div>
                <product:productPrimaryImage product="${product}" format="product"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>


