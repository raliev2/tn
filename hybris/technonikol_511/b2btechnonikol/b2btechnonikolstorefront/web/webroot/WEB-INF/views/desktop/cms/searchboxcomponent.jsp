<%@ page pageEncoding="UTF-8" %>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/search/" var="searchUrl"/>
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl"/>


<div class="siteSearch search">
   <div class="form search">
	<form name="search_form" method="get" action="<c:url value="/search"/>">
		<label class="skip" for="search">${searchText}</label>
		<ycommerce:testId code="header_search_input">
			<input id="search" class="form-search__input" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
			<br>
			<small><font color=white><b>(поиск еще не сверстан. сегодня вечером будет)</b></font>
		</ycommerce:testId>
		<ycommerce:testId code="header_search_button">
			<spring:theme code="img.searchButton" text="/" var="searchButtonPath"/>
			<c:choose>
				<c:when test="${originalContextPath ne null}">
					<c:url value="${searchButtonPath}" context="${originalContextPath}" var="searchImageUrl"/>
				</c:when>
				<c:otherwise>
					<c:url value="${searchButtonPath}" var="searchImageUrl"/>
				</c:otherwise>
			</c:choose>
			<button type="submit" class="button">Search</button>
		</ycommerce:testId>
	</form>
    </form>
</div>
<div class="siteSearch"></div>
<div class="search-advanced">
<!--	<a href="<c:url value="/search/advanced"/>"><spring:theme code="search.advanced" /></a>-->
</div>