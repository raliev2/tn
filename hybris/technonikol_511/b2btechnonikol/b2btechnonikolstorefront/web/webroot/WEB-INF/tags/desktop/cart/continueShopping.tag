<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<%@ attribute name="continueShoppingUrl" required="true" type="java.lang.String"%>

<a href="${continueShoppingUrl}" class="g-button-black g-button-black_high">
    Продолжить покупки
</a>
