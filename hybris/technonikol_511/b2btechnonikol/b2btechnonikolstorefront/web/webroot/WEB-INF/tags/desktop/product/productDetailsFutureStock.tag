<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="futureStocks" required="true" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
      
<div>
	<common:globalMessages/>

	<span class="popup_header"><spring:theme code="product.product.details.future.title" /></span>

	<div class="prod_list" >
	<a href="${productUrl}" title="${product.name}" class="productMainLink">
		<div class="thumb">
			<c:if test="${not empty product.averageRating}">
				<span class="stars large" style="display: inherit;">
					<span style="width: <fmt:formatNumber maxFractionDigits="0" value="${product.averageRating * 24}" />px;"></span>
				</span>
			</c:if>

			<product:productPrimaryImage product="${product}" format="thumbnail"/>
			<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
				<img class="promo" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
			</c:if>
		</div>

		<div class="details">
			<ycommerce:testId code="searchPage_productName_link_${product.code}">
					<div class="pd_name">${product.name}</div>
			</ycommerce:testId>
	
			<c:if test="${not empty product.summary}">
				<p>${product.summary}</p>
			</c:if>			
		</div>
	</a>
	</div>

	<div align="center">
		<c:if test="${not empty futureStocks}">
			<table>
				<tr>
					<th><spring:theme code="product.product.details.future.date" /></th>
					<th><spring:theme code="product.product.details.future.quantity" /></th>
				</tr>
				<c:forEach items="${futureStocks}" var="futureStock" varStatus="status">
					<tr>
						<td>${futureStock.formattedDate}</td>
						<td>${futureStock.stock.stockLevel}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>
