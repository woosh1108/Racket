<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/racket/common/css/sign-agreement.css">
    <script>
    	document.getElementById("continueButton").disabled = false;
        function toggleDetails(id) {
            var details = document.getElementById(id);
            details.classList.toggle("show");
        }

        function toggleAllCheckboxes() {
            var childCheckboxes = document.querySelectorAll(".child-checkbox");
            var allCheckbox = document.getElementById("all_agree");

            childCheckboxes.forEach(function (checkbox) {
                checkbox.checked = allCheckbox.checked;
            });
            validateForm();
        }

        function validateForm() {
            var termsAgree = document.getElementById("terms_agree").checked;
            var privacyAgree = document.getElementById("privacy_agree").checked;
            var continueButton = document.getElementById("continue");

            continueButton.disabled = !(termsAgree && privacyAgree);
        }
        function gotoNextPage() {
            // 원래 페이지(샘플 페이지)의 URL을 변경하고 싶은 페이지로 변경합니다.
            window.opener.location.href = "/racket/next";
            // 팝업창을 닫습니다.
            window.close();
        }

    </script>
</head>
<body>
	 <h1>회원가입 동의 form</h1>
    <form>
    	<div class="all-agree">
	        <label>
	            <input type="checkbox" id="all_agree" onclick="toggleAllCheckboxes()">
	          	 전체동의
	        </label>
        </div>
        <br>

        <label>
            <input type="checkbox" id="terms_agree" class="child-checkbox" onclick="validateForm()">
            이용약관 동의(필수)
            <span onclick="toggleDetails('details1')">&#x25BC;</span>
        </label>
        <div id="details1" class="details">이용약관 내용</div>
        <br>

        <label>
            <input type="checkbox" id="privacy_agree" class="child-checkbox" onclick="validateForm()">
            개인정보 수집 및 이용동의(필수)
            <span onclick="toggleDetails('details2')">&#x25BC;</span>
        </label>
        <div id="details2" class="details">개인정보 수집 및 이용 내용</div>
        <br>

        <label>
            <input type="checkbox" id="third_party_agree" class="child-checkbox">
            제 3자 제공 동의서(선택)
            <span onclick="toggleDetails('details3')">&#x25BC;</span>
        </label>
        <div id="details3" class="details">제 3자 제공 동의서 내용</div>
        <br>
		<button type="button" id="continue" onclick="gotoNextPage()">계속하기(이동)</button>
		
    </form>
</body>
</html>