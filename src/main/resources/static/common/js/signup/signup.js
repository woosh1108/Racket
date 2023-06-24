		function checkId() {
		    $('#regist').prop('disabled', true);
		}

		function checkInput(input) {
	        var pattern = /[\W]|[\uAC00-\uD7A3]/g;
	        return !pattern.test(input);
	    }
	    function checkDuplicateId() {
	    	var memberId = $('#memberId').val();

	        if (memberId.trim() === '') {
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
				alert("아이디에 특수문자 및 한글이 포함되어 있습니다.")
				$('#regist').prop('disabled', true); 
				$('.available-id').hide();
                $('.duplicated-id').hide();
			}
        }