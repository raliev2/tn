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

            <div class="globalMessages"><div class="help-message">
                <common:globalMessages/>
                <c:if test="${not empty accErrorMsgs}">
                    <div class="help-message">Если Вам нужна помощь - обращайтесь по телефону <spring:theme code="common.telephone"/>.</div>
                </c:if>
            </div>

            <c:url value="/register" var="loginActionUrl"/>
            <form:form action="${action}" method="post">
                <label><spring:theme code="login.enter.email"/></label>
                <input type="text" class="g-input" size="30" />
                <input type="submit" value="<spring:theme code="login.password.torecovery"/>" class="g-button-black" />
            </form:form>

            <div class="span-20 right last">
                <cms:pageSlot position="MerchantContactContent" var="feature" element="div" class="span-10 login-info">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
            </div>
        </section>
    </jsp:body>
</template:page>
