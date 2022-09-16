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
                <div class="questions-answer${index} visible">
                    <span>${faq.faqContent}</span>
                </div>
            </div>
		`; 
	});
}

const questionBtn = document.querySelector(".question-btn");
	questionBtn.onclick = () =>  {
	    if(questionBtn.innerText == "+") {
	        questionBtn.innerText = "-";
	    }else {
	        questionBtn.innerText = "+";
	    }
	    
	    const questionsAnswer = document.querySelectorAll(".questions-answer");
	    questionsAnswer.classList.toggle("visible");
}
