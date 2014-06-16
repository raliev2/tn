<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<spring:theme text="Your Shopping Cart" var="title" code="cart.page.title"/>
<c:url value="/checkout" var="checkoutUrl"/>
<template:page pageTitle="${pageTitle}">
	<spring:theme code="basket.add.to.cart" var="basketAddToCart"/>
	<spring:theme code="cart.page.checkout" var="checkoutText"/>

    <section class="g-main-content clearfix">
        <div id="globalMessages">
            <common:globalMessages/>
            <cart:cartRestoration/>
            <cart:cartValidation/>
        </div>

        <div class="cart-left">
            <c:url value="${continueUrl}" var="continueShoppingUrl"/>
            <c:if test="${not empty cartData.entries}">
                <c:if test="${not empty message}">
                    <span class="errors">
                        <spring:theme code="${message}"/>
                    </span>
                </c:if>
                <div class="clearfix">
                    <cart:continueShopping continueShoppingUrl="${continueShoppingUrl}" />
                    <div class="cart__total g-float-right">
                        <c:set value="0" var="amount" />
                        <c:forEach items="${cartData.entries}" var="entry">
                            <c:set value="${amount}" var="old_amount" />
                            <c:set value="${old_amount + entry.quantity}" var="amount" />
                        </c:forEach>
                        <spring:theme code="basket.page.total" />: <product:productAmount amount="${amount}" />
                        <cart:checkoutButton checkoutUrl="${checkoutUrl}" basketAddToCart="${basketAddToCart}" />
                    </div>
                </div>
                <cart:cartItems cartData="${cartData}"/>
                <div class="cart__text">
                    Некоторые позиции не могу быть приобретены частными лицами, не зарегистрированными на сайте «1Платформа». Если Вы не зарегистрированы, как организация или Индивидуальный предприниматель, или не авторизовались, прежде чем оформить заказ, ваш заказ будет отправлен на утверждение. Сайт «1Платформа» оставляет за собой право отказать в оформлении такого заказа. Обратитесь в кол-центр по номеру <span class="g-strong"><spring:theme code="common.telephone" /></span>  для получения подробной информации.
                </div>

                <div class="g-float-right">
                    <cart:cartTotals cartData="${cartData}"/>
                    <cart:continueShopping continueShoppingUrl="${continueShoppingUrl}" />
                    <cart:checkoutButton checkoutUrl="${checkoutUrl}" basketAddToCart="${basketAddToCart}" />
                </div>
            </c:if>
            <c:if test="${empty cartData.entries}">
                <h2><spring:theme code="basket.page.empty" /></h2>
                <cart:continueShopping continueShoppingUrl="${continueShoppingUrl}" />
            </c:if>
        </div>
        <c:if test="${not empty cartData.entries}">
            <aside class="cart__promotions">
                <cart:cartPromotions cartData="${cartData}"/>
                <cart:cartPotentialPromotions cartData="${cartData}"/>
            </aside>
        </c:if>
        <div class="clearfix"></div>
        <cms:pageSlot position="MiddleContent" var="component" element="div" class="cart-reference">
            <cms:component component="${component}"/>
        </cms:pageSlot>
    </section>

</template:page>
