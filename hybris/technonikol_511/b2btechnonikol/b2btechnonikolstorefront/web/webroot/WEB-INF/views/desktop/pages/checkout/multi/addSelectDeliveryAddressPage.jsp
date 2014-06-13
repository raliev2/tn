<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="multi-checkout"
	tagdir="/WEB-INF/tags/desktop/checkout/multi"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formUtil" tagdir="/WEB-INF/tags/desktop/form"%>

<template:page pageTitle="${currentStep.name}">
<section class="g-main-content checkout">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div class="checkout__steps clearfix">
        <ul class="checkou-steps__list clearfix g-float-left">
            <li class="checkout__step checkout__step_current"><a href="javascript:void(0)">Адрес доставки</a></li>
            <li class="checkout__step"><a href="javascript:void(0)">Способ доставки</a></li>
            <li class="checkout__step"><a href="javascript:void(0)">Способ оплаты</a></li>
            <li class="checkout__step"><a href="javascript:void(0)">Проверка заказа</a></li>
            <li class="checkout__step"><a href="javascript:void(0)">Готово</a></li>
        </ul>
    </div>
    <h1 class="checkout__head"><spring:theme code="${currentStep.name}"/></h1>
    <p class="g-strong">Клиентское обслуживание в любое время суток</p>
    <p style="margin-bottom:20px">По вопросам и проблемам с доставкой - звонить на номер <spring:theme code="common.telephone" /></p>
    <c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
    <form:form method="post" commandName="addressForm" action="${next_url}">
        <div class="checkout__wrapper clearfix">
            <c:if test="${not empty deliveryAddresses}">
                <div class="checkout__body">
                    <div class="checkout__row">
                        <label for="selectedCostCenter" class="checkout__label">Выберите адрес доставки</label>
                        <select name="selectedDeliveryAddress" class="checkout__select">
                            <c:forEach items="${deliveryAddresses}" var="address">
                                <option value="${address.id}">${address.town} - ${address.line1}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <p class="g-strong margin15px">или добавьте новый</p>
            </c:if>
            <div class="checkout__white-form">
                <form:hidden path="addressId" class="create_update_address_id" />
                <formUtil:formInputBox labelKey="Имя" idKey="address.firstName" path="firstName" inputCSS="checkout-input long-input" mandatory="false" />
                <formUtil:formInputBox labelKey="Фамилия" idKey="address.lastName" path="lastName" inputCSS="checkout-input long-input" mandatory="false" />
                <formUtil:formInputBox labelKey="Телефон" idKey="address.phone" path="phone" inputCSS="checkout-input long-input" mandatory="false" />
                <formUtil:formInputBox labelKey="Город" idKey="address.townCity" path="townCity" inputCSS="checkout-input long-input" mandatory="false" />
                <div class="g-float-left" style="margin-right:10px;">
                    <formUtil:formInputBox labelKey="Улица" idKey="address.street" path="street" inputCSS="checkout-input short-input1" mandatory="false" />
                </div>
                <formUtil:formInputBox labelKey="Дом, подъезд" idKey="address.house" path="house" inputCSS="checkout-input short-input2" mandatory="false" />
                <formUtil:formInputBox labelKey="Индекс" idKey="address.postcode" path="postcode" inputCSS="checkout-input long-input" mandatory="false" />
                <p class="g-gray-text">Для вашего удобства, компания "1Платформа" организовала службу поддержки.</p>
                <p class="g-gray-text margin-bottom-5px">По вопросам доставки вы можете обратиться в Кол-Центр, по номеру <spring:theme code="common.telephone" />.</p>
                <input type="text" class="checkout-input long-input" name="additionAddress" placeholder="Дополнительный адрес" /><br /><br />
                <input type="checkbox" id="saveAddress" name="saveAddress" /> <label for="saveAddress">Запомнить адрес</label>
            </div>
            <input type="submit" value="Далее" class="button button_big g-float-right" />
            <div class="g-float-right checkout__back"><a href="#" class="g-link-blue">Назад</a></div>
        </div>
    </form:form>
</section>
<%--



	<div class="span-20 right lsast">
		<input type="submit" value="Назад"/>
	</div>
	--%>
</template:page>