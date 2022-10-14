const questionTitle = document.querySelector(".question-title");

const questions = document.querySelector(".question-detail");

load();

function load() {
	const searchValue = document.querySelector(".search-input").value;
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/noonee/faq",
		data: {
			"searchValue": searchValue
		},
		dataType: "json",
		success: (response) => {
			getFaqList(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	});
	
}

function getFaqList(list) {
	const questionList = document.querySelector(".question-list");
	
	questionList.innerHTML = "";
	
	list.forEach((faq,index) => {
		questionList.innerHTML += `
			<div class="question">
                <div class="question-title">
                    <label for="question${index}" class="btn-label">${faq.faqTitle}</label>
                    <button type="button" id="question${index}" class="question-btn">+</button>
                </div>
                <div class="questions-answer visible">
                    <span>${faq.faqContent}</span>
                </div>
            </div>
		`; 
	});
}

const questionBtn = document.querySelectorAll(".question-btn");
for(let i = 0; questionBtn.length; i++){
	
	questionBtn[i].onclick = () =>  {
	    if(questionBtn[i].innerText == "+") {
	        questionBtn[i].innerText = "-";
	    }else {
	        questionBtn[i].innerText = "+";
	    }
	    
	    const questionsAnswer = document.querySelectorAll(".questions-answer");
	    questionsAnswer[i].classList.toggle("visible");
	}
}
