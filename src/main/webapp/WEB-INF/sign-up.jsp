<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="/racket/common/css/sign-up.css">
    <script>
	    function validateForm() {
	    }
	    function checkDuplicateId() {
            // 여기에 아이디 중복 검사를 수행하는 코드를 작성하십시오.
            // 표준 예제를 찾으려면 AJAX 및 백엔드 서 통신을 고려하십시오.
        }
	</script>
</head>
<body>
	<div class="container mt-5">
        <form action="submitForm.jsp" method="POST" onsubmit="return validateForm();" class="shadow p-3 mb-5 bg-body rounded">
            <h2 class="mb-3">회원가입</h2>
             <div class="mb-3 form-group">
                <label for="member_id" class="form-label">아이디:</label>
                <div class="input-group">
                    <input type="text" id="member_id" name="member_id" class="form-control" required>
                    <button type="button" onclick="checkDuplicateId();" class="btn btn-secondary" id="dpcheck">중복체크</button>
                </div>
            </div>
            <div class="mb-3">
                <label for="member_pass" class="form-label">비밀번호:</label>
                <input type="password" id="member_pass" name="member_pass" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="member_name" class="form-label">이름:</label>
                <input type="text" id="member_name" name="member_name" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="member_nick" class="form-label">닉네임:</label>
                <input type="text" id="member_nick" name="member_nick" class="form-control">
            </div>
            <div class="mb-3">
                <label for="member_age" class="form-label">나이:</label>
                <input type="number" id="member_age" name="member_age" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="member_phone" class="form-label">전화번호:</label>
                <input type="tel" id="member_phone" name="member_phone" class="form-control">
            </div>
            
            <div class="mb-3">
                <label for="member_gender" class="form-label">성별:</label>
                <select id="member_gender" name="member_gender" class="form-select">
                    <option value="남">남</option>
                    <option value="여">여</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="member_addr" class="form-label">주소:</label>
                <input type="text" id="member_addr" name="member_addr" class="form-control">
            </div>
             <div class="mb-3">
                <label for="member_email" class="form-label">이메일:</label>
                <input type="email" id="member_email" name="member_email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="member_grade" class="form-label">등급:</label>
                <input type="text" id="member_grade" name="member_grade" class="form-control">
            </div>
            <div class="mb-3">
            	<input type="checkbox" name="member_auth" value="0" class="member_auth">
                <label for="member_auth" class="form-label">강사권한</label>
                
            </div>
            
            <input type="hidden" name="member_status" value="0">
            <input type="hidden" name="member_absence" value="0">
           
            <input type="hidden" id="member_reg" name="member_reg" value="<%= (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new java.util.Date()) %>">

            <button type="submit" class="btn btn-primary" id="regist">회원가입</button>
        </form>
    </div>
</body>
</html>