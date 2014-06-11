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
<!--  
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div> -->


<div id="globalMessages">
	<common:globalMessages />
</div>


<c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />

<form:form method="post" commandName="addressForm" action="${next_url}">
	<div class="span-20">
		Клиентское обслуживание в любое время суток<br /> По вопросам и
		проблемам с доставкой - звонить на номер 8(800) 333 00 20<br /> <select
			name="selectedDeliveryAddress">
			<c:forEach items="${deliveryAddresses}" var="address">
				<option value="${address.code}">${address.name}</option>
			</c:forEach>
		</select> Или добавьте новый:

		<form:hidden path="addressId" class="create_update_address_id" />
		<formUtil:formInputBox labelKey="Имя" idKey="address.firstName"
			path="firstName" inputCSS="text" mandatory="true" />
		<formUtil:formInputBox labelKey="Фамилия" idKey="address.lastName"
			path="lastName" inputCSS="text" mandatory="true" />
		<formUtil:formInputBox labelKey="Телефон" idKey="address.phone"
			path="phone" inputCSS="text" mandatory="true" />
		<formUtil:formInputBox labelKey="Город" idKey="address.townCity"
			path="townCity" inputCSS="text" mandatory="false" />
		<formUtil:formInputBox labelKey="Улица" idKey="address.street"
			path="street" inputCSS="text" mandatory="true" />
		<formUtil:formInputBox labelKey="Дом, подъезд" idKey="address.house"
			path="house" inputCSS="text" mandatory="true" />
		<formUtil:formInputBox labelKey="Индекс" idKey="address.postcode"
			path="postcode" inputCSS="text" mandatory="true" />
	</div>
	<div class="span-20 right lsast">
		<input type="submit" value="Далее" />
	</div>

</form:form>

	<div class="span-20 right lsast">
		<input type="submit" value="Назад"/>
	</div>