<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function parseGetParams() {
        var $_GET = {};
        var __GET = window.location.search.substring(1).split("&");
        for(var i=0; i<__GET.length; i++) {
            var getVar = __GET[i].split("=");
            $_GET[getVar[0]] = typeof(getVar[1])=="undefined" ? "" : getVar[1];
        }
        return $_GET;
    }
    function urldecode(str) {
        return decodeURIComponent((str+'').replace(/\+/g, '%20'));
    }
    $(document).ready(function() {
        $('#search_in_result').keyup(function() {
            var q = $('input[name="freeTextSearch"]').val();
            $('input[name="q"]').val($(this).val() + ' ' + q);
        });
        var getParams = parseGetParams();

        if (typeof getParams['q'] != 'undefined') {
            $('input[name="q"]').val(urldecode(getParams['q']));
            $('input[name="freeTextSearch"]').val(urldecode(getParams['q']));
        }
        else {
            $('input[name="q"]').val(urldecode(getParams['text']));
            $('input[name="freeTextSearch"]').val(urldecode(getParams['text']));
        }


    });
    function sendFormIfNotEmpty(button,input) {
        if ($(input).val() != '')
            $(button).closest('form').submit()
    }
</script>

<input type="hidden" name="freeTextSearch" value="${pageData.freeTextSearch}"/>
<form action="" method="get" id="search_in_result_form">
    <div class="filter-block filter-block_border-bottom">
        <label for="search_in_result" class="g-label-mini">Поиск в найденном</label>
        <input type="text" name="search_in_result" id="search_in_result" size="17" class="g-input" />
        <input type="hidden" name="q" value="${pageData.freeTextSearch}"/>
        <a href="javascript:void(0)" class="g-button-black g-float-right" onclick="sendFormIfNotEmpty(this,'#search_in_result')">Найти</a>
    </div>
</form>
