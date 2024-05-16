<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script>
	function requestPurchase() {
		
	    var today = new Date();
        var month = today.getMonth() + 1; 											// 월
        var day = today.getDate(); 													// 일
	    var hours = today.getHours().toString().padStart(2, '0'); 					// 시
	    var minutes = today.getMinutes().toString().padStart(2, '0'); 				// 분
	    var seconds = today.getSeconds().toString().padStart(2, '0'); 				// 초
	    var milliseconds = today.getMilliseconds().toString().padStart(3, '0'); 	// 밀리초
	    
	    // 결제 당시의 시간정보를 조합하여 결제 번호를 생성
	    var makePaymentID = hours + minutes + seconds + milliseconds;
	    
		var transact_type = "${goodsVO.transact_type}"
		
		// 상품 아이디와 상품의 금액 (현재 즉시 구매가를 가져옴)
		var user_id = "${pResultVO.USERID}"
		var goods_id = "${goodsVO.goods_id}"
		//var current_price = "${goodsVO.current_price}"
		var pay_balance = "${pResultVO.PAY_BALANCE }"
		var instant_price = "${goodsVO.instant_price}"
		
		var csrfHeaderName = "${_csrf.headerName}";
	    var csrfTokenValue = "${_csrf.token}";
	    
	    var result = {
				"PAYMENT_ID" : makePaymentID,					// 결제 번호
				"PAYMENT_AMOUNT" : instant_price, 				// 결제 금액 (상품의 금액) (구현 필요)
				"PAYMENT_STATE" : "결제완료",					// 결제 처리상태
				"DELIVERY_TYPE" : transact_type,					// 배송 타입
				"USERID" : user_id, 									// 회원 아이디
				"GOODS_ID" : goods_id								// 상품 아이디
	    }
	    
	    console.log(result);
	    
        console.log(pay_balance);
        console.log(instant_price);
        
	    if (instant_price > pay_balance) {
	    	alert("결제 금액이 현재 페이 금액보다 큽니다.");
	    	
	    } else {
		    // 결제 정보를 검증 후 DB에 저장 (ajax)
		    $.ajax({
		        url : 'purchase',
		        type : 'POST',
		        contentType : 'application/json',
		        data : JSON.stringify(result),
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
		        success : function (res) {
		        	alert("결제 및 결제 검증이 완료되었습니다.");
		          	console.log(res);
		          	location.href = res;
		        },
		        error : function (err) {
		          console.log(err);
		        }
		    }); //ajax
	    }
	}
</script>

<div class="container py-4">
    <header class="text-center mb-4">
	        <h1>물건 구입</h1>
    </header>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">	
                <div class="card-body text-center">
                    <h2 class="card-title">상품명 : ${goodsVO.goods_title }</h2>
                    <h5 class="card-text">상품 설명 : ${goodsVO.goods_info } </h5>
                    <h5 class="card-text"><strong class="text-success">금액 : ${goodsVO.instant_price}원</strong></h5>
                    <button type="button" class="btn btn-primary mt-3" onclick="requestPurchase()">구입하기</button>
                </div>
            </div>
        </div>
    </div>
    <footer class="text-center mt-4">
        <p>&copy; 2024 중고 팔아다오</p>
    </footer>
</div>

<%@ include file = "../include/footer.jsp" %>