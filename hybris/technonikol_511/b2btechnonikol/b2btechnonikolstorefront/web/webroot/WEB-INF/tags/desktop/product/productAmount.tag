<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="amount" required="true" type="java.lang.String" %>
<%@ attribute name="cart" required="false" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${not empty cart}">
<span class="js-cart-amount">
</c:if>
${amount}
<c:if test="${not empty cart}">
    </span>
</c:if>
<c:choose>
    <c:when test="${fn:endsWith(amount, '14') || fn:endsWith(amount, '13') || fn:endsWith(amount, '12')}">
        товаров
    </c:when>
    <c:when test="${fn:endsWith(amount, '2') || fn:endsWith(amount, '3') || fn:endsWith(amount, '4')}">
        товара
    </c:when>
    <c:when test="${fn:endsWith(amount, '1')}">
        товар
    </c:when>
    <c:otherwise>
        товаров
    </c:otherwise>
</c:choose>