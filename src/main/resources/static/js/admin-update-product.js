let productCode = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);

load();

function load() {
    $.ajax({
        async: false,
        type: "get",
        url: `/api/v1/admin/product/${productCode}`,
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
    const productName = document.querySelector(".product-name");
    const productPrice = document.querySelector(".product-price");
    const categorySelect = document.querySelector(".category-select");
    const collectionSelect = document.querySelector(".collection-select");
    const productImg = document.querySelector(".product-img");
    
    productName.value = product.productName;
    productPrice.value = product.productPrice;
    categorySelect.value = product.categoryCode;
    collectionSelect.value = product.collectionCode;
    productImg.innerHTML = "";
    productImg.innerHTML = `
        <img src="/static/img/product/${product.tempName}">
    `

    const updateButton = document.querySelector(".update-button");

    updateButton.onclick = () => {
        console.log(productName.value);
        console.log(productPrice.value);
        console.log(categorySelect.value);
        console.log(collectionSelect.value);
    }
}

