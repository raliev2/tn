<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/desktop/grid" %>


<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<product:productDetailsJavascript/>
		<script type="text/javascript" src="${commonResourcePath}/js/acc.productlisting.js"></script>
	</jsp:attribute>
	
	<jsp:body>
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
		</div>
		<c:if test="${not empty message}">
			<spring:theme code="${message}" />
		</c:if>
		<div id="globalMessages">
			<common:globalMessages />
		</div>
		<div class="span-24">
			<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
				<cms:component component="${feature}" />
			</cms:pageSlot>
			<div class="span-20 right last advanced_search">
				<div class="item_container_holder">
					<div class="title_holder">
						<div class="title">
							<div class="title-top">
								<span></span>
							</div>
						</div>
						<h2>
							<spring:theme code="search.advanced" text="Advanced Search" />
						</h2>
					</div>
					<div id="form_container">
						<c:url value="/search/advanced" var="advancedSearchUrl"/>
						<form:form action="${advancedSearchUrl}" method="get" name="advancedSearchForm" commandName="advancedSearchForm">
							<div class="advanced_search_area">
								<div class="search_input">
									<div class="search_option_box">
										<formUtil:formCheckbox idKey="js-enable-product-ids" labelKey="search.advanced.onlyproductids" path="onlyProductIds" inputCSS="advanced-onlyProductIds" labelCSS="" mandatory="false" />
									</div>
									<div class="search_text_box">
										<formUtil:formInputBox idKey="js-product-ids" labelKey="search.advanced.keyword" path="keywords" inputCSS="" mandatory="false" />
										<button id="js-add-product-ids" class="form hidden" type="submit">
											<spring:theme code="search.advanced.productids.add" text="Enter" />
										</button>
									</div>

								</div>
							</div>
							<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
								<div class="adv_search_result_area">
									<formUtil:formRadioBox idKey="search-order-form" labelKey="search.advanced.orderform" value="order-form" path="searchResultType" />
									<formUtil:formRadioBox idKey="search-create-order-form" labelKey="search.advanced.createorderform" value="create-order-form" path="searchResultType" />
									<formUtil:formRadioBox idKey="search-catalog" labelKey="search.advanced.catalog" value="catalog" path="searchResultType" />
								</div>
							</sec:authorize>
							<c:set var="isCreateOrderForm" value="${form.createOrderForm}" />
							<c:if test="${empty isCreateOrderForm }">
								<c:set var="isCreateOrderForm" value="false" />
							</c:if>

							<input type="hidden" name="skus" id="skus" value=""/>
							<input type="hidden" name="isCreateOrderForm" id="isCreateOrderForm" value="${isCreateOrderForm}"/>
							<div class="adv_search_button_area">
							    <%-- please leave the instock checkbox it will come in the next sprint, just hiding for now --%>
								<%-- formUtil:formCheckbox idKey="advanced-exact" labelKey="search.advanced.inventory" path="inStockOnly" inputCSS="" labelCSS="" mandatory="false" /> --%>
								<button class="form adv_search_button" type="submit">
									<spring:theme code="search.advanced.search" text="Search" />
								</button>
							</div>
						</form:form>
					</div>
					<div id="js-selected-product-ids" class="selected_product_ids"></div>
				</div>
				
				<c:url value="/search" var="currentURL"/>
				<div id="currentPath" data-current-path="${currentURL}"></div>
			
				<c:if test="${advancedSearchForm.orderFormSearchResultType}">
					<div class="span-8 last orderFormTotal">
						<product:productFormAddToCartPanel product="${product}" showViewDetails="false"/>
					</div>
				</c:if>
				

				<c:if test="${not empty advancedSearchForm.keywords}">
				
						<nav:pagination top="true"  supportShowPaged="false"
													supportShowAll="false"
													searchPageData="${searchPageData}"
													searchUrl="${searchPageData.currentQuery.url}"
													numberPagesShown="${numberPagesShown}"
													searchResultType="${advancedSearchForm.searchResultType}"/>
				</c:if>

				<c:if test="${false}">
					<div class="span-4">
						<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
						<nav:facetNavRefinements pageData="${searchPageData}"/>

						<cms:pageSlot position="Section5" var="feature">
							<cms:component component="${feature}" element="div" class="section5 cms_disp-img_slot"/>
						</cms:pageSlot>
					</div>
				</c:if>

				<c:set var="skuIndex" scope="session" value="0" />
				<spring:theme code="product.grid.confirmQtys.message" var="gridConfirmMessage" />

				<c:if test="${advancedSearchForm.catalogSearchResultType}">
					<div id="resultsList"  data-isOrderForm="false" data-isOnlyProductIds="${advancedSearchForm.onlyProductIds}">
						<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
							<product:productListerItem product="${product}" />
						</c:forEach>
					</div>
				</c:if>

				<c:if test="${advancedSearchForm.orderFormSearchResultType}">
					
					<grid:gridLegend />
					
					<form name="AddToCartOrderForm" id="AddToCartOrderForm" class="add_to_cart_order_form" action="<c:url value="/cart/addGrid"/>" method="post" data-grid-confirm-message="${gridConfirmMessage}">
						<div id="resultsList"  data-isOrderForm="true" data-isOnlyProductIds="${advancedSearchForm.onlyProductIds}">
							<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
								<c:choose>
									<c:when test="${not empty product.variantMatrix}">
										<product:productListerOrderForm product="${product}" filterSkus="${advancedSearchForm.filterSkus}" />
									</c:when>
									<c:otherwise>
										<product:productListerItem product="${product}" isOrderForm="true" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div> 
					</form>
					<div id="skuIndexSavedValue" name="skuIndexSavedValue" data-sku-index="${sessionScope.skuIndex}"><!--  don't remove this div. This is used by the order form search --></div>
				</c:if>
				<c:if test="${advancedSearchForm.createOrderFormSearchResultType}">
					<form name="createOrderForm" id="createOrderForm" class="create-order-form" data-grid-confirm-message="${gridConfirmMessage}">
						<input id="js-create-order-form-button" type="button" value="<spring:theme code='search.advanced.createorderform' />"> 

						<div id="resultsList"  data-isOrderForm="false" data-isOnlyProductIds="false">
							<c:forEach items="${searchPageData.results}" var="product" varStatus="status">
								<product:productFilterOrderForm product="${product}" />
							</c:forEach>
						</div>
					</form>
				</c:if>

				<common:infiniteScroll/>
				
				<script id="product-id-tag-box-template" type="text/x-jquery-tmpl">
				<span class="product-id-tag-box" id="product-id-\${productId}-tag">
				  <span>\${productId}</span>
				  <button class="js-remove-product-id form" type="submit">x</button>
				</span>
				</script>

				<product:productOrderFormJQueryTemplates />
				
				<cms:pageSlot position="BottomContent" var="comp" element="div" class="span-20 cms_disp-img_slot right last">
					<cms:component component="${comp}" />
				</cms:pageSlot>
			</div>
		</div>
		<input id="searchByKeywordLabel" type="hidden" value='<spring:theme code="search.advanced.keyword"/>' />
		<input id="searchByIdsLabel" type="hidden" value='<spring:theme code="search.advanced.productids"/>' />
		<c:remove var="skuIndex" scope="session" />
	</jsp:body>
</template:page>
