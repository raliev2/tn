<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
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
                <nav:searchInCategory pageData="${searchPageData}" />
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
                     ${currentsection}
                </div>
                <div class="found-amount">
                    <product:productAmount amount="${searchPageData.pagination.totalNumberOfResults}" />
                </div>
                <div class="banner-700px">
                    <a href="javascript:void(0)">
                        <img src="${themeResourcePath}/images/banners/westard.jpg" alt="Westward" title="Westward" />
                    </a>
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

                <div class="category-description">
                    <%-- 
			Описание категории
		    %-->
                </div>
            </section>
        </section>
        <nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
        <common:infiniteScroll/>
	<%--<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	
	<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div" class="span-24 section1 cms_disp-img_slot"/>
	</cms:pageSlot>
		
	<div class="span-24">
		<div class="span-4">
			<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
			<nav:facetNavRefinements pageData="${searchPageData}"/>

			<cms:pageSlot position="Section5" var="feature">
				<cms:component component="${feature}" element="div" class="section5 cms_disp-img_slot"/>
			</cms:pageSlot>

		</div>
		<div class="span-20 last">
		
			<cms:pageSlot position="Section2" var="feature">
				<cms:component component="${feature}" element="div" class="section2 cms_disp-img_slot"/>
			</cms:pageSlot>
		
			<div class="span-16">
				<nav:pagination top="true"  supportShowPaged="false" supportShowAll="false" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}"/>
			</div>
			<div class="span-20">
				<cms:pageSlot position="Section3" var="feature">
					<cms:component component="${feature}" element="div" class="span-6 section3 small_detail"/>
				</cms:pageSlot>

				<c:url value="${searchPageData.categoryCode}" var="currentURL"/>
				<div id="currentPath" data-current-path="${currentURL}"></div>
			
				<div class="span-16">
					<div id="resultsList" data-isOrderForm="false">
						<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
							<product:productListerItem product="${product}"/>
						</c:forEach>
					</div>
					
					<common:infiniteScroll/>
				</div>
				
				<cms:pageSlot position="Section4" var="feature">
					<cms:component component="${feature}" element="div" class="span-4 section4 cms_disp-img_slot last"/>
				</cms:pageSlot>
				
			</div>
			<div class="span-16">
				<nav:pagination top="false" supportShowPaged="false" supportShowAll="false" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}"/>
			</div>
		</div>
	</div> --%>
	</jsp:body>
</template:page>
