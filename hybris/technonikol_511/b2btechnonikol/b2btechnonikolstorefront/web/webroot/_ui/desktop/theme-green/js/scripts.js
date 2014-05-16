function alignHeight(selector) {
    var mh = 0;
    $(selector).each(function () {
        var h_block = parseInt($(this).height());
        if(h_block > mh) {
            mh = h_block;
        };
    });
    $(selector).height(mh);
}
function buildSlider(selector) {
    var alts = new Array();
    $(selector + ' li').each(function(i,element) {
        alts.push($(element).find('img').attr('alt'));
    });
    $(selector).anythingSlider({
        mode                : 'f',
        navigationSize      : 5,
        navigationFormatter : function(index, panel){
            return alts[index - 1];
        },
        hashTags : false,
        buildArrows : false,
        buildStartStop : false,
        autoPlay : true,
        delay : 3000
    });
    alignHeight('.main-slider .thumbNav a');
}
$(document).ready(function() {
    $('.menu__item,.inner-menu__item').hover(function() {
        $('.js-inner-menu',this).show();
    },
    function() {
        $('.js-inner-menu',this).hide();
    });

    $('.prod-categories__header').click(function() {
        $('.prod-categories__list').slideToggle();
        $('.prod-categories__header').toggleClass('prod-categories__header_top');
        $('.prod-categories__header').toggleClass('prod-categories__header_down');
    });
    $('.brands__header').click(function() {
        $('.brands__list').slideToggle();
        $('.brands__header').toggleClass('brands__header_top');
        $('.brands__header').toggleClass('brands__header_down');
    });

    $('.js-fast-order-cp, .js-fast-order-wrh').click(function() {
        $('.modal-fast-order__body_copy-paste').toggle();
        $('.modal-fast-order__body_write-here').toggle();
    });
    $('.fast-order__button').click(function() {
        $('.modal-fast-order').toggle();
    });

    $('.g-close').click(function() {
        $(this).closest('.g-modal-for-close').hide();
    });

    buildSlider('#main-slider');

    $('.carousel-product__carousel ul').each(function(indx, element){
        $(element).easyPaginate({
            step:4,
            numeric : true,
            controls : 'pagination' + indx
        });
    });

    $('.reviews__link-tabs li').click(function() {
        if ($(this).hasClass('reviews__link-tab_active')) return;
        $('.reviews__link-tab_active').removeClass('reviews__link-tab_active');
        $(this).addClass('reviews__link-tab_active');
        $('.reviews__tab').hide();
        $('.js-reviews__tab_' + $(this).attr('id')).show();
    });

    $('.js-write-review').click(function() {
        $('#all-reviews').removeClass('reviews__link-tab_active');
        $('#write-review').addClass('reviews__link-tab_active');
        $('.js-reviews__tab_all-reviews').hide();
        $('.js-reviews__tab_write-review').show();
    });

    $('.button-to-up').click(function(){
        $('body, html').animate({
            scrollTop: 0
        });
    });

    if ($('.js-rating').length) {
        $('.js-rating').rating({
            readOnly : true,
            image : themeResourcePath + '/images/rating-stars.png'
        });
    }

    $('.js-open-img_open').click(function(event) {
        var newImg = $('.product-img img').attr('data-url');
        $('.product-img img').attr('data-url',$('.product-img img').attr('src'));
        $('.product-img img').attr('src',newImg);
        $('.product-img .g-close').toggle();
        if ($(this).hasClass('js-open-img_close')) {
            $('.product-img').removeClass('product-img_big');
            $('.product-info').removeClass('product-info_big');
            $(this).addClass('js-open-img_open');
            $(this).removeClass('js-open-img_close');
            return;
        }
        $('.product-img').addClass('product-img_big');
        $('.product-info').addClass('product-info_big');
        $(this).removeClass('js-open-img_open');
        $(this).addClass('js-open-img_close');
    });

    $('.product-img .g-close').click(function(){
        $('.product-img').removeClass('product-img_big');
        $('.product-info').removeClass('product-info_big');
        $('.open-img').addClass('js-open-img_open');
        $('.open-img').removeClass('js-open-img_close');
        var newImg = $('.product-img img').attr('data-url');
        $('.product-img img').attr('data-url',$('.product-img img').attr('src'));
        $('.product-img img').attr('src',newImg);
        $('.product-img .g-close').toggle();
    });

    $('.img-zoom').jqzoom({
        zoomType: 'innerzoom',
        title : false
    });

});