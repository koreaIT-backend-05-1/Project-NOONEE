const categoryCode = window.location.pathname.slice(-1);
const collectionCode = window.location.pathname.slice(-1);
const productList = document.querySelector(".product-list");

clearProductList();

let collectionFlag = isCollectionType();

getProductListByCollectionFlag();

function isCollectionType() {
	return location.pathname.indexOf("collection") != -1;
}

function clearProductList() {
	productList.innerHTML = ``;
}

function getProductListByCollectionFlag() {
	if(collectionFlag) {
		loadCollectionList();
	}else {
		loadCategoryList();
	}
}

function loadCategoryList() {
	
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/product/bridal/${categoryCode}`,
		datatype: "json",
		success: (response) => {
			getList(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	});
}

function loadCollectionList() {
	
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/product/collection/${collectionCode}`, 
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
	list.forEach(product => {
		let price = product.productPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		let showPrice = price + "원";
		productList.innerHTML += `
			<div class="product-img-price">
				<input type="hidden" class="product-code" value="${product.productCode}">
                <img src="/image/product/${product.tempName}" class="product-img">
                <div class="product-name-price">
                    <span class="">${product.productName}</span>
                    <span class="text-style1 product-price">${showPrice}</span>
                </div>
            </div>
		`;

	});
	
	
	const productImgPrice = document.querySelectorAll(".product-img-price");
	
	for(let i = 0; i < productImgPrice.length; i++) {
		productImgPrice[i].onclick = () => {
			const productCodes = document.querySelectorAll(".product-code")[i].value;
			location.href = "/product/detail/" + productCodes;
		}
	}
}

// function loadProductImg(img) {
// 	

// 	productImg = 
// }

		







