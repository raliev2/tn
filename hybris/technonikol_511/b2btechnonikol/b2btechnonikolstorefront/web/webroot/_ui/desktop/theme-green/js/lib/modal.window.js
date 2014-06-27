jQuery.fn.outerHTML = function(s) {
    return s
        ? this.before(s).remove()
        : jQuery("<p>").append(this.eq(0).clone()).html();
};
(function( $ ) {
    $.fn.modal = function() {
        /*$('.modal-window-content').html(''); IE fix */
        $(this).css('display','block');
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
        console.log(this)
        $('.modal-window-content').append(this);
        $('.modal-window').fadeIn();
    };

    /*
     $.fn.modal = function() {
     $('.modal-window-content').html('');
     var $content = $(this).clone();
     $content.css('display','block');
     $('.modal-window').click(function() {
     $content.hide();
     });
     $('.modal-window-content').click(function(e) {
     e.stopPropagation();
     });
     $('.modal-window__close').click(function(event) {
     event.preventDefault();
     $('.modal-window').hide();
     });
     $('.modal-window-content').append($content);
     $('.modal-window').fadeIn();
     };
    */
})(jQuery);