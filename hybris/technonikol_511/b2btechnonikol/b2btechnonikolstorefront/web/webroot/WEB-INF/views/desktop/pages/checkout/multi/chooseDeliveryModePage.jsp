<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<template:page pageTitle="${currentStep.name}">
    <section class="g-main-content checkout">
        <div id="globalMessages">
            <common:globalMessages />
        </div>
        <div class="checkout__steps clearfix">
            <ul class="checkou-steps__list clearfix g-float-left">
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Адрес доставки</a></li>
                <li class="checkout__step checkout__step_current"><a href="javascript:void(0)">Способ доставки</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Способ оплаты</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Проверка заказа</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Готово</a></li>
            </ul>
        </div>
        <h1 class="checkout__head"><spring:theme code="${currentStep.name}"/></h1>
        <p style="margin-bottom:20px"><span class="g-strong">Появились вопросы?</span> Задайте их оператору по номеру <span class="g-strong"><spring:theme code="common.telephone" /></span>.</p>
        <c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
        <form method="get" action="${next_url}">
            <div class="checkout__wrapper clearfix">
                <div class="checkout__cart-content">
                    <div class="padding20px checkout__gradient">
                        <table class="checkout-cart-content__table">
                            <thead>
                                <tr>
                                    <td class="checkout-cart-content__product-td">Товар</td>
                                    <td class="checkout-cart-content__delivery-td">Срок доставки</td>
                                    <td class="checkout-cart-content__price-td">Цена</td>
                                </tr>
                            </thead>
                            <c:forEach items="${cartData.entries}" var="entry">
                                <tr class="js-cart-entry" data-id="${entry.product.code}" data-quantity="${entry.quantity}">
                                    <td class="checkout-cart-content__product-td">
                                        <p class="g-strong margin-bottom-5px">${entry.product.name}</p>
                                        <p>Артикул # ${entry.product.code}</p>
                                        <p>Количество: ${entry.quantity}</p>
                                        <p>Стоимость: <format:fromPrice priceData="${entry.basePrice}" /></p>
                                    </td>
                                    <td class="checkout-cart-content__delivery-td js-entry-stock"></td>
                                    <td class="checkout-cart-content__price-td"><format:fromPrice priceData="${entry.totalPrice}" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="padding20px">
                        <div class="checkout__dlivery-cost g-strong">
                            Стоимость доставки: <format:fromPrice priceData="${cartData.deliveryMode.deliveryCost}" />
                        </div>
                    </div>
                    <div class="checkout__delivery-mode">
                        <p class="g-strong">Выберите, как доставить Ваш заказ</p>
                        <c:forEach items="${deliveryModes}" var="mode">
                            <div class="checkout-delivery-mode__item">
                                <input type="radio" name="selectedDeliveryMode" value="${mode.code}" required id="selectedDeliveryMode${mode.code}" ${mode.code == cartData.deliveryGroupMode.code ? 'checked' : ''} />
                                <label for="selectedDeliveryMode${mode.code}"><spring:theme code="${mode.name}"/></label>
                                <p><spring:theme code="${mode.description}"/></p>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="gray-div"></div>
                </div>

                <input type="submit" value="Далее" class="button button_big g-float-right" />
                <c:url value="/checkout/multi/select-address?isPrev=true" var="prev_url" />
                <div class="g-float-right checkout__back"><a href="${prev_url}" class="g-link-blue">Назад</a></div>
            </div>
        </form>
    </section>

</template:page>
