<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${not empty errorMessage}">
     	${errorMessage}
	</c:when>
	<c:otherwise>
		<c:if test="${rows.size() == 1}">
			В наличии на складе, готов к отгрузке <br/>
			Ожидаемая дата поставки: ${rows.get(0).datePost}
		</c:if>
		<c:if test="${rows.size() > 1}">
			<c:forEach items="${rows}" var="row">
				<br />
				<c:choose>	
					<c:when test="${row.datePost == '01.01.1000'}">
     					${row.count} недоступно для заказа
					</c:when>
					<c:otherwise>	
						<c:if test="${not empty row.datePost}">
							Дата отгрузки для ${row.count}: ${row.datePost}
						</c:if>
					</c:otherwise>
				</c:choose>		
				<br />
			</c:forEach>
		</c:if>
	</c:otherwise>
</c:choose>
