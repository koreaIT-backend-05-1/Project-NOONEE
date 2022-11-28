let nowPage = 1;

load(nowPage);

loaduser(user);

function load(nowPage) {
	const searchValue = document.querySelector(".search-input").value;
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/inquiry/list/${nowPage}` ,
		data: {
			"searchValue": searchValue
		},
		dataType: "json",
		success: (response) => {
			if(response.data[0] != null) {
				getList(response.data);
				inquiryPageNumber(response.data[0].totalinquiryCount);
				getinquiry(response.data)
			}else{
				getList(new Array());
				inquiryPageNumber(0);
			}
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function getList(list, index) {
	const tbody = document.querySelector("tbody");
	
	tbody.innerHTML = "";
	
	list.forEach(inquiry => {
		tbody.innerHTML += `
			<tr class="board-list-row">
                <td class="inquiry-code">${inquiry.inquiryCode}</td>
                <td>
                    <i class="fa-solid fa-lock"></i>
                    <span class="inquiry-title">${inquiry.inquiryTitle}</span>
                    <div class="visible">
                        <i class="fa-regular fa-comment fa-1x"></i>
                        <span>1</span>
                    </div>
					<input type="hidden" class="inquiry-password" value=${inquiry.inquiryPassword}>
                </td>
                <td>${inquiry.username}</td>
                <td>${inquiry.createDate}</td>
                <td>${inquiry.inquiryCount}</td>
            </tr>
		`	
	});
	const inquiryTitle = document.querySelectorAll(".inquiry-title");
	const pwModal = document.querySelector(".pw-modal");
	
	
	
	for(let i = 0; i < inquiryTitle.length; i++) {
		inquiryTitle[i].onclick = () => {
			pwModal.classList.toggle("pw-modal-visible");
			const okBtn = document.querySelector(".ok-btn");
			okBtn.onclick = () => {
				console.log("클릭")
				const pwInput = document.querySelector(".pw-input");
				const inquiryPassword = document.querySelectorAll(".inquiry-password");
				if(pwInput.value == inquiryPassword[i].value) {
					const inquiryCode = document.querySelectorAll(".inquiry-code");
					location.href = "/inquiry/detail/" + inquiryCode[i].textContent;
				}
			}
		}
	}

	pwModal.onclick = (e) => {
		if(e.target == pwModal) {
			pwModal.classList.toggle("pw-modal-visible");
		}
	}
}		


	


function getinquiry(list) {
	

}
	

function inquiryPageNumber(totalinquiryCount) {
	const pageBtn = document.querySelector(".page-btn-box");
	const totalPageCount = totalinquiryCount % 10 == 0 ? totalinquiryCount / 10 : (totalinquiryCount / 10) + 1;
	const startIndex = nowPage % 9 == 0 ? nowPage - 8 : nowPage - (nowPage % 9) + 1;
	const endIndex = startIndex + 8 <= totalPageCount ? startIndex + 8 : totalPageCount;
	const totalCount = document.querySelector(".list-amount");
	
	totalCount.innerHTML = ``;
	
	totalCount.innerHTML = `${totalinquiryCount}`;
	
	pageBtn.innerHTML = ``;
	
	if(startIndex != 0) {
		pageBtn.innerHTML += `
			<button type="button" class="page-button pre">&lt;</button>
		`;
	}
	
	for(let i = startIndex; i <= endIndex; i++) {
		pageBtn.innerHTML += `
			<button type="button" class="page-number-btn">${i}</button>
		`
	}
	
	if(endIndex != totalinquiryCount) {
		pageBtn.innerHTML += `
			<button type="button" class="page-button next">&gt;</button>
		`;
	}
	
	if(startIndex != 1) {
		const prePageButton = document.querySelector(".pre");
		prePageButton.onclick = () => {
			nowPage = startIndex - 1;
			load(nowPage);
		}
	}
	
	if(endIndex != totalinquiryCount) {
		const nextPageButton = document.querySelector(".next");
		nextPageButton.onclick = () => {
			nowPage = endIndex + 1;
			load(nowPage);
		}
	}
	
	const pageNumberButtons = document.querySelectorAll(".page-number-btn");
	pageNumberButtons.forEach(button => {
		if(button.textContent != "<" && button.textContent != ">"){
			button.onclick = () => {
				nowPage = button.textContent;
				load(nowPage);
			}
		}
	})
}

function loaduser(user) {
	const writeBtn = document.querySelector(".write-btn");
	if(user == null) {
		writeBtn.onclick = () => {
			alert("로그인 필요");
		}
	}else {
		writeBtn.onclick = () => {
			location.href = "/inquiry/insert";
	}
	
}
}

