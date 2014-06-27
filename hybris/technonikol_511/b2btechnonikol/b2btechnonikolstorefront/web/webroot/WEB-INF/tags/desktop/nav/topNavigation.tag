<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header" %>

<style type="text/css">
ul.columns { margin: 0; padding: 0; list-style-type: none; word-spacing: -1ex; }
/* 
    Firefox ниже версии 3 не знает о inline-block.
    Поэтому указываем для него -moz-inline-stack
*/
.columns li { display: -moz-inline-stack; display: inline-block; //display: inline; }
/*
    Чтобы ссылки не нажимались кликом
    по пустому месту, применим фильтр
*/
.columns a, .columns b { display: inline; word-spacing: normal; //display: inline-block; //filter: alpha(opacity=100); }
.columns a, .columns b, x:-moz-any-link { display:block; }
/*
    Если Firefox выше 2.0, Возвращаем a и b в inline
*/
.columns a, .columns b, x:-moz-any-link, x:default { display: inline; }
.columns li, .columns a, .columns b { vertical-align: top; }

/* customize */
.columns li { margin: 0 10% 0.4em 0; //margin: 0; width: 40%; //width: auto; }
.columns a, .columns b { //margin: 0 10% 0.4em 0; //width: 40%; }
.columns ul { width: 100%; }
</style>

<nav class="clearfix">
    <div class="menu-container">
    <ul class="menu">
        <li class="menu__item">
            <div class="item-menu__position">
                <c:url var="catalog" value="/all-categories" />
                <a href="${catalog}" class="item-menu__link">каталог</a>
                <ul class="item-menu__inner-menu js-inner-menu columns">
                    <li class="inner-menu__item"></li>
                    <li class="inner-menu__item"></li>
                    <li class="inner-menu__item"><a href="${contextPath}/c/metallocherepica">Металлочерепица</a></li>
<li class="inner-menu__item"><a href="${contextPath}/c/ploskie_krovli">Плоские кровли
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/skatnye_krovli">Скатные кровли
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/komplektujushhie_dlja_skatnoj_krovli">Комплектующие для скатной кровли
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/drevesnye_materialy">Древесные материалы
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/gidro-_i_paroizoljacionnye_plenki">Гидро-и пароизоляционные пленки
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/utepliteli">Утеплители
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/zabory_i_ograzhdenija">Заборы и ограждения
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/fasady_i_stenovye_materialy">Фасады и стеновые материалы
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/fundamenty_gidroizoljacija">Фундаменты, гидроизоляция
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/transportnoe_i_dorozhnoe_stroitelstvo">Транспортное и дорожное строительство
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/ustrojstvo_pridomovoj_territorii_landshaft">Устройство придомовой территории, ландшафт</a>
</li><li class="inner-menu__item"><a href="${contextPath}/c/materialy_dlja_vnutrennej_otdelki">Материалы для внутренней отделки
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/dymohodnye_sistemy_i_ventiljacionnye_kanaly">Дымовые системы и вентиляционные каналы
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/vorotnye_sistemy">Воротные системы
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/bassejny">Бассейны
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/svetotehnika">Светотехника
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/kabelno-provodnikovaya_produktsiya">Кабельно-проводниковая продукция
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/kabelenesuschie_sistemy">Кабеленесущие системы
</a></li><li class="inner-menu__item"><a href="${contextPath}/c/elektroschitovoe_oborudovanie">Низковольтное и электрощитовое оборудование</a></li>
                   <li class="inner-menu__item"></li>
                   <li class="inner-menu__item"></li>

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