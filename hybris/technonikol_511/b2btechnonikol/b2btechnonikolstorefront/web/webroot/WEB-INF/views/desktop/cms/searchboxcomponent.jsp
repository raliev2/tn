<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/search/" var="searchUrl"/>
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl"/>

<div class="form-search">
    <form name="search_form" method="get" action="<c:url value="/search"/>">
        <ycommerce:testId code="header_search_input">
            <input id="search" class="form-search__input" type="text" name="text" value="" maxlength="100" placeholder="Введите товар или артикул" data-options="{&quot;autocompleteUrl&quot; : &quot;${autocompleteUrl}&quot;,&quot;minCharactersBeforeRequest&quot; : &quot;3&quot;,&quot;waitTimeBeforeRequest&quot; : &quot;500&quot;,&quot;displayProductImages&quot; : false}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true"/>
        </ycommerce:testId>
        <ycommerce:testId code="header_search_button">
            <button type="submit" class="button">Поиск</button>
        </ycommerce:testId>
    </form>
    <div class="siteSearch"></div>
</div>


<%--

<c:url value="/search/" var="searchUrl"/>
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl"/>


<div class="siteSearch search">
    <form name="search_form" method="get" action="<c:url value="/search"/>">
        <spring:theme code="text.search" var="searchText"/>
        <label class="skip" for="search">${searchText}</label>
        <spring:theme code="search.placeholder" var="searchPlaceholder"/>
        <ycommerce:testId code="header_search_input">
            <input id="search" class="text" type="text" name="text" value="" maxlength="100" placeholder="${searchPlaceholder}" data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
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
            <input class="button" type="image" src="${searchImageUrl}" alt="${searchText}" />
        </ycommerce:testId>
    </form>
</div>
<div class="search-advanced">
    <a href="<c:url value="/search/advanced"/>"><spring:theme code="search.advanced" /></a>
</div>
--%>
<%--<div class="search-advanced">
	<a href="<c:url value="/search/advanced"/>"><spring:theme code="search.advanced" /></a>
</div>--%>