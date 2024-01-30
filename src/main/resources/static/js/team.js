jQuery(document).ready(function($) {

    // Iterate over each element with the class 'square'
    $('.square').each(function(index) {
        var firstImage = $(this);
        var secondImage = $('.square2').eq(index); // Assuming '.square2' elements have the same index as '.square'

        var getImage = firstImage.attr('data-image');
        var getImage2 = secondImage.attr('data-image');

        // Set background images
        firstImage.css('background-image', 'url(' + getImage + ')');
        secondImage.css('background-image', 'url(' + getImage2 + ')');
    });

});