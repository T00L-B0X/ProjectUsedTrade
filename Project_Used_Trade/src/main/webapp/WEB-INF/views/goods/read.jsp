<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<style>
#remainTime{
color: red;
}
.details{
display:grid;
grid-template-columns: reapet(auto-fit, minmax(320px,1fr));
grid-gap: 1rem;
}
.product-image{
display:grid;
grid-template-columns: reapet(auto-fit, minmax(320px,1fr));
grid-gap: 1rem;
}

</style>
<script type="text/javascript">
$(document).ready(function() {
        // 디비에서 최신글부터 조회@
        var csrfHeaderName = "${_csrf.headerName}";
        var csrfTokenValue = "${_csrf.token}";
        var goods_id = "${goodsVO.goods_id}";
        var au_status = "${avo.au_status}";
        $.ajax({
            url: "/goods/read/${goodsVO.goods_id}",
            method: "GET",
            success: function(data) {
            	console.log(data);
            	var date = new Date(data.regdate);
                
                
             // 시작 시간 - 년, 월, 일
             	var regyear = date.getFullYear(); // 연도(네 자리)
            	var regmonth = date.getMonth() + 1; // 월 (0부터 시작하므로 +1 필요)
            	var regday = date.getDate(); // 일
            	regmonth = regmonth < 10 ? '0'
            			+ regmonth
            			: regmonth;
            	regday = regday < 10 ? '0'
            			+ regday
            			: regday;
            	var regdate = regyear+ '-'+ regmonth+ '-'+ regday
            	console.log(regdate);
             
             // 종료 시간 설정
                var startTime = new Date(data.regdate);
                var endTime = new Date(startTime.getTime()+ (data.auction_time * 60 * 60 * 1000));
             // 종료 시간 - 년, 월, 일, 시, 분, 초 추출
            	var endyear = endTime.getFullYear(); // 연도(네 자리)
            	var endmonth = endTime.getMonth() + 1; // 월 (0부터 시작하므로 +1 필요)
            	var endday = endTime.getDate(); // 일
            	var endhours = endTime.getHours(); // 시
            	var endminutes = endTime.getMinutes(); // 분
            	var endseconds = endTime.getSeconds(); // 초
            	endmonth = endmonth < 10 ? '0'
            			+ endmonth
            			: endmonth;
            	endday = endday < 10 ? '0'
            			+ endday
            			: endday;
            	endhours = endhours < 10 ? '0'
            			+ endhours
            			: endhours;
            	endminutes = endminutes < 10 ? '0'
            			+ endminutes
            			: endminutes;
            	endseconds = endseconds < 10 ? '0'
            			+ endseconds
            			: endseconds;
            	var formattedEndTime = endyear
            			+ '-'
            			+ endmonth
            			+ '-'
            			+ endday
            			+ ' '
            			+ endhours
            			+ ':'
            			+ endminutes
            			+ ':'
            			+ endseconds;


            	function updateRemainingTime() {
            	    // 현재 시간과 종료 시간 간의 차이 계산
            	    var currentTime = new Date();
            	    var timeDiff = endTime.getTime() - currentTime.getTime();
            	    
            	    // 남은 시간 계산 (밀리초를 일, 시, 분, 초로 변환)
            	    var remaindays = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
            	    var remainhours = Math.floor((timeDiff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            	    var remainminutes = Math.floor((timeDiff % (1000 * 60 * 60)) / (1000 * 60));
            	    var remainseconds = Math.floor((timeDiff % (1000 * 60)) / 1000);
            	    
            	    if (timeDiff < 0) {
            	        console.log("경매 종료");
            	        var remainingTime = "경매 종료";
            	        $('#bidButton').hide();            	        
            	    } else {
            	        // 출력 포맷에 맞게 문자열 조합
            	        var remainingTime = remaindays + "일 " + remainhours + "시간 " + remainminutes + "분 " + remainseconds+ "초";
            	    }
            	    /* if(timeDiff > 0 && au_status ==1) {
            	    	$('#bidEnd').hide();
            	    }
            	    if(au_status == 0){
            	    	$('#bidEnd').hide();
            	    } */
            	    
            	    // 남은 시간 업데이트
            	    $('#remainTime').text(remainingTime);
            	}

            	// 최초 업데이트 수행
            	updateRemainingTime();

            	// 1초마다 업데이트 수행
            	remainTimer = setInterval(updateRemainingTime, 1000);
                
                $('<span>').text(regdate).appendTo('#regdate');
                $('<span>').text(formattedEndTime).appendTo('#endTime');
                
                // 즉시 입찰가로 입찰된 경매
                if(data.current_price == data.instant_price){
                	$('#remainTime').text('경매 종료');
                	clearInterval(remainTimer);
                	$('#bidButton').hide();
                }
            }
        });
        
        $('#bidEnd').click(function(){
        	$.ajax({
        		url: "/bidEnd/${goodsVO.goods_id}",
        		beforeSend : function(xhr){
         			xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
         		},
         		contentType : 'application/json; charset=UTF-8',
        		method: "PUT",
				success : function(){
					alert("경매가 종료되었습니다.");
					location.reload(true);
				},
				error : function(){
					alert("실패");
				}
        	});
        	
        });
        
        if(au_status == 0){
            $('#bidEnd').hide();
            $('#bidEndMessage').text("경매가 종료되었습니다.");       
        }
        
        var bxslider = document.querySelector('.bxslider');

	     // 첫 번째 carousel-item은 이미 추가되어 있으므로 1부터 반복
	     for (var i = 0; i <= ${imgCount}-1; i++) {
	    	 var bxItem = '<div><img src="/displayImages?goods_id=${goodsVO.goods_id}&amp;imageNo='+i+'"></div>';
	    	 bxslider.innerHTML += bxItem;
	     }
	     $('.bxslider').bxSlider({
	   	  adaptiveHeight: true,
	   	  slideWidth: 600
	   	 });

});


$(function() {
    var joinBtn = $("#joinBtn");
    joinBtn.on("click", function() {
        var chat_no = $(this).attr("id");
        var goods_id = ${goodsVO.goods_id};
        var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
        var ChatObject = {
            "userid" : "${goodsVO.userid}",
            "usernm" : "${user.usernm}",
            "chat_title" : "${goodsVO.goods_title}",
            "auth_role" : "채팅인원",
            "chat_type" : 1,
        };

        console.log(ChatObject);
        console.log(goods_id);

        // 채팅방 생성 Ajax 요청
        $.ajax({
            type: "post",
            url: "read/joinChat",
            beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
            data: JSON.stringify(ChatObject),
            contentType: "application/json; charset=utf-8",
            success: function(response) {
                // 채팅방 생성이 성공적으로 완료된 경우 추가 작업을 수행하는 Ajax 요청
                $.ajax({
                    type: "post",
                    url: "/goods/read/connectChat?goods_id=" + goods_id,
                    beforeSend : function(xhr) {
						// CSRF 헤더 설정
						xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					},
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(ChatObject),
                    success: function(response) {
                    	alert(" 채팅이 연결되었습니다. ")
                        // 성공적으로 처리된 경우의 동작
                    },
                    error: function(xhr, status, error) {
                        // 요청이 실패한 경우의 동작
                    }
                });
            },
            error: function(xhr, status, error) {
                // 채팅방 생성에 실패한 경우 처리
                console.error("채팅방 생성에 실패했습니다.");
                console.error(error);
            }
        });
    });
});
</script>
<div class="container">
    <div class="product-info">
	    <div id="titleDate">
	    <hr><h3>제목 : ${goodsVO.goods_title}</h3><span id="regdate"></span><hr>
	    </div>
	    <div class="bxslider">
		</div>
        <div class="details">
            <hr>
            <h3>제품 상태 : ${goodsVO.status}</h3>
            <h3>거래 희망 지역 : ${goodsVO.area}</h3>
            <h3>거래 방법 : ${goodsVO.transact_type}</h3>
            <h3>시작가 : ${goodsVO.start_price}</h3>
            <h3>현재 입찰가 : ${goodsVO.current_price}</h3>
            <h3>입찰수 : ${bidCount }</h3>
            <h3>남은 경매 시간 : <span id="remainTime"></span></h3>
            <h3>종료시간 : <span id="endTime"></span></h3>
            <h3>즉시구매가 : ${goodsVO.instant_price }</h3>
        </div>
        <!-- 판매자와 로그인을 하지 않은 사람은 입찰할 수 없음 -->
            <c:if test="${sessionScope['SPRING_SECURITY_CONTEXT'].authentication.isAuthenticated() and sessionScope['SPRING_SECURITY_CONTEXT'].authentication.name != goodsVO.userid}">
            <input id="bidButton" type="button" value="입찰하기" onclick="location.href='/auction/bid?goods_id=${goodsVO.goods_id}'">
            </c:if>
            <!-- 경매 종료 버튼은 판매자에게만 보임 -->
        <input type="button" value="경매 기록 보기" onclick="window.open('/goods/record?goods_id=${goodsVO.goods_id}','win2','scrollbars=yes width=650, height=700');return false"/><hr>
        <sec:authorize access="isAuthenticated()">
        <button id="likeGoods">관심 상품 등록 </button><br><br><br>        
        <button id="joinBtn">1:1 채팅</button><br><br>
        </sec:authorize>
        <c:if test="${avo.au_status == 1 && sessionScope['SPRING_SECURITY_CONTEXT'].authentication.name eq goodsVO.userid}">
    		<input id="bidEnd" type="button" value="경매 종료 하기">
    		<div id="bidEndMessage"></div>
    	</c:if>
    	<!-- arvo.ar_userid는 auction_record테이블에 있는 가장 높게 입찰한 사람의 id -->
        <c:if test="${avo.au_status == 0 && arvo.ar_userid eq sessionScope['SPRING_SECURITY_CONTEXT'].authentication.name}">
			<input type="button" class="btn btn-block btn-success" value="결제하기" onclick="location.href='/payment/purchase?goods_id=${goodsVO.goods_id}'"/><hr>
        </c:if>
        <h3>상품 설명</h3>
            <p>${goodsVO.goods_info}</p>
    </div>
</div>

<%@ include file="../include/footer.jsp"%>