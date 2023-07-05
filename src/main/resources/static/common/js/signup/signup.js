		function checkId() {
		    $('#regist').prop('disabled', true);
		}
		function checkNick() {
		    $('#regist').prop('disabled', true);
		}
		function checkInput_nick(input) {
//		    var pattern =  /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣&&[^ㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣ\s]]+$/;
		    var pattern = /^[가-힣a-zA-Z0-9]+$/;
		    console.log(pattern.test(input))
		    if (pattern.test(input)&& input.length >= 2) {
		        return true;
		    } else {
		        return false;
		    }
		}
		function checkInput(input) {
		    var pattern = /[\W]|[\uAC00-\uD7A3]/g;
		    console.log(!pattern.test(input))
		    if (!pattern.test(input)&& input.length >= 6) {
		        console.log(input);
		        return true;
		    } else {
		        return false;
		    }
		}
	    function checkDuplicateId() {
	    	var memberId = $('#memberId').val();

	        if (memberId.trim() === '') {
	        	alert("아이디를 입력해주세요")
	            $('#regist').prop('disabled', true); 
	            return;
	        }
			if(checkInput(memberId)){
	        $.ajax({
	            type: 'POST',
	            url: '/racket/check-duplicate-id',
	            data: { memberId: memberId },
	            success: function (response) {
	            	alert("이미 사용되고 있는 아이디입니다.")
	                $('.duplicated-id').show(); // 중복인 경우 duplicated-id 요소를 표시
	                $('.available-id').hide(); // available-id 요소를 숨깁니다.
	                $('#regist').prop('disabled', true); 
	            },
	            error: function (jqXHR) {
	                if (jqXHR.status === 404) {
	                    // memberId가 중복되지 않을 경우
	                	alert("사용 가능한 아이디입니다.")
	                    $('.available-id').show(); // 사용 가능한 아이디 available-id 요소를 표시
	                    $('.duplicated-id').hide(); // duplicated-id 요소를 숨깁니다.
	                    $('#regist').prop('disabled', false);
	                } else {
	                    // 기타 오류인 경우
	                    $('#regist').prop('disabled', true); 
	                }
	            }
	        });
			}else{
				alert("아이디에 특수문자 및 한글, 혹은 글자수가 맞지 않습니다.")
				$('#regist').prop('disabled', true); 
				$('.available-id').hide();
                $('.duplicated-id').hide();
			}
        }
	    
	    window.addEventListener('DOMContentLoaded', (event) => {
	   	 function setMemberData() {
	   		    const memberEmail = window.localStorage.getItem("email");
	   		 document.querySelector("#memberEmail").value = memberEmail;
	   		if(memberEmail!=null){
	   			checkDuplicateEmail();
		    	$('#memberEmail').prop('readonly', true);
		    }
	   	}
		 setMemberData();
		 window.localStorage.clear;
		});
	    
	    function checkDuplicateEmail() {
	    	var memberEmail = $('#memberEmail').val();
	    	console.log(memberEmail)
	        $.ajax({
	            type: 'POST',
	            url: '/racket/check-duplicate-email',
	            data: { memberEmail: memberEmail },
	            success: function (response) {
	            	window.location.href='/racket/login'; //리다이렉트 되는 코드
	            	alert("이미 가입된 이메일입니다. 가입된 이메일로 로그인 해주세요")
	            },
	            error: function (jqXHR) {
	                if (jqXHR.status === 404) {
	                    $('#regist').prop('disabled', false);
	                } else {
	                    // 기타 오류인 경우
	                    $('#regist').prop('disabled', true); 
	                }
	            }
	        });
	    	}
	    
	    function checkDuplicateNick() {
	  	  var memberNick = $('#memberNick').val();
	  	  if (memberNick.trim() === '') {
	  	    $('#regist').prop('disabled', true);
	  	    return;
	  	  }

//	  	  var regex = /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣 ]*$/; // 한글, 알파벳, 공백만 허용
//	  	  if (!regex.test(memberNick)) {
//	  	    $('#regist').prop('disabled', true);
//	  	    $('.available-nick').hide();
//	  	    $('.duplicated-nick').hide();
//	  	    alert("한글, 알파벳, 공백만 입력해주세요.");
//	  	    return;
//	  	  }
	  	  if(checkInput_nick(memberNick)){
	  	  $.ajax({
	  	    type: 'POST',
	  	    url: '/racket/check-duplicate-Nick',
	  	    data: { memberNick: memberNick },
	  	    success: function (response) {
	  	      $('.duplicated-nick').show(); // 중복인 경우 duplicated-nick 요소를 표시
	  	      $('.available-nick').hide(); // available-nick 요소를 숨깁니다.
	  	      $('#regist').prop('disabled', true);
	  	    },
	  	    error: function (jqXHR) {
	  	      if (jqXHR.status === 404) {
	  	        $('.available-nick').show(); // 사용 가능한 아이디 available-nick 요소를 표시
	  	        $('.duplicated-nick').hide(); // duplicated-nick 요소를 숨깁니다.
	  	        $('#regist').prop('disabled', false);
	  	      } else {
	  	        $('#regist').prop('disabled', true);
	  	      }
	  	    }
	  	  });
	  	  }else{
	  			alert("닉네임 형식이 올바르지 않습니다.")
	  			$('#regist').prop('disabled', true); 
	  			$('.available-nick').hide();
	  	        $('.duplicated-nick').hide();
	  		}
	  }