<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker example</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
  <!-- <input type="text" id="datepicker"> -->
  <div class="container mt-5">
        <p>날짜를 선택해주세요:</p>
        <input type="text" class="form-control" id="datePicker">
       <!--  <script>
            flatpickr('#datePicker', {});
        </script> -->
    </div>
  <script>
    	$(function() {
    	    var minDate = 0;
    	    var maxDate = 7;
    	  
    	    function set_custom_date_range(dp_inst) {
    	        var current_date = dp_inst.selectedDay ? new Date(dp_inst.selectedYear, dp_inst.selectedMonth, dp_inst.selectedDay) : new Date();
    	      
    	        minDate = new Date(current_date);
    	        maxDate = new Date(current_date);
    	        maxDate.setDate(maxDate.getDate() + 7);
    	      
    	        $(dp_inst.input[0]).datepicker('option', 'minDate', minDate);
    	        $(dp_inst.input[0]).datepicker('option', 'maxDate', maxDate);
    	    }
    	  
    	    $("#datePicker").datepicker({
    	        changeMonth: true,
    	        changeYear: false,
    	        stepMonths: 1,
    	        minDate: minDate,
    	        maxDate: maxDate,
    	        onClose: function(dateText, dp_inst) {
    	            set_custom_date_range(dp_inst);
    	        }
    	    });
    	});
  </script>
</body>
</html>