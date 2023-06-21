	$('#mail-send-btn').click(function() {
    const memberEmail1 = $('#memberEmail1').val();
    const memberEmail2 = $('#memberEmail2').val();
    
    if (!validateEmail(memberEmail1 + memberEmail2)) {
        alert('올바른 이메일 주소가 아닙니다.');
        return;
    }
    
    const email = memberEmail1 + memberEmail2;
    console.log('완성된 이메일 : ' + email);
    
    const checkInput = $('.mail-check-input');
    $.ajax({
        type: 'get',
        url: '/racket/mailCheck',
        data: "email=" + email,
        success: function(data) {
            console.log("data : " + data);
            checkInput.attr('disabled', false);
            code = data;
            alert('인증번호가 전송되었습니다.');
        }
    });
});
	// 이메일 확인
	function validateEmail(email) {
	    // Regular expression for validating email addresses
	    const emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
	    return emailRegex.test(email);
	}

	// 인증번호 비교
	$('#mail-check-btn').click(function() {
	    const inputCode = $('.mail-check-input').val();
		const $resultMsg = $('#mail-check-warn');
		
		if (inputCode === code) {
	        // 인증번호 일치 시
	        $resultMsg.html('인증번호가 일치합니다.');
	        $resultMsg.css('color', 'green');
	        $('#mail-check-btn').attr('disabled', true);
	        $('#mail-send-btn').attr('disabled', true);
	        $('#id-send-btn').attr('disabled', false);  // 다음 버튼 활성화
	        // ...
	    } else {
	        // 인증번호 불일치 시
	        $resultMsg.html('인증번호가 불일치합니다. 다시 확인해주세요!.');
	        $resultMsg.css('color', 'red');
	    }
	});
