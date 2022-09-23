const inputs = document.querySelectorAll("input");
const signupButton = document.querySelectorAll("button")[0];

let checkUsernameFlag = false;
 
inputs[0].onblur = () => {

	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/auth/signup/validation/username",
		data: {username : inputs[0].value},
		dataType: "json",
		success: (response) => {
			checkUsernameFlag = response.data;
			
			if(checkUsernameFlag) {
				alert("사용 가능한 이메일입니다.");
			}else{
				alert("이미 사용중인 이메일입니다.");
			}
		},
		error: (error) => {
			if(error.status == 400){
				alert(JSON.stringify(error.responseJSON.data));
			}else{
				console.log("요청 실패");
				console.log(error);				
			}
		}
	});
}

signupButton.onclick = () => {
	let signupData = {
		email: inputs[0].value,
		password: inputs[1].value,
		username: inputs[3].value,
		userphone: inputs[4].value,
		"checkUsernameFlag": checkUsernameFlag
	}
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/auth/signup",
		contentType: "application/json",
		data: JSON.stringify(signupData),
		dataType: "json",
		success: (response) => {
			if(response.data){
				alert("회원가입 완료.");
				location.replace("/auth/signin");
			}
		},
		error: (error) => {
			if(error.status == 400){
				alert(JSON.stringify(error.responseJSON.data));
			}else{
				console.log("요청 실패");
				console.log(error);				
			}
		}
	})
}















