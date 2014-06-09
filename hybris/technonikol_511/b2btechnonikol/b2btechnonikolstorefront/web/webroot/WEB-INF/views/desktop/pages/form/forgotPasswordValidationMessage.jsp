<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="password" tagdir="/WEB-INF/tags/desktop/password" %>

<template:page pageTitle="${pageTitle}">
    <jsp:body>
        <cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
            <cms:component component="${feature}"/>
        </cms:pageSlot>

        <section class="g-main-content g-main-content_padding40">
            <cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 top-content-slot cms_disp-img_slot last">
                <cms:component component="${feature}"/>
            </cms:pageSlot>

            <div class="login-panel__title">
                <password:forgotPasswordValidation/>
            </div>

            <div class="span-20 right last">
                <cms:pageSlot position="MerchantContactContent" var="feature" element="div" class="span-10 login-info">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
            </div>
        </section>
    </jsp:body>
</template:page>
