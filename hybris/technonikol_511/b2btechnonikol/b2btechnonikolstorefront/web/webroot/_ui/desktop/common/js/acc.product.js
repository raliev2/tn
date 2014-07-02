function checkProduct(url) {
    $.ajax({
        type : 'get',
        data : {
            count: $(".clone-popup-qty").val()
        },
        url: url,
        dataType: 'html',
        beforeSend : function() {
            $('.modal-wrapper').append('<div class="loading"></div>');
        },
        success: function(data){
            $('.modal-wrapper .loading').remove();
            $(".check-in-stock__result_text").html(data);
            $(".check-in-stock__result").slideDown();
        }
    });
}
function addToCartAfterCheck() {
    $("#qty").val($(".clone-popup-qty").val());
    $('.add_to_cart_button').click();
    $('.modal-window').hide();
}

function refreshMiniCart(cartResult) {
    var amount = 0.;
    for (var i = 0; i < cartResult['cartData']['products'].length; i++) {
        amount += parseInt(cartResult['cartData']['products'][i]['quantity']);
    }
    if ($('.js-cart-amount').length == 0) {
        $('.link-cart').html('В корзине <span class="js-cart-amount">'+amount+'</span> товар');
    } else {
        $('.js-cart-amount').text(amount);
    }
}
ACC.product = {
	// cached jQuery objects
	$cartPopup:             $('.cart-popup'),
	$addToCartButton:       $(':submit.add_to_cart_button'),
	$addToCartOrderForm:    $('.add_to_cart_order_form'),
	$addToCartForm:         $('.add_to_cart_form'),
    cartResult:             new Array(),
	// selector, used for forced refreshes when you need uncached jQuery objects after DOM updates
	addToCartFormSelector: '.add_to_cart_form',


	bindToAddToCartForm: function($selector) {
		/*options = ACC.common.ensureAtleastDefaultAttributeSet(options, 'enforce', false);

		if (options.enforce) {
			ACC.product.$addToCartForm = $(ACC.product.addToCartFormSelector);
		}*/
        //console.log(ACC.product.$addToCartForm);
        if ($selector) ACC.product.$addToCartForm = $selector;
		ACC.product.$addToCartForm.ajaxForm({
            beforeSubmit: function(arr, $form, options) {
                var qty = arr[0].value;
                
                // FIXME: для связанных продуктов все значения берутся некорректно, т.к. ищутся по ID 
                var minOrderQuantity = parseFloat($('#addToCartButton').attr('data-min-quantity')); //считаем, что для base

                var coefficient = 1;
                var baseToSales = 1;
                
                // YTODO: калькулятор пересчета отключен
                //if ($('#priceUnits option').length > 0 ) {
                //    var coefficient = parseFloat($('#priceUnits option:selected').attr('data-coefficient')); //коэффициент перевода из выбранной сс в base
                //} else {
                //    coefficient = 1;
                //}
                
                //if ($('#addToCartButton').attr('data-base-to-sales') == '') {
                //    baseToSales = 1;
                //} else {
                //    baseToSales = parseFloat($('#addToCartButton').attr('data-base-to-sales')); //коэффициент перевода из base в sales
                //}

                if (coefficient == 0 || minOrderQuantity == 0 || baseToSales==0 || isNaN(coefficient) || isNaN(minOrderQuantity) || isNaN(baseToSales)) {
                    arr[0].value = Math.ceil(qty);
                    ACC.product.cartResult['message'] = '<p>Товар добавлен в корзину.</p>';
                    return true;
                }
                var qtyBase = qty * coefficient;

                if (qtyBase < minOrderQuantity) {
                    var minOrderQuantityCur = minOrderQuantity / coefficient;
                    ACC.product.cartResult['message'] = '<p style="font-weight:normal">Для данного товара минимально возможное для отгрузки количество ' + minOrderQuantityCur + $('#priceUnits option:selected').text() + '</p>\
                    <p style="font-weight:normal">Ваш заказ будет автоматически исправлен.</p>';
                    arr[0].value = Math.ceil(minOrderQuantity / baseToSales);
                    return true;
                } else {
                    ACC.product.cartResult['message'] = '<p>Товар добавлен в корзину.</p>';
                    arr[0].value = Math.ceil(qtyBase / baseToSales);
                    return true;
                }
            },
            success: function(cartResult) {
                var productID = $('input[name="productCodePost"]').val();
                $.ajax({
                    type : 'get',
                    url : '/store/k/' + productID,
                    dataType : 'html',
                    success : function(data) {
                        ACC.product.cartResult['productReference'] = data;
                        ACC.product.displayAddToCartPopup(cartResult);
                    }
                });
            }
        });
	},

	bindToAddToCartButton: function() {
		ACC.product.$addToCartButton.removeAttr("disabled");
	},

	bindToAddToCartOrderForm: function() {
		ACC.product.$addToCartOrderForm.ajaxForm({success: ACC.product.displayAddToCartPopup});
	},

	displayAddToCartPopup: function(cartResult, statusText, xhr, formElement) {
		var productCode   = $('[name=productCodePost]', formElement).val();
		var quantityField = $('[name=qty]', formElement).val();
		var quantity      = 1;
		if (quantityField != undefined) {
			quantity = quantityField;
		}
		/*ACC.common.$globalMessages.html(cartResult.cartGlobalMessagesHtml);*/
		if (typeof refreshMiniCart == 'function') {
			refreshMiniCart(cartResult);
		}
		if (cartResult.cartGlobalMessagesHtml !== "") {
			return;
		}

        var tmpl = doT.template($('#addToCartTmpl').html());
        $(ACC.product.$cartPopup).html(tmpl(ACC.product.cartResult));
        $('.cart-popup .product-carousel__item').removeClass('product-carousel__item_930px');
        $('.cart-popup .product-carousel-item__img').removeClass('product-carousel-item__img_930px');
        $('.cart-popup .block-chars__header').remove();

        if (ACC.product.cartResult['productReference'] == '<div class="yCmsContentSlot"></div>') $('.cart-popup-carousel__header').remove();
        $('.carousel-product__carousel ul').each(function(indx, element){
            $(element).easyPaginate({
                step:4,
                numeric : true,
                controls : 'pagination' + indx
            });
        });

        $(ACC.product.$cartPopup).modal();
        ACC.product.bindToAddToCartForm($(".modal-window .add_to_cart_form"));
	},

	trackAddToCart: function(productCode, quantity, cartData) {
		window.mediator.publish('trackAddToCart', {
			productCode: productCode,
			quantity:    quantity,
			cartData:    cartData
		});
	},

	zoomImage: function() {
		// jcarousel().jcarouse() is a workaround for a jcarousel bug.
		$('#carousel_modal').jcarousel({vertical: true}).jcarousel({
			vertical:              true,
			itemFallbackDimension: 512
		});

		$(".noaction").click(function(e) {
			e.preventDefault(); // preventing the screen from jumping since the hrefs are #
		});

	},

	bindAll: function() {
		ACC.product.bindToAddToCartForm();
		ACC.product.bindToAddToCartButton();
		ACC.product.bindToAddToCartOrderForm();

		/*$('#carousel_modal').jcarousel({
			// Configuration goes here
			vertical:              true,
			itemFallbackDimension: 512
		});*/

		$(".noaction").click(function(e) {
			e.preventDefault(); // preventing the screen from jumping since the hrefs are #
		});

        $('.checkInStockPopup').click(function() {
        	$(".check-in-stock__result_text").html('');
            $(".check-in-stock__result").hide();
        	$clone = $('#checkInStockPopup').clone();
        	$clone.find(".popup-qty").addClass("clone-popup-qty");
        	$clone.find(".g-input").addClass("clone-popup-qty");
        	$clone.find(".g-button-black").addClass("clone-popup-qty");
        	$clone.modal();
            $(".g-input.clone-popup-qty").keyup(function (e) {
                if (e.keyCode == 13) {
                	$(".g-button-black.clone-popup-qty").click();
                }
            });
            $(".popup-qty").keydown(function (e) {
                // Allow: backspace, delete, tab, escape, enter and .
                if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                     // Allow: Ctrl+A
                    (e.keyCode == 65 && e.ctrlKey === true) || 
                     // Allow: home, end, left, right
                    (e.keyCode >= 35 && e.keyCode <= 39)) {
                         // let it happen, don't do anything
                         return;
                }
                // Ensure that it is a number and stop the keypress
                if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                    e.preventDefault();
                }
            });
        });

	}

};

$(document).ready(function() {
	ACC.product.bindAll();
	
	var productsInfo = '';
	var total = $(this).find('.js-cart-entry').length;
    $('.js-cart-entry').each(function(index,item) {
    	var code = $(item).attr('data-id');
    	var count = $(item).attr('data-quantity');
    	productsInfo += code + ':' + count + ';';
    	if(index+1 == total){	    
            $.ajax({
                type : 'post',
                data : {
                	products: productsInfo,
                },
                url: check_stocks_url,
                dataType: 'json',
                beforeSend : function() {
                    $('#check-loading').append('<div class="loading-stock"></div>');
                },
                success: function(data){
                    $('#check-loading .loading-stock').remove();
                    if(data.text){
                    	$('#check-loading').append(data.text);
                    	return;
                    }
                	var productInfo = data.productInfo;
                	var roughOrderDate = data.roughOrderDate;
                	$('.js-cart-entry').each(function(index,item) {
                		var code = $(item).attr('data-id');
                    	for(var key in productInfo){
                    		if(productInfo[key].code == code){
                    			$(item).find('.js-entry-stock').html(productInfo[key].html);
                    		}
                    	}                		
                	})
                	$(document).find('#roughOrderDate').html(roughOrderDate);
                	$('.btn_final_submit').removeAttr("disabled");
                }
            });    	    
    	}
    });

});
