<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>

<!-- jQuery -->
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script> -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script>
    var IMP = window.IMP; 
    IMP.init("imp14154650"); // 가맹점 식별코드
  
    var today = new Date();   
    var hours = today.getHours(); // 시
    var minutes = today.getMinutes();  // 분
    var seconds = today.getSeconds();  // 초
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours +  minutes + seconds + milliseconds;
    
    $(function(){
        // 직접입력 선택 전에는 인풋 박스를 숨김
        $("#selboxDirect").hide();

        $("#selbox").change(function() {
            // 직접입력을 누를 때 나타남
            if($("#selbox").val() == "direct") {
            $("#selboxDirect").show();
            }  else {
            $("#selboxDirect").hide();
            }
        }) 
    });
    
    function requestPay() {
    	
    	// 선택 or 직접입력한 충전금액을 저장
    	var selectBox = document.getElementById("selbox");
		var selectedValue;
		if (selectBox.value === "direct") {
		  selectedValue = document.getElementById("selboxDirect").value;
		} else {
		  selectedValue = selectBox.value;
		}
		
        // 네이버페이, 카카오페이, 카드결제, 무통장입금
    	// 선택된 결제 방법을 저장
    	var selectedOption = document.querySelector('input[name="inlineRadioOptions"]:checked').value;
    	
        IMP.request_pay(
            {
                pg: "html5_inicis",						// pg파라미터 값(KG이니시스)
                pay_method: selectedOption,				// 결제 방법
                merchant_uid: "IMP"+makeMerchantUid,	// 주문번호(결제고유번호)
                name: "페이 충전 테스트",				// 상품명
                amount: selectedValue,					// 결제 금액
  				buyer_email: "pbg736837@gmail.com",
  				buyer_name: "홍길동",
  				buyer_tel: "010-1234-5678",
  				buyer_addr: "부산광역시 부산진구 동천로",
  				buyer_postcode: "01181"
 	
            },
            function (rsp) {
                // callback
                // rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단
                // 서버 결제 정보 검증 요청 부분
                if (rsp.success) {
                    var msg = '페이 충전이 완료되었습니다.';
                    var result = {
                    		
                      "imp_uid" : rsp.imp_uid,							// 포트원 결제 ID
                      "PAY_ID" : rsp.merchant_uid, 						// 결제 번호 (주문 고유 번호)
                      "USER_ID" : "test1", 								// 회원 번호
                      "PAY_TYPE" : 1,									// 페이 유형
                      "PAY_STATE" : 1,									// 페이 처리상태
                      "PAY_AMOUNT" : rsp.paid_amount, 					// 결제 금액
                      "CHARGE_TYPE" : rsp.pay_method, 					// 결제 수단
                    }
                    
                    console.log(result);
                    
                    // 결제 정보를 검증 후 DB에 저장 (ajax)
                    $.ajax({
                        url : 'vertifyIamport',
                        type : 'POST',
                        contentType : 'application/json',
                        data : JSON.stringify(result),
                        success : function (res) {
                        	
                        	alert("결제 및 결제 검증이 완료되었습니다.");
                          	console.log(res);
                          	//location.href = res;
                        },
                        error : function (err) {
                          console.log(err);
                        }
                    }); //ajax
                    
                    console.log(rsp);
                    
                } else {
                	console.log(rsp);
                    alert("결제에 실패하였습니다. 에러 내용 : " + rsp.error_msg);
                }
                alert(msg);
            }
        );
        
        /*
        var msg = '페이 충전이 완료되었습니다.';
        var result = {
          "PAY_ID" : 1, 						// 결제 번호
          "USER_ID" : "test1", 								// 회원 번호
          "PAY_TYPE" : 1,									// 페이 유형
          "PAY_STATE" : 1,									// 페이 처리상태
          "PAY_AMOUNT": 1, 					// 결제 금액
          "CHARGE_TYPE": 1, 					// 결제 수단
        }
        
        console.log(result);
        
        // 결제 정보를 DB에 저장 (ajax)
        $.ajax({
            url : 'insertPayCharge',
            type : 'POST',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(result),
            success : function (res) {
              console.log(res);
              //location.href = res;
            },
            error : function (err) {
              console.log(err);
            }
          }); //ajax
          */
    }
</script>
   
<meta charset="UTF-8">
<title>Sample Payment</title>

<h1> 페이 충전 페이지 (payCharge.jsp) </h1>

<div class="container">
  <h2 class="mt-5 mb-4">페이 충전</h2>
  <div class="row">
    <!-- 왼쪽 영역: 충전할 금액과 충전 수단 -->
    <div class="col-md-6">
      <form id="paymentForm">
        <div class="form-group">
          <label for="amount">충전할 금액</label>
          <input type="number" class="form-control" id="amount" placeholder="충전할 금액을 입력하세요">
        </div>
        <div class="form-group">
          <label for="paymentMethod">결제 수단</label>
          <select class="form-control" id="paymentMethod">
            <option value="신용카드">신용카드</option>
            <option value="계좌이체">계좌이체</option>
            <option value="휴대폰결제">휴대폰결제</option>
          </select>
        </div>
        
      </form>
    </div>
    
    <!-- 오른쪽 영역: 사용자의 현재 페이 금액과 충전 금액, 충전 이후 금액 -->
    <div class="col-md-6">
      <div class="row">
        <div class="col-md-4">
          <p>현재 페이 금액</p>
          <input type="text" class="form-control" id="currentAmount" value="$100" readonly>
        </div>
        <div class="col-md-4">
          <p>충전할 금액</p>
          <input type="text" class="form-control" id="chargeAmount" readonly>
        </div>
        <div class="col-md-4">
          <p>충전 후 페이 금액</p>
          <input type="text" class="form-control" id="afterAmount" readonly>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- 결제하기 버튼 생성 -->
<button onclick="requestPay()">결제하기</button> 

<%@ include file = "../include/footer.jsp" %>