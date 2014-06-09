<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:page pageTitle="${pageTitle}">
    <jsp:body>
        <cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
            <cms:component component="${feature}"/>
        </cms:pageSlot>

        <section class="g-main-content g-main-content_padding40">
            <cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 top-content-slot cms_disp-img_slot last">
                <cms:component component="${feature}"/>
            </cms:pageSlot>

            <h1><spring:theme code="login.password.recovery"/></h1>
            <form:form action="${action}" method="post" commandName="forgottenPwdForm">
                <c:if test="${not empty message}">
                    <spring:theme code="${message}"/>
                </c:if>
                <formUtil:formInputBox idKey="email" labelKey="forgottenPwdForm.email" path="email" mandatory="true" inputCSS="g-input" size="45"/>
            </form:form>

            <div class="span-20 right last">
                <cms:pageSlot position="MerchantContactContent" var="feature" element="div" class="span-10 login-info">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
            </div>
        </section>
    </jsp:body>
</template:page>
