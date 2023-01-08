changeSlide = function(index, cl) {
    cl = (typeof cl == undefined) ? '.carousel.carousel-slide' : cl;
    $(cl).carousel('set', index);
}
$('.carousel.carousel-slider').carousel({
        fullWidth: true,
        onCycleTo: function(data){
            changeSlide($(data).data('cindex'), '.carousel.thumbnails');
        }
    });
    $('.carousel.thumbnails').carousel({
        dist: 0,
        padding: 20,
        onCycleTo: function(data){
            changeSlide($(data).data('cindex'), '.carousel.carousel-slider');
        }
    });