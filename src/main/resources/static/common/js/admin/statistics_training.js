/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
    var myChart = document.getElementById('bar_chart_training').getContext('2d');
    var labels = [
        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'
    ];
    var trainingData = [];
    var selectedYear = new Date().getFullYear();
    var chartInstance = null;

    var yearSelection = document.getElementById('trainingYearSelection');
    var trainingCurrentYearElement = document.getElementById('trainingCurrentYear');
    trainingCurrentYearElement.textContent = selectedYear;

    yearSelection.addEventListener('change', function() {
        selectedYear = parseInt(yearSelection.value, 10);
        trainingCurrentYearElement.textContent = selectedYear;
        displayTrainingData(selectedYear);
    });

    jQuery.ajax({
        url: '/racket/statistics/training',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            trainingData = response;
            displayTrainingData(selectedYear);
        },
        error: function(error) {
            console.log(error);
        }
    });

    function displayTrainingData(year) {
        var filteredData = trainingData.filter(function(training) {
            var trainingDate = new Date(training.trainingDate);
            return trainingDate.getFullYear() === year;
        });

        var trainingCounts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

        filteredData.forEach(function(training) {
            var trainingDate = new Date(training.trainingDate);
            var month = trainingDate.getMonth();
            trainingCounts[month]++;
        });

        var chartData = {
            labels: labels,
            datasets: [
                {
                    label: '월별 강습 수',
                    data: trainingCounts,
                    backgroundColor: 'rgba(150, 150, 150, 0.5)'
                }
            ]
        };

        if (chartInstance) {
            chartInstance.destroy();
        }

        chartInstance = new Chart(myChart, {
            type: 'bar',
            data: chartData
        });
    }

    jQuery.ajax({
        url: '/racket/statistics/trainingInfo_years',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            var yearSelection = document.getElementById('trainingYearSelection');

            response.yearsTraining.forEach(function(year) {
                var option = document.createElement('option');
                option.value = year;
                option.textContent = year + '년';
                yearSelection.appendChild(option);
            });

            yearSelection.value = selectedYear;
        },
        error: function(error) {
            console.log(error);
        }
    });
});
