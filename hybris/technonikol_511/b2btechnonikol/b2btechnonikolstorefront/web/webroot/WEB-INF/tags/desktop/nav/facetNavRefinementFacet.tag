<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="facetData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetData" %>
<%@ attribute name="facetIndex" required="false" type="Integer" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<c:if test="${not empty facetData.values}">
    <div class="filter-block">
        <div class="filter-block__header ${facetIndex == 0 ? 'filter-block__header_red-big' : ''} filter-block__header_top">
            ${facetData.name}
        </div>
        <div class="filter-block__body">
            <ycommerce:testId code="facetNav_facet${facetData.name}_links">
                <div class="facetValues">
                    <c:if test="${not empty facetData.topValues}">
                        <ul class="facet_block ${facetData.multiSelect ? '' : 'indent'}">
                            <c:forEach items="${facetData.topValues}" var="facetValue">
                                <li>
                                    <c:if test="${facetData.multiSelect}">
                                        <form action="#" method="get">
                                            <input type="hidden" name="q" value="${facetValue.query.query.value}"/>
                                            <input type="hidden" name="text" value="${searchPageData.freeTextSearch}"/>
                                            <label class="facet_block-label">
                                                <input type="checkbox" ${facetValue.selected ? 'checked="checked"' : ''} onchange="$(this).closest('form').submit()"/>
                                                    ${facetValue.name}
                                            </label>
                                            <span class="g-amount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                        </form>
                                    </c:if>
                                    <c:if test="${not facetData.multiSelect}">
                                        <c:url value="${facetValue.query.url}" var="facetValueQueryUrl"/>
                                        <a href="${facetValueQueryUrl}">${facetValue.name}</a>
                                        <span class="g-amount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                        <a href="javascript:void(0)" class="js-show-all filter-block__show-all" data-text="Скрыть">Показать все</a>
                    </c:if>

                    <ul class="facet_block ${facetData.multiSelect ? '' : 'indent'} ${not empty facetData.topValues ? ' g-hidden' : ''}">
                        <c:forEach items="${facetData.values}" var="facetValue">
                            <li>
                                <c:if test="${facetData.multiSelect}">
                                    <form action="#" method="get">
                                        <input type="hidden" name="q" value="${facetValue.query.query.value}"/>
                                        <input type="hidden" name="text" value="${searchPageData.freeTextSearch}"/>
                                        <label class="facet_block-label">
                                            <input type="checkbox" ${facetValue.selected ? 'checked="checked"' : ''} onchange="$(this).closest('form').submit()"/>
                                                ${facetValue.name}
                                        </label>
                                        <span class="g-amount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                    </form>
                                </c:if>
                                <c:if test="${not facetData.multiSelect}">
                                    <c:url value="${facetValue.query.url}" var="facetValueQueryUrl"/>
                                    <a href="${facetValueQueryUrl}&text=${searchPageData.freeTextSearch}">${facetValue.name}</a>
                                    <span class="g-amount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </ycommerce:testId>
        </div>
    </div>

</c:if>
