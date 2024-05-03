<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
	table tr {
	width: 50px;
	}
</style>
<script type="text/javascript">
$(document).ready(function() {
    $.ajax({
        url: "/auction/${gvo.goods_id}",
        method: "GET",
        success: function(response) {
            var gvo = response.gvo; // 서버에서 전달된 gvo 데이터
            console.log(gvo.goods_id);
        },
        error: function(xhr, status, error) {
            console.error("AJAX 요청 중 에러 발생:", error);
        }
    });
  //즉시구매가보다 작아야하고 현재가보다 1000이상, 100원단위이어야함.
	$('#bid_submit').click(function(e){
		var inputedBidPrice = document.querySelector("#inputBidPrice").value;
		if (inputedBidPrice%100!=0) {
            alert("올바른 입찰가를 입력하세요.");
            e.preventDefault();
            return;
     	}
		
		
	});
	
	/* if (GoodsVO.current_price == null) {
	    var currentPrice = auctionVO.startprice;
	    $('#currentBid').text(currentPrice);
	} */
	// 시작가, 즉구가 입력값 (숫자만 허용)
    $("#inputBidPrice").keyup(function() {
	    chk_input_filter("numOnly", $(this));
	});

	function chk_input_filter(type, obj) {
	    var str = $(obj).val();

	    if (type == 'numOnly') {
	        // 숫자만 허용
	        var filteredStr = str.replace(/[^\d]/g, "");
	        
	        if (filteredStr !== str) {
	            // 입력된 값에 숫자 이외의 문자가 포함되어 있다면
	            $(obj).val(filteredStr); // 필터링된 문자열로 입력값을 대체
	            alert("숫자만 입력하세요.");
	        }
	    }
	}
	
	// 복붙도 안되게
	$("#inputBidPrice").on("input", function() {
	    chk_input_filter($(this));
	});

	function chk_input_filter(obj) {
	    var str = $(obj).val();
	    var filteredStr = str.replace(/[^\d]/g, ""); // 숫자 이외의 문자를 제거

	    if (str !== filteredStr) {
	        $(obj).val(filteredStr);
	        alert("숫자만 입력하세요.");
	    }
	}
	
});

</script>



<div class="container">
	<h1>경매입찰</h1><hr>
	<div>
		<h3>상품정보</h3><hr>
		<table border="1">
			<tr>
				<td>제목</td>
				<td>${avo.au_title }</td>
			</tr>
			<tr>
				<td>판매자</td>
				<td>${avo.au_userid }</td>
			</tr>
			<tr>
				<td>경매 마감 일자</td>
				<td>${avo.end_time }</td>
			</tr>
			<tr>
				<td>판매 지역</td>
				<td>${gvo.area }</td>
			</tr>
			<tr>
				<td>거래 방법</td>
				<td>${gvo.transact_type }</td>
			</tr>
		</table>
	</div><hr>
	<h3>입찰하기</h3><hr>
	<div>
	<table border="1">
			<tr>
				<td>즉시 구매가</td>
				<td>${avo.instant_price }</td>
			</tr>
			<tr>
				<td>시작 경매가</td>
				<td><span id="startPrice">${avo.start_price }</span></td>
			</tr>
			<tr>
				<td>현재가</td>
				<td><span id="currentBid">${gvo.current_price }</span></td>
			</tr>		
		</table>
		<br>
		<form>
			입찰가 <input id="inputBidPrice" type="text" required/>			
			<button id="bid_submit" type="submit" >입찰하기</button>
		</form>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>