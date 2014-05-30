<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
	<cms:pageSlot position="SearchBox" var="component">
		<cms:component component="${component}"/>
	</cms:pageSlot>

<!--
<div class="form-search">
    <form name="search_form" method="get" action="<c:url value="/search"/>">
        <ycommerce:testId code="header_search_input">
            <input id="search" class="form-search__input" type="text" name="text" value="" maxlength="100" placeholder="Введите товар или артикул" data-options="{&quot;autocompleteUrl&quot; : &quot;/store/ru/search/autocomplete/rg_search_box&quot;,&quot;minCharactersBeforeRequest&quot; : &quot;3&quot;,&quot;waitTimeBeforeRequest&quot; : &quot;500&quot;,&quot;displayProductImages&quot; : false}" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true"/>
        </ycommerce:testId>
        <ycommerce:testId code="header_search_button">
            <button type="submit" class="button">Поиск</button>
        </ycommerce:testId>
    </form>
</div>
<div class="siteSearch"></div>
-->
<%--<div class="search-advanced">
	<a href="<c:url value="/search/advanced"/>"><spring:theme code="search.advanced" /></a>
</div>
--%>