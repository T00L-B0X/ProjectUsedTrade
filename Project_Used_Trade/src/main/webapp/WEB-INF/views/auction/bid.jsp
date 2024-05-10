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
	var inputedBidPrice = parseInt(document.querySelector("#inputBidPrice").value);
	var currentBidValue = parseInt(document.getElementById("currentBid").innerText);
	var startBidValue = parseInt(document.getElementById("startPrice").innerText);
	var instantPriceValue = parseInt(document.getElementById("instantPrice").innerText);
	
	if(instantPriceValue == currentBidValue){
		$("#bidForm").hide();
	    // 경매 종료 웹페이지에 삽입
	    $("#auction_status_message").text("종료된 경매입니다.");
		
	}
	
	// 입력값이 변경될 때마다 inputedBidPrice 업데이트
    $("#inputBidPrice").on("input", function() {
        inputedBidPrice = parseInt($(this).val());
        console.log(inputedBidPrice);
    });
    
    //
    
  	//즉시구매가보다 작거나 같아야하고 현재가보다 1000이상, 100원단위이어야함.
	$('#bid_submit').click(function(e){
		if(instantPriceValue == currentBidValue){
			alert("이미 낙찰된 경매입니다.");
			location.reload(true);
			e.preventDefault();
            return;
		}
		// 100원단위이어야 하고
		if (inputedBidPrice%100==0) {
			// 즉구가보다 같거나 작고 시작가보다 같거나 커야함
            if(inputedBidPrice>=startBidValue && inputedBidPrice<=instantPriceValue){
            	//첫 입찰자가 아니라면
            	if(currentBidValue != 0){
            		//현재 입찰가보다 1000원높은 가격으로 입찰가능            		
            		if(inputedBidPrice < currentBidValue+1000){
            			alert("현재 입찰가보다 1000원높은 가격으로 입찰할 수 있습니다.");
            			e.preventDefault();
                        return;
            		}
            	}
            }else{
            	alert("시작가 이상 즉시구매가 이하 가격을 입력해주세요.");
         		e.preventDefault();
                return;
            }
     	}else{
     		alert("100원단위로 입력하세요.");
     		e.preventDefault();
            return;
     	}
		// 처음(아무도 입찰하지 않았을 때) 입찰할 금액은 시작 경매가 보다 커야함
     	if(currentBidValue == 0){
     		if(inputedBidPrice < startBidValue){
     			console.log(startBidValue);
     			alert("시작 경매가 이상의 입찰 금액을 입력하세요.");
     			e.preventDefault();
     			return;
     		}
     	
     	// 입찰할 금액은 현재 입찰가보다 1000원 이상이거나 
     	}else if(currentBidValue != 0){
     		if(inputedBidPrice < currentBidValue+1000){
     			alert("현재입찰가의 1000원 이상의 금액을 입력하세요");
     			e.preventDefault();
     			return;
     		}
     	}
		
     	var confirmed = confirm(inputedBidPrice + "원으로 입찰을 하시겠습니까?");
     	if (!confirmed) {
            e.preventDefault(); // 폼 제출을 막음
        }else {
        	// confirm 메세지 확인을 눌렀을 때
            $.ajax({
                url: "/goods/${gvo.goods_id}",
                method: "GET",
                success: function(data) {
                	alert(data);
                    alert("글번호"+data.goods_id);
                    alert("data.current_price"+data.current_price);
                    alert("입력한 금액"+inputedBidPrice);
                    if (data.current_price == data.instant_price) {
                        alert('이미 낙찰된 경매입니다.');
                        e.preventDefault();
                    } else if (data.current_price + 1000 > inputedBidPrice) {
                        alert('다시 시도해주세요.');
                        location.href = '/auction/bid?goods_id=${gvo.goods_id}';
                        e.preventDefault();
                    } else {
                        console.log("현재db입찰가:" + parseInt(data.current_price));
                        console.log("입력값:" + inputedBidPrice);
                        alert('값 확인');
                    }
                },
                error: function(xhr, status, error) {
                    console.error("AJAX 요청 중 에러 발생:", error);
                }
            });
        }
		
	});
	
	/* if (GoodsVO.current_price == null) {
	    var currentPrice = auctionVO.startprice;
	    $('#currentBid').text(currentPrice);
	} */
	// 시작가, 즉구가 입력값 (숫자만 허용)
	$("#inputBidPrice").on("input", function() {
	    chk_input_filter($(this)); // 함수 호출 시 $(this) 전달
	});

	function chk_input_filter(obj) {
	    if (!obj) { // obj가 유효한지 확인
	        return;
	    }

	    var str = obj.val();
	    var filteredStr = str.replace(/[^\d]/g, ""); // 숫자 이외의 문자를 제거

	    if (str !== filteredStr) {
	        obj.val(filteredStr);
	        alert("숫자만 입력하세요.");
	    }
	}
    /* $("#inputBidPrice").keyup(function() {
	    chk_input_filter("numOnly", $(this));
	});

	function chk_input_filter(type, obj) {
		if (!obj) {
	        return;
	    }
		
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
	} */
	
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
	<%-- ${avo }<hr>
	${gvo } --%>
	<div>
		<h3>상품정보</h3><hr>
		<table border="1">
			<tr>
				<td>제목</td>
				<td>${avo.au_title }</td>
			</tr>
			<tr>
				<td>판매자</td>
				<td>${avo.userid }</td>
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
		<br>
		<input type="button" value="경매 기록 보기" onclick="window.open('/goods/record?goods_id=${gvo.goods_id}','win2','scrollbars=yes width=650, height=700');return false"/>
	</div><hr>
	<h3>입찰하기</h3><hr>
	<div>
	<table border="1">
			<tr>
				<td>즉시 구매가</td>
				<td><span id="instantPrice">${avo.instant_price }</span></td>
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
		<div id="auction_status_message"></div>
		<form id="bidForm"role="form" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			입찰가 <input id="inputBidPrice" type="text" name="current_price" required/><br><br>
			<button id="bid_submit" type="submit" onclick="location.href='/auction/bid?goods_id=${avo.goods_id}'">입찰하기</button>
		</form>
		<br>
			<p>* 현재입찰가보다 1000이상의 금액을 입력해주세요. </p>
			<p>* 입찰자가 없을 때는 시작입찰가 이상의 금액으로 입찰할 수 있습니다.</p>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>