
const addButton = document.querySelector(".add-button");
const fileInput = document.querySelector(".file-input");
const submitButton = document.querySelector(".submit-button");

let productImageFiles = new Array();

addButton.onclick = () => {
    fileInput.click();
}

fileInput.onchange = () => {
    const formData = new FormData(document.querySelector("form"));
    let changeFlge = false;

    formData.forEach((value) => {
        if(value.size != 0) {
            productImageFiles.push(value);
            changeFlge = true;
        }
    });
    
    if(changeFlge){
        getImagePreview();
        fileInput.value = null;
    }
}

function getImagePreview() {
    const productImages = document.querySelector(".product-images");

    productImages.innerHTML = "";

    productImageFiles.forEach((file, i) => {
        const reader = new FileReader();
    
        reader.onload = (e) => {
            productImages.innerHTML += `
                <div class="img-box">
                    <span class="icon-box fa-x"></span>
                    <img class="product-img" src="${e.target.result}">
                </div>
            `;

            const deleteButton = document.querySelectorAll(".fa-x");
            deleteButton.forEach((xbutton, index) => {
            console.log(xbutton)
            xbutton.onclick = () => {
                    if(confirm("상품 이미지를 지우시겠습니까?")) {
                        productImageFiles.splice(index, 1);
                        console.log(productImageFiles);
                        getImagePreview();
                    }
                };
            })
        }

        setTimeout(() => {reader.readAsDataURL(file)}, i * 100);
    });
}

submitButton.onclick = () => {
    const productInput = document.querySelectorAll(".product-input");

    let formData = new FormData();

    formData.append("category_code", productInput[0].value);
    formData.append("collection_code", productInput[1].value);
    formData.append("product_name", productInput[2].value);
    formData.append("product_price", productInput[3].value);

    productImageFiles.forEach((file) => {
        formData.append("files", file);
    });

    add(formData);

//     for (var item of formData.entries()) {
//         console.log(item[0] + " : " + item[1]);
//     }
}

function add(formData) { 
    $.ajax({
        async: false,
        type: "post",
        url: "/api/v1/admin/product",
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        data: formData,
        dataType: "json",
        success: (response) => {
            alert("상품 등록 완료");
            console.log(response.data);
        },
        error: (error) => {
            alert("상품 등록 실패");
            console.log(error);
        }
    });
}

