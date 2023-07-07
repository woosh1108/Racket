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


let courtCount = 1; // 초기 코트 수
let courtHoursCount = 1; // 초기 코트 시간 수
let courtNameCount = 1;

function addCourt() {
  const courtContainer = document.getElementById('courtContainer');

  // courtlist 필드 추가
  const courtlistDiv = document.createElement('div');
  courtlistDiv.classList.add('mb-3');
  courtlistDiv.innerHTML = `
    <label for="stadiumContent" class="form-label">코트 이름:</label>
    <input type="text" id="courtName_${courtNameCount}" name="courtlist[${courtNameCount}].courtName" class="form-control" required>
  `;
  courtContainer.appendChild(courtlistDiv);
  
  const courtbutton = document.createElement('div');
  courtbutton.innerHTML = `
	  <button type="button" onclick="addCourtHours()">코트 운영 시간 추가</button>
  `
	  courtContainer.appendChild(courtbutton);
  const courtLabel = document.createElement('label');
  courtLabel.setAttribute('for', `courtStart_${courtCount}`);
  courtLabel.setAttribute('class', 'form-label');
  courtLabel.setAttribute('style', 'width: 100px;');
  courtLabel.innerText = '코트 운영 시간:';
  courtContainer.appendChild(courtLabel);
  
  // courtHour 필드 추가
  const courtHourDiv = document.createElement('div');
  courtHourDiv.classList.add('TimeContainer');
  courtHourDiv.innerHTML = `
    <select id="courtStart_${courtCount}" name="courtHour[${courtCount}].courtStart" class="form-select" onchange="updateCourtEnd(${courtCount})">
      <option value="06:00">06:00</option>
      <option value="08:00">08:00</option>
      <option value="10:00">10:00</option>
      <option value="12:00">12:00</option>
      <option value="14:00">14:00</option>
      <option value="16:00">16:00</option>
      <option value="18:00">18:00</option>
      <option value="20:00">20:00</option>
      <option value="22:00">22:00</option>
    </select>
    <label for="stadiumTimeEnd" class="form-label" style="width: 100px;">~</label>
    <select id="courtEnd_${courtCount}" name="courtHour[${courtCount}].courtEnd" class="form-select" readonly>
      <option value="08:00">08:00</option>
      <option value="10:00">10:00</option>
      <option value="12:00">12:00</option>
      <option value="14:00">14:00</option>
      <option value="16:00">16:00</option>
      <option value="18:00">18:00</option>
      <option value="20:00">20:00</option>
      <option value="22:00">22:00</option>
      <option value="24:00">24:00</option>
    </select>
  `;
  courtContainer.appendChild(courtHourDiv);
  courtNameCount++;
  courtCount++; // 코트 수 증가
  courtHoursCount++; //코트 시간 증가
  console.log(courtHoursCount);
}

function updateCourtEnd(courtIndex) {
  const courtStartSelect = document.getElementById(`courtStart_${courtIndex}`);
  const courtEndSelect = document.getElementById(`courtEnd_${courtIndex}`);

  // 현재 선택된 courtStart의 값
  const courtStartValue = courtStartSelect.value;

  // courtStart에 따라 courtEnd의 값을 설정
  if (courtStartValue === '06:00') {
    courtEndSelect.value = '08:00';
  } else if (courtStartValue === '08:00') {
    courtEndSelect.value = '10:00';
  } else if (courtStartValue === '10:00') {
    courtEndSelect.value = '12:00';
  } else if (courtStartValue === '12:00') {
    courtEndSelect.value = '14:00';
  } else if (courtStartValue === '14:00') {
    courtEndSelect.value = '16:00';
  } else if (courtStartValue === '16:00') {
    courtEndSelect.value = '18:00';
  } else if (courtStartValue === '18:00') {
    courtEndSelect.value = '20:00';
  } else if (courtStartValue === '20:00') {
    courtEndSelect.value = '22:00';
  } else if (courtStartValue === '22:00') {
    courtEndSelect.value = '24:00';
  }
}




function addCourtHours() {
  const courtHoursContainer = document.getElementById('courtHoursContainer');

  const courtHoursLabel = document.createElement('label');
  courtHoursLabel.setAttribute('for', `courtStart_${courtHoursCount}`);
  courtHoursLabel.setAttribute('class', 'form-label');
  courtHoursLabel.setAttribute('style', 'width: 100px;');
  courtHoursLabel.innerText = '코트 운영 시간:';
  courtHoursContainer.appendChild(courtHoursLabel);

  const timeContainer = document.createElement('div');
  timeContainer.setAttribute('class', 'TimeContainer');
  courtHoursContainer.appendChild(timeContainer);

  const courtStartSelect = document.createElement('select');
  courtStartSelect.setAttribute('id', `courtStart_${courtHoursCount}`);
  courtStartSelect.setAttribute('name', `courtHour[${courtHoursCount}].courtStart`);
  courtStartSelect.setAttribute('class', 'form-select');
  courtStartSelect.setAttribute('onchange', `updateCourtEnd(${courtHoursCount})`);
  timeContainer.appendChild(courtStartSelect);

  const courtEndLabel = document.createElement('label');
  courtEndLabel.setAttribute('for', 'courtEnd');
  courtEndLabel.setAttribute('class', 'form-label');
  courtEndLabel.setAttribute('style', 'width: 100px;');
  courtEndLabel.innerText = '~';
  timeContainer.appendChild(courtEndLabel);

  const courtEndSelect = document.createElement('select');
  courtEndSelect.setAttribute('id', `courtEnd_${courtHoursCount}`);
  courtEndSelect.setAttribute('name', `courtHour[${courtHoursCount}].courtEnd`);
  courtEndSelect.setAttribute('class', 'form-select');
  courtEndSelect.setAttribute('readonly', '');
  timeContainer.appendChild(courtEndSelect);
  

  // 옵션 값 추가
  const courtStartOptions = ['06:00', '08:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00'];
  courtStartOptions.forEach((optionValue) => {
    const option = document.createElement('option');
    option.setAttribute('value', optionValue);
    option.innerText = optionValue;
    courtStartSelect.appendChild(option);
  });
  // 옵션 값 추가
  const courtEndOptions = ['08:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00', '24:00'];
  courtEndOptions.forEach((optionValue) => {
    const option2 = document.createElement('option');
    option2.setAttribute('value', optionValue);
    option2.innerText = optionValue;
    courtEndSelect.appendChild(option2);
  });
  courtHoursCount++;
  courtCount++;
  console.log(courtHoursCount);
  
}