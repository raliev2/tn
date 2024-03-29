ACC.common = {
	currentPath: window.location.pathname,
	currentCurrency: "EUR",
	$page:           $("#page"),
	$globalMessages: $("#globalMessages"),

	setCurrentCurrency: function() {
		ACC.common.currentCurrency = ACC.common.$page.data("currencyIsoCode");
	},

	refreshScreenReaderBuffer: function() {
	    // changes a value in a hidden form field in order
	    // to trigger a buffer update in a screen reader
	    $('#accesibility_refreshScreenReaderBufferField').attr('value', new Date().getTime());
	},

	bindAll: function() {
		ACC.common.bindToUiCarouselLink();
	},

	bindToUiCarouselLink: function() {
		/*$("ul.carousel > li a.popup").colorbox({
			onComplete: function() {
			    ACC.common.refreshScreenReaderBuffer();
			},

			onClosed: function() {
				ACC.common.refreshScreenReaderBuffer();
			}
		});*/
	},
	processingMessage: $("<img src='" + ACC.config.commonResourcePath + "/images/spinner.gif'/>"),

	bindShowProcessingMessageToSubmitButton : function () {

		$(':submit.show_processing_message').each(function(){
			$(this).on("click",ACC.common.showProcessingMessageAndBlockForm);
		});
	},

	showProcessingMessageAndBlockForm: function () {
		var form = $(this).parents('form:first');
		form.block({ message: ACC.common.processingMessage });
	},

	blockFormAndShowProcessingMessage: function (submitButton) {
		var form = submitButton.parents('form:first');
		form.block({ message: ACC.common.processingMessage });
	},

	showSpinner: function(message) {
		$('.spinner-wrapper').show();
		$('#spinnerMessage').html(message);
	},
	
	showSpinnerById: function(id) {
		$('#'+id).show();
	},

	hideSpinner: function() {
		$('.spinner-wrapper').hide();
		$('#spinnerMessage').html("");
	},
	
	hideSpinnerById: function(id) {
		$('#'+id).hide();
	},

	ensureAtleastDefaultAttributeSet: function(options, attribute, dafaultValue) {
		// either the passed in option or if its undefined create new object
		options = options || {};
		// set either the avail. value for the attribute is set or set default
		options[attribute] = options[attribute] || dafaultValue;

		return options;
	}

};

$(document).ready(function() {
	ACC.common.setCurrentCurrency();
	ACC.common.bindAll();
	ACC.common.bindShowProcessingMessageToSubmitButton();
});


/* Extend jquery with a postJSON method */
jQuery.extend({
	postJSON: function (url, data, callback)
	{
		return jQuery.post(url, data, callback, "json");
	}
});

// add a CSRF request token to POST ajax request if its not available
$.ajaxPrefilter(function (options, originalOptions, jqXHR)
{
	// Modify options, control originalOptions, store jqXHR, etc
	if (options.type === "post" || options.type === "POST")
	{
		var noData = (typeof options.data === "undefined");
		if (noData || options.data.indexOf("CSRFToken") === -1)
		{
			options.data = (!noData ? options.data + "&" : "") + "CSRFToken=" + ACC.config.CSRFToken;
		}
	}
});


