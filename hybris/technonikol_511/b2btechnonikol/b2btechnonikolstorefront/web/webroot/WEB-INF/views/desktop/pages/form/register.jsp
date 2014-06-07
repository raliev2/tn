<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
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

            <div class="login-panel__title">
                <h1><spring:theme code="register.title"/></h1>
            </div>

            <div class="register-form">
                <c:url value="/register" var="loginActionUrl"/>
                <user:registerForm actionNameKey="registrationRequestForm.button.action" action="${registerActionUrl}"/>
            </div>
            <aside class="register__benefits">
                <div class="benefits__header">
                    Преимущества регистрации
                </div>
                <div class="benefits__body">
                    <ul class="benefits-list">
                        <li>Отслеживание заказов</li>
                        <li>Заказывайте быстрее</li>
                        <li>Сохраняйте любимые товары</li>
                        <li>Управляемый аккаунт</li>
                    </ul>
                </div>
            </aside>
            <div class="clearfix"></div>

            <div class="span-20 right last">
                <cms:pageSlot position="MerchantContactContent" var="feature" element="div" class="span-10 login-info">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
            </div>
        </section>
    </jsp:body>
</template:page>
