<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>

<template:page pageTitle="${currentStep.name}">

<section class="g-main-content checkout">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div class="checkout__steps clearfix">
        <ul class="checkou-steps__list clearfix g-float-left">
            <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Адрес доставки</a></li>
            <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Способ доставки</a></li>
            <li class="checkout__step checkout__step_current"><a href="javascript:void(0)">Способ оплаты</a></li>
            <li class="checkout__step"><a href="javascript:void(0)">Проверка заказа</a></li>
            <li class="checkout__step"><a href="javascript:void(0)">Готово</a></li>
        </ul>
    </div>
    <h1 class="checkout__head"><spring:theme code="${currentStep.name}"/></h1>
    <p style="margin-bottom:20px"><span class="g-strong">Появились вопросы?</span> Задайте их оператору по номеру <span class="g-strong"><spring:theme code="common.telephone" /></span>.</p>
    <c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
    <form method="get" action="${next_url}">
        <div class="checkout__wrapper clearfix">
            <div class="checkout__body">
                <p class="g-strong">Способ оплаты</p>

                <input type="radio" name="selectedPaymentMethod" value="${method.code}" id="selectedPaymentMethod${method.code}" />
                <label for="selectedPaymentMethod${method.code}" style="margin-right:30px;">Предоплата</label>

                <input type="radio" name="selectedPaymentMethod" value="${method.code}" id="selectedPaymentMethod${method.code}" />
                <label for="selectedPaymentMethod${method.code}">Отсрочка платежа</label>

                <c:forEach items="${paymentMethods}" var="method">
                    <input type="radio" name="selectedPaymentMethod" value="${method.code}">${method.name}
                </c:forEach>
            </div>

            <input type="submit" value="Далее" class="button button_big g-float-right" />
            <div class="g-float-right checkout__back"><a href="javascript:history.go(-1)" class="g-link-blue">Назад</a></div>
        </div>
    </form>
</section>
</template:page>
