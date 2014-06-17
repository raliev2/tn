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

<template:page pageTitle="${currentStep.name}">
    <section class="g-main-content checkout">
        <div id="globalMessages">
            <common:globalMessages />
        </div>
        <div class="checkout__steps clearfix">
            <ul class="checkou-steps__list clearfix g-float-left">
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Адрес доставки</a></li>
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Способ доставки</a></li>
                <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Способ оплаты</a></li>
                <li class="checkout__step checkout__step_current"><a href="javascript:void(0)">Проверка заказа</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Готово</a></li>
            </ul>
        </div>
        <h1 class="checkout__head"><spring:theme code="${currentStep.name}"/></h1>
        <p>После нажатия на кнопку “Отправить заказ”, мы вышлем Вам письмо с полной информацией о текущем заказе.</p>
        <p style="margin:10px 0 20px 0"><span class="g-strong">Появились вопросы?</span> Задайте их оператору по номеру <span class="g-strong"><spring:theme code="common.telephone" /></span>.</p>
        <c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
        <form method="post" action="${next_url}">
            <div class="checkout__wrapper clearfix">
                <c:url value="/cart" var="cartUrl"/>
                <h3>Товары в корзине <a href="${cartUrl}" class="g-link-blue checkout__change-cart">Изменить</a></h3>
                <div class="checkout-summary__cart">
                    <table class="checkout-summary-cart__table">
                        <thead>
                        <tr>
                            <td class="checkout-summary-cart__product">Товар</td>
                            <td class="checkout-summary-cart__amount">Количество</td>
                            <td class="checkout-summary-cart__stock">Наличие</td>
                            <td class="checkout-summary-cart__price">Цена</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cartData.entries}" var="entry">
                            <c:url value="${entry.product.url}" var="productUrl"/>
                            <tr class="checkout-summary-cart__tr">
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
                                <td class="checkout-summary-cart__stock">
                                    На складе
                                </td>
                                <td  class="checkout-summary-cart__price">
                                    <format:fromPrice priceData="${entry.totalPrice}" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <input type="submit" value="Далее" class="button button_big g-float-right" />
                <c:url value="/checkout/multi${currentStep.previous.url}" var="prev_url" />
                <div class="g-float-right checkout__back"><a href="${prev_url}" class="g-link-blue">Назад</a></div>
            </div>
        </form>
    </section>
</template:page>
