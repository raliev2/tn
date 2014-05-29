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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="clearfix">
    <div class="also-viewed">
        <div class="also-viewed__head">Customers Also Viewed</div>
        <div class="also-viewed__product">
            <div class="also-viewed-product__img">
                <a href="javasxript:void(0)">
                    <img src="${themeResourcePath}/images/products/prod7.jpg" alt="Двухсторонняя акриловая лента, 7 футов" title="Двухсторонняя акриловая лента, 7 футов" />
                </a>
            </div>
            <div class="also-viewed-product__info">
                <p><a href="javascript:void(0)">Cordless Impact Wrench Kit,9/2 In.L</a></p>
                <p>Артикул # <a href="javascript:void(0)">2L728HKE92J</a></p>
                <p>ROBERTS</p>
            </div>
            <div class="search-item__price">
                Цена: <span class="price-value">234 532 <span class="g-rouble">P</span></span>
            </div>
            <div class="to-cart">
                <input type="text" value="1" name="qty" class="g-input" size="2">
                <a href="javascript:void(0)" class="button">В корзину</a>
            </div>
        </div>
        <div class="also-viewed__product">
            <div class="also-viewed-product__img">
                <a href="javasxript:void(0)">
                    <img src="${themeResourcePath}/images/products/prod9.jpg" alt="Двухсторонняя акриловая лента, 7 футов" title="Двухсторонняя акриловая лента, 7 футов" />
                </a>
            </div>
            <div class="also-viewed-product__info">
                <p><a href="javascript:void(0)">Cordless Impact Wrench Kit,9/2 In.L</a></p>
                <p>Артикул # <a href="javascript:void(0)">2L728HKE92J</a></p>
                <p>ROBERTS</p>
            </div>
            <div class="search-item__price">
                Цена: <span class="price-value">234 532 <span class="g-rouble">P</span></span>
            </div>
            <div class="to-cart">
                <input type="text" value="1" name="qty" class="g-input" size="2">
                <a href="javascript:void(0)" class="button">В корзину</a>
            </div>
        </div>
        <div class="also-viewed__product">
            <div class="also-viewed-product__img">
                <a href="javasxript:void(0)">
                    <img src="${themeResourcePath}/images/products/prod8.jpg" alt="Двухсторонняя акриловая лента, 7 футов" title="Двухсторонняя акриловая лента, 7 футов" />
                </a>
            </div>
            <div class="also-viewed-product__info">
                <p><a href="javascript:void(0)">Cordless Impact Wrench Kit,9/2 In.L</a></p>
                <p>Артикул # <a href="javascript:void(0)">2L728HKE92J</a></p>
                <p>ROBERTS</p>
            </div>
            <div class="search-item__price">
                Цена: <span class="price-value">234 532 <span class="g-rouble">P</span></span>
            </div>
            <div class="to-cart">
                <input type="text" value="1" name="qty" class="g-input" size="2">
                <a href="javascript:void(0)" class="button">В корзину</a>
            </div>
        </div>
    </div>

    <div class="product-images">
        <product:productImagePanel product="${product}"/>
        <product:productImageCarousel galleryImages="${galleryImages}" product="${product}"/>
    </div>

    <div class="product-info">
        <div class="product-name">
            <h1 class="fn">${product.name}</h1>
        </div>
        <div class="product-manufacturer"><a href="javascript:void(0)" class="g-link-blue brand">${product.brand.name}</a></div>
        <div class="product-info__characteristics">
            <div class="characteristics__line clearfix">
                <div class="characteristics-line__col1">
			        <product:productPricePanel product="${product}"/>

                    <p class="to-compare"><input type="checkbox" id="to_compare" /> <label for="to_compare">К сравнению</label></p>

                    <sec:authorize ifNotGranted="ROLE_CUSTOMERGROUP">
                        <c:url value='/login' var="loginUrl"/>
                        <p class="auth-message"><a href="${loginUrl}">Авторизуйтесь, пожалуйста, для получения актуальной цены</a></p>
                    </sec:authorize>
                </div>
                <div class="characteristics-line__col2">
                    <cms:pageSlot position="AddToCart" var="component" element="div" class="to-cart g-float-left">
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
            <div class="characteristics__line clearfix">
                <ul>
                    <li class="one-characteristic identifier"><span class="type" title="mpn">Артикул</span>: <span class="one-characteristic__value value">${product.manufacturerCode}</span></li>

                    <li class="one-characteristic">код ТН: <span class="one-characteristic__value">${product.code}</span></li>
		  <!--  	<li class="one-characteristic">UNSPSC: <span class="one-characteristic__value">30161701</span></li> -->
		            <li class="one-characteristic">КодГОСТ/КодТУ: <span class="one-characteristic__value">${product.documentCode}</span></li>

                  <!--  <li class="one-characteristic">Страница каталога: <span class="one-characteristic__value">1186</span></li>-->
                    <li class="one-characteristic">Отгрузочный вес (брутто): <span class="one-characteristic__value">${product.weightGross}</span></li>
                    <li class="one-characteristic">Вес нетто: <span class="one-characteristic__value">${product.weightNet}</span></li>
                    <li class="one-characteristic">Тип товара: <span class="one-characteristic__value">${product.productType.name}</span></li>
                    <li class="one-characteristic">Запрещен к закупке:
                        <span class="one-characteristic__value">
                            <c:choose>
                                <c:when test="${product.unreplenishable}">
                                    Да
                                </c:when>
                                <c:otherwise>
                                    Нет
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </li>
                    <li class="one-characteristic">Объем: <span class="one-characteristic__value">${product.volume}</span></li>
                    <li class="one-characteristic">Количество товара в упаковке: <span class="one-characteristic__value">${product.quantityInPackage}</span></li>
                </ul>
            </div>
            <div class="product-country">
                Страна производитель: <span class="one-characteristic__value">${product.productionCountry.name}</span>
            </div>
            <product:productPromotionSection product="${product}"/>
        </div>
    </div>
</section>
<section class="other-chars">
    <div class="block-chars">
        <div class="block-chars__header">Технические характеристики</div>
        <div class="block-chars__list clearfix">
            <ul>
                <c:if test="${not empty product.classifications}">
                    <c:forEach items="${product.classifications}" var="classification">
                        <c:forEach items="${classification.features}" var="feature">
                            <li class="block-chars-list__item">
                                <div class="block-chars-item__inner clearfix">
                                    <div class="name-char">${feature.name}: </div>
                                    <div class="value-char">
                                        <c:forEach items="${feature.featureValues}" var="value" varStatus="status">
                                            ${value.value}
                                            <c:choose>
                                                <c:when test="${feature.range}">
                                                    ${not status.last ? '-' : feature.featureUnit.symbol}
                                                </c:when>
                                                <c:otherwise>
                                                    &nbsp;${feature.featureUnit.name}
                                                    ${not status.last ? '<br/>' : ''}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </c:forEach>
                </c:if>
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
        <div class="block-chars__header">Сертификаты и техническая документация</div>
        <div class="block-chars__body">
            <a href="javascript:void(0)" class="g-link-blue">Roberts 50 550 Carpet Adhesive Tape Data Sheet</a>
        </div>
    </div>

</section>