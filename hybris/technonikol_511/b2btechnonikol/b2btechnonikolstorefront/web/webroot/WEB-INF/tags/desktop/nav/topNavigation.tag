<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header" %>

<nav class="clearfix">
    <div class="menu-container">
    <ul class="menu">
        <li class="menu__item">
            <div class="item-menu__position">
                <a href="javascript:void(0)" class="item-menu__link">каталог</a>
                <ul class="item-menu__inner-menu js-inner-menu">
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
                </ul>
            </div>
        </li>
        <li class="menu__item"><a href="javascript:void(0)" class="item-menu__link">сервисы</a></li>
        <li class="menu__item">
            <a href="javascript:void(0)" class="item-menu__link">статьи</a>
        </li>
        <li class="menu__item"><a href="javascript:void(0)" class="item-menu__link">о компании</a></li>
    </ul>
    </div>
    <cms:pageSlot position="SearchBox" var="component">
        <cms:component component="${component}"/>
    </cms:pageSlot>
</nav>

<div class="fast-links fast-links_notlogin clearfix">
    <div class="header-banner">
        <c:url value="/строительные-материалы/плоские-кровли/мастики-и-праймеры/мастики-битумные/Герметик-бутил-каучуковый-ТехноНИКОЛЬ-№45-%28белый%29%2C-ведро-16/p/395727" var="headerBannerUrl" />
        <a href="${headerBannerUrl}">
            <img src="${themeResourcePath}/images/banners/header-banner.png" alt="Герметик бутил-каучуковый ТехноНИКОЛЬ №45 (белый), ведро 16" />
        </a>
    </div>

</div>