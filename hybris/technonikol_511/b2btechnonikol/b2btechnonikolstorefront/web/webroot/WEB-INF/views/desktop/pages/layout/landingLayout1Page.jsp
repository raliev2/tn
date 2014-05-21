<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>


[<spring:theme code="key" var="key"/>]
<template:page pageTitle="${pageTitle}">

<section class="g-main-content clearfix">
    <aside class="g-left-col">
        <div class="prod-categories">
            <div class="prod-categories__header prod-categories__header_top">Категории продукции</div>
            <ul class="prod-categories__list">
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Абразивы</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Пластыри, изоляторы и лента</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Клеммы</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Инструменты</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Аппаратные средства</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Гидравлика</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Освещение</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Смазки</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Офисное оборудование</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Наруюное оборудование</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Пневматика</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Электроприборы</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Насосы</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Безопасность</a></li>
                <li class="prod-categories-list__item"><a href="javascript:void(0)">Сварочное оборудование</a></li>
            </ul>
        </div>
        <div class="brands">
            <div class="brands__header brands__header_top">Бренды</div>
            <ul class="brands__list clearfix">
                <li class="brands-list__item"><a href="javascript:void(0)">#</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">А</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Б</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">В</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Г</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Д</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Е</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Ж</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">З</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">И</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">К</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Л</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">М</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Н</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">О</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">П</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Р</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">С</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Т</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">У</a></li>
                <li class="brands-list__item"><a href="javascript:void(0)">Ф</a></li>
            </ul>
        </div>
        <div class="delivery">
            <h2 class="delivery__header">Рассылка</h2>
            <div class="delivery__text">Оставьте адрес эл. почты и будьте в курсе всех новостей.</div>
            <div class="delivery__email">
                <input type="text" class="email__input" placeholder="Эл. почта" />
                <a href="javascript:void(0)" class="email__button">Отправить</a>
            </div>
        </div>
    </aside>

    <section class="g-right-col">
        <section class="main-slider clearfix">
            <ul id="main-slider">
                <li><img src="${themeResourcePath}/images/slider/slide1.jpg" alt="Новые мастики и герметики" /></li>
                <li><img src="${themeResourcePath}/images/slider/slide2.jpg" alt="Кровельные материалы" /></li>
                <li><img src="${themeResourcePath}/images/slider/slide3.jpg" alt="Гидроизоляция" /></li>
                <li><img src="${themeResourcePath}/images/slider/slide4.jpg" alt="Профмембраны" /></li>
                <li><img src="${themeResourcePath}/images/slider/slide3.jpg" alt="Полимерные мембраны" /></li>
            </ul>
        </section>
        <section class="carousel-product">
            <h2>Эти товары могут вас заинтересовать</h2>
            <div class="carousel-product__carousel">
                <ul>
                    <li class="product-carousel__item">
                        <div class="product-carousel-item__img">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod1.jpg" alt="Двухсторонняя" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двухсторонняя</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул # <a href="javascript:void(0)">12L728</a> ROBERTS
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: 340&nbsp;232,00&nbsp;<span class="g-rouble">c</span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                    </li>
                    <li class="product-carousel__item">
                        <div class="product-carousel-item__img">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod2.jpg" alt="Двусторонняя акриловая лента, 75м" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двусторонняя акриловая лента, 75м</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул # <a href="javascript:void(0)">12L728HKE92J</a> ROBERTS
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: 34&nbsp;232,00&nbsp;<span class="g-rouble">c</span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" />
                            <a href="javascript:void(0)" class="button">В корзине</a>
                        </div>
                    </li>
                    <li class="product-carousel__item">
                        <div class="product-carousel-item__img">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod3.jpg" alt="Гигиеническая бумага, двуслойная" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Гигиеническая бумага, двуслойная</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул # <a href="javascript:void(0)">12L728</a> ROBERTS ROBERTS ROBERTS
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: 4&nbsp;232,00&nbsp;<span class="g-rouble">c</span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                    </li>
                    <li class="product-carousel__item">
                        <div class="product-carousel-item__img">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod4.jpg" alt="Теплосберегающая лента" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Теплосберегающая лента</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул # <a href="javascript:void(0)">12L728</a> ROBERTS
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: 4&nbsp;232,00&nbsp;<span class="g-rouble">c</span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                    </li>
                    <li class="product-carousel__item">
                        <div class="product-carousel-item__img">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod2.jpg" alt="Двусторонняя акриловая лента, 75м" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Двусторонняя акриловая лента, 75м</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул # <a href="javascript:void(0)">12L728HKE92J</a> ROBERTS
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: 34&nbsp;232,00&nbsp;<span class="g-rouble">c</span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" />
                            <a href="javascript:void(0)" class="button">В корзине</a>
                        </div>
                    </li>
                    <li class="product-carousel__item">
                        <div class="product-carousel-item__img">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod3.jpg" alt="Гигиеническая бумага, двуслойная" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Гигиеническая бумага, двуслойная</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул # <a href="javascript:void(0)">12L728</a> ROBERTS ROBERTS ROBERTS
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: 4&nbsp;232,00&nbsp;<span class="g-rouble">c</span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                    </li>
                    <li class="product-carousel__item">
                        <div class="product-carousel-item__img">
                            <a href="javascript:void(0)"><img src="${themeResourcePath}/images/products/prod4.jpg" alt="Теплосберегающая лента" /></a>
                        </div>
                        <div class="product-carousel-item__info">
                            <div class="product-carousel-item__name">
                                <a href="javascript:void(0)">Теплосберегающая лента</a>
                            </div>
                            <div class="product-carousel-item__articul">
                                Артикул # <a href="javascript:void(0)">12L728</a> ROBERTS
                            </div>
                        </div>
                        <div class="product-carousel-item__price">
                            Цена: 4&nbsp;232,00&nbsp;<span class="g-rouble">c</span>
                        </div>
                        <div class="product-carousel-item__in-cart">
                            <input type="text" class="in-cart__input" />
                            <a href="javascript:void(0)" class="button">В корзину</a>
                        </div>
                    </li>
                </ul>
            </div>
        </section>
        <section class="two-banners clearfix bottom-line">
            <h3>Плюшки</h3>
            <div class="two-banners__banner two-banners__banner_left g-banner">
                <a href="javascript:void(0)"><img src="${themeResourcePath}/images/banners/banner1.jpg" alt="Спецовки и униформы" /></a>
            </div>
            <div class="features-banners__banner two-banners__banner_right g-banner">
                <a href="javascript:void(0)"><img src="${themeResourcePath}/images/banners/banner2.jpg" alt="Каски и защитные очки" /></a>
            </div>
        </section>
        <section class="one-banner g-banner bottom-line">
            <a href="javascript:void(0)">
                <img src="${themeResourcePath}/images/banners/banner3.jpg" alt="Защитная каска и очки" />
            </a>
        </section>
        <section class="promos bottom-line clearfix">
            <div class="promos__item">
                <div class="promos-item__header promos-item__header_new">Новая продукция</div>
                <div class="promos-item__body clearfix">
                    <div class="promos-item-img promos-item-img_new"></div>
                    <div class="promos-item__text">Инновации, которые помогают вашему бизнесу развиваться быстрее.</div>
                    <div class="promos-item__buy"><a href="javascript:void(0)">Купить</a><span class="web-symbols">&#215;</span></div>
                </div>
            </div>
            <div class="promos__item">
                <div class="promos-item__header promos-item__header_popular">Популярные товары</div>
                <div class="promos-item__body clearfix">
                    <div class="promos-item-img promos-item-img_popular"></div>
                    <div class="promos-item__text">Популярные бюджетные товары, которые вы используете каждый день.</div>
                    <div class="promos-item__buy"><a href="javascript:void(0)">Купить</a><span class="web-symbols">&#215;</span></div>
                </div>
            </div>
            <div class="promos__item promos__item_other">
                <div class="promos-item__header promos-item__header_other"></div>
                <div class="promos-item__body clearfix">
                    <div class="promos-item-img promos-item-img_other"></div>
                    <div class="promos-item__text">Качественные и недорогие запчасти.</div>
                    <div class="promos-item__buy"><a href="javascript:void(0)">Купить</a><span class="web-symbols">&#215;</span></div>
                </div>
            </div>
        </section>
        <section class="safety">
            <h3>Безопасность на рабочем месте</h3>
            <div class="safety__see-all"><a href="javascript:void(0)">Смотреть все коллекции</a><span class="web-symbols">&#215;</span></div>
            <div class="safety__items clearfix">
                <div class="safety-item">
                    <div class="safety-item__header">Безопасность</div>
                    <div class="safety-item__body safety-item__body_block1">
                        Все продукты и ресурсы, которые помогут вам сохранить ваш объект безопасным.
                        <div class="safety-item__buy"><a href="javascript:void(0)">Купить</a><span class="web-symbols">&#215;</span></div>
                    </div>
                </div>
                <div class="safety-item">
                    <div class="safety-item__header">Экологичность</div>
                    <div class="safety-item__body safety-item__body_block2">
                        Экологически чистые продукты и ресурсы для более безопасного рабочего места.
                        <div class="safety-item__buy"><a href="javascript:void(0)">Купить</a><span class="web-symbols">&#215;</span></div>
                    </div>
                </div>
                <div class="safety-item">
                    <div class="safety-item__header">Безопасность</div>
                    <div class="safety-item__body safety-item__body_block3">
                        Помощь в планировании и подготовке, ликвидации сбоев в вашем бизнесе.
                        <div class="safety-item__buy"><a href="javascript:void(0)">Купить</a><span class="web-symbols">&#215;</span></div>
                    </div>
                </div>
            </div>
            <div class="safety__text">
                Технониколь является ведущим дистрибьютором промышленных поставок, MRO оборудования, инструментов и материалов.
                Имея доступ к более 900 000 видов продукции, доступных онлайн, мы оказываем обслуживание, ремонт и оперативные решения
                для вашего бизнеса. MRO, производство, снабжение, инструменты и многое другое - для тех, кто кто знает толк.
            </div>
        </section>
    </section>
</section>



</template:page>