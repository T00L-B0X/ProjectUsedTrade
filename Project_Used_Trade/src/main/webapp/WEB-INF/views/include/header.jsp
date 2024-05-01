<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no"
>
<meta name="author" content="Untree.co">
<link rel="shortcut icon" href="favicon.png">

<meta name="description" content="" />
<meta name="keywords" content="bootstrap, bootstrap4" />

<!-- Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet"
>
<!-- 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"> -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
></script>
<!-- 		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"
></script>

<link href="/resources/css/tiny-slider.css" rel="stylesheet">
<link href="/resources/css/style.css" rel="stylesheet">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- 		<link href="/resources/css/test.css" rel="stylesheet"> -->
<title>Furni Free Bootstrap 5 Template for Furniture and
	Interior Design Websites by Untree.co</title>
<style>
.container {
	display: flex;
	width: 1300px;
	margin-top: 20px;
}

.chatList, .chatRoom {
	border: 1px solid black;
	height: 682px;
	padding-top: 5px
}
/* .chatList{flex:2} */
/* .chatRoom{flex:3} */
.room {
	border: 1px solid black;
	height: 70px;
	padding-top: 20px;
}

.roomTitle {
	height: 30px;
}

.chatMiddle {
	height: 450px;
	border: 1px solid black;
	overflow: auto;
}

.display-none {
	display: none;
}

.right {
	text-align: right;
	margin-right: 45px;
}

.chatMiddle li {
	list-style: none;
}

.sender {
	font-weight: bold;
	font-size: 18px;
}

.message {
	margin-top: 8px;
	margin-bottom: 4px;
}

.message .msg {
	padding: 5px;
	border: 1px solid rgb(99, 99, 102);
	color: rgb(99, 99, 102);
}

.message .cnt {
	color: red;
	font-size: 13px;
}

.regDate span {
	font-size: 12px;
}
</style>
<script>

	// ------------------------------chat에 대한 script 시작------------------------------------------------------------------
	// 웹소켓
	var websocket = null;
	  connect();
	 //입장 버튼을 눌렀을 때 호출되는 함수
	function connect() {
	    // 웹소켓 주소
	    var wsUri 
	    = "ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/websocket/chat.do";
	    // 소켓 객체 생성
	    websocket = new WebSocket(wsUri);
	    //웹 소켓에 이벤트가 발생했을 때 호출될 함수 등록 (오버라이딩)
	    websocket.onopen = function(){
	    	console.log('info: connection opened.');
	    }
	}
	 
	// 채팅방 이외의 공간에 있을 떄 실시간 채팅 개수 알람 받기.
	websocket.onmessage = function(evt) {
	 	console.log("evt.data : ", evt.data)
	 	
	    if(evt.data == "reload"){
	    	getChatCnt();
	    }
	 }
	// ------------------------------chat에 대한 script 끝------------------------------------------------------------------
	
	// ------------------------------alarm 에 대한 script 시작----------------------------------------------------------------
 	// 처음 로드시 실행
  getAlarm();
  
  //3초마다 반복 실행
//   setInterval(() => {
// 	  getAlarm();
// 	}, 3000);
  
  function getAlarm(){
	$.ajax({
		type : "post",
		url : "/chatting/getAlarmInfo",
		contentType : "application/json; charset=utf-8",
		success : function(res){
			if(res.length > 0){
				var msg = "";
				for(var i = 0; i < res.length; i++){
					msg += `<li><a style='color: black;'class='dropdown-item' id='\${res[i].alarm_no}'href='chatting' >\${res[i].alarm_prefix}<br>\${res[i].alarm_cdate}</a></li>`
				}
				$("#toast").html(msg);
				$("#alarm").attr("style", "color:red;");
			}else{
				$("#toast").html(`<li style='margin-left:20px;'>알람이 없습니다.</li>`);
				$("#alarm").attr("style", "");
			}
		}
	})
}
  
  
  function getChatCnt(){
		$.ajax({
			type : "post",
			url : "/getChatCnt.do",
			data : {
				"userid" : "${sessionScope.user.userid}"
			},
			success : function(res){
				var chatCnt = $(".chatCnt")
				if(res > 0){
					chatCnt.removeClass("chat-none");
					chatCnt.text(res);
				}else{
					chatCnt.addClass("chat-none");
				}
			}
		})
	}
  

	$(document).on("click", "#toast a",function(){
		var alarm_no = $(this).attr("id");
		$.ajax({
			type : "post",
			data : {
				"alarm_no" : alarm_no
				},
			url : "/chatting/deleteAlarm",
// 			contentType : "application/json; charset=utf-8", // 이렇게 설정하면 JSON.stringify()로 js를 JSON문자열로 변환해서 보내야함
			success : function(res){
				if(res == "SUCCESS"){
					console.log(res)
				}
			}
		})
	})
// ------------------------------------alarm 에 대한 script 끝----------------------------------------------------------------	
</script>
</head>

<body>

	<!-- Start Header/Navigation -->
	<nav
		class="custom-navbar navbar navbar navbar-expand-md navbar-dark bg-dark"
		arial-label="Furni navigation bar"
	>

		<div class="container">
			<a class="navbar-brand" href="/">홈으로<span>.</span></a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarsFurni"
				aria-controls="navbarsFurni" aria-expanded="false"
				aria-label="Toggle navigation"
			>
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarsFurni">
				<ul class="custom-navbar-nav navbar-nav ms-auto mb-2 mb-md-0">
					<li class="nav-item "><a class="nav-link" href="#">Home</a></li>
					<li><a class="nav-link" href="#">Shop</a></li>
					<li><a class="nav-link" href="#">About us</a></li>
					<li><a class="nav-link" href="#">Services</a></li>
					<li><a class="nav-link" href="/chatting">채팅구하기</a></li>
					<li><a class="nav-link" href="/chathome">채팅방</a>
						<div class="chatCnt chat-none"></div></li>
				</ul>

				<ul class="custom-navbar-cta navbar-nav mb-2 mb-md-0 ms-5">
					<li><a class="nav-link" href="#"><img
							src="/resources/images/user.svg"
						></a></li>
					<li><a class="nav-link" href="#"><img
							src="/resources/images/cart.svg"
						></a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="alarm" role="button"
						data-bs-toggle="dropdown"
					>알림</a>
						<ul class="dropdown-menu" id="toast">
							<!-- 동적 처리 -->
						</ul></li>
				</ul>

			</div>
		</div>

	</nav>
	<!-- End Header/Navigation -->