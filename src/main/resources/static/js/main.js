const mainBtn = document.querySelectorAll(".img-btn button");
const marketPlace = document.querySelector(".market-place-text");
const img = document.querySelector(".img");
const directionsBtn = document.querySelector(".directions");

let flag = [true, false, false, false]
let mainText = ["Edizione NOONEE - HANNAM MAIN", "b", "c", "d"];
let mainImg = ["/static/img/brandPage/page3-4.jpg", "/static/img/brandPage/page3-5.jpg",
 "/static/img/brandPage/page3-6.jpg", "/static/img/brandPage/page3-7.jpg"];

change();

load();

for(let i = 0; i < mainBtn.length; i++) {
    mainBtn[i].onmousedown = () => {
		for(let j = 0; j < flag.length; j++) {
			flag[j] = false;
		}
		marketPlace.textContent = mainText[i];
		img.src = mainImg[i];
        flag[i] = true;
        change();
    }
}

function change() {
	for(let i = 0; i < flag.length; i++) {
		mainBtn[i].style.backgroundColor ="#ffffff00";
	}
	mainBtn[flag.indexOf(true)].style.backgroundColor ="#ffffff";
}

directionsBtn.onclick = () => {
	location.href = "/shopinfo";
}


function load() {
	
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/product/loadmain`, 
		datatype: "json",
		success: (response) => {
			getList(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	});
	
}

function getList(list) {
	const productList = document.querySelector(".product");
	productList.innerHTML = "";
		list.forEach(product => { 
			productList.innerHTML += `
				<div class="product-img-price">
					<input type="hidden" class="product-code" value="${product.productCode}">
	                <img src="${product.productImg}">
	                <div class="product-name-price">
	                    <span class="text-style3 ">${product.productName}</span>
	                    <span class="text-style1 product-price">${product.productPrice}</span>
	                </div>
	            </div>
			`
		})
		
	const productImgPrice = document.querySelectorAll(".product-img-price");
	
	for(let i = 0; i < productImgPrice.length; i++) {
		productImgPrice[i].onclick = () => {
			const productCodes = document.querySelectorAll(".product-code")[i].value;
			location.href = "/product/detail/" + productCodes;
		}
	}
}
	
 
const linkBtn = document.querySelector(".link-btn");

linkBtn.onclick = () => {
	location.href = "https://www.youtube.com/channel/UCy-HBYQTyVlnbctMvK4y3ig";
}





