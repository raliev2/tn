<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="galleryImages" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<section class="clearfix">
    <div class="product-images">
        <product:productImagePanel product="${product}"/>
        <product:productImageCarousel galleryImages="${galleryImages}" product="${product}"/>
    </div>

    <div class="product-info">
        <div class="product-name">
            <h1 class="fn">${product.name}</h1>
        </div>
        <div class="product-manufacturer"><a href="javascript:void(0)" class="g-link-blue brand">Roberts</a></div>
        <div class="product-info__characteristics">
            <div class="characteristics__line clearfix">
                <div class="characteristics-line__col1 characteristics-line__col_border-right">
                    <p class="regularPrice">Цена:
                        <span class="regularPrice__price price">
                            <span class="value-title" title="320 RUB">
                                320,00&nbsp;<span class='g-rouble'>P</span>
                            </span>
                        </span>
                    </p>
                    <p class="to-compare"><input type="checkbox" id="to_compare" /> <label for="to_compare">К сравнению</label></p>
                </div>
                <div class="characteristics-line__col2 characteristics-line__col_border-right">

                    <cms:pageSlot position="AddToCart" var="component" element="div" class="to-cart">
                        <cms:component component="${component}"/>
                    </cms:pageSlot>

                    <div class="in-wishlist g-float-right"><a href="javascript:void(0)" class="g-link-blue">+ В список<br />желаний</a></div>
                </div>
                <div class="characteristics-line__col3">
                    <p class="g-italic">Наличие</p>
                    <div class="stock in-stock"><span>В наличии</span>
                        <div class="g-info"></div>
                    </div>
                </div>
            </div>
            <div class="characteristics__line b-rating clearfix">
                <div class="rating__stars js-rating">
                    <input type="hidden" name="val" value="2.5" />
                </div>
                <div class="anchors"><a href="#reviews" class="g-link-blue js-write-review">Написать отзыв</a> | <a href="javascript:void(0)" class="g-link-blue">Вопрос-ответ</a></div>
            </div>
            <div class="characteristics__line clearfix">
                <ul>
                    <li class="one-characteristic identifier"><span class="type" title="mpn">Артикул</span>: <span class="one-characteristic__value value">5HXE0</span></li>
                    <li class="one-characteristic">Модель: <span class="one-characteristic__value">5HXE0</span></li>
                    <li class="one-characteristic">UNSPSC: <span class="one-characteristic__value">30161701</span></li>
                    <li class="one-characteristic">Страница каталога: <span class="one-characteristic__value">1186</span></li>
                    <li class="one-characteristic">Вес: <span class="one-characteristic__value">${product.weightNet}</span></li>
                </ul>
            </div>
            <div class="product-country">
                Страна производитель: <span class="one-characteristic__value">США</span>
            </div>
        </div>
    </div>
</section>
<section class="other-chars">
    <div class="block-chars">
        <div class="block-chars__header">Технические характеристики</div>
        <div class="block-chars__list clearfix">
            <ul>
                <li class="block-chars-list__item">
                    <div class="block-chars-item__inner clearfix">
                        <div class="name-char">Наименование</div><div class="value-char">Двухсторонняя акриловая лента</div>
                    </div>
                </li>
                <li class="block-chars-list__item">
                    <div class="block-chars-item__inner clearfix">
                        <div class="name-char">Количество</div><div class="value-char">1 шт.</div>
                    </div>
                </li>
                <li class="block-chars-list__item">
                    <div class="block-chars-item__inner clearfix">
                        <div class="name-char">Размеры</div><div class="value-char">1-7/8” x 75ft.</div>
                    </div>
                </li>
                <li class="block-chars-list__item">
                    <div class="block-chars-item__inner clearfix">
                        <div class="name-char">Конструкция</div><div class="value-char">Нетоксична</div>
                    </div>
                </li>
                <li class="block-chars-list__item">
                    <div class="block-chars-item__inner clearfix">
                        <div class="name-char">Назначение</div><div class="value-char">Для внутренних и наружных работ потому что в 2 строки</div>
                    </div>
                </li>
                <li class="block-chars-list__item">
                    <div class="block-chars-item__inner clearfix">
                        <div class="name-char">Использовать с</div><div class="value-char">Корвровое нечто</div>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <div class="block-chars">
        <div class="block-chars__header">Описание продукта</div>
        <div class="block-chars__body">
            ${product.description}
        </div>
    </div>

    <div class="block-chars">
        <div class="block-chars__header">Противопоказания и Ограничения</div>
        <div class="block-chars__body">
            <p>Нет</p>
        </div>
    </div>

    <div class="block-chars">
        <div class="block-chars__header">Документация</div>
        <div class="block-chars__body">
            <a href="javascript:void(0)" class="g-link-blue">Roberts 50 550 Carpet Adhesive Tape Data Sheet</a>
        </div>
    </div>
</section>