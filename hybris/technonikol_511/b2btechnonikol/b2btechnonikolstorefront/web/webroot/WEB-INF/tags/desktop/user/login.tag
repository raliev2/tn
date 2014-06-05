<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<div class="login-panel">
    <h3><spring:theme code="login.enter"/></h3>
	<div class="login-panel__body">
		<form:form action="${action}" method="post" commandName="loginForm">
			<c:if test="${not empty message}">
				<div class="login-errors">
					<spring:theme code="${message}"/>
				</div>
			</c:if>
            <c:if test="${not empty accErrorMsgs}">
                <span class="form_field_error">
            </c:if>

			<formUtil:formInputBox idKey="j_username" labelKey="login.email" path="j_username" inputCSS="g-input" size="30" />
			<formUtil:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="g-input" size="30" />
            <div class="capslockIsOn">Включена клавиша CAPS LOCK</div>

            <div class="login-panel__language g-hidden">
                Язык ввода - <span class="lang">Русский</span>
            </div>

            <div class="login-links">
                <a href="<c:url value='/login/pw/request'/>" class="g-link-blue">
                    <spring:theme code="login.link.forgottenPwd"/>
                </a>
            </div>

            <div class="remember-me">
                <input type="checkbox" name="rememberMe" id="rememberMe" />
                <label for="rememberMe">Запомнить меня</label>
            </div>

            <c:if test="${not empty accErrorMsgs}">
                </span>
            </c:if>

			<ycommerce:testId code="login_Login_button">
				<button type="submit" class="button"><spring:theme code="${actionNameKey}"/></button>
			</ycommerce:testId>

		</form:form>
	</div>
</div>
