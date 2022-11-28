const submitBtn = document.querySelector(".submit");

let user = getPrincipal();

loadUser(user);

function loadUser(user) {
	const writerName = document.querySelector(".input-group");
	
	if(user == null) {
		writerName.innerHTML = `
		<input type="text" placeholder="작성자 이름" class="inputs username">
        <input type="text" placeholder="비밀번호" class="inputs user-password">
        `;
        username = document.querySelector(".username").value;
        inquiryPassword = document.querySelector(".user-password").value;
        
	}else {
		writerName.innerHTML = `
        <input class="username" value="${user.user_name}" readonly></input>
        `;
        username = user.user_name;
        usercode = user.user_code;
	}
}

submitBtn.onclick = () => {
	const textareaValue = document.querySelector("#summernote").value;
	const questionTitle = document.querySelector(".question-title").textContent;
	let username = null;
	let inquiryPassword = "1234";
	const usercode = user.user_code;
	
	
    username = document.querySelector(".username").value;
	
	if(user == null) {
        inquiryPassword = document.querySelector(".user-password").value;
	}
	console.log(user); 
    
    $.ajax({
        async: false, 
        type: "post", 
        url: "/api/v1/inquiry/",
        contentType: "application/json",
        data: JSON.stringify({
			"userCode": usercode,
			"questionTitle": questionTitle,
			"textareaValue": textareaValue,
			"username": username,
			"inquiryPassword": inquiryPassword
		}), 
        dataType: "json",
        success: (response) => {
			alert( "success");
			console.log(response.data);
			location.href = "/inquiry/list";
        },
        error: (error) => {
            console.log(error);
        }
})
}

function getPrincipal() {
	let user = null;
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/auth/principal",
		dataType: "json",
		success: (response) => {
			user = response.data;
			console.log(user);
		},
		error: (error) => {
			console.log(error);
		}
	});
	
	return user;
}








