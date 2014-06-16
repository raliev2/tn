<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<c:if test="${not empty cartData.potentialOrderPromotions}">
    <div class="item_container">
        <ycommerce:testId code="potentialPromotions_promotions_labels">
            <ul>
                <c:forEach items="${cartData.potentialOrderPromotions}" var="promotion">
                    <li class="cart-promotions__applied-item">${promotion.description}</li>
                </c:forEach>
            </ul>
        </ycommerce:testId>
    </div>
</c:if>
