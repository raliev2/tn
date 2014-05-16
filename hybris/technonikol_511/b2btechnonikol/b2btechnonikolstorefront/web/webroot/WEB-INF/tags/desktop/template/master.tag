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
    <title>Первая платформа</title>
    <link rel="stylesheet" type="text/css" href="${themeResourcePath}/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${themeResourcePath}/css/style.css" />
    <script src="${themeResourcePath}/js/lib/less-1.3.3.min.js" type="text/javascript"></script>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"  type="text/javascript"></script>
    <script>if( !window.jQuery )document.write('<script src="js/lib/jquery-1.11.1.min.js" charset="utf-8"><'+'/script>');</script>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="${themeResourcePath}/js/lib/jquery.easing.1.2.js"></script>
    <link rel="stylesheet" href="${themeResourcePath}/css/anythingslider.css">
    <script type="text/javascript" src="${themeResourcePath}/js/lib/jquery.anythingslider.js"></script>
    <script type="text/javascript" src="${themeResourcePath}/js/lib/easypaginate.min.js"></script>
</head>
<body>
<div id="wrapper">

		<jsp:doBody/>

</div>
<script src="${themeResourcePath}/js/scripts.js"></script>
</body>
</html>		
