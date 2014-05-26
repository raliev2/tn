<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="amount" required="true" type="Integer" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${amount}
<c:choose>
    <c:when test="${amount == 2 || amount == 3 || amount == 4}">
        товара
    </c:when>
    <c:when test="${amount == 1}">
        товар
    </c:when>
    <c:otherwise>
        товаров
    </c:otherwise>
</c:choose>