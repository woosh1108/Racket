window.addEventListener('DOMContentLoaded', (event) => {
	 function setMemberData() {
		    const memberData = JSON.parse(window.localStorage.getItem("data"));
		    const memberEmail = memberData.email;
		    const memberName = memberData.nickname;
//			console.log(memberData);
		    // 입력 필드 값을 불러온 사용자 정보로 설정
		    document.querySelector("#memberEmail").value = memberEmail;
		    document.querySelector("#memberName").value = memberName;
		    if(memberEmail!=null){
		    	$('#memberEmail').prop('readonly', true);
		    }
		    if(memberName!=null){
		    	$('#memberName').prop('readonly', true);
		    }
		}
	 setMemberData();
	 window.localStorage.clear;
	});