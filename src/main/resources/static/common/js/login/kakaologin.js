        window.Kakao.init('0f5c58b68fea7766e85c60f243488cec');
        function kakaoLogin() {
            window.Kakao.Auth.login({
                scope: 'profile_nickname, account_email, gender', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
                success: function(response) {
                    console.log(response) // 로그인 성공하면 받아오는 데이터
                    window.Kakao.API.request({ // 사용자 정보 가져오기 
                        url: '/v2/user/me',
                        success: (res) => {
                            const kakao_account = res.kakao_account;
//                            console.log(kakao_account)
//                            console.log(kakao_account.email)
//                            console.log(kakao_account.gender)
//                            console.log(kakao_account.profile.nickname)
                        	const data = {
                        	 email: kakao_account.email,
                             nickname: kakao_account.profile.nickname,
                             gender: kakao_account.gender
                        	  };
                            console.log(data)
//                        	sendKakaoDataToServer(kakao_account);
                            window.localStorage.setItem("data", JSON.stringify(data));
                            alert("회원가입페이지로 이동합니다.")
                            window.location.href='/racket/signup_kakao'; //리다이렉트 되는 코드
                        }
                    });
                    
                },
                fail: function(error) {
                    console.log(error);
                }
            });
        }
        
        
        function sendKakaoDataToServer(kakao_account) {
            const xhr = new XMLHttpRequest();
            const url = "/signup_kakao";

            // FormData 객체를 생성하고 데이터를 추가합니다.
            const formData = new FormData();
            formData.append("memberEmail", kakao_account.email);
            formData.append("memberNickname", kakao_account.nickname);
            formData.append("memberGender", kakao_account.gender);
            alert(formData);
            // 서버 응답이 도착했을 때의 처리 로직을 정의합니다.
            xhr.onload = function() {
                if (xhr.status === 200) {
                    // 서버 응답이 성공적으로 도착한 경우의 처리 로직
                    console.log("Data successfully sent to the server.");
                    window.location.href = "/signup_kakao";
                } else {
                    // 서버 응답이 실패한 경우의 처리 로직
                    console.error("Error while sending data to the server.");
                }
            };

            // 서버와 통신하기 위한 설정을 지정합니다.
            xhr.open("POST", url, true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // 데이터를 서버로 전송합니다.
            xhr.send(formData);
            
        }
        
        
    	function kakaoLogout() {
        	if (!Kakao.Auth.getAccessToken()) {
    		    console.log('Not logged in.');
    		    return;
    	    }
    	    Kakao.Auth.logout(function(response) {
        		alert(response +' logout');
    		    window.location.href='/'
    	    });
    	};
