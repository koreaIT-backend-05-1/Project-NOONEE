const nextBtn = document.querySelector(".next-btn");
const preBtn = document.querySelector(".pre-btn");

let inquiryCode = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);

load("/api/v1/inquiry/");

function load(uri) {
    $.ajax({
        async: false,
        type: "get",
        url: uri + inquiryCode,
        datatype: "json",
        success: (response) => {
			console.log(JSON.stringify(response.data))
            getInquiry(response.data);
        },
        error: (error) => {
            console.log(error);
        }
    })
}

function getInquiry(inquiry) {
	const title = document.querySelector(".title-text");
	const writer = document.querySelector (".writer");
	const inquiryContent = document.querySelector(".inquiry-content");
	title.innerHTML = "";
	title.innerHTML = inquiry.inquiryTitle;
	
	writer.innerHTML = "";
	writer.innerHTML = `
		<div class="writer">
            <img src="" alt="">
            <div class="name">
                <span>${inquiry.username}</span>
                <div class="details">
                    <span>${inquiry.inquiryTitle}</span>
                    <span>${inquiry.createDate}</span>
                    <span>${inquiry.inquiryCount}</span>
                </div>
            </div>
        </div>
	`;
	
	inquiryContent.innerHTML = "";
	inquiryContent.innerHTML = inquiry.inquiryContent;
}

nextBtn.onclick = () => {
	load("api/v1/inquiry/next/")
}

preBtn.onclick = () => {
	load("api/v1/inquiry/pre/")
}