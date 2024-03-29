<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<ycommerce:testId code="productDetails_promotion_label">
    <c:if test="${not empty product.potentialPromotions}">
        <div class="bundle">
            <h2>Акция: </h2>
            <c:choose>
                <c:when test="${not empty product.potentialPromotions[0].couldFireMessages}">
                    <p>${product.potentialPromotions[0].couldFireMessages[0]}</p>
                </c:when>
                <c:otherwise>
                    <p>${product.potentialPromotions[0].description}</p>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</ycommerce:testId>
