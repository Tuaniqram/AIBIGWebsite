$(document).ready(function() {
    let $selectHeader = $('#header');
    if ($selectHeader.length) {
        $(window).on('load', headerScrolled);

        $(window).on('scroll', headerScrolled);

        function headerScrolled() {
            if ($(window).scrollTop() > 100) {
                $selectHeader.addClass('header-scrolled');
            } else {
                $selectHeader.removeClass('header-scrolled');
            }
        }
    }
});