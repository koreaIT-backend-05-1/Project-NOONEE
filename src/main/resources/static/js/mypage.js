const username = document.querySelector(".user-name");
const updateUser = document.querySelector(".update-user");
const updateModal = document.querySelector(".update-modal");
const deleteUser = document.querySelector(".delete-user");
const deleteModal = document.querySelector(".delete-modal");
const cancel = document.querySelector(".cancel");

username.innerHTML = "";

username.innerHTML = `${user.user_name}님 안녕하세요.`;



updateUser.onclick = () => {
	updateModal.classList.toggle("update-visible");
	
}
	
deleteUser.onclick = () => {
	deleteModal.classList.toggle("visible");
	
}

cancel.onclick = () => {
	deleteModal.classList.toggle("visible");
}
	
