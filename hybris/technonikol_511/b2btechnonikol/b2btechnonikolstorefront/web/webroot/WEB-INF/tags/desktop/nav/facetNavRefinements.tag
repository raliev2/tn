<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<c:if test="${not empty pageData.facets}">
	<c:forEach items="${pageData.facets}" var="facet" varStatus="varStatus">
		<c:choose>
			<c:when test="${facet.code eq 'availableInStores'}">
				<nav:facetNavRefinementStoresFacet facetData="${facet}" userLocation="${userLocation}"/>
			</c:when>
			<c:otherwise>
				<nav:facetNavRefinementFacet facetData="${facet}" facetIndex="${varStatus.index}" />
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>
