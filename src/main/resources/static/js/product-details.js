const productImg = document.querySelector(".product-img");
const interestList = document.querySelector(".interest-list btn2");

let productCode = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);

load("/api/v1/product/");

function load(uri) {
	$.ajax({
		async: false,
		type: "get",
        url: uri + productCode,
        datatype: "json",
        success: (response) => {
			console.log(JSON.stringify(response.data))
            getProduct(response.data);
        },
        error: (error) => {
            console.log(error);
        }
	})
}

function getProduct(product) {
	const productImg = document.querySelector(".product-img");	
	const productTitle = document.querySelector(".product-title");
	let price = product.productPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
	let showPrice = price + "원";
	const productPrice = document.querySelector(".product-price");
	const detailsImg = document.querySelector(".details-img");
	
	productImg.innerHTML = ``;
	productImg.innerHTML = `<img  src="/image/product/${product.tempName}" alt="">`;
	productTitle.innerHTML = product.productName;
	productPrice.innerHTML = showPrice;
	detailsImg.innerHTML = ``;
	detailsImg.innerHTML = `
		<img  src="/image/product/${product.tempName}" alt="">
        <img  src="/image/product/${product.tempName}" alt="">
        <span>힘들다.......</span>
	`;
}

