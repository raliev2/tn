$(document).ready(function() {
    var $easyzoom = $('.easyzoom').easyZoom();
    var imgZoom = $easyzoom.data('easyZoom');
    $('.slider-pager a:first-child').addClass('active');
    $('.slider-pager a').click(function() {
        if ($(this).hasClass('active')) return;

        var size = $('.productImgMain').attr('data-img-size');
        var newImg = $(this).attr('data-img-' + size);
        if (newImg == '') newImg = themeResourcePath + '/images/missing-product-300x300.jpg';

        $('.productImgMain').attr('src',newImg);
        $('.productImgMain').attr('data-index',$(this).attr('data-index'));

        var newImgBig = $(this).attr('data-img-big');
        if (newImgBig == '') newImgBig = themeResourcePath + '/images/missing-product-515x515.jpg';
        imgZoom.changeImg(newImgBig);
        $('.productImgMainBig').attr('href',newImgBig);

        $('.slider-pager a.active').removeClass('active');
        $(this).addClass('active');
    });

    $('.js-open-img_open').click(function(event) {
        var size = ($('.productImgMain').attr('data-img-size') == 'mid' ? 'big' : 'mid');
        $('.productImgMain').attr('data-img-size',size);

        var newImg = $('.slider-pager a[data-index="' + $('.slider-pager a.active').attr('data-index') + '"]').attr('data-img-' + size);
        if (newImg == '') newImg = themeResourcePath + '/images/missing-product-300x300.jpg';
        $('.productImgMain').attr('src',newImg);

        var newImgBig = $('.slider-pager a[data-index="' + $('.slider-pager a.active').attr('data-index') + '"]').attr('data-img-big');
        if (newImgBig == '') newImgBig = themeResourcePath + '/images/missing-product-515x515.jpg';
        imgZoom.changeImg(newImgBig);
        $('.productImgMainBig').attr('href',newImgBig);

        $('.product-img .g-close').toggle();
        if ($(this).hasClass('js-open-img_close')) {
            $('.product-images').removeClass('product-images_big');
            $('.product-info').removeClass('product-info_big');
            $(this).addClass('js-open-img_open');
            $(this).removeClass('js-open-img_close');
            return;
        }
        $('.product-images').addClass('product-images_big');
        $('.product-info').addClass('product-info_big');
        $(this).removeClass('js-open-img_open');
        $(this).addClass('js-open-img_close');

    });

    $('.product-img .g-close').click(function(){
        $('.product-images').removeClass('product-images_big');
        $('.product-info').removeClass('product-info_big');
        $('.open-img').addClass('js-open-img_open');
        $('.open-img').removeClass('js-open-img_close');
        $('.product-img .g-close').toggle();

        var size = ($('.productImgMain').attr('data-img-size') == 'mid' ? 'big' : 'mid');
        $('.productImgMain').attr('data-img-size',size);
        var newImg = $('.slider-pager a[data-index="' + $('.slider-pager a.active').attr('data-index') + '"]').attr('data-img-' + size);
        $('.productImgMain').attr('src',newImg);

    });
});