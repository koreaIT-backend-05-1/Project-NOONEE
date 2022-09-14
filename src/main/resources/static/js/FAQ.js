const questionTitle = document.querySelector(".question-title");
const questionBtn = document.querySelector(".question-btn");
const questions = document.querySelector(".question-detail");

questionBtn.addEventListener("click", function() {
    if(questionBtn.innerText == "+") {
        questionBtn.innerText = "-";
    }else {
        questionBtn.innerText = "+";
    }
    
    const questionsAnswer = document.querySelector(".questions-answer");
    questionsAnswer.classList.toggle("visible");
           
})

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
	
	list.forEach(faq => {
		questionList.innerHTML += `
			<div class="question">
                <div class="question-title">
                    <label for="question" class="btn-label">${faq.faqTitle}</label>
                    <button type="button" id="question" class="question-btn">+</button>
                </div>
                <div class="questions-answer visible">
                    <span>${faq.faqContent}</span>
                </div>
            </div>
		`;
		
		const question = document.querySelectorAll(".question");
	});
}