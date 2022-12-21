let nowPage = 1;

load(nowPage);

function load(nowPage) {
	const searchFlag = document.querySelector(".select-box").value;
	$.ajax({
		async: false,
		type: "get",
		url: `/api/v1/admin/list/${nowPage}` ,
		data: {
			"searchFlag": searchFlag
		},
		dataType: "json",
		success: (response) => {
            getList(response.data);
            productPageNumber(response.data[0].totalProductCount, nowPage);
            console.log(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	})
}

function getList(list) {
    const tbody = document.querySelector("tbody");

    tbody.innerHTML = "";

    list.forEach(product => {
        let price = product.productPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
		let showPrice = price + "원";
        tbody.innerHTML += `
            <tr class="board-list-row product">
                <td>${product.productCode}</td>
                <td class="product-name">
                    <img  src="/image/product/${product.tempName}" class="product-img">
                    ${product.productName}
                </td>
                <td>${product.categoryName}</td>
                <td>${product.collectionName}</td>
                <td>${showPrice}</td>
                <td><button type="button" class="list-button delete-button"><i class="fa-regular fa-trash-can"></i></button></td>
            </tr>
        `
    });

    const boardListRow = document.querySelectorAll(".board-list-row");
    const productName = document.querySelectorAll(".product-name");
    const deleteButton = document.querySelectorAll(".delete-button");

    for(let i = 0; i< boardListRow.length; i++) {
        productName[i].onclick = () => {
            const productCode = boardListRow[i].querySelectorAll("td")[0].textContent;
            location.href = "/admin/update-product/" + productCode;
        }
        deleteButton[i].onclick = () => {
            confirm("해당 상품을 삭제 하시겠습니까?")
            const productCode = boardListRow[i].querySelectorAll("td")[0].textContent;
            $.ajax({
                async: false,
                type: "delete",
                url: "/api/v1/admin/" + productCode,
                dataType: "json",
                success: (response) => {
                    alert("상품 삭제 완료");
                    location.reload();
                },
                error: (error) => {
                    alert("상품 삭제 실패");
                    console.log(error);
                }
            });
        }
    }	
}


function productPageNumber(totalinquiryCount, selectPage) {
	const pageBtn = document.querySelector(".page-btn-box");
	const totalPageCount = totalinquiryCount % 10 == 0 ? totalinquiryCount / 10 : (totalinquiryCount / 10) + 1;
	const startIndex = nowPage % 5 == 0 ? nowPage - 4 : nowPage - (nowPage % 5) + 1;
	const endIndex = startIndex + 4 <= totalPageCount ? startIndex + 4 : totalPageCount;
	
	pageBtn.innerHTML = ``;
	
	if(startIndex != 0) {
		pageBtn.innerHTML += `
			<button type="button" class="page-button pre">&lt;</button>
		`;
	}
	
	for(let i = startIndex; i <= endIndex; i++) {
		pageBtn.innerHTML += `
			<button type="button" class="page-number-btn ${selectPage == i ? 'select-page' : ''}">${i}</button>
		`
	}
	
	if(endIndex != totalinquiryCount) {
		pageBtn.innerHTML += `
			<button type="button" class="page-button next">&gt;</button>
		`;
	}
	
	if(startIndex != 1) {
		const prePageButton = document.querySelector(".pre");
		prePageButton.onclick = () => {
			nowPage = startIndex - 1;
			load(nowPage);
		}
	}
	
	if(endIndex != totalinquiryCount) {
		const nextPageButton = document.querySelector(".next");
		nextPageButton.onclick = () => {
			nowPage = endIndex + 1;
			load(nowPage);
		}
	}
	
	const pageNumberButtons = document.querySelectorAll(".page-number-btn");
	pageNumberButtons.forEach(button => {
		if(button.textContent != "<" && button.textContent != ">"){
			button.onclick = () => {
				nowPage = button.textContent;
				load(nowPage);
				console.log(button)
				
			}
		}
	})
}