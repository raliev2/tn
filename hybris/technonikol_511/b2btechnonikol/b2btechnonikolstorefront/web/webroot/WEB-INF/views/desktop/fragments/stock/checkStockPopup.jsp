<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${not empty errorMessage}">
     	${errorMessage}
	</c:when>
	<c:otherwise>
		<c:forEach items="${rows}" var="row">
			<c:choose>
    			<c:when test="${row.count == 0}">
					Недоступен
    			</c:when>
    			<c:when test="${row.count < count}">
        			Доступен частично (${row.count} шт.)
    			</c:when>
    			<c:otherwise>
        			Доступен полностью
    			</c:otherwise>
    		</c:choose>
    		<br/>
			<c:if test="${not empty row.datePost}">
				Дата доставки: ${row.datePost}
			</c:if>
			<br/>			
		</c:forEach>		
	</c:otherwise>
</c:choose>