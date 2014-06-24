<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<c:choose>
	<c:when test="${empty product.variantOptions}">
		<c:set var="allowAddToCart" value="${true}"/>
	</c:when>
	<c:otherwise>
		<c:set var="allowAddToCart" value="${false}"/>
	</c:otherwise>
</c:choose>

<c:url value="/cart" var="cartUrl"/>

<form id="addToCartForm" class="add_to_cart_form" action="<c:url value="/cart/add"/>" method="post">
    <%--span class="prod_results">
        <product:productFutureAvailability product="${product}" futureStockEnabled="${futureStockEnabled}" />
    </span--%>
    <%-- ПЕРЕДЕЛАТЬ! СДЕЛАНО НА СКОРУЮ РУКУ (rauf) --%>
    <%--
    <script>
      function changeqty(a)
        {
              var priceUnits = document.getElementById('priceUnits');
              if (priceUnits == null) {
                  var factor = 1;
              } else {
                  factor = priceUnits.options[priceUnits.selectedIndex].id + 0;
              }
              document.getElementById ('qty').value = Math.ceil(a * factor);
              document.getElementById ('cartquantitynumber').innerHTML = document.getElementById ('qty').value;
        }
    </script>
    --%>
    <c:if test="${(product.purchasable) || (true)}">
        <label for="qty" class="g-italic">Кол-во:</label>

	<%-- Rauf / после убирания списка единиц измерения это уже нужно делать проще
        <input type="hidden" value="1" id="qty" name="qty" class="g-input"  />
        <input type="text" value="1" id="realqty" name="realqty" class="g-input" size="2" maxlength="6" onkeyup="changeqty(this.value)" />
        --%>
	<input 
		type="text" 
		id="qty" 
		name="qty" 
		class="g-input"
		onChange="checkqty('qty', '${product.minOrderQuantity}')" 
		value="<c:choose><c:when test="${not empty product.minOrderQuantity}">${product.minOrderQuantity}</c:when><c:otherwise>1</c:otherwise></c:choose>"
		size="2" 
		maxlength="6"
	/>

    </c:if>
    <input type="hidden" name="productCodePost" value="${product.code}"/>

    <c:set var="buttonType">button</c:set>
    <c:if test="${allowAddToCart and product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
        <c:set var="buttonType">submit</c:set>
    </c:if>    

    <%--- ПЕРЕВЕРСТАТЬ! криво очень --%>
    <script>
        function changeprice(a,b) {
            pos = a.indexOf(".");
            if (pos > 0) { 		a = a + "00"; }
            aCel = a.substr(0,pos);
            aDr  = a.substr(pos+1,2);
            a = aCel + "." + aDr;
            document.getElementById ('spanprice').innerHTML = a.replace(/\d(?=(\d{3})+\.)/g, '$&,') + " <span class='g-rouble'>Р<span>";
            document.getElementById ('cartquantitynumber').innerHTML = Math.ceil(b * document.getElementById ('realqty').value);
            changeqty(document.getElementById ('realqty').value);
        }
    </script>

    <!--${product.baseUnit.code}]-->

    <c:if test="${not empty product.units}">
    <select style="display:none" onChange="changeprice(this.value, this.options[this.selectedIndex].id)" style="width:50px" id="priceUnits">
        <c:forEach items="${product.units}" var="unit" varStatus="status">
            <c:set var="price" value="${product.price.value*product.unitsMap[unit.code]}"/>
            <c:choose>
                <c:when test="${product.unitsMap[unit.code] == 1}">
                  <c:set var="unitdefault" value="selected"/>
                  <c:set var="defaultunitname" value="${unit.name}"/>
                </c:when>
                <c:otherwise>
                  <c:set var="unitdefault" value=""/>
                </c:otherwise>
            </c:choose>
            <option value="${price}" id="${product.unitsMap[unit.code]}" ${unitdefault} data-coefficient="${product.unitsMap[unit.code]}">${unit.name}</option>
        </c:forEach>
    </select>
    </c:if>

    <button id="addToCartButton" data-min-quantity="${product.minOrderQuantity}" data-base-to-sales="${product.unitsMap[product.salesUnit.code]}" type="${buttonType}" disabled="true" class="button add_to_cart_button <c:if test="${fn:contains(buttonType, 'button')}">button_disabled</c:if>">
        В корзину
    </button>
    <div id="cartquantityinfo" style="color:gray;margin-top:7px;">
    <%-- ПЕРЕВЕРСТАТЬ --%>
    <%--- корзину будет добавлено <span id="cartquantitynumber">1</span> ${defaultunitname}.--%>
    </div>
    <c:if test="${multiDimensionalProduct}" >
        <sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
            <c:url value="${product.url}/orderForm" var="productOrderFormUrl"/>
            <a href="${productOrderFormUrl}" ><spring:theme code="order.form" /></a>
        </sec:authorize>
    </c:if>
</form>
