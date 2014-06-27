<%@ page trimDirectiveWhitespaces="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
	<section class="g-main-content clearfix">	
		<div id="breadcrumb" class="breadcrumb">
			<breadcrumb:breadcrumb breadcrumbs="${additionalBreadcrumb}"/>
		</div>
	    <div class="clearfix"></div>
	    
	    <c:url value="/" var="homeUrl"/>
	    
	    <!-- 
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		
		
		<div>
			<c:if test="${not empty message}">
				<c:out value="${message}"/>
			</c:if>
		</div>
		 -->
		 <div style="padding-top:10px; text-align:center; color:#000;">
		 	<p style="font-size: 18px; font-weight: bold;">Увы, мы не можем найти эту страницу...</p>
		 	<p style="padding-top: 40px; font-size: 12px; ">Попробуйте найти нужный вам<br/>материал на сайте</p>
		 	<div style="padding: 30px 0 0 315px">
		 		<a href="${homeUrl}"><div style="width:300px; height:40px; background-color:#ca2120; color:#fff; padding-top:20px">вернитесь на главную страницу</div></a>
		 	</div>
		 	<p style="padding-top: 30px; font-size: 16px; font-weight: bold;">Также вы можете позвонить нам по телефону <span style="font-size: 22px; ">8 (800) 333 00 20</span></p>
		 	<p style="padding-top: 10px; font-size: 12px; ">24 часа в сутки, 7 дней в неделю</p>
		 </div>
		 
		 <div style="padding: 30px 0 0 430px"><a href="${homeUrl}"><img src="${themeResourcePath}/images/logo.png" alt="ТЕХНОНИКОЛЬ" title="ТЕХНОНИКОЛЬ" /></a></div>
		 
		<cms:pageSlot position="MiddleContent" var="comp" element="div" class="span-20">
			<cms:component component="${comp}"/>
		</cms:pageSlot>
	
		<cms:pageSlot position="BottomContent" var="comp" element="div" class="span-20 cms_disp-img_slot last">
			<cms:component component="${comp}"/>
		</cms:pageSlot>
		
		<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 narrow-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</section>
</template:page>