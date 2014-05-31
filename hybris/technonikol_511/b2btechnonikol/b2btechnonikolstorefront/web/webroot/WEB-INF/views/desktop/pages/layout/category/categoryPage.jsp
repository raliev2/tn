<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<template:page pageTitle="${pageTitle}">
    <jsp:body>
        <div id="globalMessages">
            <common:globalMessages/>
        </div>

        <section class="g-main-content clearfix">
            <div class="g-float-right block-buttons">
                <a href="javascript:void(0)" class="g-button-white">Печать</a>
            </div>
            <div class="clearfix"></div>
            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" className="bread-crumbs_mini" />
            <div class="clearfix"></div>
            <div class="catalog-header"><h1>Каталог</h1></div>
            <div class="category-description">
                Грейнджер несет огромный выбор ручного инструмента для каждой сделки и задачи. Обеспечить надлежащее болт напряженность
                в отношениях с нашим выбором микрометра динамометрических ключей. Для точного измерения, посмотрите нашу подборку лазерных уровней, рулетки и измерительных колес. У нас на складе сотни режущих инструментов, как болт катера, снятия изоляции
                и многоцелевых коммунальных ножей. Магазин Грейнджер и получить все ручные инструменты, необходимые в одном месте.
            </div>
            <div class="banner-700px banner-700px_center">
                <c:url value="/строительные-материалы/плоские-кровли/мастики-и-праймеры/c/mastiki_i_prajmery" var="bannerUrl" />
                <a href="${bannerUrl}">
                    <img src="${themeResourcePath}/images/banners/westard.jpg" alt="Westward" title="Westward" />
                </a>
            </div>
            <div class="g-gradient-gray catalog-category">Категории товаров</div>
            <div class="all-categories">
                <ul class="clearfix">
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat1.jpg" />
                            </div>
                            <p>Сгибатели</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat2.jpg" />
                            </div>
                            <p>Средства для ковра</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat3.jpg" />
                            </div>
                            <p>Зажимы</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat4.jpg" />
                            </div>
                            <p>Коммуникационные инструменты</p>
                        </a>
                    </li>
                    <li class="one-category_line"></li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat5.jpg" />
                            </div>
                            <p>Сантехнические инструменты</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat6.jpg" />
                            </div>
                            <p>Гвоздодеры</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat7.jpg" />
                            </div>
                            <p>Съемники и сепараторы</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat8.jpg" />
                            </div>
                            <p>Пробойники, зубила и ручные дрели</p>
                        </a>
                    </li>
                    <li class="one-category_line"></li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat9.jpg" />
                            </div>
                            <p>Электрические товары</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat10.jpg" />
                            </div>
                            <p>Напильники</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat11.jpg" />
                            </div>
                            <p>Молотки и ударные инструменты</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <a href="#">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/cat12.jpg" />
                            </div>
                            <p>Ручная пила и козлы</p>
                        </a>
                    </li>
                </ul>
            </div>

            <div class="block-chars block-chars_margintop">
                <div class="block-chars__header">Хиты продаж</div>
                <div class="carousel-product__carousel carousel-product__carousel_height clearfix">
                    <ul>
                        <li class="product-carousel__item product-carousel__item_930px">
                            <c:url value="/p/000100" var="productUrl" />
                            <div class="product-carousel-item__img product-carousel-item__img_930px">
                                <a href="${productUrl}"><img src="${themeResourcePath}/images/products/prod14.jpg" alt="Материал битумно-полимерный Техноэласт ЭПП 11000327" /></a>
                            </div>
                            <div class="product-carousel-item__info">
                                <div class="product-carousel-item__name">
                                    <a href="${productUrl}">Материал битумно-полимерный Техноэласт ЭПП 11000327</a>
                                </div>
                                <div class="product-carousel-item__articul">
                                    Артикул: <a href="${productUrl}">11000327</a>
                                    <p class="product-carousel-item__manufacturer">ТЕХНОЭЛАСТ</p>
                                </div>
                            </div>
                            <div class="product-carousel-item__price">
                                Цена: <span class="product-carousel-item__price_green">152.83&nbsp;<span class='g-rouble'>P</span></span>
                            </div>
                            <div class="product-carousel-item__in-cart">
                                <input type="text" class="in-cart__input" value="1" />
                                <a href="javascript:void(0)" class="button">В корзину</a>
                            </div>
                        </li>
                        <li class="product-carousel__item product-carousel__item_930px">
                            <c:url value="/p/395727" var="productUrl" />
                            <div class="product-carousel-item__img product-carousel-item__img_930px">
                                <a href="${productUrl}"><img src="${themeResourcePath}/images/products/prod15.jpg" alt="Герметик бутил-каучуковый ТехноНИКОЛЬ №45 (белый), ведро 16 кг" /></a>
                            </div>
                            <div class="product-carousel-item__info">
                                <div class="product-carousel-item__name">
                                    <a href="${productUrl}">Герметик бутил-каучуковый ТехноНИКОЛЬ №45 (белый), ведро 16 кг</a>
                                </div>
                                <div class="product-carousel-item__articul">
                                    <p class="product-carousel-item__manufacturer">ТЕХНОНИКОЛЬ</p>
                                </div>
                            </div>
                            <div class="product-carousel-item__price">
                                Цена: <span class="product-carousel-item__price_green">291.65&nbsp;<span class='g-rouble'>P</span></span>
                            </div>
                            <div class="product-carousel-item__in-cart">
                                <input type="text" class="in-cart__input" value="1" />
                                <a href="javascript:void(0)" class="button">В корзину</a>
                            </div>
                        </li>
                        <li class="product-carousel__item product-carousel__item_930px">
                            <c:url value="/p/393557" var="productUrl" />
                            <div class="product-carousel-item__img product-carousel-item__img_930px">
                                <a href="${productUrl}"><img src="${themeResourcePath}/images/products/prod16.jpg" alt="Мастика битумно-резиновая AquaMast (10кг)" /></a>
                            </div>
                            <div class="product-carousel-item__info">
                                <div class="product-carousel-item__name">
                                    <a href="${productUrl}">Мастика битумно-резиновая AquaMast (10кг)</a>
                                </div>
                                <div class="product-carousel-item__articul">
                                    <p class="product-carousel-item__manufacturer">AQUAMAST</p>
                                </div>
                            </div>
                            <div class="product-carousel-item__price">
                                Цена: <span class="product-carousel-item__price_green">133.90&nbsp;<span class='g-rouble'>P</span></span>
                            </div>
                            <div class="product-carousel-item__in-cart">
                                <input type="text" class="in-cart__input" value="1" />
                                <a href="javascript:void(0)" class="button">В корзину</a>
                            </div>
                        </li>
                        <li class="product-carousel__item product-carousel__item_930px">
                            <c:url value="/p/393557" var="productUrl" />
                            <div class="product-carousel-item__img product-carousel-item__img_930px">
                                <a href="${productUrl}"><img src="${themeResourcePath}/images/products/prod17.jpg" alt="Лента самоклеющаяся Nicoband серебристый 10м х 10см ГП 12830079" /></a>
                            </div>
                            <div class="product-carousel-item__info">
                                <div class="product-carousel-item__name">
                                    <a href="${productUrl}">Лента самоклеющаяся Nicoband серебристый 10м х 10см ГП 12830079</a>
                                </div>
                                <div class="product-carousel-item__articul">
                                    <p class="product-carousel-item__manufacturer">NICOBAND</p>
                                </div>
                            </div>
                            <div class="product-carousel-item__price">
                                Цена: <span class="product-carousel-item__price_green">507.69&nbsp;<span class='g-rouble'>P</span></span>
                            </div>
                            <div class="product-carousel-item__in-cart">
                                <input type="text" class="in-cart__input" value="1" />
                                <a href="javascript:void(0)" class="button">В корзину</a>
                            </div>
                        </li>
                        <li class="product-carousel__item product-carousel__item_930px">
                            <c:url value="/p/393557" var="productUrl" />
                            <div class="product-carousel-item__img product-carousel-item__img_930px">
                                <a href="${productUrl}"><img src="${themeResourcePath}/images/products/prod18.jpg" alt="Рубероид ABS ТУ РПП 300 12000208" /></a>
                            </div>
                            <div class="product-carousel-item__info">
                                <div class="product-carousel-item__name">
                                    <a href="${productUrl}">Рубероид ABS ТУ РПП 300 12000208</a>
                                </div>
                                <div class="product-carousel-item__articul">
                                    Артикул: <a href="${productUrl}">12000208</a>
                                    <p class="product-carousel-item__manufacturer">A.D.W. KLINKER</p>
                                </div>
                            </div>
                            <div class="product-carousel-item__price">
                                Цена: <span class="product-carousel-item__price_green">19.00&nbsp;<span class='g-rouble'>P</span></span>
                            </div>
                            <div class="product-carousel-item__in-cart">
                                <input type="text" class="in-cart__input" value="1" />
                                <a href="javascript:void(0)" class="button">В корзину</a>
                            </div>
                        </li>
                        <li class="product-carousel__item product-carousel__item_930px">
                            <c:url value="/p/393557" var="productUrl" />
                            <div class="product-carousel-item__img product-carousel-item__img_930px">
                                <a href="${productUrl}"><img src="${themeResourcePath}/images/products/prod19.jpg" alt="Плита минераловатная для создания разуклонки на кровле" /></a>
                            </div>
                            <div class="product-carousel-item__info">
                                <div class="product-carousel-item__name">
                                    <a href="${productUrl}">Плита минераловатная для создания разуклонки на кровле</a>
                                </div>
                                <div class="product-carousel-item__articul">
                                    Артикул: <a href="${productUrl}">5754</a>
                                    <p class="product-carousel-item__manufacturer">ТЕХНОРУФ</p>
                                </div>
                            </div>
                            <div class="product-carousel-item__price">
                                Цена: <span class="product-carousel-item__price_green">5,915.28&nbsp;<span class='g-rouble'>P</span></span>
                            </div>
                            <div class="product-carousel-item__in-cart">
                                <input type="text" class="in-cart__input" value="1" />
                                <a href="javascript:void(0)" class="button">В корзину</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />
    </jsp:body>
 </template:page>