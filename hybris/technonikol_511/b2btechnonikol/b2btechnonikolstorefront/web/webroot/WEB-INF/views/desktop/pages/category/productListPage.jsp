<%@ page trimDirectiveWhitespaces="true" %>
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
	<div id="breadcrumb" class="breadcrumb">
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
	</div>
	</jsp:body>
</template:page>
