const logo = document.querySelector(".logo-img");
const navMenus = document.querySelectorAll(".nav-menu");
const subMenu = document.querySelectorAll(".nav-sub");
const visibleDiv = document.querySelectorAll(".nav-menu div");



for(let i = 0; i < navMenus.length; i++) {
    navMenus[i].onmouseover = () => {
        subMenu[i].classList.remove("visible");
        visibleDiv[i].classList.add("fadein");
    }

    navMenus[i].onmouseout = () => {
        visibleDiv[i].classList.add("visible");
        visibleDiv[i].classList.remove("fadein");
    }

    subMenu[i].onmouseover = () => {
        visibleDiv[i].classList.remove("visible");
        visibleDiv[i].classList.add("fadein");
    }

    subMenu[i].onmouseout = () => {
        visibleDiv[i].classList.add("visible");
        visibleDiv[i].classList.remove("fadein");
    }

}

navMenus.forEach((navMenu) => {
	const mainMenuButton = navMenu.querySelector(".main-menu-btn");
	const subMenuLinks = navMenu.querySelectorAll("a");
	subMenuLinks.forEach((subMenuLink, index) => {
		if(mainMenuButton.textContent == 'BRIDAL'){
			subMenuLink.href = `/product/bridal/${index + 1}`
		}else if(mainMenuButton.textContent == 'COLLECTION'){
			subMenuLink.href = `/product/collection/${index + 1}`
		}
	})
	
})

logo.onclick = () => {
	location.href = "/main";
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
		},
		error: (error) => {
			console.log(error);
		}
	});
	
	return user;
}

function loadHeader(user) {
	const authItems = document.querySelector(".auth-items");
	
	if(user == null) {
		authItems.innerHTML = `
		<button type="button" class="sub-menu-btn signin">LOGIN</button>
        <button type="button" class="sub-menu-btn signup">JOIN</button>
        `;
        
        const signin = document.querySelector(".signin");
        const signup = document.querySelector(".signup");
        
        signin.onclick = () => {
			location.href = "/auth/signin";
		}
		
		signup.onclick = () => {
			location.href = "/auth/signup";
		}
	}else {
		authItems.innerHTML = `
        <button type="button" class="sub-menu-btn logout">LOGOUT</button>
		<button type="button" class="sub-menu-btn mypage">MY</button>
        `;
        
        const logout = document.querySelector(".logout");
        const mypage = document.querySelector(".mypage");
        
        logout.onclick = () => {
			location.replace("/logout");
		}
		
		mypage.onclick = () => {
			location.href = "/user/mypage";
		}
	}
}

let user = getPrincipal();

loadHeader(user);

function getUser() {
	return user;
}

const search = document.querySelector(".search");
const modalClose = document.querySelector(".modal-close");
const searchModal = document.querySelector(".search-modal");

search.onclick = () => {
	
	searchModal.classList.toggle("modal-visible");
}

modalClose.onclick = () => {
	searchModal.classList.toggle("modal-visible");
}




