<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
<jsp:body>
<section class="g-main-content g-main-content_over-view clearfix hproduct">

    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" className="category"/>

    <div class="g-float-right block-buttons">
        <product:productButtonsPrintShare product="${product}" />
    </div>
    <div class="clearfix"></div>
    <section class="product-card">

        <product:productDetailsPanel product="${product}" galleryImages="${galleryImages}"/>

        <cms:pageSlot position="Section2" var="comp" element="div">
            <cms:component component="${comp}"/>
        </cms:pageSlot>

        <product:productDisclaimer />

    </section>
    <div class="button-up clearfix">
        <a href="javascript:void(0)" class="g-button-black button-to-up">Наверх</a>
    </div>
</section>
</jsp:body>
</template:page>
