<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="searchUrl" required="true" %>
<%@ attribute name="searchPageData" required="true" type="de.hybris.platform.commerceservices.search.pagedata.SearchPageData" %>
<%@ attribute name="top" required="true" type="java.lang.Boolean" %>
<%@ attribute name="supportShowAll" required="true" type="java.lang.Boolean" %>
<%@ attribute name="searchResultType" required="false" type="java.lang.String" %>
<%@ attribute name="supportShowPaged" required="true" type="java.lang.Boolean" %>
<%@ attribute name="sortQueryParams" required="false" %>
<%@ attribute name="msgKey" required="false" %>
<%@ attribute name="numberPagesShown" required="false" type="java.lang.Integer" %>

<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags/desktop/nav/pagination" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="themeMsgKey" value="${not empty msgKey ? msgKey : 'search.page'}"/>


<div class="search-controls g-gradient-gray clearfix">

    <div class="search-controls__grid-list search-controls__grid-list_grid">
        <a href="javascript:void(0)" class="g-link-blue">Плитка</a>
    </div>
    <div class="search-controls__grid-list search-controls__grid-list_list">
        Список
    </div>
    <div class="search-controls__pagination">
        Результатов на странице:   <a href="javascript:void(0)" class="g-link-blue">5</a>  |  10  |  <a href="javascript:void(0)" class="g-link-blue">20</a>
    </div>

    <c:if test="${paginationType eq 'pagination' && (searchPageData.pagination.numberOfPages > 1)}">
        <pagination:pageSelectionPagination searchUrl="${searchUrl}" searchPageData="${searchPageData}" numberPagesShown="${numberPagesShown}" themeMsgKey="${themeMsgKey}"/>
    </c:if>

</div>

<div class="prod_refine">
	
	<c:catch var="notFacetSearchPageDataInstance">
		<spring:eval expression="searchPageData.currentQuery" var="currentQuery"/>
	</c:catch>

	<c:if test="${empty searchPageData.sorts && empty notFacetSearchPageDataInstance && not empty searchPageData.currentQuery }">
		<div id="sort_form1">
			<input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
		</div>
	</c:if>

	<c:if test="${supportShowAll}">
		<spring:url value="${searchUrl}" var="showAllUrl" htmlEscape="true">
			<spring:param name="show" value="All" />
		</spring:url>
		<ycommerce:testId code="searchResults_showAll_link">
			<a href="${showAllUrl}"><spring:theme code="${themeMsgKey}.showAllResults" /></a>
		</ycommerce:testId>
	</c:if>

	<c:if test="${supportShowPaged}">
		<spring:url value="${searchUrl}" var="showPageUrl" htmlEscape="true">
			<spring:param name="show" value="Page" />
		</spring:url>
		<ycommerce:testId code="searchResults_showPage_link">
			<a href="${showPageUrl}"><spring:theme code="${themeMsgKey}.showPageResults" /></a>
		</ycommerce:testId>
	</c:if>

</div>