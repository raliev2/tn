<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<%@ attribute name="galleryImages" required="true" type="java.util.List" %>
<%@ attribute name="product" required="false" type="de.hybris.platform.commercefacades.product.data.ProductData" %>


<c:choose>
    <c:when test="${fn:length(galleryImages) < 2}">
        <c:set value="${ycommerce:productImage(product, 'zoom')}" var="zoomImage"/>
        <c:set value="${ycommerce:productImage(product, 'product')}" var="primaryImage"/>
        <div class="slider-pager clearfix">
            <a data-index="${varStatus.index}" href="javascript:void(0)" data-img-mid="${primaryImage.url}" data-img-big="${zoomImage.url}" class="slider-pager__hidden"></a>
        </div>
    </c:when>

    <c:otherwise>
        <div class="slider-pager clearfix">
            <c:forEach items="${galleryImages}" var="container" varStatus="varStatus">
                <a data-index="${varStatus.index}" href="javascript:void(0)" data-img-mid="${container.product.url}" data-img-big="${container.zoom.url}">
                    <img src="${container.thumbnail.url}" alt="${product.name}" class="product-img__thumbnail" title="${product.name}" />
                </a>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>