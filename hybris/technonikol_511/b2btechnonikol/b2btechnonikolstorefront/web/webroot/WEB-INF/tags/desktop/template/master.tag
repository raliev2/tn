<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="metaDescription" required="false" %>
<%@ attribute name="metaKeywords" required="false" %>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="analytics" tagdir="/WEB-INF/tags/shared/analytics" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <title><spring:theme code="site.title"/></title>

    <link rel="stylesheet/less" type="text/css" href="${themeResourcePath}/css/reset.less" />
    <link rel="stylesheet/less" type="text/css" href="${themeResourcePath}/css/style.less" />
    <link rel="stylesheet" type="text/css" href="${themeResourcePath}/css/anythingslider.css" />
    <link rel="stylesheet" type="text/css" href="${themeResourcePath}/css/jquery.rating.css" />
    <link rel="stylesheet" type="text/css" href="${themeResourcePath}/css/easyzoom.css">


    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"  type="text/javascript"></script>
    <script>if( !window.jQuery )document.write('<script src="${themeResourcePath}/js/lib/jquery-1.11.1.min.js" charset="utf-8"><'+'/script>');</script>
    <script>
        var themeResourcePath = '${themeResourcePath}';
    </script>
    <script src="${themeResourcePath}/js/lib/less-1.3.3.min.js" type="text/javascript"></script>

    <!--[if lt IE 9]>
        <script type="text/javascript" src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <template:javaScriptVariables/>

</head>
<body>
<div class="g-wrapper">
    <noscript>Ваш браузер не поддерживает скрипты</noscript>

    <jsp:doBody/>

		<template:javaScript />
		<jsp:invoke fragment="pageScripts"/>

		<script type="text/javascript" src="${commonResourcePath}/js/jquery.pstrength.custom-1.2.0.js"></script>
		<script type="text/javascript">
			/*<![CDATA[*/
				$(function() {
					$('.strength').pstrength({ verdicts:["<spring:theme code="password.strength.veryweak" />",
														 "<spring:theme code="password.strength.weak" />",
														 "<spring:theme code="password.strength.medium" />",
														 "<spring:theme code="password.strength.strong" />",
														 "<spring:theme code="password.strength.verystrong" />"],
											   tooShort: "<spring:theme code="password.strength.tooshortpwd" />",
											   minCharText: "<spring:theme code="password.strength.minchartext"/>" });
				});
			/*]]>*/
		</script>
    <script type='text/javascript' src='${themeResourcePath}/js/lib/easyzoom.js'></script>
    <script type='text/javascript' src='${themeResourcePath}/js/jquery.sliderImages.js'></script>
    <script type="text/javascript" src="${themeResourcePath}/js/lib/jquery.rating-2.0.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/js/lib/jquery.anythingslider.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/js/lib/easypaginate.js"></script>
    <script type='text/javascript' src="${themeResourcePath}/js/lib/jquery.easing.1.2.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/js/scripts.js"></script>
</div>

</body>
</html>
