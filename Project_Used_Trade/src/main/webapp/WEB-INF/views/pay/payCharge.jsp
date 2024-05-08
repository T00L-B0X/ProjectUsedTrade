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
    var hours = today.getHours().toString().padStart(2, '0'); // 시
    var minutes = today.getMinutes().toString().padStart(2, '0'); // 분
    var seconds = today.getSeconds().toString().padStart(2, '0'); // 초
    var milliseconds = today.getMilliseconds().toString().padStart(3, '0'); // 밀리초

    var makeMerchantUid = hours + minutes + seconds + milliseconds;
    
    $(document).ready(function() {
        // 직접입력 선택 전에는 인풋 박스를 숨김
        $("#selboxDirect").hide();

        $("#selbox").change(function() {
            // 직접입력을 누를 때 나타남
            if ($(this).val() == "direct") {
                $("#selboxDirect").show();
            } else {
                $("#selboxDirect").hide();
                updateAmount(parseInt($(this).val()));
            }
        });
        
        $("#selboxDirect").on('input', function(event) {
            // 입력된 값을 가져옵니다.
            var inputValue = $(this).val();

            // 입력된 값에서 숫자 이외의 문자를 제거하여 숫자만 남깁니다.
            var numericValue = inputValue.replace(/\D/g, '');

            // 입력란에 숫자만 남긴 값을 설정합니다.
            $(this).val(numericValue);
            
            // 숫자만 입력되었을 때 chargeAmount를 설정합니다.
            var chargeAmount = numericValue === '' ? 0 : parseInt(numericValue);

            // updateAmount 함수를 호출합니다.
            updateAmount(chargeAmount);
        });

        function updateAmount(chargeAmount) {
            var currentAmount = parseInt($("#currentAmount").val());
            
			// 충전 후 페이 계산
            var afterAmount = currentAmount + chargeAmount;

			// 충전 후 페이 표시
            $("#chargeAmount").val(chargeAmount);
            $("#afterAmount").val(afterAmount);

            console.log(currentAmount);
            console.log(chargeAmount);
            console.log(afterAmount);
        }
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
    	
    	var USERID = "${pResultVO.USERID}";
        
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
							"imp_uid" : rsp.imp_uid,						// 포트원 결제 ID
							"merchant_uid" : rsp.merchant_uid, 				// 결제 번호 (주문 고유 번호)
							"PAY_ID" : USERID || makeMerchantUid,			// 페이 번호
							"USER_ID" : USERID, 							// 회원 번호
							"PAY_TYPE" : "충전",							// 페이 유형
							"PAY_STATE" : "충전완료",						// 페이 처리상태
							"PAY_AMOUNT" : rsp.paid_amount, 				// 결제 금액
							"CHARGE_TYPE" : rsp.pay_method, 				// 결제 수단
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
                          	location.href = res;
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
            }
        );
    }
</script>
   
<div class="container">
	<h2 class="mt-6 mb-4">페이 충전 페이지</h2>

	<div class="row">
		<!-- 왼쪽 영역: 충전할 금액과 충전 수단 표시 -->
		<div class="col-md-6">
			<!-- 충전금액 입력 -->
			<select class="form-select" aria-label="Default select example" id="selbox">
				<option value="" selected disabled hidden="">금액을 선택해주세요</option>
				<option value="1000">1000</option>
				<option value="5000">5000</option>
				<option value="10000">10000</option>
				<option value="50000">50000</option>
				<option value="100000">100000</option>
				<option value="direct">직접입력</option>
			</select>

			<!-- 상단의 select box에서 '직접입력'을 선택하면 나타날 인풋 박스 -->
			<input type="text" id="selboxDirect" name="selboxDirect" />
			
			<div style="margin-top: 20px;">
				<!-- 결제방법 선택 -->
				<div class="form-check">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="naverpay"> <label class="form-check-label" for="inlineRadio1">네이버페이</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="kakaopay"> <label class="form-check-label" for="inlineRadio2">카카오페이</label>
					</div>
				</div>
	
				<div class="form-check">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="card"> <label class="form-check-label" for="inlineRadio3">카드결제</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio4" value="vbank"> <label class="form-check-label" for="inlineRadio4">무통장입금</label>
					</div>
				</div>
			</div>
		</div>

		<!-- 오른쪽 영역: 사용자의 현재 페이 금액과 충전 금액, 충전 이후 금액 -->
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-4">
					<p>현재 페이 금액</p>
					<input type="text" class="form-control" id="currentAmount" value="${pResultVO.PAY_BALANCE}" readonly>
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

		<div class="col-md-6">
			<!-- 충전하기 버튼  -->
			<button type="button" class="btn btn-primary" onclick="requestPay()">충전하기</button>
		</div>

	</div>
</div>


<%@ include file = "../include/footer.jsp" %>