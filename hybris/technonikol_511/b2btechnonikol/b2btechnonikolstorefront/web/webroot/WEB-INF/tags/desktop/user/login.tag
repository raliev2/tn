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

<div class="login-panel">
	<div class="login-panel__title">
		<h1><spring:theme code="login.title"/></h1>
	</div>

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

			<formUtil:formInputBox idKey="j_username" labelKey="login.username" path="j_username" inputCSS="g-input" size="30" />
			<formUtil:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="g-input" size="30" />
            <div class="capslockIsOn">Включена клавиша CAPS LOCK</div>

            <div class="login-panel__language g-hidden">
                Язык ввода - <span class="lang">Русский</span>
            </div>

			<%--<a href="javascript:void(0)" data-url="<c:url value='/login/pw/request'/>" class="password-forgotten"><spring:theme code="login.link.forgottenPwd"/></a>--%>


            <c:if test="${not empty accErrorMsgs}">
                </span>
            </c:if>

			<ycommerce:testId code="login_Login_button">
				<button type="submit" class="g-button-black"><spring:theme code="${actionNameKey}"/></button>
			</ycommerce:testId>

		</form:form>
	</div>
</div>
