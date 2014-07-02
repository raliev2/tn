<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>

<template:page pageTitle="Способ оплаты">
<c:url value="/checkout/multi/apply_voucher" var="apply_voucher" />
<script>
    $(document).ready(function() {
        $('.js-promocode').click(function() {
            if ($('#promocode').val() == '') return;
            $.ajax({
                type : 'get',
                data : {voucherCode : $('#promocode').val()},
                url : "${apply_voucher}",
                success : function(data) {
                	$(data).modal();
                }
            });
        });
        
        $('input[type=radio][name=selectedPaymentMethod]').change(function() {
            var pm = $(this).val();
            $("#hiddenInput").val(pm);
        });
    });
</script>
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
    <form method="get" action="${next_url}" id="pmForm">
        <div class="checkout__wrapper clearfix g-float-left">
            <div class="checkout__body" style="padding:23px 20px;">
                <p class="g-strong margin-bottom-5px">Способ оплаты</p>
                <c:forEach items="${paymentMethods}" var="method">
                    <input type="radio" name="selectedPaymentMethod" value="${method.code}" required id="selectedPaymentMethod${method.code}" ${method.code == cartData.paymentMethod.code ? 'checked' : ''}/>
                    <label for="selectedPaymentMethod${method.code}" style="margin:10px 30px 20px 0;"><spring:theme code="${method.name}"/></label>
                </c:forEach>
            </div>

            <input type="submit" value="Далее" class="button button_big g-float-right" />
            <c:url value="/checkout/multi/select-delivery-mode?isPrev=true" var="prev_url" />
            <!--
            <div class="g-float-right checkout__back"><a href="${prev_url}" class="g-link-blue">Назад</a></div>
            -->
        </div>
        <aside class="checkout__promo g-float-right">
            <label for="promocode" class="label-promocode">Промо код</label>
            <input id="promocode" name="promocode" class="promocode__input" maxlength="20" />
            <a href="javascript:void(0)" class="button button_left-border js-promocode" style="margin-left:-5px">Пересчитать</a>
        </aside>
    </form>
    <!-- 
    <c:url value="/checkout/multi${currentStep.previous.url}" var="prev_url"/>
     -->
    <form method="get" action="${prev_url}" id="spmForm" >
        <div class="g-float-right checkout__back" style="position:relative; top:10px; right:385px;"><a href="javascript:void(0)" class="g-link-blue" onclick="$('#spmForm').submit()">Назад</a></div>
    	<input id="hiddenInput" type="hidden" name="selectedPaymentMethod" />    
    	<input id="isPrev" type="hidden" name="isPrev" value="true"/>   
    </form>
</section>
</template:page>
