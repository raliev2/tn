<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <title>Наличие товара</title>
    <body>
    	<c:choose>
     		<c:when test="${not empty errorMessage}">
     			${errorMessage}
			</c:when>
			<c:otherwise>
				Товар с артикулом ${productCode} ${availabilityMessage} <br/>
				<c:if test="${not empty postDate}">
					Дата доставки товара: ${postDate}
				</c:if>
			</c:otherwise>
		</c:choose>
    </body>
</html>