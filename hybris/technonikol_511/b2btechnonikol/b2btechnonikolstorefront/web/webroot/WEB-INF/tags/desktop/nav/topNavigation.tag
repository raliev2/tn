<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header" %>

<nav class="clearfix">
    <div class="menu-container">
    <ul class="menu">
        <li class="menu__item">
            <a href="javascript:void(0)" class="item-menu__link">каталог</a>
        </li>
        <li class="menu__item"><a href="javascript:void(0)" class="item-menu__link">сервисы</a></li>
        <li class="menu__item">
            <a href="javascript:void(0)" class="item-menu__link">статьи</a>
        </li>
        <li class="menu__item"><a href="javascript:void(0)" class="item-menu__link">о компании</a></li>
    </ul>
    </div>
    <header:searchBox/>
</nav>

<div class="fast-links fast-links_notlogin clearfix">
    <div class="header-banner">
        <c:url value="/строительные-материалы/плоские-кровли/мастики-и-праймеры/мастики-битумные/Герметик-бутил-каучуковый-ТехноНИКОЛЬ-№45-%28белый%29%2C-ведро-16/p/395727" var="headerBannerUrl" />
        <a href="${headerBannerUrl}">
            <img src="${themeResourcePath}/images/banners/header-banner.png" alt="Новое руководство для Motormatch уже тут" />
        </a>
    </div>

</div>