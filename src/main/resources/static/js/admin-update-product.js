const fileInput = document.querySelector(".file-input");
const productName = document.querySelector(".product-name");
const productPrice = document.querySelector(".product-price");
const categorySelect = document.querySelector(".category-select");
const collectionSelect = document.querySelector(".collection-select");
const productImgs = document.querySelector(".product-images");
let imgValue = null;
let formData = new FormData();

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
    productName.value = product.productName;
    productPrice.value = product.productPrice;
    categorySelect.value = product.categoryCode;
    collectionSelect.value = product.collectionCode;
    productImgs.innerHTML = "";
    productImgs.innerHTML = `
        <img class="product-img" src="/image/product/${product.tempName}">
    `
    const addImgButton = document.querySelector(".add-img-button");
    
    const productImg = document.querySelector(".product-img");
    

    addImgButton.onclick = () => {
        fileInput.click();
    }

    const updateButton = document.querySelector(".update-button");

    updateButton.onclick = () => {
        formData.append("productName", productName.value);
        formData.append("productPrice", productPrice.value);
        formData.append("categoryCode", categorySelect.value);
        formData.append("collectionCode", collectionSelect.value);
        formData.append("img", imgValue);
        formData.append("tempName", product.tempName);
        // formData.append("img", fileInput);
        // let imgName = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
        console.log(productImg.src)

        for (var item of formData.entries()) {
            console.log(item[0] + " : " + item[1]);
        }
        updateProduct(product);
    }
}

fileInput.onchange = () => {
    const formData = new FormData(document.querySelector("form"));
    let changeFlag = false;
    imgValue = null;

    formData.forEach((value) => {
        if(value.size != 0) {
            changeFlag = true;
            imgValue = value;
            console.log(imgValue);
        }
    });
    
    if(changeFlag){
        getImagePreview(imgValue);
        fileInput.value = null;
    }
}

function getImagePreview(imgValue) {
    const productImages = document.querySelector(".product-images");

    productImages.innerHTML = "";
    
    const reader = new FileReader();

    reader.onload = (e) => {
        productImages.innerHTML = `
            <div class="img-box">
                <img class="product-img" src="${e.target.result}">
            </div>
        `;
       
    }

    setTimeout(() => {reader.readAsDataURL(imgValue)},  100);
    
}

function updateProduct(product) {

    $.ajax({
        async: false,
        type: "put",
        url: `/api/v1/admin/update/${product.productCode}`,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        data: formData,
        dataType: "json",
        success: (response) => {
            if(response.data) {
                alert("수정 완료")
            }
        },
        error: (request, status, error) => {
            console.log(request.status);
            console.log(request.responseText);
            console.log(error);
        }
    });

}




