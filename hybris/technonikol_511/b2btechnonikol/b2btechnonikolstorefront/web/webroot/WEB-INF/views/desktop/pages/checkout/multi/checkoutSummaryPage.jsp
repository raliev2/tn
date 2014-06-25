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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<template:page pageTitle="Проверка заказа">
    <script>
        $(document).ready(function() {
            $("#providedDeliveryDate").mask("99/99/9999 99:99", {placeholder: '_' });
            
            $('#js-checkout-summary-form').submit(function(){
                if (!$('#agree').is(':checked')) {
                    $('<p>Пожалуйста, отметьте чекбокс в блоке "Конфеденциальность"</p>').modal();
                    return false;
                }
            });
        });
    </script>
    <section class="g-main-content checkout clearfix">
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
        <form method="post" action="${next_url}" id="js-checkout-summary-form">
            <div class="checkout__wrapper clearfix g-float-left">
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
                <div class="checkout-summary-total__button" style="padding-top:20px">
                   <button type="button" class="button button_big" id="scheduleReplenishmentButton" onclick="$('#replenishment-schedule-div').toggle()">
							Повторить заказ
						 </button>  
					 </div>             
            </div>
            <div class="checkout-summary__total">
                <h4>Итог заказа</h4>
                <c:if test="${not (cartData.deliveryMode.deliveryCost.value > 0)}">
                    <div class="free-shipping">Бесплатная доставка</div>
                </c:if>
                <table class="checkout-summary-total__table">
                    <tr>
                        <td>Сумма заказа</td>
                        <td class="g-align-right"><format:fromPrice priceData="${cartData.subTotal}"/></td>
                    </tr>
                    <tr>
                        <td>В т.ч. налоговые сборы</td>
                        <td class="g-align-right">
                            <c:choose>
                                <c:when test="${cartData.totalTax.value > 0}">
                                    <format:fromPrice priceData="${cartData.totalTax}"/>
                                </c:when>
                                <c:otherwise>
                                    нет
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>Примененные скидки</td>
                        <td class="g-align-right">
                            <c:choose>
                                <c:when test="${cartData.totalDiscounts.value > 0}">
                                    -<format:fromPrice priceData="${cartData.totalDiscounts}"/>
                                </c:when>
                                <c:otherwise>
                                    нет
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>                                        
                    <tr>
                        <td>Стоимость доставки</td>
                        <td class="g-align-right">
                            <c:choose>
                                <c:when test="${cartData.deliveryMode.deliveryCost.value > 0}">
                                    <format:fromPrice priceData="${cartData.deliveryMode.deliveryCost}"/>
                                </c:when>
                                <c:otherwise>
                                    БЕСПЛАТНО
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr class="checkout-summary-total-table__total">
                        <td class="g-strong">Итого</td>
                        <td class="g-align-right">
                            <format:fromPrice priceData="${cartData.totalPrice}"/>
                        </td>
                    </tr>
                </table>
                <p>Наличие товара на складе, его окончательная
                    стоимость и стоимость заказа, будет пересчитана
                    на последнем шаге оформления корзины</p><br/>
                <p>Прогнозируемая дата доставки заказа:</p> <div id="roughOrderDate"></div>                    
                <div class="checkout-summary-total__white-line"></div>
                <div class="checkout-summary-total__button">
                    <input type="submit" value="Подтвердить" class="button button_big" />
                </div>
                <div class="checkout-summary-total__white-line"></div>
                <div>
                    <c:choose>
                        <c:when test="${not empty cartData.deliveryAddress}">
                           <h4 class="g-float-left">Адрес доставки</h4>
                        </c:when>
                        <c:otherwise>
                           <h4 class="g-float-left">Адрес магазина</h4>
                        </c:otherwise>
                    </c:choose>  
                    <c:url value="/checkout/multi/select-address?isPrev=true" var="address_url" />
                    <a  href="${address_url}" class="checkout__change-cart g-float-right g-link-blue">Изменить</a>
                    <div class="clearfix"></div>
                    <div class="checkout-summary-total__value">
                        <c:choose>
                            <c:when test="${not empty cartData.deliveryAddress}">
                                ${cartData.deliveryAddress.town} - ${cartData.deliveryAddress.line1} - ${cartData.deliveryAddress.line2} - ${cartData.deliveryAddress.postalCode}
                            </c:when>
                            <c:otherwise>
                            	 ${cartData.entries.get(0).deliveryPointOfService.address.town} - ${cartData.entries.get(0).deliveryPointOfService.address.line1} - ${cartData.entries.get(0).deliveryPointOfService.address.postalCode}
                            </c:otherwise>
                        </c:choose>                      
                    </div>
                    <h4 class="g-float-left">Получатель</h4>
                    <c:url value="/checkout/multi/select-address?isPrev=true" var="customer_url" />
                    <a  href="${customer_url}" class="checkout__change-cart g-float-right g-link-blue">Изменить</a>
                    <div class="clearfix"></div>
                    <div class="checkout-summary-total__value">
                        ${cartData.b2bCustomerData.lastName}&nbsp;${cartData.b2bCustomerData.firstName}
                    </div>
                    <div class="checkout-summary-total__white-line" style="margin-top:0"></div>

                    <h4 class="g-float-left">Метод доставки</h4>
                    <c:url value="/checkout/multi/select-delivery-method" var="delivery_method_url" />
                    <a  href="${delivery_method_url}" class="checkout__change-cart g-float-right g-link-blue">Изменить</a>
                    <div class="clearfix"></div>
                    <div class="checkout-summary-total__value">
                        <spring:theme code="checkout.delivery.method.${fn:toLowerCase(cartData.deliveryMethod)}" />
                        <c:choose>
                            <c:when test="${cartData.deliveryMode.deliveryCost.value > 0}">
                                (<format:fromPrice priceData="${cartData.deliveryMode.deliveryCost}"/>)
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="checkout-summary-total__white-line" style="margin-top:0"></div>

                    <h4 class="g-float-left">Оплата</h4>
                    <c:url value="/checkout/multi/select-payment-method?isPrev=true" var="payment_method_url" />
                    <a  href="${payment_method_url}" class="checkout__change-cart g-float-right g-link-blue">Изменить</a>
                    <div class="clearfix"></div>
                    <div class="checkout-summary-total__value">
                        <spring:theme code="checkout.delivery.method.${fn:toLowerCase(cartData.paymentMethod)}" />
                    </div>
                    <div class="checkout-summary-total__white-line" style="margin-top:0"></div>

                    <h4>Дополнительная информация</h4>
                    <div><label for="providedDeliveryDate" class="checkout__label">Удобное время доставки</label>
                    <input type="text" placeholder="dd/MM/yyyy HH:mm" name="providedDeliveryDate" id="providedDeliveryDate" class="checkout__input" /></div>

                    <div><label for="providedDescription" class="checkout__label">Комментарий</label>
                    <input type="text" name="providedDescription" id="providedDescription" class="checkout__input" /></div>

                    <div class="checkout-summary-total__white-line" style="margin-top:0"></div>

                    <h4>Email уведомления</h4>
                    <input type="checkbox" name="emailNotification" id="emailNotification" checked />
                    <label for="emailNotification" class="inline-label">Подпишите меня на маркетинговые рассылки
                        от компании 1Платформа, а также на новости
                        компании и отрасли.</label>

                    <div class="checkout-summary-total__white-line"></div>

                    <h4>Конфиденциальность</h4>
                    <input type="checkbox" name="agree" id="agree" checked />
                    <label for="agree" class="inline-label">Я согласен с правилами обработки персональных данных на сайте 1Платформа.</label>
                    <div class="checkout-summary-total__white-line"></div>
                    <div class="checkout-summary-total__button">
                        <input type="submit" value="Подтвердить" class="button button_big" />
                    </div>         
                </div>                
            </div>
        </form>

		 <c:url value="/checkout/multi/schedule-order" var="schedule_order_url" />
       <form:form action="${schedule_order_url}" id="replenishmentForm" commandName="placeOrderForm">
			<cart:replenishmentScheduleForm/> 
    	</form:form>    
    </section>
</template:page>
