const questionTitle = document.querySelector(".questions-title");
const questionsBtn = document.querySelector(".questions-btn");
const questions = document.querySelector(".questions-detail");
const btnClick = document.querySelector(".btn-click");
btnClick.onclick = () => {
    questionsBtn.click();
}

questionsBtn.addEventListener("click", function() {
    if(questionsBtn.innerText == "+") {
        questionsBtn.innerText = "-";
    }else {
        questionsBtn.innerText = "+";
    }
    
    questions.classList.toggle("visible");
    
})