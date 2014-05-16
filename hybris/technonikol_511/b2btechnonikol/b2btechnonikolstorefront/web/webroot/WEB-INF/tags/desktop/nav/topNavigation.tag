<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<nav class="clearfix">
    <div class="menu-container">
    <ul class="menu">
        <li class="menu__item">
            <a href="javascript:void(0)" class="item-menu__link">продукция</a>
                <menu class="item-menu__inner-menu js-inner-menu">
                    <li class="inner-menu__item"><a href="javascript:void(0)">Custom Product Center</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Emergency Preparedness</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Exclusive Brands</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Government</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Green Resources</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Industries</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Info Library</a></li>
                </menu>
        </li>
        <li class="menu__item"><a href="javascript:void(0)" class="item-menu__link">услуги</a></li>
        <li class="menu__item">
            <a href="javascript:void(0)" class="item-menu__link">ресурсы</a>
                <menu class="item-menu__inner-menu js-inner-menu">
                    <li class="inner-menu__item"><a href="javascript:void(0)">Custom Product Center</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Emergency Preparedness</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Exclusive Brands</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Government</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Green Resources</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Industries</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Info Library</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Product Resources</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Productivity</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Safety Resources</a></li>
                    <li class="inner-menu__item"><a href="javascript:void(0)">Small Business</a></li>
                </menu>
        </li>
        <li class="menu__item"><a href="javascript:void(0)" class="item-menu__link">запчасти</a></li>
        <li class="menu__item"><a href="javascript:void(0)" class="item-menu__link">мы в сети</a></li>
    </ul>
    </div>
    <div class="form-search">
        <input type="text" placeholder="Введите товар или артикул" class="form-search__input" />
        <a href="javascript:void(0)" class="button">Поиск</a>
    </div>
</nav>

<div class="fast-links fast-links_notlogin clearfix">
    <div class="header-banner">
        <a href="javascript:void(0)"><img src="${themeResourcePath}/images/banners/header-banner.png" alt="Новое руководство для Motormatch уже тут" /></a>
    </div>
    <div class="fast-order">
        <a href="javascript:void(0)" class="fast-order__button button">Быстрый заказ <span class="web-symbols">&#203;</span></a>
        <div class="modal-fast-order g-modal-for-close">
            <div class="modal-fast-order__head">
                <div class="fast-order-head__header">Быстрый заказ</div>
                <div class="g-close"><a href="javascript:void(0)">Закрыть</a></div>
            </div>
            <div class="modal-fast-order__body modal-fast-order__body_write-here">
                <div class="fast-order-body__text"><span class="g-strong">Пишите тут</span></div>
                <div class="fast-order-link"><a href="javascript:void(0)" class="js-fast-order-cp">Скопировать и вставить</a></div>
                <div class="clearfix"></div>
                <table class="fast-order-body__table" cellpadding="0" cellspacing="0">
                    <thead>
                    <td>Артикул</td>
                    <td>Кол-во</td>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="text" class="fast-order-table__input" /></td>
                        <td><input type="text" class="fast-order-table__input" value="1" /></td>
                    </tr>
                    <tr>
                        <td><input type="text" class="fast-order-table__input" /></td>
                        <td><input type="text" class="fast-order-table__input" value="1" /></td>
                    </tr>
                    <tr>
                        <td><input type="text" class="fast-order-table__input" /></td>
                        <td><input type="text" class="fast-order-table__input" value="1" /></td>
                    </tr>
                    <tr>
                        <td><input type="text" class="fast-order-table__input" /></td>
                        <td><input type="text" class="fast-order-table__input" value="1" /></td>
                    </tr>
                    <tr>
                        <td><input type="text" class="fast-order-table__input" /></td>
                        <td><input type="text" class="fast-order-table__input" value="1" /></td>
                    </tr>
                    </tbody>
                </table>
                <div class="fast-order-link"><a href="javascript:void(0)">Показать больше полей</a></div>
                <div class="clearfix"></div>
            </div>
            <div class="modal-fast-order__body modal-fast-order__body_copy-paste clearfix">
                <div class="fast-order-body__text"><span class="g-strong">Копировать / вставить</span></div>
                <div class="fast-order-link"><a href="javascript:void(0)" class="js-fast-order-wrh">Поля ввода</a></div>
                <div class="clearfix"></div>
                <p class="fast-order-body__text"><span class="g-strong">Хотите заказать быстрее?</span><br />
                    Просто копия и паста пункты Grainger от Ваш файл в области ниже использования следующий формат:</p>
                <p class="fast-order-body__format fast-order-body__text"><span class="g-strong">Количество</span> [TAB или запятая] <span class="g-strong">товар</span></p>
                <textarea class="fast-order-body__textarea" placeholder="Просто копия и паста пункты Grainger от Ваш файл в области ниже использования следующий формат"></textarea>
            </div>
            <div class="fast-order-footer">
                <a href="javascript:void(0)" class="button fast-order-footer__button">В корзину</a>
            </div>
        </div>
    </div>
</div>