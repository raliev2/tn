function checkqty(inputobject, restriction) {
  // функция используется в листинге для проверки минимального количества товара для заказа
  // функцию бы переписать
  // Это типа заглушки сейчас
  // вызывается в onChange
  // Rauf

  // inputobject - это объект типа input, в который пользователь ввел новое значение количества
  // restriction - минимальное количество товара. Если пользователь ввел меньше указанного числа, нужно исправить на новое и сообщить
  //   может иметь пустое значение - это значит, что нет ограничения
 if (!restriction) { return true; }  // ничего проверять не надо, ограничений нет
 if (document.getElementById(inputobject).value.length == 0) {
            return false;
        }


var intValue = parseInt(restriction);
 if (intValue == Number.NaN) {
            return false; 
        }


 var intValue = parseInt(document.getElementById(inputobject).value);
 if (intValue == Number.NaN) {
            return false; 
        }

 
 if (intValue < restriction)
        {
	    alert("Для этого товара минимальное количество - " + restriction + ". Исправлено.");
            document.getElementById(inputobject).value = restriction;
        } 
}
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
        $('.js-rating_readOnly').rating({
            readOnly : true,
            image : themeResourcePath + '/images/rating-stars.png'
        });
    }

    $('.filter-block__header').click(function() {
        $(this).next('.filter-block__body').slideToggle();
        $(this).toggleClass('filter-block__header_top');
        $(this).toggleClass('filter-block__header_down');
    });

    $('.js-show-all').click(function() {
        $(this).prev().toggle();
        $(this).closest('.facetValues').find('.topValues').toggle();
        var newTxt = $(this).attr('data-text');
        var oldTxt = $(this).text();
        $(this).text(newTxt);
        $(this).attr('data-text',oldTxt);
    });

    $('.required').attr('required', '');

});