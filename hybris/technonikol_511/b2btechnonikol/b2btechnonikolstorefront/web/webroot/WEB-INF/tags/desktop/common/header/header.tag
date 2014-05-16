<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<header class="clearfix">
    <div class="logo">
        <a href="javascript:void(0)"><img src="${themeResourcePath}/images/logo.png" alt="ТЕХНОНИКОЛЬ" title="ТЕХНОНИКОЛЬ" /></a>
        Для тех, кто делает дело
    </div>
    <div class="g-float-right">
        <div class="auth_links">
            <ul>
                <li class="hello">Здравствуйте, Антон</li>
                <li><a href="javascript:void(0)">Вы не Антон?</a></li>
                <li><a href="javascript:void(0)">Выйти</a></li>
                <li><a href="javascript:void(0)">Обслуживание клиентов</a></li>
            </ul>
        </div>
        <div class="custom-message">
            <a href="javascript:void(0)">[+] Что вы думаете о новой версии сайта 1 Платформа?<span class="web-symbols">&#215;</span></a>
        </div>
        <div>
            <a href="javascript:void(0)" class="gray-link">Каталог</a>
            <a href="javascript:void(0)" class="link-cart">В корзине (2) товара</a>
        </div>
    </div>
</header>
