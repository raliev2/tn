<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="${action}" method="post" commandName="registrationRequestForm">
    <c:if test="${not empty message}">
        <spring:theme code="${message}"/>
    </c:if>
    <formUtil:formInputBox idKey="firstName" labelKey="registrationRequestForm.firstName" path="firstName" mandatory="true" inputCSS="g-input" size="45"/>
    <formUtil:formInputBox idKey="lastName" labelKey="registrationRequestForm.lastName" path="lastName" mandatory="true" inputCSS="g-input" size="45"/>
    <formUtil:formInputBox idKey="surName" labelKey="registrationRequestForm.surName" path="surName" mandatory="true" inputCSS="g-input" size="45"/>

    <formUtil:formInputBox idKey="company" labelKey="registrationRequestForm.company" path="company" mandatory="true" inputCSS="g-input" size="45"/>
    <formUtil:formInputBox idKey="inn" labelKey="registrationRequestForm.inn" path="inn" mandatory="true" inputCSS="g-input" size="45"/>

    <formUtil:formInputBox idKey="kpp" labelKey="registrationRequestForm.kpp" path="kpp" mandatory="true" inputCSS="g-input" size="45"/>

    <div class="g-float-left" style="margin-right:17px;">
        <formUtil:formInputBox idKey="phone" labelKey="registrationRequestForm.phone" path="phone" mandatory="true" inputCSS="g-input" size="18"/>
    </div>
    <div class="g-float-left">
        <formUtil:formInputBox idKey="email" labelKey="registrationRequestForm.email" path="email" mandatory="true" inputCSS="g-input" size="18"/>
    </div>
    <div class="clearfix"></div>

    <div class="register-form__subscribe">
        <formUtil:formCheckbox idKey="subscribedToSmsAndEmailNotification" labelKey="registrationRequestForm.subscribedToSmsAndEmailNotification" path="subscribedToSmsAndEmailNotification" checked="true" />
    </div>
    <input type="submit" class="g-button-black g-button-black_high" value="<spring:theme code='${actionNameKey}'/>" />

    <p class="g-p">* Отмечены поля, обязательные для заполнения</p>
</form:form>