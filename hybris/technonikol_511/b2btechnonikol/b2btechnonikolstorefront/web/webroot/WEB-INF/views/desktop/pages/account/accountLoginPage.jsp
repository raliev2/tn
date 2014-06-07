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
    <jsp:attribute name="pageScripts">
		<user:userLoginJavascript/>
	</jsp:attribute>

    <jsp:body>
        <cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
            <cms:component component="${feature}"/>
        </cms:pageSlot>

        <section class="g-main-content">
            <cms:pageSlot position="TopContent" var="feature" element="div" class="span-20 top-content-slot cms_disp-img_slot last">
                <cms:component component="${feature}"/>
            </cms:pageSlot>

            <div class="login-panel__title">
                <h1><spring:theme code="login.title"/></h1>
            </div>

            <div class="globalMessages">
                <common:globalMessages/>
                <c:if test="${not empty accErrorMsgs}">
                    <div class="help-message">Если Вам не удается пройти авторизацию, вы можете обратиться в контактный центр по номеру 88003330020 24 часа в сутки, 7 дней в неделю</div>
                </c:if>
            </div>
            <div class="clearfix">
                <div class="auth__item">
                    <c:url value="/j_spring_security_check" var="loginActionUrl"/>
                    <user:login actionNameKey="login.login" action="${loginActionUrl}"/>
                </div>
                <div class="auth__item auth__item_send-request">
                    <h3><spring:theme code="login.send.request"/></h3>
                    <p class="g-strong">
                        Станьте пользователем системы 1Платформа и Вы получите:
                    </p>
                    <p>
                        Уникальный выбор товаров<br />
                        Круглосуточное клиентское обслуживание<br />
                        Доставку в любую точку России<br />
                        Удобную систему заказов.
                    </p>
                    <c:url value="/register" var="registerActionUrl"/>
                    <a href="${registerActionUrl}" class="button">Отправить запрос</a>
                </div>
            </div>

            <div class="span-20 right last">
                <cms:pageSlot position="MerchantContactContent" var="feature" element="div" class="span-10 login-info">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
            </div>
        </section>
    </jsp:body>
</template:page>
