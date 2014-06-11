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

<!--  
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div> -->


<template:page pageTitle="${currentStep.name}">

${currentStep.name}

	<div id="globalMessages">
		<common:globalMessages />
	</div>

	<c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
	<form method="get" action="${next_url}">
		<div class="span-20">
			Выберите юр. лицо для оформления заказа:<br /> <select
				name="selectedCostCenter">
				<c:forEach items="${costCenters}" var="costCenter">
					<option value="${costCenter.code}">${costCenter.name}</option>
				</c:forEach>
			</select> <br /> Выберите метод доставки:<br />
			<c:forEach items="${deliveryModes}" var="mode">
				<input type="radio" name="selectedDeliveryMode" value="${mode.code}">${mode.name}
			</c:forEach>
		</div>

		<div class="span-20 right lsast">
			<input type="submit" value="Далее" />
		</div>

	</form>
</template:page>
