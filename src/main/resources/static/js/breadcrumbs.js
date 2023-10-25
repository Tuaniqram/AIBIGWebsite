$(document).ready(function() {

    $('.second ol li a.active-2 span').css({ 'border-bottom': '3px solid rgb(0, 183, 255) ', 'padding-bottom': ' 21px  ' });

    $('.second ol li a').click(function() {
        $('.second ol li a.active-2 span').css('padding-bottom', '0px');
        $('.second ol li a.active-2 span').css('border-bottom', 'none');
        $('.second ol li a.active-2').removeClass('active-2');
        $(this).closest('a').find('span').css('padding-bottom', ' 21px  ');
        $(this).closest('a').find('span').css('border-bottom', '3px solid rgb(0, 183, 255) ');
        $(this).closest('a').addClass('active-2');
    });


});