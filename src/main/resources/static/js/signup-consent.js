const allConsent = document.querySelector(".all-consent");
const mustConsent = document.querySelector(".must-consent");
const personalInfom = document.querySelector(".personal-infom");
const signupBtn = document.querySelector(".signup-btn");
const cancelBtn = document.querySelector(".cancel-btn");

allConsent.onclick = () => {
	if(allConsent.checked == true) {
		mustConsent.checked = true;
		personalInfom.checked = true;
	}else {
		mustConsent.checked = false;
		personalInfom.checked = false;
	}
};

signupBtn.onclick = () => {
	if(mustConsent.checked == true && personalInfom.checked == true) {
		location.href = "/auth/signupdetail"
	}else {
		alert("이용약관 및 개인정보 처리방침에 동의하셔야 가입이 가능합니다.");
	}
}

cancelBtn.onclick = () => {
	location.href = "/auth/signup"
}
