function modalWindowShow() {
    $('.modal-window').fadeIn();
    $('.modal-window').click(function() {
        $(this).hide();
    });
    $('.modal-window-content').click(function(e) {
        e.stopPropagation();
    });
    $('.modal-window__close').click(function(event) {
        event.preventDefault();
        $('.modal-window').hide();
    });
}

ACC.product = {
	// cached jQuery objects
	$cartPopup:             $('.modal-window-content'),
	$addToCartButton:       $(':submit.add_to_cart_button'),
	$addToCartOrderForm:    $('.add_to_cart_order_form'),
	$addToCartForm:         $('.add_to_cart_form'),
	// selector, used for forced refreshes when you need uncached jQuery objects after DOM updates
	addToCartFormSelector: '.add_to_cart_form',


	bindToAddToCartForm: function(options) {
		options = ACC.common.ensureAtleastDefaultAttributeSet(options, 'enforce', false);

		if (options.enforce) {
			ACC.product.$addToCartForm = $(ACC.product.addToCartFormSelector);
		}

		ACC.product.$addToCartForm.ajaxForm({
            beforeSubmit: function(arr, $form, options) {
                var qty = parseFloat($('#qty').val());
                var minOrderQuantity = parseFloat($('#addToCartButton').attr('data-min-quantity'));
                var coefficient = parseFloat($('#priceUnits option:selected').attr('data-coefficient'));

                if (coefficient == 0 || minOrderQuantity == 0) return false;
                var _tmp = qty / coefficient;

                if (_tmp < minOrderQuantity) {
                    ACC.product.$cartPopup.html('<p>Для данного товара минимально возможное для отгрузки количество ' + minOrderQuantity/coefficient + ' ' + $('#priceUnits option:selected').text() + '</p>\
                    <p>Ваш заказ будет автоматически исправлен.</p>');
                    modalWindowShow();
                    arr[0].value = Math.ceil(10);
                    console.log(arr)
                    return false;
                }

                return false;
            },
            success: ACC.product.displayAddToCartPopup
        });
	},

	bindToAddToCartButton: function() {
		ACC.product.$addToCartButton.removeAttr("disabled");
	},

	bindToAddToCartOrderForm: function() {
		ACC.product.$addToCartOrderForm.ajaxForm({success: ACC.product.displayAddToCartPopup});
	},

	displayAddToCartPopup: function(cartResult, statusText, xhr, formElement) {console.log(cartResult)
		var productCode   = $('[name=productCodePost]', formElement).val();
		var quantityField = $('[name=qty]', formElement).val();
		var quantity      = 1;

		if (quantityField != undefined) {
			quantity = quantityField;
		}

		ACC.common.$globalMessages.html(cartResult.cartGlobalMessagesHtml);

		if (typeof refreshMiniCart == 'function') {
			refreshMiniCart();
		}

		if (cartResult.cartGlobalMessagesHtml !== "") {
			return;
		}

		//ACC.product.trackAddToCart(productCode, quantity, cartResult.cartData);
        //$('#colorbox').show();
		//$('#colorbox').hide();

		//ACC.product.$cartPopup.hide();
		//ACC.product.$cartPopup.html(cartResult.cartPopupHtml);
        ACC.product.$cartPopup.html('Товар добавлен в корзину');

		/*$('#add_to_cart_close').click(function(event) {
			event.preventDefault();
			ACC.product.$cartPopup.hide();
		});*/

        modalWindowShow();
		//ACC.product.$cartPopup.fadeIn();
		/*if (typeof timeoutId != 'undefined') {
			clearTimeout(timeoutId);
		}
		timeoutId = setTimeout(function() {ACC.product.$cartPopup.fadeOut();}, 5000);
		$.colorbox.close();*/
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
	}

};

	function getProductDeliveryInfo(url){
		$.ajax({
			type : 'get',
			data : {
				count: $("#qty").val()
			},
			url: url,
			dataType: 'html',
			success: function(data){
				$("#productDeliveryInfo").html(data);
			}
		})
	}

$(document).ready(function() {
	ACC.product.bindAll();
});
