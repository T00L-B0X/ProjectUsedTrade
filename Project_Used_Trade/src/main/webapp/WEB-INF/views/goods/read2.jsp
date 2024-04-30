<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
    h2 {
        color: #333;
        text-align: center;
        margin-bottom: 20px;
    }

    .product-info {
        display: flex;
        flex-direction: row;
    }

    .regdate {
        color: #777;
        margin-bottom: 15px;
    }

    .product-image {
        flex: 1;
        padding-right: 20px;
    }

    .product-image img {
        display: block;
        width: 100%;
        border-radius: 5px;
    }

    .details {
        flex: 2;
    }

    .details h3 {
        color: #555;
        margin-bottom: 10px;
    }

    .details hr {
        margin: 20px 0;
        border: none;
        border-top: 1px solid #ccc;
    }

    .details p {
        color: #666;
        line-height: 1.6;
    }
</style>

<script type="text/javascript">
$(document).ready(function(){
	var regdateString = "${goodsVO.regdate}";		
	//현재시간
	var regdate = new Date(regdateString.replace(/\.0$/, ""));
	console.log(regdate);
	
	//종료시간
	var auction_time = parseInt("${auction_time}");
	var endTime = new Date(regdate.getTime() + (auction_time * 60 * 60 * 1000));
	console.log(endTime);
	$('<h5>').text(regdate).appendTo('.regdate');
});

</script>

<div class="container">
    <h2>판매글 Read</h2>
    <div class="product-info">
        <div class="product-image">
            <img src="/resources/images/${goodsVO.goods_repimg}" alt="상품 이미지">
        </div>
        <div class="details">
            <h3>제목 : ${goodsVO.goods_title}</h3><h5 class="regdate"></h5>
            <hr>
            <h3>제품 상태 : ${goodsVO.status}</h3>
            <h3>거래 희망 지역 : ${goodsVO.area}</h3>
            <h3>거래 방법 : ${goodsVO.transact_type}</h3>
            <h3>시작가 : ${goodsVO.start_price}</h3>
            <h3>현재 입찰가 : ${goodsVO.current_price}</h3>
            <h3>남은 경매 시간 : </h3>
            <h3>관심 상품 등록 버튼 </h3>
            <hr>
            <h3>내용</h3>
            <p>${goodsVO.goods_info}</p>
        </div>
    </div>
</div>

	
<%@ include file="../include/footer.jsp"%>