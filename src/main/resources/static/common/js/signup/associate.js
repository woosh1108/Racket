//function updateEndTime() {
//	var startTimeSelect = document.getElementById("stadiumTimeStart");
//	var endTimeSelect = document.getElementById("stadiumTimeEnd");
//	var startTime = startTimeSelect.value;
//	var endTime = endTimeSelect.value;
//	endTimeSelect.innerHTML = "";
//
//	// 선택한 시작 시간 이후의 시간들을 두 번째 select 엘리먼트에 추가
//	for (var i = startTimeSelect.selectedIndex; i < startTimeSelect.options.length; i++) {
//		var option = document.createElement("option");
//		option.text = startTimeSelect.options[i].text;
//		option.value = startTimeSelect.options[i].value;
//		endTimeSelect.add(option);
//	}
//
//	var selectedEndTimeIndex = endTimeSelect.selectedIndex;
//	var selectedEndTime = endTimeSelect.options[selectedEndTimeIndex].value;
//	var stadiumTime = startTime + " ~ " + selectedEndTime;
//	document.getElementById("stadiumTime").value = stadiumTime;
//}
//
//document.getElementById("stadiumTimeEnd").addEventListener("change", updateEndTime);
function updateEndTime(event) {
  var startTimeSelect = document.getElementById("stadiumTimeStart");
  var endTimeSelect = document.getElementById("stadiumTimeEnd");
  var startTime = startTimeSelect.value;
  var endTime = endTimeSelect.value;

  // 이벤트가 stadiumTimeEnd 엘리먼트에 의해 발생하지 않았을 경우만 endTimeSelect의 옵션을 수정
  if (!event || event.target.id !== "stadiumTimeEnd") {
    endTimeSelect.innerHTML = "";

    for (var i = startTimeSelect.selectedIndex; i < startTimeSelect.options.length; i++) {
      var option = document.createElement("option");
      option.text = startTimeSelect.options[i].text;
      option.value = startTimeSelect.options[i].value;
      endTimeSelect.add(option);
    }
  }

  var selectedEndTimeIndex = endTimeSelect.selectedIndex;
  var selectedEndTime = endTimeSelect.options[selectedEndTimeIndex].value;
  var stadiumTime = startTime + " ~ " + selectedEndTime;
  document.getElementById("stadiumTime").value = stadiumTime;
}

document.getElementById("stadiumTimeStart").addEventListener("change", updateEndTime);
document.getElementById("stadiumTimeEnd").addEventListener("change", function (event) {
  updateEndTime(event);
});



function checkId() {
	var memberId = $('#memberId').val();

    if (memberId.trim() === '') {
        alert('아이디를 입력해주세요.');
        return;
    }
	
    $.ajax({
        type: 'POST',
        url: '/racket/check-duplicate-id',
        data: { memberId: memberId },
        success: function (response) {
            alert('아이디 확인 완료!');
            $('#associate').prop('disabled', false); 
        },
        error: function (jqXHR) {
            if (jqXHR.status === 404) {
                // memberId가 없을 경우
            	alert('가입된 정보가 없습니다.');
                $('#associate').prop('disabled', true);
            } else {
                // 기타 오류인 경우
                alert('서버와의 통신에 문제가 발생했습니다. 다시 시도해주세요.');
            }
        }
    });
	function checkId(){
		$('#associate').prop('disabled', true);
	}
}