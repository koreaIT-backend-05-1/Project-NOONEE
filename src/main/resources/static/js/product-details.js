const productImg = document.querySelector(".product-img");

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
	const productPrice = document.querySelector(".product-price");
	const detailsImg = document.querySelector(".details-img");
	
	productImg.innerHTML = ``;
	productImg.innerHTML = `<img src="${product.productImg}" alt="">`;
	productTitle.innerHTML = product.productName;
	productPrice.innerHTML = product.productPrice;
	detailsImg.innerHTML = ``;
	detailsImg.innerHTML = `
		<img src="${product.productImg}" alt="">
        <img src="${product.productImg}" alt="">
        <span>힘들다.......</span>
	`;
}