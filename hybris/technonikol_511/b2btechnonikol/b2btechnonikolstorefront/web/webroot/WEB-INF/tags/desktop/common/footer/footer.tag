<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<footer class="clearfix">
    <div class="g-gradient-gray"></div>
    <div class="col-links">
        <h6>Обслуживание клиентов</h6>
        <ul>
            <li class="footer__link"><a href="javascript:void(0)">Заказать печатный каталог</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Форма обратной связи</a></li>
            <li class="footer__link"><a href="javascript:void(0)">География присутствия</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Карта сайта</a></li>
        </ul>
        <div class="g-text">Есть вопросы? Позвоните нам<br /><span class="footer__telephone"><spring:theme code="common.telephone" /></span></div>
    </div>
    <div class="col-links">
        <h6>Поддержка</h6>
        <ul>
            <li class="footer__link"><a href="javascript:void(0)">Заказ</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Доставка</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Возврат</a></li>
        </ul>
    </div>
    <div class="col-links">
        <h6>О компании</h6>
        <ul>
            <li class="footer__link"><a href="javascript:void(0)">О нас</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Контакты</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Вакансии</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Политика конфиденциальности</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Социальная ответственность</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Как стать поставщиком</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Пресс-центр</a></li>
        </ul>
    </div>
    <div class="col-links">
        <h6>Онлайн-сервисы</h6>
        <ul>
            <li class="footer__link"><a href="javascript:void(0)">Запасные части</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Вебинары</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Заявка на кредитный лимит</a></li>
            <li class="footer__link"><a href="javascript:void(0)">Кровельный калькулятор</a></li>
        </ul>
    </div>
    <div class="col-links">
        <h6>Подписка на рассылку</h6>
        <div class="g-text">Получать новости отрасли, информацию о новинках, полезные советы и многое другое</div>
        <a href="javascript:void(0)" class="footer__button-register g-button-black">Подписаться</a>
        <h6>Мы в социальных сетях</h6>
        <div class="socials clearfix">
            <ul>
                <li class="socials__item socials__item_facebook"><a href="javascript:void(0)"></a></li>
                <li class="socials__item socials__item_twitter"><a href="javascript:void(0)"></a></li>
                <li class="socials__item socials__item_linkedin"><a href="javascript:void(0)"></a></li>
                <li class="socials__item socials__item_youtube"><a href="javascript:void(0)"></a></li>
            </ul>
        </div>
    </div>
    <div class="clearfix"></div>
    <%--<div class="footer-legal">2013 - 2014, 1Платформа. Разработка сайта - TeamIdea.</div>--%>
    <div class="modal-window"><div class="modal-wrapper"><div class="modal-window__close"></div><div class="modal-window-content"></div></div></div>
    <c:url value="/cart" var="cartUrl"/>
    <script type="text/plain" id="addToCartTmpl">
        <div class="cart-popup__header clearfix">
            <div class="cart-popup__mesage">{{=it.message}}</div>
            <div class="cart-popup__tocart g-float-right"><a href="${cartUrl}">Посмотреть корзину</a></div>
        </div>
        <div class=cart-popup__body>
                    <div class="cart-popup-carousel__header">Также покупают:</div>
                    {{=it.productReference}}
        </div>
    </script>
    <div class="cart-popup"></div>
    <script>
        <c:url value="/checkout/multi/check_product?productCode=" var="check_stock_url"/>
        <c:url value="/checkout/multi/check-products" var="check_stocks_url"/>
        var check_stock_url='${check_stock_url}';
        var check_stocks_url='${check_stocks_url}';
    </script>
</footer>
