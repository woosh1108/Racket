/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
    var mychart = document.getElementById('bar_chart_training').getContext('2d');
    var labels = [
        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'
    ];
    var trainingData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; // 월별 강습 수를 저장할 배열 초기화

    $.ajax({
        url: '/racket/statistics/training',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            response.forEach(function(training) {
                var trainingDate = new Date(training.trainingDate); // 강습 날짜 가져오기
                var month = trainingDate.getMonth(); // 월 추출
                trainingData[month]++; // 해당 월의 강습 수 증가
            });

            var chartData = {
                labels: labels,
                datasets: [{
                    label: '월별 강습 수',
                    data: trainingData
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
