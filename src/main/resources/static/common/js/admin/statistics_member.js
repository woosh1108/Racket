/**
 * 
 */

document.addEventListener("DOMContentLoaded", function() {
    var myChart = document.getElementById('bar_chart_member').getContext('2d');
    var labels = [
        '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'
    ];
    var memberData = [];
    var selectedYear = new Date().getFullYear();
    var chartInstance = null;

    jQuery.ajax({
        url: '/racket/statistics/members',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            memberData = response;

            displayMemberData(selectedYear);

            var yearSelection = document.getElementById('memberYearSelection');
            yearSelection.addEventListener('change', function() {
                selectedYear = parseInt(yearSelection.value, 10);
                displayMemberData(selectedYear);
            });
        },
        error: function(error) {
            console.log(error);
        }
    });

    function displayMemberData(year) {
        var memberCurrentYearElement = document.getElementById('memberCurrentYear');
        memberCurrentYearElement.textContent = year;

        var filteredData = memberData.filter(function(member) {
            var regDate = new Date(member.memberReg);
            return regDate.getFullYear() === year;
        });

        var monthlyCounts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

        filteredData.forEach(function(member) {
            var regDate = new Date(member.memberReg);
            var month = regDate.getMonth();
            monthlyCounts[month]++;
        });

        var chartData = {
            labels: labels,
            datasets: [{
                label: '월별 가입자 수',
                data: monthlyCounts,
                backgroundColor: 'rgba(150, 150, 150, 0.5)'
            }]
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
        url: '/racket/statistics/membersInfo_years',
        method: 'GET',
        dataType: 'json',
        success: function(response) {
            var yearSelection = document.getElementById('memberYearSelection');

            response.years.forEach(function(year) {
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
