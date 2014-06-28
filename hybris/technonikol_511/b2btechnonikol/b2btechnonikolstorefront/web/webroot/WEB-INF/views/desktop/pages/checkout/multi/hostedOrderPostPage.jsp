<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<template:page pageTitle="Заказ принят">
    <section class="g-main-content checkout">
        <div id="globalMessages">
            <common:globalMessages />
        </div>
        <div class="checkout__steps clearfix">
            <ul class="checkou-steps__list clearfix g-float-left">
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Адрес доставки</a></li>
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Способ доставки</a></li>
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Способ оплаты</a></li>
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Проверка заказа</a></li>
                <li class="checkout__step checkout__step_current"><a href="javascript:void(0)">Готово</a></li>
            </ul>
        </div>
        <h1 class="checkout__head">
            Ваш заказ принят. Номер заказа ${orderData.code}
        </h1>
        <p class="margin-bottom-5px">Письмо с подтверждением заказа отправлено на адрес <span class="g-strong">${email}</span></p>
        <p style="margin-bottom:20px"><span class="g-strong">Появились вопросы?</span> Задайте их оператору по номеру <span class="g-strong"><spring:theme code="common.telephone" /></span>.</p>
        <p class="g-float-left margin-bottom-5px">Товары в заказе</p>
        <c:url value="/" var="homeUrl"/>
        <a href="${homeUrl}" class="g-link-blue g-float-right checkout__top-link" style="font-size:11px;line-height:22px;">Вернуться на главную страницу</a>
        <a href="javascript:void(0)" class="g-button-black g-float-right checkout__top-link" style="display: none;">Распечатать счет</a>
        <a href="javascript:void(0)" class="g-button-black g-float-right checkout__top-link" style="display: none;">Отправить счет на e-mail</a>
        <div class="clearfix"></div>
        <div class="checkout-summary__cart">
            <table class="checkout-summary-cart__table">
                <thead>
                <tr>
                    <td class="checkout-summary-cart__product" style="width:400px">Товар</td>
                    <td class="checkout-summary-cart__amount" style="width:160px">Количество</td>
                    <td class="checkout-summary-cart__stock" style="width:200px">Наличие</td>
                    <td class="checkout-summary-cart__price">Цена с НДС</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${entries}" var="entry">
                    <c:url value="${entry.product.url}" var="productUrl"/>
                    <tr class="checkout-summary-cart__tr js-cart-entry" data-quantity="${entry.quantity}" data-id="${entry.product.code}">
                        <td class="checkout-summary-cart__product clearfix">
                            <div class="cart-table__product-image">
                                <a href="${productUrl}">
                                    <product:productPrimaryImage product="${entry.product}" format="thumbnail"/>
                                </a>
                            </div>
                            <div class="cart-table__product-info">
                                <ycommerce:testId code="cart_product_name">
                                    <a href="${productUrl}" class="g-strong g-link-blue">${entry.product.name}</a>
                                </ycommerce:testId>
                                <p>Артикул # ${entry.product.manufacturerCode}</p>
                                <p>Ваша цена: <format:fromPrice priceData="${entry.basePrice}" /></p>
                            </div>
                        </td>
                        <td class="checkout-summary-cart__amount">
                                ${entry.quantity}
                        </td>
                        <td class="checkout-summary-cart__stock js-entry-stock">
                        	<div id="check-loading"></div>
                        </td>
                        <td  class="checkout-summary-cart__price">
                            <format:fromPrice priceData="${entry.totalPrice}" />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="checkout-summary-cart__text">
                Некоторые позиции не могу быть приобретены частными лицами, не зарегистрированными на сайте
                "1Платформа". Если Вы не зарегистрированы, как организация или Индивидуальный предприниматель,
                или не авторизовались, прежде чем оформить заказ, ваш заказ будет отправлен на утверждение.
                Сайт "1Платформа" оставляет за собой право отказать в оформлении такого заказа. Обратитесь
                в Кол-Центр по номеру <spring:theme code="common.telephone" />  для получения подробной информации.
            </div>
        </div>
    </section>
</template:page>
