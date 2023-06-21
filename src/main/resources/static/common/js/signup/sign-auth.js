	$('#mail-send-btn').click(function() {
    const userEmail1 = $('#userEmail1').val();
    const userEmail2 = $('#userEmail2').val();
    
    if (!validateEmail(userEmail1 + userEmail2)) {
        alert('올바른 이메일 주소가 아닙니다.');
        return;
    }
    
    const email = userEmail1 + userEmail2;
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
	//이메일 확인
	function validateEmail(email) {
	    // Regular expression for validating email addresses
	    const emailRegex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
	    return emailRegex.test(email);
	}

	// 인증번호 비교 
	$('#mail-check-btn').click(function() {
	    const inputCode = $('.mail-check-input').val();
		const $resultMsg = $('#mail-check-warn');
		
		if(inputCode === code){
			$resultMsg.html('인증번호가 일치합니다.');
			$resultMsg.css('color','green');
			$('#mail-Check-Btn').attr('disabled',true);
			$('#next-btn').attr('disabled', false);
			$('#userEmail1').attr('readonly',true);
			$('#userEmail2').attr('readonly',true);
			$('#userEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
	         $('#userEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
		}else{
			$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
			$resultMsg.css('color','red');
		}
	});
	
	$('#next-btn').click(function() {
	    window.location.href = '/racket/signup'; // 다음 페이지로 이동
	  });