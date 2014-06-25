<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	</head>
	<body>
		<div class="filter-block filter-block_border-bottom">
			<div class="filter-block__header_red">${title}</div>
			<c:if test="${categories.size() == 0}">
			<%--Отсутствуют--%>
			</c:if>
			<div class="facetValues filter-block__body">
			<ul class="facet_block indent topValues">
				<c:forEach items="${categories}" var="category" varStatus="status">
					<li>
					<c:url value="/${category.url}" var="category_url"/>
					<a href="${category_url}" style="font-size: 13px; !important">${category.name}</a>
					</li>
				</c:forEach>
			</ul>
			</div>
		</div>
	</body>
</html>