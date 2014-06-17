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
            <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Способ оплаты</a></li>
            <li class="checkout__step checkout__step_notactive"><a href="javascript:void(0)">Проверка заказа</a></li>
            <li class="checkout__step checkout__step_current"><a href="javascript:void(0)">Готово</a></li>
        </ul>
    </div>
    <h1 class="checkout__head"><spring:theme code="${currentStep.name}"/></h1>
    <div class="information-message information-message_negative checkout__error-message">
        <p>Ваш заказ не удалось разместить</p>
        <p style="color:#333;font-weight: normal;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ornare sit amet tellus dignissim eleifend. Cras non consequat enim, nec venenatis dolor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec sed sem ac ipsum interdum rhoncus et id massa. Nunc dolor magna, euismod eget nibh quis, fringilla posuere nisi. Donec eget tellus a justo suscipit mollis ut at massa. Sed non nisi magna. Duis fermentum vitae ipsum eget condimentum. In sem lacus, congue ac velit id, congue viverra lorem. Fusce viverra lacinia dui, at varius ligula porttitor rhoncus. Aenean sed turpis eget sapien auctor venenatis. Pellentesque ipsum sem, egestas eget pellentesque tincidunt, dignissim ultrices ante. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum interdum sodales erat, non cursus dolor dictum eget.</p>
    </div>
    <a class="checkout__callback g-float-left" href="javascript:void(0)">Заказать обратный звонок</a>
    <div class="clearfix"></div>
    <p style="margin-bottom:20px"><span class="g-strong">Появились вопросы?</span> Задайте их оператору по номеру <span class="g-strong"><spring:theme code="common.telephone" /></span>.</p>
    <p class="g-float-left margin-bottom-5px">Товары в заказе</p>
    <c:url value="/" var="homeUrl"/>
    <a href="${homeUrl}" class="g-link-blue g-float-right" style="font-size:11px;">Вернуться на главную страницу</a>
    <div class="clearfix"></div>
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
                    <td class="checkout-summary-cart__stock checkout-cart-content__delivery-td">
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
