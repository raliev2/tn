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
                        <c:url value="/Строительные-Материалы/Плоские-Кровли/c/ploskie_krovli" var="cat1" />
                        <a href="${cat1}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/01_плоские кровли.jpg" />
                            </div>
                            <p>Плоские кровли</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Строительные-Материалы/Скатные-Кровли/c/skatnye_krovli" var="cat2" />
                        <a href="${cat2}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/02_скатные кровли.jpg" />
                            </div>
                            <p>Скатные кровли</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/комплектующие-для-скатной-кровли/c/komplektujushhie_dlja_skatnoj_krovli" var="cat3" />
                        <a href="${cat3}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/03_комплектующие для скатной кровли.jpg" />
                            </div>
                            <p>Комплектующие для скатной кровли</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/древесные-материалы/c/drevesnye_materialy" var="cat4" />
                        <a href="${cat4}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/04_древесные материалы.jpg" />
                            </div>
                            <p>Древесные материалы</p>
                        </a>
                    </li>
                    <li class="one-category_line"></li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/гидро--и-пароизоляционные-пленки/c/gidro-_i_paroizoljacionnye_plenki" var="cat5" />
                        <a href="${cat5}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/05_гидро- и пароизоляционные пленки.jpg" />
                            </div>
                            <p>Гидро- и пароизоляционные пленки</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/утеплители/c/utepliteli" var="cat6" />
                        <a href="${cat6}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/06_утеплители.jpg" />
                            </div>
                            <p>Утеплители</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/заборы-и-ограждения/c/zabory_i_ograzhdenija" var="cat7" />
                        <a href="${cat7}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/07_заборы и ограждения.jpg" />
                            </div>
                            <p>Заборы и ограждения</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/фасады-и-стеновые-материалы/c/fasady_i_stenovye_materialy" var="cat8" />
                        <a href="${cat8}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/08_Фасады и стеновые материалы.jpg" />
                            </div>
                            <p>Фасады и стеновые материалы</p>
                        </a>
                    </li>
                    <li class="one-category_line"></li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/фундаменты%2C-гидроизоляция/c/fundamenty_gidroizoljacija" var="cat9" />
                        <a href="${cat9}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/09_фундаменты, гидроизоляция.jpg" />
                            </div>
                            <p>Фундаменты, гидроизоляция</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/транспортное-и-дорожное-строительство/c/transportnoe_i_dorozhnoe_stroitelstvo" var="cat10" />
                        <a href="${cat10}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/10_транспортное и дорожное строительство.jpg" />
                            </div>
                            <p>Транспортное и дорожное строительство</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/устройство-придомовой-территории%2C-ландшафт/c/ustrojstvo_pridomovoj_territorii_landshaft" var="cat11" />
                        <a href="${cat11}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/11_устройство придомовой территории, ландшафт.jpg" />
                            </div>
                            <p>Устройство придомовой территории, ландшафт</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/материалы-для-внутренней-отделки/c/materialy_dlja_vnutrennej_otdelki" var="cat12" />
                        <a href="${cat12}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/12_материалы для внутренней отделки.jpg" />
                            </div>
                            <p>Материалы для внутренней отделки</p>
                        </a>
                    </li>
                    <li class="one-category_line"></li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/дымоходные-системы-и-вентиляционные-каналы/c/dymohodnye_sistemy_i_ventiljacionnye_kanaly" var="cat13" />
                        <a href="${cat13}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/13_дымоходные системы и вентиляционные каналы.jpg" />
                            </div>
                            <p>Дымоходные системы и вентиляционные каналы</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/воротные-системы/c/vorotnye_sistemy" var="cat14" />
                        <a href="${cat14}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/14_воротные системы.jpg" />
                            </div>
                            <p>Воротные системы</p>
                        </a>
                    </li>
                    <li class="one-category">
                        <c:url value="/Cтроительные-материалы/бассейны/c/bassejny" var="cat15" />
                        <a href="${cat15}">
                            <div class="one-category__img">
                                <img src="${themeResourcePath}/images/maincatalog/15_бассейены.jpg" />
                            </div>
                            <p>Бассейны</p>
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
                            <c:url value="/Строительные-Материалы/Фундаменты-Гидроизоляция/Изоляция-Стыков/Герметизирующие-Ленты/лента-самоклеющаяся-Nicoband-серебристый-10м-х-10см-ГП-12830079/p/343851" var="productUrl" />
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
                            <c:url value="/Строительные-Материалы/Плоские-Кровли/Традиционная-Кровля/Рубероид/рубероид-ABS-ТУ-РПП-300-12000208/p/022421" var="productUrl" />
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
                            <c:url value="/Строительные-Материалы/Утеплители/Базальтовый-Утеплитель/Утеплитель-для-Скатных-Кровель/плита-минераловатная-для-создания-разуклонки-на-кровле-Техноруф-В70-1200х600х40-мм/p/210501" var="productUrl" />
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