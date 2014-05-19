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

        <div class="block-chars">
            <div class="block-chars__header">Похожие товары</div>
            <div class="carousel-product__carousel clearfix">
                <ul>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod1.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p>ROBERTS</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">400,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare1" /> <label for="to_compare1">К сравнению</label>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div class="block-chars">
            <div class="block-chars__header">С этим товаром часто смотрят</div>
            <div class="carousel-product__carousel carousel-product__carousel_height clearfix">
                <ul>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod5.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">ROBERTS</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">400,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare7" /> <label for="to_compare7">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod6.jpg" alt="Батарейки пальчиковые" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Батарейки пальчиковые</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Durasell</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">70,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare2" /> <label for="to_compare2">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod7.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Grainger approved</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">1&nbsp;270,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare3" /> <label for="to_compare3">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod8.jpg" alt="Clorox" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Clorox</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Clorox</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">3&nbsp;380,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare4" /> <label for="to_compare4">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod7.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Grainger approved</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">1&nbsp;270,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare5" /> <label for="to_compare5">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod5.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">ROBERTS</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">400,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare6" /> <label for="to_compare6">К сравнению</label>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div class="block-chars">
            <div class="block-chars__header">С этим товаром часто покупают</div>
            <div class="carousel-product__carousel carousel-product__carousel_height clearfix">
                <ul>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod6.jpg" alt="Батарейки пальчиковые" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Батарейки пальчиковые</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Durasell</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">70,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare2" /> <label for="to_compare2">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod5.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">ROBERTS</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">400,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare7" /> <label for="to_compare7">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod8.jpg" alt="Clorox" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Clorox</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Clorox</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">3&nbsp;380,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare4" /> <label for="to_compare4">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod7.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Grainger approved</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">1&nbsp;270,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare3" /> <label for="to_compare3">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod7.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">Grainger approved</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">1&nbsp;270,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare5" /> <label for="to_compare5">К сравнению</label>
                        </div>
                    </li>
                    <li class="product-carousel__item product-carousel__item_930px">
                        <div class="product-carousel-item__img product-carousel-item__img_930px">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod5.jpg" alt="Двухсторонняя акриловая лента, 15ft" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя акриловая лента, 15ft</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул: <a href="javascript:void(0)">12L728</a>
                                <p class="product-carousel-item__manufacturer">ROBERTS</p>
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: <span class="product-carousel-item__price_green">400,00&nbsp;<span class='g-rouble'>P</span></span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" value="1" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                        <div class="product-carousel-item__to-compare">
                            <input type="checkbox" id="to_compare6" /> <label for="to_compare6">К сравнению</label>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

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
            <p class="g-strong">Product Reviews Disclaimer:</p>
            <p>Grainger is neither responsible for, nor does it endorse, the content of any product review or statement posted. Any statements posted constitute the statements of the poster and are not the statements of Grainger. The statements posted by Grainger employees with the Grainger employee badge represent the views of such employees and are not the statements of Grainger. Grainger makes no representations as to the appropriateness, accuracy, completeness, correctness, currentness, suitability, or validity of any product review or statements posted, including those posted by employees with the Grainger employee badge, and is not liable for any losses, injuries or damages which may result from any such product review or statements. Use of any linked web site provided in a product review or post is at the user's own risk.</p>
        </div>
    </div>
</section>
    <div class="button-up clearfix">
        <a href="javascript:void(0)" class="g-button-black button-to-up">Наверх</a>
    </div>
</section>
</jsp:body>
</template:page>
