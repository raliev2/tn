<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="amount" required="true" type="String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

${amount}
<c:choose>
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