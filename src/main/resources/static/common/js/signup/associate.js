function updateEndTime() {
	var startTimeSelect = document.getElementById("stadiumTimeStart");
	var endTimeSelect = document.getElementById("stadiumTimeEnd");
	var startTime = startTimeSelect.value;
	endTimeSelect.innerHTML = "";

	// 선택한 시작 시간 이후의 시간들을 두 번째 select 엘리먼트에 추가
	for (var i = startTimeSelect.selectedIndex; i < startTimeSelect.options.length; i++) {
		var option = document.createElement("option");
		option.text = startTimeSelect.options[i].text;
		option.value = startTimeSelect.options[i].value;
		endTimeSelect.add(option);
	}

	var selectedEndTimeIndex = endTimeSelect.selectedIndex;
	var selectedEndTime = endTimeSelect.options[selectedEndTimeIndex].value;
	var stadiumTime = startTime + " ~ " + selectedEndTime;
	document.getElementById("stadiumTime").value = stadiumTime;
}