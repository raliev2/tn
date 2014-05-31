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
                    <li class="inner-menu__item"><a href="/store/firstplatform/ru/RUB/строительные-материалы/плоские-кровли/рулонные-кровельные-материалы/c/rulonnye_krovelnye_materialy">Рулонные кровельные материалы</a></li>
                    <li class="inner-menu__item"><a href="/store/firstplatform/ru/RUB/%D1%81%D1%82%D1%80%D0%BE%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5-%D0%BC%D0%B0%D1%82%D0%B5%D1%80%D0%B8%D0%B0%D0%BB%D1%8B/c/stroitelnye_materialy">Строительные материалы</a></li>
                    <li class="inner-menu__item"><a href="/store/firstplatform/ru/RUB/строительные-материалы/плоские-кровли/полимерные-мембраны-и-аксессуары/c/polimernye_membrany_i_aksessuary">Полимерные мембраны и аксессуары</a></li>
                    <li class="inner-menu__item"><a href="/store/firstplatform/ru/RUB/строительные-материалы/скатные-кровли/гибкая-черепица-и-аксессуары/гибкая-черепица/c/gibkaja_cherepica">Гибкая черепица</a></li>
                    <li class="inner-menu__item"><a href="/store/firstplatform/ru/RUB/строительные-материалы/скатные-кровли/металлочерепица-и-аксессуары/металлочерепица/c/metallocherepica">Металлочерепица</a></li>
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
    <header:searchBox/>
</nav>

<div class="fast-links fast-links_notlogin clearfix">
    <div class="header-banner">
        <c:url value="/строительные-материалы/плоские-кровли/мастики-и-праймеры/мастики-битумные/Герметик-бутил-каучуковый-ТехноНИКОЛЬ-№45-%28белый%29%2C-ведро-16/p/395727" var="headerBannerUrl" />
        <a href="${headerBannerUrl}">
            <img src="${themeResourcePath}/images/banners/header-banner.png" alt="Герметик бутил-каучуковый ТехноНИКОЛЬ №45 (белый), ведро 16" />
        </a>
    </div>

</div>