(function() {

  jQuery.keyboardLayout = {};

  jQuery.keyboardLayout.indicator = $('.lang');

  jQuery.keyboardLayout.target;

  jQuery.keyboardLayout.layout;

  jQuery.keyboardLayout.show = function(layout,capslock){
    this.layout = layout;
    this.indicator.text(layout);
      console.log(layout)
    $('.login-panel__language').show();
    if (capslock) {
        $('.capslockIsOn').show();
        $('#j_password').addClass('g-input-attention');
    }
    else {
        $('.capslockIsOn').hide();
        $('#j_password').removeClass('g-input-attention');
    }
  };

  jQuery.keyboardLayout.hide = function(){
    this.target = null;
    this.layout = null;
    $('.login-panel__language').hide();
    $('.capslockIsOn').hide();
  };

  jQuery.fn.keyboardLayout = function()
  {
    this.each(function(){

      $(this).focus(function(){
        jQuery.keyboardLayout.target = $(this);
      });

      $(this).blur(function(){
        jQuery.keyboardLayout.hide();
          $('.login-panel__language').hide();
          $('.capslockIsOn').hide();
          $('#j_password').removeClass('g-input-attention');
      });

      $(this).keypress(function(e){
        var c = (e.charCode == undefined ? e.keyCode : e.charCode);
        var layout = jQuery.keyboardLayout.layout;
        var capslock = false;
        if (c >= 97/*a*/  && c <= 122/*z*/ && !e.shiftKey ||
            c >= 65/*A*/  && c <= 90/*Z*/  &&  e.shiftKey ||
             (c == 91/*[*/  && !e.shiftKey ||
              c == 93/*]*/  && !e.shiftKey ||
              c == 123/*{*/ &&  e.shiftKey ||
              c == 125/*}*/ &&  e.shiftKey ||
              c == 96/*`*/  && !e.shiftKey ||
              c == 126/*~*/ &&  e.shiftKey ||
              c == 64/*@*/  &&  e.shiftKey ||
              c == 35/*#*/  &&  e.shiftKey ||
              c == 36/*$*/  &&  e.shiftKey ||
              c == 94/*^*/  &&  e.shiftKey ||
              c == 38/*&*/  &&  e.shiftKey ||
              c == 59/*;*/  && !e.shiftKey ||
              c == 39/*'*/  && !e.shiftKey ||
              c == 44/*,*/  && !e.shiftKey ||
              c == 60/*<*/  &&  e.shiftKey ||
              c == 62/*>*/  &&  e.shiftKey) && layout != 'EN') {

          layout = 'Английский';
          capslock = false;

        } else if (c >= 65/*A*/ && c <= 90/*Z*/  && !e.shiftKey ||
                   c >= 97/*a*/ && c <= 122/*z*/ &&  e.shiftKey) {

          layout = 'Английский';
          capslock = true;

        } else if (c >= 1072/*а*/ && c <= 1103/*я*/ && !e.shiftKey ||
                   c >= 1040/*А*/ && c <= 1071/*Я*/ &&  e.shiftKey ||
                   (c == 1105/*ё*/ && !e.shiftKey ||
                    c == 1025/*Ё*/ &&  e.shiftKey ||
                    c == 8470/*№*/ &&  e.shiftKey ||
                    c == 59/*;*/   &&  e.shiftKey ||
                    c == 44/*,*/   &&  e.shiftKey) && layout != 'RU') {

          layout = 'Русский';
          capslock = false;

        } else if (c >= 1040/*А*/ && c <= 1071/*Я*/ && !e.shiftKey ||
                   c >= 1072/*а*/ && c <= 1103/*я*/ &&  e.shiftKey) {

          layout = 'Русский';
          capslock = true;

        }
        if (layout) {
            jQuery.keyboardLayout.show(layout,capslock);
        }
      });
    });
  };

})();