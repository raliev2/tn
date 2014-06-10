jQuery.fn.outerHTML = function(s) {
    return s
        ? this.before(s).remove()
        : jQuery("<p>").append(this.eq(0).clone()).html();
};
(function( $ ) {
    $.fn.modal = function() {
        $('.modal-window-content').html('');
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
        $('.modal-window-content').append(this);
        $('.modal-window').fadeIn();
    };
})(jQuery);