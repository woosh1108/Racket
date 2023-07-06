/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
    var mychart = document.getElementById('bar_chart_member').getContext('2d');
    var labels = [
        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'
    ];
    var memberData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; // 월별 가입 수를 저장할 배열 초기화
    //강습, 매칭 추가.

    $.ajax({
        url: '/racket/statistics/members',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            response.forEach(function(member) {
                var regDate = member.memberReg.split('-')[1]; // 가입 월 추출
                var month = parseInt(regDate, 10); // 가입 월을 정수로 변환
                memberData[month - 1]++; // 해당 월의 가입 수 증가
            });

            var chartData = {
                labels: labels,
                datasets: [{
                    label: '월별 가입자 수',
                    data: memberData
                }]
            };

            new Chart(mychart, {
                type: 'bar',
                data: chartData
            });
        },
        error: function(error) {
            console.log(error);
        }
    });
});




