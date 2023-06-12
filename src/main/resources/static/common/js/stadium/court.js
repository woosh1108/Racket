function go_menu(targetClass) {
    var targetElement = document.querySelector('.' + targetClass);
    var menuElement = document.querySelector('.menu_home');
    if (targetElement) {
        targetElement.scrollIntoView({
            behavior: "smooth"
        });
        if (menuElement) {
        }
    }
}

var swiper = new Swiper(".mySwiper", {
    spaceBetween: 30,
    centeredSlides: true,
    autoplay: {
        delay: 2500,
        disableOnInteraction: false,
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});

/* 지도버튼 클릭시 */
var mapShown = false; // 지도가 표시되었는지 확인하는 변수
var originalContent = document.querySelector(".swiper").cloneNode(true); // 원래
// 내용의  사본을 저장합니다.
document.getElementById("mapButton").addEventListener("click", function () {
    var swiperElement = document.querySelector(".swiper");
    if (!mapShown) {
        swiperElement.innerHTML = "<div id='map' style='width:100%;height:100%;''></div>";
        // 그리고 지도 API를 호출하십시오.
        var map = new naver.maps.Map('map', {
            center: new naver.maps.LatLng(37.3595704,
                127.105399),
            zoom: 15
        });

        var marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(37.3595704,
                127.105399),
            map: map
        });
        mapShown = true;
    } else {
        // 지도가 이미 표시된 경우, 원래 코드를 복원합니다.
        swiperElement.innerHTML = "";
        swiperElement.appendChild(originalContent
            .cloneNode(true));
        mapShown = false;
    }
});