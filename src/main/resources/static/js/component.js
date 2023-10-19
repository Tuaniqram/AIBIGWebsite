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

$(document).ready(function() {
    let $backtotop = $('.back-to-top');
    if ($backtotop.length) {
        $(window).on('load', toggleBacktotop);

        $(window).on('scroll', toggleBacktotop);

        function toggleBacktotop() {
            if ($(window).scrollTop() > 100) {
                $backtotop.addClass('active');
            } else {
                $backtotop.removeClass('active');
            }
        }
    }
});
$(document).ready(function() {
    $(".team-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        center: false,
        dots: false,
        loop: true,
        margin: 50,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            }
        }
    });
});