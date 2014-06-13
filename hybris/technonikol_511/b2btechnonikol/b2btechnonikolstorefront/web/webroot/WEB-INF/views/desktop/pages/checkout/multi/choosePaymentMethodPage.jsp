<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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


	<div id="globalMessages">
		<common:globalMessages />
	</div>

	<c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
	<form method="get" action="${next_url}">

Выберите способ оплаты:<br />
			<c:forEach items="${paymentMethods}" var="method">
				<input type="radio" name="selectedPaymentMethod" value="${method.code}">${method.name}
			</c:forEach>
		</div>
		<div class="span-20 right lsast">
			<input type="submit" value="Далее" />
		</div>

	</form>
</template:page>
