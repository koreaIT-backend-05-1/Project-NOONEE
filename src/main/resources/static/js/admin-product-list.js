let nowPage = 1;

load(nowPage);

function load(nowPage) {
	// const searchFlag = document.querySelector(".select-box").value;
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/admin/list/${nowPage}` ,
		data: {
			"searchFlag": searchFlag
		},
		dataType: "json",
		success: (response) => {
            getList(response.data);
            userPageNumber(response.data[0].totalProductCount, nowPage);
            console.log(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function getList(list) {
    const tbody = document.querySelector("tbody");

    tbody.innerHTML = "";

    list.forEach(product => {
        
    });

    
}


function userPageNumber(totalinquiryCount, selectPage) {
	const pageBtn = document.querySelector(".page-btn-box");
	const totalPageCount = totalinquiryCount % 10 == 0 ? totalinquiryCount / 10 : (totalinquiryCount / 10) + 1;
	const startIndex = nowPage % 5 == 0 ? nowPage - 4 : nowPage - (nowPage % 5) + 1;
	const endIndex = startIndex + 4 <= totalPageCount ? startIndex + 4 : totalPageCount;
	
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