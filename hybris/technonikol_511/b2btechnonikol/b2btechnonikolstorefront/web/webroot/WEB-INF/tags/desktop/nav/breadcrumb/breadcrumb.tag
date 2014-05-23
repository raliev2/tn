<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="breadcrumbs" required="true" type="java.util.List" %>
<%@ attribute name="className" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="bread-crumbs ${className}">
    <c:url value="/" var="homeUrl"/>
    <a href="${homeUrl}" class="g-link-blue">Главная</a>
    <c:forEach items="${breadcrumbs}" var="breadcrumb" varStatus="status">
        <c:choose>
            <c:when test="${breadcrumb.url eq '#'}">
                / <a href="javascript:void(0)" class="g-link-blue"> ${breadcrumb.name}</a>
            </c:when>
            <c:otherwise>
                <c:url value="${breadcrumb.url}" var="breadcrumbUrl"/>
                <c:if test="${not status.last}">
                    / <a href="${breadcrumbUrl}" class="g-link-blue"> ${breadcrumb.name}</a>
                </c:if>
                <c:if test="${status.last}">
                    / ${breadcrumb.name}
                </c:if>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
