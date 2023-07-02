 function validateForm() {
					        var searchBox = document.getElementById("searchBox");
					        var searchData = searchBox.value.trim();
					
					        if (searchData === "") {
					            alert("검색할 내용을 입력하세요.");
					            return false;
					        }
					
					        return true;
					    }