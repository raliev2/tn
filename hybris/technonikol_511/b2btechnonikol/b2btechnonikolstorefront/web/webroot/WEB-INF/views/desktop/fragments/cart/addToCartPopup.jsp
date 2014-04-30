<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="json" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

{
	"cartData": {
		"total":	"${cartData.totalPrice.value}",
		"products":	[ <c:forEach items="${cartData.entries}" var="cartEntry" varStatus="status">
						{
							"code":			"${cartEntry.product.code}",
							"name":			"<c:out value='${cartEntry.product.name}' />",
							"quantity":		"${cartEntry.quantity}",
							"price":		"${cartEntry.basePrice.value}",
							"categories":	[<c:forEach items="${cartEntry.product.categories}" var="category" varStatus="categoryStatus">
												"<c:out value='${category.name}' />"<c:if test="${not categoryStatus.last}">,</c:if>
											</c:forEach>]
						}<c:if test="${not status.last}">,</c:if>
					</c:forEach>]
	},

	"cartGlobalMessagesHtml" : "<spring:escapeBody javaScriptEscape="true"><common:globalMessages/></spring:escapeBody>"
	,

	"cartPopupHtml": "<spring:escapeBody javaScriptEscape="true">
						<spring:theme code="text.addToCart" var="addToCartText"/>
						<c:url value="/cart" var="cartUrl"/>
						<c:url value="/cart/checkout" var="checkoutUrl"/>

						<div class="title">
							<theme:image code="img.addToCartIcon" alt="${addToCartText}" title="${addToCartText}"/>
							<h3> <spring:theme code="basket.added.to.basket" /></h3>
							<a href="#" class="close" id="add_to_cart_close"></a>
						</div>
				
						
						<c:if test="${numberShowing > 0 and fn:length(products) > numberShowing}">
							<p class="legend">
								<spring:theme code="popup.cart.showing" arguments="${numberShowing},${fn:length(products)}"/>
								<c:if test="${fn:length(products) > numberShowing}">
									<a href="${cartUrl}">Show All</a>
								</c:if>
							</p>
						</c:if>

						<c:forEach items="${products}" var="product" end="${numberShowing - 1}">
						<div class="cart_modal_popup">
							<div class="cart_popup_error_msg"><spring:theme code="${errorMsg}" /></div>
							<div class="prod_image">
								<product:productPrimaryImage product="${product}" format="cartIcon"/>
							</div>
							<div class="prod_info">
								<p><c:out value="${product.name}" /></p>
								<p class="prod_options">
									<c:forEach items="${product.baseOptions}" var="baseOptions">
										<c:forEach items="${baseOptions.selected.variantOptionQualifiers}" var="baseOptionQualifier">
											<c:if test="${baseOptionQualifier.qualifier eq 'style' and not empty baseOptionQualifier.image.url}">
												<span class="prod_color">
													<spring:theme code="product.variants.colour"/>&nbsp;
													<img src="${baseOptionQualifier.image.url}"  alt="${baseOptionQualifier.value}" title="${baseOptionQualifier.value}"/>
												</span>
											</c:if>
											<c:if test="${baseOptionQualifier.qualifier eq 'size'}">
												<span class="prod_size"><spring:theme code="product.variants.size"/>&nbsp;${baseOptionQualifier.value}</span>
											</c:if>
										</c:forEach>
									</c:forEach>
								</p>
								<p class="prod_quantity"><spring:theme code="popup.cart.quantity.added"/>&nbsp;${quantity}</p>
								<div class="prod_price">
									<p><format:price priceData="${entry.basePrice}"/></p>
								</div>
							</div>
						</div>
						</c:forEach>
						<div class="links">
							<a href="${cartUrl}" class="positive large">
								<theme:image code="img.addToCartIcon" alt="${addToCartText}" title="${addToCartText}"/>
								<spring:theme code="checkout.checkout" />
							</a>
						</div>
					</spring:escapeBody>"
}
