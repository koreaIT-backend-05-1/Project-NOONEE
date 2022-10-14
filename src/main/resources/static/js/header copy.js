const navMenu = document.querySelectorAll(".nav-menu");
const subMenu = document.querySelectorAll(".nav-sub");
const visibleDiv = document.querySelectorAll(".nav-menu div");

for(let i = 0; i < navMenu.length; i++) {
    navMenu[i].onmouseover = () => {
        subMenu[i].classList.remove("visible");
        visibleDiv[i].classList.add("fadein");
    }

    navMenu[i].onmouseout = () => {
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
