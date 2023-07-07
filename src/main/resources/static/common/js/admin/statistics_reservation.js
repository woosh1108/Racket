/**

 * 
 */

document.addEventListener("DOMContentLoaded", function() {
    var myChart = document.getElementById('bar_chart_reservation').getContext('2d');
    var labels = [
        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'
    ];
    var reservationData = [];
    var selectedYear = new Date().getFullYear();
    var chartInstance = null;

    jQuery.ajax({
        url: '/racket/statistics/reservations',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            reservationData = response;

            displayReservationData(selectedYear);

            var yearSelection = document.getElementById('reservationYearSelection');
            yearSelection.addEventListener('change', function() {
                selectedYear = parseInt(yearSelection.value, 10);
                displayReservationData(selectedYear);
            });
        },
        error: function(error) {
            console.log(error);
        }
    });

    function displayReservationData(year) {
        var reservationCurrentYearElement = document.getElementById('reservationCurrentYear');
        reservationCurrentYearElement.textContent = year;

        var filteredData = reservationData.filter(function(reservation) {
            var reservationDate = new Date(reservation.reservationDate);
            return reservationDate.getFullYear() === year;
        });

        var matchingCounts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        var completedCounts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        var endedCounts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

        filteredData.forEach(function(reservation) {
            var reservationDate = new Date(reservation.reservationDate);
            var month = reservationDate.getMonth();

            if (reservation.reservationStatus === '매칭중') {
                matchingCounts[month]++;
            } else if (reservation.reservationStatus === '매칭완료') {
                completedCounts[month]++;
            } else if (reservation.reservationStatus === '경기종료') {
                endedCounts[month]++;
            }
        });

        var chartData = {
            labels: labels,
            datasets: [
                {
                    label: '매칭중',
                    data: matchingCounts,
                    backgroundColor: 'rgba(75, 192, 192, 0.5)'
                },
                {
                    label: '매칭완료',
                    data: completedCounts,
                    backgroundColor: 'rgba(54, 162, 235, 0.5)'
                },
                {
                    label: '경기종료',
                    data: endedCounts,
                    backgroundColor: 'rgba(255, 99, 132, 0.5)'
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
        url: '/racket/statistics/reservationsInfo_years',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            var yearSelection = document.getElementById('reservationYearSelection');

            response.yearsReservation.forEach(function(year) {
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