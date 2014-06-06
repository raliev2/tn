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
    <formUtil:formInputBox idKey="firstName" labelKey="registrationRequestForm.firstName" path="firstName" mandatory="true"/>
    <formUtil:formInputBox idKey="lastName" labelKey="registrationRequestForm.lastName" path="lastName" mandatory="true"/>
    <formUtil:formInputBox idKey="surName" labelKey="registrationRequestForm.surName" path="surName" mandatory="true"/>

    <formUtil:formInputBox idKey="company" labelKey="registrationRequestForm.company" path="company" mandatory="true"/>
    <formUtil:formInputBox idKey="inn" labelKey="registrationRequestForm.inn" path="inn" mandatory="true"/>
    <formUtil:formInputBox idKey="phone" labelKey="registrationRequestForm.phone" path="phone" mandatory="true"/>
    <formUtil:formInputBox idKey="email" labelKey="registrationRequestForm.email" path="email" mandatory="true"/>
    <formUtil:formCheckbox idKey="subscribedToSmsAndEmailNotification" labelKey="registrationRequestForm.subscribedToSmsAndEmailNotification" path="subscribedToSmsAndEmailNotification"/>

    <button type="submit"><spring:theme code="${actionNameKey}"/></button>
</form:form>