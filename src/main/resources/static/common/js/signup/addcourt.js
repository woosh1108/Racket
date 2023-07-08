var courtHourCount = 0; // 초기 코트 운영 시간 개수

function addCourtHours() {
  courtHourCount++; // 코트 운영 시간 개수 증가

  // courtHoursContainer에 코트 운영 시간 요소 추가
  var courtHoursContainer = document.getElementById("courtHoursContainer");
  var newCourtHours = document.createElement("div");
  newCourtHours.innerHTML = `
    <label for="courtStart_${courtHourCount}" class="form-label">코트 운영 시간:</label>
    <div class="TimeContainer">
      <select id="courtStart_${courtHourCount}" name="courtHour[${courtHourCount}].courtStart" class="form-select" onchange="updateCourtEnd(${courtHourCount})">
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
      <label for="courtEnd_${courtHourCount}" class="form-label" style="width: 100px;">~</label>
      <select id="courtEnd_${courtHourCount}" name="courtHour[${courtHourCount}].courtEnd" class="form-select" readonly>
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
    </div>
  `;
  courtHoursContainer.appendChild(newCourtHours);
}

function updateCourtEnd(courtIndex) {
  var courtStartSelect = document.getElementById(`courtStart_${courtIndex}`);
  var courtEndSelect = document.getElementById(`courtEnd_${courtIndex}`);

  // courtStart의 선택된 값과 courtEnd의 옵션 값 비교하여 courtEnd 업데이트
  var selectedStartIndex = courtStartSelect.selectedIndex;
  var selectedStartValue = courtStartSelect.options[selectedStartIndex].value;

  courtEndSelect.innerHTML = "";

  for (var i = selectedStartIndex + 1; i < courtStartSelect.options.length; i++) {
    var option = document.createElement("option");
    option.text = courtStartSelect.options[i].text;
    option.value = courtStartSelect.options[i].value;
    courtEndSelect.add(option);
  }

  courtEndSelect.value = courtEndSelect.options[0].value; // courtEnd 초기값 설정
}