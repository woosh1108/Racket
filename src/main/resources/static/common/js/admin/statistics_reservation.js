/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
    var mychart = document.getElementById('bar_chart_reservation').getContext('2d');
    var labels = [
        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'
    ];
    var matchingData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; // 월별 매칭 현황을 저장할 배열 초기화

    $.ajax({
        url: '/racket/statistics/reservations',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            response.forEach(function(matching) {
                var status = matching.matchingStatus;
                var regDate = matching.matchingDate; // 매칭 월 추출
                var month = parseInt(regDate, 10); // 매칭 월을 정수로 변환
                if (status === '매칭중') {
                    matchingData[month - 1]++; // 해당 월의 매칭중 수 증가
                } else if (status === '매칭완료') {
                    // 매칭완료
                } else if (status === '경기완료') {
                    // 경기종료
                }
            });

            var chartData = {
                labels: labels,
                datasets: [{
                    label: '매칭중',
                    data: matchingData,
                    backgroundColor: 'rgba(255, 99, 132, 0.5)'
                }, {
                    label: '매칭완료',
                    data: [], // 매칭완료 수 데이터 입력
                    backgroundColor: 'rgba(54, 162, 235, 0.5)'
                }, {
                    label: '경기종료',
                    data: [], // 경기종료 수 데이터 입력
                    backgroundColor: 'rgba(75, 192, 192, 0.5)'
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
