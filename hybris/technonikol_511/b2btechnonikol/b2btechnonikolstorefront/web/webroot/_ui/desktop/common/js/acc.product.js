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
                var minOrderQuantity = parseFloat($('#addToCartButton').attr('data-min-quantity')); //считаем, что для base
                var coefficient = parseFloat($('#priceUnits option:selected').attr('data-coefficient')); //коэффициент перевода из выбранной сс в base
                var baseToSales = parseFloat($('#addToCartButton').attr('data-base-to-sales')); //коэффициент перевода из base в sales

                if (coefficient == 0 || minOrderQuantity == 0 || baseToSales==0 || isNaN(coefficient) || isNaN(minOrderQuantity) || isNaN(baseToSales)) {
                    arr[0].value = Math.ceil(arr[0].value);
                    console.log(arr);
                    ACC.product.$cartPopup.html('<p>Товар добавлен в корзину.</p>');
                    return true;
                }
                var qtyBase = qty * coefficient;

                if (qtyBase < minOrderQuantity) {
                    var minOrderQuantityCur = minOrderQuantity / coefficient;
                    ACC.product.$cartPopup.html('<p>Для данного товара минимально возможное для отгрузки количество ' + minOrderQuantityCur + $('#priceUnits option:selected').text() + '</p>\
                    <p>Ваш заказ будет автоматически исправлен.</p>');
                    arr[0].value = Math.ceil(minOrderQuantity / baseToSales);
                    console.log(arr)
                    return true;
                } else {
                    ACC.product.$cartPopup.html('<p>Товар добавлен в корзину.</p>');
                    var qtySales = qtyBase / baseToSales;
                    arr[0].value = Math.ceil(qtySales);
                    console.log(arr)
                    return true;
                }
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

        modalWindowShow();

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

	function checkProduct(url){
		$.ajax({
			type : 'get',
			data : {
				count: $("#popup-qty").val()
			},
			url: url,
			dataType: 'html',
			success: function(data){
				$("#productDeliveryInfo").html(data);
			}
		})
	}
	
	$("#qty").keyup(function() {
	    $("#popup-qty").val($("#qty").val());
	});

$(document).ready(function() {
	ACC.product.bindAll();
});
