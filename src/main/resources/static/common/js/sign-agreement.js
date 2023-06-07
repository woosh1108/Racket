   /*document.addEventListener("DOMContentLoaded", function() {*/
            document.getElementById("continue").disabled = false;
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
/*   });*/