<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<script type="text/javascript" src="${commonResourcePath}/js/acc.productlisting.js"></script>
	</jsp:attribute>

    <jsp:body>
        <div id="globalMessages">
            <common:globalMessages/>
        </div>

        <section class="g-main-content g-main-content_no-padding clearfix">
            <aside class="g-left-col g-left-col_right-shadow">
                <div class="filter-block filter-block_border-bottom">
                    <div class="filter-block__header_red">Фильтр</div>
                </div>
                <nav:facetNavAppliedFilters pageData="${searchPageData}"/>
                <nav:facetNavRefinements pageData="${searchPageData}"/>
            </aside>

            <section class="g-right-col">
                <div class="g-float-right block-buttons">
                    <a href="javascript:void(0)" class="g-button-white">Печать</a>
                </div>
                <div class="clearfix"></div>
                <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" className="bread-crumbs_mini" />
                <div class="clearfix"></div>
                <div class="search-string">
                        ${searchPageData.freeTextSearch}
                </div>
                <div class="found-amount">
                    Найдено ${searchPageData.pagination.totalNumberOfResults} товаров
                </div>
                <nav:searchSorts top="true"  supportShowPaged="false"
                                 supportShowAll="false"
                                 searchPageData="${searchPageData}"
                                 searchUrl="${searchPageData.currentQuery.url}"
                                 numberPagesShown="${numberPagesShown}"
                        />

                <nav:pagination top="true"  supportShowPaged="false"
                                supportShowAll="false"
                                searchPageData="${searchPageData}"
                                searchUrl="${searchPageData.currentQuery.url}"
                                numberPagesShown="${numberPagesShown}"
                        />
                <section class="search-results" id="resultsList" data-isOrderForm="false">
                    <c:forEach items="${searchPageData.results}" var="product" varStatus="varStatus">
                        <product:productListerItem product="${product}" index1="${varStatus.index}" />
                    </c:forEach>
                </section>
                <nav:pagination top="true"  supportShowPaged="false"
                                supportShowAll="false"
                                searchPageData="${searchPageData}"
                                searchUrl="${searchPageData.currentQuery.url}"
                                numberPagesShown="${numberPagesShown}"
                        />
                <nav:searchSorts top="true"  supportShowPaged="false"
                                 supportShowAll="false"
                                 searchPageData="${searchPageData}"
                                 searchUrl="${searchPageData.currentQuery.url}"
                                 numberPagesShown="${numberPagesShown}"
                        />


            </section>
        </section>
        <nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
        <common:infiniteScroll/>
    </jsp:body>
</template:page>
