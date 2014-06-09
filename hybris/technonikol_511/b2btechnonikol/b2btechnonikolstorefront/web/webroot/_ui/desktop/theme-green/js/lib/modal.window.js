(function( $ ) {
    $.fn.modal = function() {
        var $innerHTML = $(this).clone();
        $innerHTML.css('display','block');
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
        $('.modal-window-content').html($innerHTML);
        $('.modal-window').fadeIn();
    };
})(jQuery);