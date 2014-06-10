<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:choose>
	<c:when test="${empty product.variantOptions}">
		<c:set var="allowAddToCart" value="${true}"/>
	</c:when>
	<c:otherwise>
		<c:set var="allowAddToCart" value="${false}"/>
	</c:otherwise>
</c:choose>

<c:url value="/cart" var="cartUrl"/>
<script type="text/plain" id="addToCartTmpl">
    <div class="cart-popup__header clearfix">
        <div class="cart-popup__mesage">{{=it.message}}</div>
        <div class="cart-popup__tocart g-float-right"><a href="${cartUrl}">Посмотреть корзину</a></div>
    </div>
    <div class=cart-popup__body>
                <div class="cart-popup-carousel__header">Также покупают:</div>
                <div class="carousel-product__carousel clearfix">
                    <ul>
                        <li class="product-carousel__item">
                            <c:url value="/p/000100" var="productUrl" />
                            <div class="product-carousel-item__img">
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
                        <li class="product-carousel__item">
                            <c:url value="/p/395727" var="productUrl" />
                            <div class="product-carousel-item__img">
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
                        <li class="product-carousel__item">
                            <c:url value="/p/393557" var="productUrl" />
                            <div class="product-carousel-item__img">
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
                        <li class="product-carousel__item">
                            <c:url value="/Строительные-Материалы/Фундаменты-Гидроизоляция/Изоляция-Стыков/Герметизирующие-Ленты/лента-самоклеющаяся-Nicoband-серебристый-10м-х-10см-ГП-12830079/p/343851" var="productUrl" />
                            <div class="product-carousel-item__img">
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
                        <li class="product-carousel__item">
                            <c:url value="/Строительные-Материалы/Плоские-Кровли/Традиционная-Кровля/Рубероид/рубероид-ABS-ТУ-РПП-300-12000208/p/022421" var="productUrl" />
                            <div class="product-carousel-item__img">
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
                        <li class="product-carousel__item">
                            <c:url value="/Строительные-Материалы/Утеплители/Базальтовый-Утеплитель/Утеплитель-для-Скатных-Кровель/плита-минераловатная-для-создания-разуклонки-на-кровле-Техноруф-В70-1200х600х40-мм/p/210501" var="productUrl" />
                            <div class="product-carousel-item__img">
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


</script>
<form id="addToCartForm" class="add_to_cart_form" action="<c:url value="/cart/add"/>" method="post">
    <%--span class="prod_results">
        <product:productFutureAvailability product="${product}" futureStockEnabled="${futureStockEnabled}" />
    </span--%>
    <c:if test="${(product.purchasable) || (true)}">
        <label for="qty" class="g-italic">Кол-во:</label>
        <input type="text" value="1" id="qty" name="qty" class="g-input" size="2" />
    </c:if>
    <input type="hidden" name="productCodePost" value="${product.code}"/>

    <c:set var="buttonType">button</c:set>
    <c:if test="${allowAddToCart and product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
        <c:set var="buttonType">submit</c:set>
    </c:if>    

    <%--- ПЕРЕВЕРСТАТЬ! криво очень --%>
    <script>
        function changeprice(a) {
            pos = a.indexOf(".");
            if (pos > 0) { 		a = a + "00"; }
            aCel = a.substr(0,pos);
            aDr  = a.substr(pos+1,2);
            a = aCel + "." + aDr;
            document.getElementById('spanprice').innerHTML = a.replace(/\d(?=(\d{3})+\.)/g, '$&,') + " <span class='g-rouble'>Р<span>";
        }
    </script>

    <!--${product.baseUnit.code}]-->

    <c:if test="${not empty product.units}">
    <select onChange="changeprice(this.value)" style="width:50px" id="priceUnits">
        <c:forEach items="${product.units}" var="unit" varStatus="status">
            <c:set var="price" value="${product.price.value*product.unitsMap[unit.code]}"/>
            <c:choose>
                <c:when test="${product.unitsMap[unit.code] == 1}">
                  <c:set var="unitdefault" value="selected"/>
                </c:when>
                <c:otherwise>
                  <c:set var="unitdefault" value=""/>
                </c:otherwise>
            </c:choose>
            <option value="${price}" ${unitdefault} data-coefficient="${product.unitsMap[unit.code]}">${unit.name}</option>
        </c:forEach>
    </select>
    </c:if>

    <button id="addToCartButton" data-min-quantity="${product.minOrderQuantity}" data-base-to-sales="${product.unitsMap[product.salesUnit.code]}" type="${buttonType}" disabled="true" class="button add_to_cart_button <c:if test="${fn:contains(buttonType, 'button')}">button_disabled</c:if>">
        В корзину
    </button>

    <c:if test="${multiDimensionalProduct}" >
        <sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
            <c:url value="${product.url}/orderForm" var="productOrderFormUrl"/>
            <a href="${productOrderFormUrl}" ><spring:theme code="order.form" /></a>
        </sec:authorize>
    </c:if>
    <div class="cart-popup"></div>
</form>
