<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "../include/header.jsp" %>

<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script>
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
	
	    $("#selboxDirect").keyup(function() {
	        var refundAmount = parseInt($(this).val());
	        if (!isNaN(refundAmount)) {
	            updateAmount(refundAmount);
	        } else {
	            // 숫자가 아닌 값이 입력되었을 때 처리할 내용
	            //alert("숫자를 입력해주세요!");
	        }
	    });
	
	    function updateAmount(refundAmount) {
	        var currentAmount = parseInt($("#currentAmount").val());
	        
			// 환불 후 페이 계산
	        var afterAmount = currentAmount - refundAmount;
	        
	        // 현재 페이 금액보다 환불 금액이 클 때 메시지 표시
	        if (refundAmount > currentAmount) {
	            alert("환불 할 페이 금액이 현재 페이 금액보다 큽니다.");
	            
	        } else {
				// 환불 후 페이 표시
		        $("#refundAmount").val(refundAmount);
		        $("#afterAmount").val(afterAmount);
	        }
	        
	        console.log(currentAmount);
	        console.log(refundAmount);
	        console.log(afterAmount);
	    }
	});

	function refundPay() {
		var result = {
			"PAY_TYPE" : "환불", // 페이 유형
			"PAY_STATE" : "환불신청", // 페이 처리상태
			"PAY_AMOUNT" : $("#refundAmount").val(), // 결제 금액
			"BANK" : $("#bankName").val(),
			"ACCOUNT" : $("#accountNumber").val(),
			"ACCOUNT_HOLDER" : $("#accountHolder").val()

		}

		$.ajax({
			url : 'payRefund',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(result),
			success : function(res) {
				alert("환불 신청이 완료되었습니다.");
				console.log(res);
				location.href = res;
			},
			error : function(err) {
				alert("환불 신청이 실패하였습니다.");
				console.log(err);
			}
		}); //ajax
	}
</script>

<div class="container">
	<h2 class="mt-6 mb-4">페이 환불 신청 페이지</h2>

	<div class="row">
		<!-- 왼쪽 영역: 환불할 금액과 환불 계좌 입력 표시 -->
		<div class="col-md-6">
			<!-- 환불금액 입력 -->
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
			<input type="text" id="selboxDirect" name="selboxDirect"/>
			
			<div style="margin-top: 20px;">
				<!-- 환불 계좌 입력 -->
				<form action="" method='post'>
					<label for="bankName">은행명:</label>
					<input type="text" id="bankName" required><br><br>
					<label for="accountNumber">계좌번호:</label>
					<input type="text" id="accountNumber" required><br><br>
					<label for="accountHolder">이름:</label>
					<input type="text" id="accountHolder" required><br><br>
					<div class="col-md-6">
						<!-- 환불 신청하기 버튼  -->
						<button type="button" class="btn btn-primary" onclick="refundPay()">환불 신청하기</button>
					</div>
				</form>
			</div>

		</div>

		<!-- 오른쪽 영역: 사용자의 현재 페이 금액과 환불 금액, 환불 이후 금액 -->
		<div class="col-md-6">		
			<div class="row">
				<div class="col-md-4">
					<p>현재 페이 금액</p>
					<input type="text" class="form-control" id="currentAmount" value="${pResultVO.PAY_BALANCE }" readonly>
				</div>
				<div class="col-md-4">
					<p>환불할 금액</p>
					<input type="text" class="form-control" id="refundAmount" readonly>
				</div>
				<div class="col-md-4">
					<p>환불 후 페이 금액</p>
					<input type="text" class="form-control" id="afterAmount" readonly>
				</div>
			</div>
		</div>
	</div>
</div>
    
<%@ include file = "../include/footer.jsp" %>