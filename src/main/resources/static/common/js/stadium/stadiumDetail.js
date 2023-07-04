function go_menu(targetClass) {
    var targetElement = document.querySelector('.' + targetClass);
    var menuElement = document.querySelector('.menu_home');
    var offset = 180; // 상단과의 거리를 조정하는 픽셀 값

    if (targetElement) {
        var targetOffset = targetElement.offsetTop - offset;
        window.scrollTo({
            top : targetOffset,
            behavior : "smooth"
        });
    }
}
/* 네이버 지도 관련 */

//var map = new naver.maps.Map('map', {
//    center : new naver.maps.LatLng(37.3595704, 127.105399),
//    zoom : 15
//});
//
//var marker = new naver.maps.Marker({
//    position : new naver.maps.LatLng(37.3595704, 127.105399),
//    map : map
//});
//
///* 사진 슬라이더 처리 */
//var swiper = new Swiper(".mySwiper", {
//    spaceBetween : 30,
//    centeredSlides : true,
//    autoplay : {
//        delay : 2500,
//        disableOnInteraction : false,
//    },
//    pagination : {
//        el : ".swiper-pagination",
//        clickable : true,
//    },
//    navigation : {
//        nextEl : ".swiper-button-next",
//        prevEl : ".swiper-button-prev",
//    },
//});