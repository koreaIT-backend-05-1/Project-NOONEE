let nowPage = 1;

if(localStorage.getItem('page') == null) {
 	load(nowPage);
	localStorage.removeItem('page');
}else {
	load(localStorage.getItem('page'));
	localStorage.removeItem('page');
}

function load(nowPage) {
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/user-list/${nowPage}`,
		dataType: "json",
		success: (response) => {
			if(response.data[0] != null) {
				getList(response.data);
				inquiryPageNumber(response.data[0].totalinquiryCount, nowPage);
			}else{
				getList(new Array());
				inquiryPageNumber(1, 1);
			}
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function getList(list) {
	const tbody = document.querySelector("tbody");
	
	tbody.innerHTML = "";
	
	list.forEach(inquiry => {
		tbody.innerHTML += `
			<tr class="board-list-row">
                <td>${inquiry.inquiryCode}</td>
                <td class="inquiry-title">
                    <span>${inquiry.inquiryTitle}</span>
                    <div class="visible">
                        <i class="fa-regular fa-comment fa-1x"></i>
                        <span>1</span>
                    </div>
                </td>
                <td>${inquiry.username}</td>
                <td>${inquiry.createDate}</td>
                <td>${inquiry.inquiryCount}</td>
                <td><button type="button" class="list-button delete-button"><i class="fa-regular fa-trash-can"></i></button></td>
            </tr>
		`	
	});
}

function inquiryPageNumber(totalinquiryCount, selectPage) {
	const pageBtn = document.querySelector(".page-btn-box");
	const totalPageCount = totalinquiryCount % 10 == 0 ? totalinquiryCount / 10 : (totalinquiryCount / 10) + 1;
	const startIndex = nowPage % 9 == 0 ? nowPage - 8 : nowPage - (nowPage % 9) + 1;
	const endIndex = startIndex + 8 <= totalPageCount ? startIndex + 8 : totalPageCount;
	
	pageBtn.innerHTML = ``;
	
	if(startIndex != 0) {
		pageBtn.innerHTML += `
			<button type="button" class="page-button pre">&lt;</button>
		`;
	}
	
	for(let i = startIndex; i <= endIndex; i++) {
		pageBtn.innerHTML += `
			<button type="button" class="page-number-btn ${selectPage == i ? 'select-page' : ''}">${i}</button>
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
				console.log(button)
				
			}
		}
	})
}







