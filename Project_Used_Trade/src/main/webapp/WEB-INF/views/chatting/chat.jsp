<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ include file="../include/header.jsp"%>



<div class="chatContainer" style = "margin: auto; margin-bottom: 100px;">
	<div class="chatList">
		<!-- 채팅방리스트 동적 처리 -->
	</div>
	<div class="chatRoom display-none">
		<div class="chatTop">
			<h3 style="text-align: center">
				<!-- 채팅방 제목 동적 처리 -->
			</h3>
			<button class="close">닫기</button>
			<div style="border: 1px solid black; padding-top: 5px;">
				<p id="leader" style="margin-left: 20px"></p>
				<p id="chatMem" style="margin-left: 20px"></p>
			</div>
		</div>
		<div class="chatMiddle">
			<ul>
				<!-- 채팅 메시지 동적 처리 -->
			</ul>
		</div>
		<div class="chatBottom">
			<textarea placeholder="메세지를 입력해 주세요." cols="62" rows="3"></textarea>
		</div>
	</div>
</div>

<script type="text/javascript">
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";

	let roomId; // roomId 초기화 입장 버튼 누르면 id 들어오게 설계

	getChatList();

	// 채팅방 리스트 가져오기
	function getChatList() {
		$.ajax({
					type : "post",
					url : "chathome/chatList.do",
					beforeSend : function(xhr) {
						// CSRF 헤더 설정
						xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					},
					dataType : "json",
					success : function(res) {
						room = `<h3 style="text-align: center" >채팅방 리스트</h3>`;
						for (var i = 0; i < res.length; i++) {
							console.log(res.length);
							console.log(res[i].usernm);
							room += "<div class='room'>";
							room += "    <div class='name' style='display: inline; padding-left: 20px; color: white;'>"
									+ res[i].usernm + "</div>";
							room += "    <div class='title' style='display: inline; padding-left: 10px; color: white;'>"
									+ res[i].chat_title + "</div>";
							room += "    <button class='enter' style='margin-right: 10px;' id='"
									+ res[i].chat_no
									+ "' value='"
									+ res[i].chat_title
									+ "' onclick='enterRoom(this)'>입장</button>";
							if (res[i].msg_count > 0) {
								room += "    <div class='counter' style='display: inline; color: red; font-weight:bold; font-size: 18px;'>"
										+ res[i].msg_count + "</div>";
							}
							room += "</div>";
						}
						$(".chatList").html(room);
					}
				})
	}

	// 채팅방에서 닫기 버튼 누를 시 
	$('.close').on('click', function() {
		// 방에 입장 시 해당 정보 서버에 전달
		const roomData = {
			"chat_no" : roomId,
			"usernm" : "${sessionScope.user.usernm}",
			"userid" : "${sessionScope.user.userid}",
			"type" : "close-room"
		}
		websocket.send(JSON.stringify(roomData));

		$('.chatRoom').toggleClass("display-none");
	})

	// 입장 버튼 누를때 onclick(this)으로 해당 함수 호출
	function enterRoom(obj) {
		// 입장하면 display-none 클래스 제거
		$('.chatRoom').removeClass("display-none");

		// 	roomId = $(obj).attr("id"); // obj를 jQuery 객체로 변환해서 가져오기
		roomId = obj.getAttribute("id") // javaScript 객체에서 id 속성값 가져오기
		roomTitle = obj.value // 채팅방 제목 가져오기
		// 방에 입장 시 해당 정보 서버에 전달
		const roomData = {
			"chat_no" : roomId,
			"usernm" : "${sessionScope.user.usernm}",
			"userid" : "${sessionScope.user.userid}",
			"type" : "enter-room"
		}
		websocket.send(JSON.stringify(roomData));

		chatMessageList();
	}

	// 채팅 메시지 가져오기
	function chatMessageList() {
		// 해당 html에 추가되었던 동적 태그 전부 지우기
		$('.chatMiddle ul').html("");
		// ajax를 이용하여 해당 방에 메시지 가져오기
		$.ajax({
			url : roomId + ".do",
			beforeSend : function(xhr) {
				// CSRF 헤더 설정
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			data : {
				userid : "${sessionScope.user.userid}"
			},
			dataType : "json",
			success : function(data) {
				$(".chatTop h3").text(roomTitle);

				for (var i = 0; i < data.length; i++) {
					// 채팅 목록 동적 추가 (내가 썼는지 안썼는지 판단 : 채팅 정렬 정하기)
					CheckLR(data[i]);
				}

				$.ajax({
					url : "/chatmember.do",
					data : {
						"chat_no" : roomId
					},
					dataType : "json",
					success : function(res) {

						var cnt = 0;
						var member = "멤버 : ";
						for (var i = 0; i < res.length; i++) {
							if (res[i].auth_role == '방장') {
								$("#leader").html(`방장 : \${res[i].usernm}`)
							} else if (i == res.length - 1) {
								member += `\${res[i].usernm}`
							} else {
								member += `\${res[i].usernm}, `
							}
							cnt += 1;
						}
						if (cnt > 1) {
							$("#chatMem").html(member);
						} else {
							member += "없음";
							$("#chatMem").html(member);
						}
					}
				})
			}
		});
	}

	// enter 누르면 메시지 전송
	$(document).on('keydown', '.chatBottom textarea', function(e) {
		if (e.keyCode == 13 && !e.shiftKey) { // enter와 shift키와 동시에 눌리지 않았을 때 동작
			e.preventDefault(); // 엔터키가 입력되는 것을 막아준다. enter누를 때 줄바꿈 동작 막음
			const message = $(this).val(); // 현재 입력된 메세지를 담는다.

			let search3 = $('.chatBottom textarea').val();

			// 공백 및 공백 문자열을 제거하고, 

			// 입력된 메시지가 공백 또는 문자열로만 이루어져 있을 때 함수 실행 멈춤
			if (search3.replace(/\s|  /gi, "").length == 0) {
				return false;
				$('.chatBottom textarea').focus();
			}

			sendMessage(message);
			// textarea 비우기
			clearTextarea();
		}
	});

	// * 1 메시지 전송
	function sendMessage(message) {
		const data = {
			"chat_no" : roomId,
			"usernm" : "${sessionScope.user.usernm}",
			"userid" : "${sessionScope.user.userid}",
			"message_content" : message,
			"message_regdat" : dateFormat(),
			"type" : "msg"
		};

		// 해당 data 채팅리스트에 세팅
		CheckLR(data);

		// websocket 서버에 해당 data 전송
		websocket.send(JSON.stringify(data));
	}

	// * 2 메세지 수신 (웹소켓 서버로부터 메시지를 수신하는 함수)
	websocket.onmessage = function(evt) {
		console.log("evt.data : ", evt.data)

		if (evt.data == "reload") {
			getChatList();
			getChatCnt();
			chatMessageList()
		} else {
			let receive = evt.data.split(","); // evt.data 서버에서 전송된 메시지 데이터

			const data = {
				"usernm" : receive[0],
				"userid" : receive[1],
				"message_content" : receive[2],
				"message_regdat" : receive[3],
				"unread_count" : receive[4],
			};
			CheckLR(data);
		}
	}

	// * 2-1 추가된 메시지가 내가 보낸 것인지, 상대방이 보낸 것인지 확인하는 함수
	function CheckLR(data) {
		// id가 로그인한 회원의 id와 다르면 left , 같으면 right
		const LR = (data.userid != "${sessionScope.user.userid}") ? "left"
				: "right";
		// 메시지 추가 함수 호출
		appendMessageTag(LR, data.userid, data.message_content, data.usernm,
				data.message_regdat, data.unread_count);
	}

	// * 3 메시지 태그 append
	function appendMessageTag(LR_className, id, msg, name, rdate, unread_count) {

		const chatList = createMessageTag(LR_className, id, msg, name, rdate,
				unread_count);

		$(".chatMiddle ul").append(chatList);

		// 스크롤바 아래 고정
		$(".chatMiddle").scrollTop($(".chatMiddle").prop("scrollHeight")); //선택한 요소의 scrollHeight 속성 값을 가져옴
	}

	// * 4 메시지 태그 생성
	function createMessageTag(LR_className, id, msg, name, rdate, unread_count) {
		// chatMiddle ul 안에 넣을 메시지 태그 생성
		let chatList = "<li class='" + LR_className + "'>";
		chatList += "<div class='sender'>";
		chatList += "<span>" + name + "</span>";
		chatList += "</div>";
		chatList += "<div class='message'>";
		if (unread_count > 0) {
			if (LR_className == "right") {
				chatList += "<span class='cnt'>" + unread_count
						+ "</span> <span class='msg'>" + msg + "</span>";
			} else {
				chatList += "<span class='msg'>" + msg
						+ "</span> <span class='cnt'>" + unread_count
						+ "</span>";
			}
		} else {
			chatList += "<span class='msg'>" + msg + "</span>";
		}
		chatList += "</div>";
		chatList += "<div class='regDate'>";
		chatList += "<span>" + rdate + "</span>";
		chatList += "</div>";
		chatList += "</li>";

		return chatList;

	}
	//* 5 - 채팅창 비우기
	function clearTextarea() {
		$('.chatBottom textarea').val("");
		return false;
	};

	// 현재 시각 불러오기
	function dateFormat() {
		const currentDate = new Date();
		const year = currentDate.getFullYear();
		const month = String(currentDate.getMonth() + 1).padStart(2, '0');
		const day = String(currentDate.getDate()).padStart(2, '0');
		const hours = String(currentDate.getHours()).padStart(2, '0');
		const minutes = String(currentDate.getMinutes()).padStart(2, '0');
		const seconds = String(currentDate.getSeconds());
	}
</script>

<%@ include file="../include/footer.jsp"%>