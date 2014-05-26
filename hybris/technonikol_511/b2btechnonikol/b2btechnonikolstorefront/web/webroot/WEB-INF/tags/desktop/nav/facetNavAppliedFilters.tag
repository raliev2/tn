<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>


<c:if test="${not empty pageData.breadcrumbs}">
<div class="filter-block">
    <div class="filter-block__header_filters">
        <p>Установленные фильтры</p>
        <div class="link-blue"><a href="?q=${searchPageData.freeTextSearch}" class="g-link-blue">Сбросить все</a></div>
    </div>
    <div class="filter-block__body filter-block_border-bottom">
        <ul class="facet_block">
            <c:forEach items="${pageData.breadcrumbs}" var="breadcrumb">
                <li>
                    <c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl"/>
                    <a href="${removeQueryUrl}" class="facet-block__remove-filter">
                        ${breadcrumb.facetValueName}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</c:if>
