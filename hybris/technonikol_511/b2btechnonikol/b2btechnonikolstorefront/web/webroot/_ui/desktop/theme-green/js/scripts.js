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
        /*resizeContents      : false,*/
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
        //.modal-fast-order__body_copy-paste*/.modal-fast-order__body_write-here
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

    $('.carousel-product__carousel ul').easyPaginate({
        step:4,
        numeric : true
    });
});