function toggleStatus(input) {
                input.value = input.checked ? 1 : 0;
            }
            function updateStadiumStatus() {
            	event.preventDefault();
                var data = serializeData();
                $.ajax({
                    type: "POST",
                    url: "/racket/admin/update",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "html"
                    /* ,
                    success: function (response) {
                      alert("등록이 완료되었습니다.");
                      window.location.href = "/racket/main";
                    } */
		                })
		                .done(function (response) {
		                  alert("등록이 완료되었습니다.");
		                  window.location.href = "/racket/admin_register";
		                })
		                .fail(function (jqXHR, textStatus, errorThrown) {
		                	console.error("Request failed: " + textStatus + " - " + errorThrown);
		                	  console.warn("Server response: ", jqXHR.responseText); // 추가된 코드
		                	  alert("오류가 발생했습니다. 다시 시도해 주세요.");
                  });
            }

            function serializeData() {
            	  var form = document.getElementById("stadiumForm");
            	  var rowList = form.querySelectorAll("tbody tr");
            	  var data = [];

            	  for (var i = 0; i < rowList.length; i++) {
            	    var stadium = {};
            	    var row = rowList[i];
            	    var inputStadiumStatus = row.querySelector("input[name='stadiumStatus']");
            	    var inputStadiumNo = row.querySelector("input[name='stadiumNo']");

            	    stadium.stadiumStatus = inputStadiumStatus.checked ? 1 : 0;
            	    stadium.stadiumNo = Number(inputStadiumNo.value);
            	    data.push(stadium);
            	  }
            	  return data;
            	}