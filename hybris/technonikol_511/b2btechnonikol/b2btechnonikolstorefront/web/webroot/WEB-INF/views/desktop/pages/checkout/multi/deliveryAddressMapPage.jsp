<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<template:page pageTitle="${currentStep.name}">
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>
    <script>
        var points = new Array(
                {
                    address : '${pointsOfService[0].address.formattedAddress}',
                    mode : '<c:forEach items="${pointsOfService[0].openingHours.weekDayOpeningList}" var="weekDayOpening">\
                                ${weekDayOpening.weekDay} <br/>\
                                ${weekDayOpening.closed ? "закрыто!" : "не очень закрыто!"} <br/>\
                            </c:forEach>',
                    lat : ${pointsOfService[0].geoPoint.latitude},
                    lng : ${pointsOfService[0].geoPoint.longitude}
                }
                <c:forEach items="${pointsOfService}" var="store" varStatus="varstatus" begin="1">
                    ,{
                        address : '${store.address.formattedAddress}',
                        mode : '<c:forEach items="${store.openingHours.weekDayOpeningList}" var="weekDayOpening">\
                                    ${weekDayOpening.weekDay} <br/>\
                                    ${weekDayOpening.closed ? "закрыто!" : "не очень закрыто!"} <br/>\
                                </c:forEach>',
                    lat : ${store.geoPoint.latitude},
                    lng : ${store.geoPoint.longitude}
                    }
                </c:forEach>
        );
        var markers = new Array();
        $(document).ready(function() {
            $("#checkout__map").css({
                height: 320,
                width: 620
            });
            var myLatLng = new google.maps.LatLng(points[0]['lat'], points[0]['lng']);
            MYMAP.init('#checkout__map', myLatLng, 13);

            MYMAP.placeMarkers();

            $('select[name="selectedShopAddress"]').change(function() {
                var index = $('select[name="selectedShopAddress"] option:selected').attr('rel');
                $('.js-address').text(points[index].address);
                $('.js-mode').text(points[index].mode);
                markers.forEach(function(item,ind) {
                    if (ind == index) {
                        item.setIcon('${themeResourcePath}/images/map-current-point.png');
                        if (points[ind]['lat'] != '' && points[ind]['lng'] != '') {
                            var myLatLng = new google.maps.LatLng(points[ind]['lat'], points[ind]['lng']);
                        }
                        MYMAP.map.setCenter(myLatLng);
                    } else {
                        item.setIcon('${themeResourcePath}/images/map-point.png');
                    }
                });
            });


        });

        var MYMAP = {
            map: null,
            bounds: null
        }

        MYMAP.init = function(selector, latLng, zoom) {
            var myOptions = {
                mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            this.map = new google.maps.Map($(selector)[0], myOptions);
            this.bounds = new google.maps.LatLngBounds();
        }

        MYMAP.placeMarkers = function() {
            points.forEach(function(item,index) {
                var address = item.address;

                // create a new LatLng point for the marker
                var lat = item.lat;
                var lng = item.lng;
                if (lat != '' && lng != '') {
                    var point = new google.maps.LatLng(parseFloat(lat), parseFloat(lng));

                    // extend the bounds to include the new point
                    MYMAP.bounds.extend(point);

                    if (index == 0) var icon = '${themeResourcePath}/images/map-current-point.png';
                    else icon = '${themeResourcePath}/images/map-point.png';
                    var marker = new google.maps.Marker({
                        position: point,
                        map: MYMAP.map,
                        icon: icon
                    });
                    markers.push(marker);
                    var infoWindow = new google.maps.InfoWindow();
                    var html = address;
                    google.maps.event.addListener(marker, 'click', function () {
                        infoWindow.setContent(html);
                        infoWindow.open(MYMAP.map, marker);
                    });
                    MYMAP.map.fitBounds(MYMAP.bounds);
                }
            });
        }
    </script>
    <section class="g-main-content checkout">
        <div id="globalMessages">
            <common:globalMessages />
        </div>
        <div class="checkout__steps clearfix">
            <ul class="checkou-steps__list clearfix g-float-left">
                <li class="checkout__step checkout__step_current"><a href="javascript:void(0)">Адрес доставки</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Способ доставки</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Способ оплаты</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Проверка заказа</a></li>
                <li class="checkout__step"><a href="javascript:void(0)">Готово</a></li>
            </ul>
        </div>
        <h1 class="checkout__head"><spring:theme code="${currentStep.name}"/></h1>
        <p class="g-strong">Клиентское обслуживание в любое время суток</p>
        <p style="margin-bottom:20px">По вопросам и проблемам с доставкой - звонить на номер <spring:theme code="common.telephone" /></p>
        <c:url value="/checkout/multi${currentStep.next.url}" var="next_url" />
        <form method="post" action="${next_url}">
            <div class="checkout__wrapper clearfix">
                <div class="checkout__body">
                    <div class="checkout__row">
                        <label for="selectedShopAddress" class="checkout__label">Выберите адрес магазина</label>
                        <select id="selectedShopAddress" name="selectedStore" class="checkout__select">
                            <c:forEach items="${pointsOfService}" var="store" varStatus="varstatus">
                                <option value="${store.name}" rel="${varstatus.index}">${store.address.formattedAddress}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="checkout__map" id="checkout__map"></div>
                <p class="g-strong js-address">${pointsOfService[0].address.formattedAddress}</p>
                <p class="checkout__mode">Режим работы: <span class="g-strong js-mode">
                    <c:forEach items="${pointsOfService[0].openingHours.weekDayOpeningList}" var="weekDayOpening">\
                        ${weekDayOpening.weekDay} <br/>\
                        ${weekDayOpening.closed ? "закрыто!" : "не очень закрыто!"} <br/>\
                    </c:forEach>
                </span></p>
                <input type="submit" value="Далее" class="button button_big g-float-right" />
                <div class="g-float-right checkout__back"><a href="javascript:history.go(-1)" class="g-link-blue">Назад</a></div>
            </div>
        </form>
    </section>
</template:page>