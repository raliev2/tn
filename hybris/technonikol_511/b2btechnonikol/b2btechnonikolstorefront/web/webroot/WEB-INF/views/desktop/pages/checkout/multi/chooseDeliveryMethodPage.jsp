<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>

<template:page pageTitle="${currentStep.name}">
<section class="g-main-content">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <h1 class="checkout__head"><spring:theme code="${currentStep.name}"/></h1>
    <c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
    <form method="get" action="${next_url}">
    <div class="checkout__wrapper clearfix">
        <div class="checkout__body">
            <div class="checkout__row">
                <label for="selectedCostCenter" class="checkout__label">Выберите юр. лицо для оформления заказа</label>
                <select id="selectedCostCenter" name="selectedCostCenter" class="checkout__select">
                    <c:forEach items="${costCenters}" var="costCenter">
                        <option value="${costCenter.code}"><spring:theme code="${costCenter.name}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="checkout__row">
                <span class="checkout__label">Выберите метод доставки</span>
                <c:forEach items="${deliveryModes}" var="mode">
                    <input type="radio" id="${mode.code}" name="selectedDeliveryMode" value="${mode.code}" required />
                    <label for="${mode.code}" class="label-radio">${mode.name}</label>
                </c:forEach>
            </div>
        </div>
        <input type="submit" value="Далее" class="button button_big g-float-right" />
    </div>
    </form>
</section>
</template:page>
