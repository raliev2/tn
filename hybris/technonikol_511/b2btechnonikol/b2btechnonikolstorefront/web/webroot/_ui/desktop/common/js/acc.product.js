function checkProduct(url) {
    $.ajax({
        type : 'get',
        data : {
            count: $("#popup-qty").val()
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
    $("#qty").val($("#popup-qty").val());
    $('.add_to_cart_button').click();
    $('.modal-window').hide();
}

function refreshMiniCart(cartResult) {
    var amount = 0.;
    for (var i = 0; i < cartResult['cartData']['products'].length; i++) {
        amount += parseInt(cartResult['cartData']['products'][i]['quantity']);
    }
    if ($('.js-cart-amount').length == 0) {
        $('.link-cart').html('В корзине <span class="js-cart-amount">1</span> товар');
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

                var productID = $('input[name="productCodePost"]').val();
                $.ajax({
                    type : 'get',
                    url : '/store/k/' + productID,
                    dataType : 'html',
                    success : function(data) {
                        ACC.product.cartResult['productReference'] = data;
                    }
                });

                if (coefficient == 0 || minOrderQuantity == 0 || baseToSales==0 || isNaN(coefficient) || isNaN(minOrderQuantity) || isNaN(baseToSales)) {
                    arr[0].value = Math.ceil(arr[0].value);
                    ACC.product.cartResult['message'] = '<p>Товар добавлен в корзину.</p>';
                    return true;
                }
                var qtyBase = qty * coefficient;

                if (qtyBase < minOrderQuantity) {
                    var minOrderQuantityCur = minOrderQuantity / coefficient;
                    ACC.product.cartResult['message'] = '<p>Для данного товара минимально возможное для отгрузки количество ' + minOrderQuantityCur + $('#priceUnits option:selected').text() + '</p>\
                    <p>Ваш заказ будет автоматически исправлен.</p>';
                    arr[0].value = Math.ceil(minOrderQuantity / baseToSales);
                    console.log(arr)
                    return true;
                } else {
                    ACC.product.cartResult['message'] = '<p>Товар добавлен в корзину.</p>';
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
			refreshMiniCart(cartResult);
		}
		if (cartResult.cartGlobalMessagesHtml !== "") {
			return;
		}

        var tmpl = doT.template($('#addToCartTmpl').html());
        $(ACC.product.$cartPopup).html(tmpl(ACC.product.cartResult));
        $('.product-carousel__item').removeClass('product-carousel__item_930px');
        $('.product-carousel-item__img').removeClass('product-carousel-item__img_930px');
        $('.block-chars__header').remove();
        console.log(ACC.product.cartResult['productReference'])
        if (ACC.product.cartResult['productReference'] == '<div class="yCmsContentSlot"></div>') $('.cart-popup-carousel__header').remove();
        $('.carousel-product__carousel ul').each(function(indx, element){
            $(element).easyPaginate({
                step:4,
                numeric : true,
                controls : 'pagination' + indx
            });
        });

        $(ACC.product.$cartPopup).modal();

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
            $('#checkInStockPopup').modal();
        });

	}

};

$(document).ready(function() {
	ACC.product.bindAll();
});
