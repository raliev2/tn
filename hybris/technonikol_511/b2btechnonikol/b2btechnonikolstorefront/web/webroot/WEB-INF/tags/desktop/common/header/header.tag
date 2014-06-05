<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<c:url value="/" var="homeUrl"/>
<header class="clearfix">
    <div class="logo">
        <a href="${homeUrl}"><img src="${themeResourcePath}/images/logo.png" alt="ТЕХНОНИКОЛЬ" title="ТЕХНОНИКОЛЬ" /></a>
        Для тех, кто делает дело
    </div>
    <div class="g-float-right">
        <div class="auth_links">
            <ul class="nav">
				<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
					<li class="hello"><ycommerce:testId code="header_LoggedUser"><spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" htmlEscape="true"/></ycommerce:testId></li>
				</sec:authorize>
				<sec:authorize ifNotGranted="ROLE_CUSTOMERGROUP">
					<li><ycommerce:testId code="header_Login_link"><a href="<c:url value='/login'/>"><spring:theme code="header.link.login"/></a></ycommerce:testId></li>
				</sec:authorize>
				<li><ycommerce:testId code="header_myAccount"><a href="<c:url value='/my-account'/>"><spring:theme code="header.link.account"/></a></ycommerce:testId></li>
				<sec:authorize ifAnyGranted="ROLE_B2BADMINGROUP">
					<li><ycommerce:testId code="header_myCompany"><a href="<c:url value='/my-company/organization-management'/>"><spring:theme code="header.link.company"/></a></ycommerce:testId></li>
				</sec:authorize>
				<sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
					<li><ycommerce:testId code="header_signOut"><a href="<c:url value='/logout'/>"><spring:theme code="header.link.logout"/></a></ycommerce:testId></li>
				</sec:authorize>
			</ul>
        </div>
        <div class="custom-message">
            <span class="header__telephone"><spring:theme code="common.telephone" /></span>
        </div>
        <cms:pageSlot position="MiniCart" var="cart" limit="1">
            <cms:component component="${cart}"/>
        </cms:pageSlot>
    </div>

<%--	<cms:pageSlot position="MiniCart" var="cart" limit="1">
		<cms:component component="${cart}"/>
	</cms:pageSlot> --%>

</header>
