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
    <section class="g-main-content clearfix hproduct">

    <div class="bread-crumbs category">
        <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
    </div>
    <div class="g-float-right block-buttons">
        <product:productButtonsPrintShare product="${product}" />
    </div>
    <div class="clearfix"></div>
    <section class="product-card">

        <product:productDetailsPanel product="${product}" galleryImages="${galleryImages}"/>

        <cms:pageSlot position="Section2" var="comp" element="div">
            <cms:component component="${comp}"/>
        </cms:pageSlot>
        <div class="block-chars">
            <product:productAlternativeSearches product="${product}" />
        </div>

    <div class="reviews hreview-aggregate">

        <div class="reviews__link-tabs clearfix">
            <ul>
                <li class="reviews__link-tab reviews__link-tab_active" id="all-reviews">Отзывы</li>
                <li class="reviews__link-tab" id="write-review">Написать отзыв</li>
            </ul>
        </div>
        <div class="reviews__body">
            <a name="reviews"></a>
            <div class="reviews__tab js-reviews__tab_all-reviews">
                <div class="reviews-tab__head clearfix">
                    <div class="amout-reviews"><span class="votes"><span class="value-title" title="1">0</span></span> Отзывов</div>
                    <div class="rating__stars js-rating">
                        <input type="hidden" name="val" value="0" />
                    </div>
                    <div class="numeric-rating rating">
                        <span class="average"><span class="value-title" title="1">0</span></span> из <span class="best">5</span>
                        <abbr class="worst" title="1" />
                    </div>
                </div>
                <div class="reviews-tab__items">
                    <div class="rating__stars js-rating">
                        <input type="hidden" name="val" value="0" />
                    </div>
                    <div class="clearfix"></div>
                    <p>У этого товара еще нет отзывов. <a href="javascript:void(0)" class="g-link-blue js-write-review">Станьте первым.</a></p>
                </div>
            </div>
            <div class="reviews__tab js-reviews__tab_write-review g-hidden">
                Написать отзыв
            </div>
        </div>


        <div class="reviews__disclaimer">
            <p class="g-strong">Правовая оговорка отзывов о продукции:</p>
            <p>1Платформа ни ответственен за, и при этом он не подтверждает, содержание никакого обзора продукции или опубликованного заявления. Любые опубликованные заявления составляют заявления плаката и не являются заявлениями 1Платформа. Заявления, опубликованные сотрудниками 1Платформасо значком сотрудника 1Платформа, представляют взгляды таких сотрудников и не являются заявлениями 1Платформа. 1Платформа не делает представления относительно уместности, точности, полноты, правильности, актуальности, пригодности или законности никакого обзора продукции 1Платформазаявлений объявленными, включая отправленных сотрудниками со значком сотрудника 1Платформа, и не ответственен ни за какие потери, раны или убытки, которые могут следов ать из любого такого обзора продукции или заявлений. Использование любого связанного веб-сайта, обеспеченного в обзоре продукции или почте, в собственном риске пользователя.</p>
        </div>
    </div>
</section>
    <div class="button-up clearfix">
        <a href="javascript:void(0)" class="g-button-black button-to-up">Наверх</a>
    </div>
</section>
</jsp:body>
</template:page>
