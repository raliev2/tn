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

<c:if test="${not empty searchPageData.sorts}">
    <div class="sort-by-block">
        <div class="sort-by-block__select">
            <form id="sort_form${top ? '1' : '2'}" name="sort_form${top ? '1' : '2'}" method="get" action="#">
                <c:if test="${not empty sortQueryParams}">
                    <c:forEach var="queryParam" items="${fn:split(sortQueryParams, '&')}">
                        <c:set var="keyValue" value="${fn:split(queryParam, '=')}"/>
                        <c:if test="${not empty keyValue[1]}">
                            <input type="hidden" name="${keyValue[0]}" value="${keyValue[1]}"/>
                        </c:if>
                    </c:forEach>
                </c:if>
                <label for="sortOptions${top ? '1' : '2'}">Сортировать товары по:</label>
                <select id="sortOptions${top ? '1' : '2'}" name="sort" class="sortOptions select-sort-by">
                    <c:forEach items="${searchPageData.sorts}" var="sort">
                        <option value="${sort.code}" ${sort.selected ? 'selected="selected"' : ''}>
                            <c:choose>
                                <c:when test="${not empty sort.name}">
                                    ${sort.name}
                                </c:when>
                                <c:otherwise>
                                    <spring:theme code="${themeMsgKey}.sort.${sort.code}"/>
                                </c:otherwise>
                            </c:choose>
                        </option>
                    </c:forEach>
                </select>
                <c:if test="${not empty searchResultType}">
                    <input type="hidden" name="searchResultType" value="${searchResultType}"/>
                </c:if>
                <c:catch var="errorException">
                    <spring:eval expression="searchPageData.currentQuery.query" var="dummyVar"/><%-- This will throw an exception is it is not supported --%>
                    <input type="hidden" name="q" value="${searchPageData.currentQuery.query.value}"/>
                </c:catch>

                <c:if test="${supportShowAll}">
                    <ycommerce:testId code="searchResults_showAll_link">
                        <input type="hidden" name="show" value="Page"/>
                    </ycommerce:testId>
                </c:if>
                <c:if test="${supportShowPaged}">
                    <ycommerce:testId code="searchResults_showPage_link">
                        <input type="hidden" name="show" value="All"/>
                    </ycommerce:testId>
                </c:if>
            </form>
        </div>
    </div>
</c:if>