<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/resources/article/css/articles.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script type="text/javascript" src="/resources/article/js/notices.js"></script>

<%@ include file="../include/header.jsp"%>

<section>
	<input type="hidden" id="csrfToken" value="${_csrf.token}">
	<input type="hidden" id="userid" value="${memberVO.userid}">
	<input type="text" id="artitle" name="artitle" placeholder="제목을 입력해주세요." required="required" />
	
	<select id="locatns" name="locatns">
		<option value="전국">전국</option>
		<option value="강원권">강원권</option>
		<option value="광주권">광주권</option>
		<option value="대구권">대구권</option>
		<option value="대전권">대전권</option>
		<option value="부산울산권">부산울산권</option>
		<option value="제주권">제주권</option>
		<option value="수도권">수도권</option>
	</select>
	<select id="categry" name="categry">
		<option value="공지">공지</option>
		<option value="광고">광고</option>
	</select>
	
	<div id="content"></div>
	
	<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
	<script>
		const editor = new toastui.Editor(
			{
				el : document.querySelector('#content'),
				height : '350px',
				initialEditType : 'wysiwyg',
				hideModeSwitch : true,
				initialValue : '게시물 작성 유의사항<br><br>1. 비방, 욕설 사용, 루머 게시 글은 누군가에게 상처를 줄 수 있습니다.<br>2. 이미지 & BGM 무단 도용 등 저작권 상 문제가 되는 게시물은 삭제됩니다.',
				previewStyle : 'vertical'
			});
	</script>
	
	<p id="noti">소통해요 게시판을 이용해주셔서 감사합니다.</p>
	<button class="insert" onclick="insertNotices();">작성하기</button>
	<button class="cancel" onclick="history.back()">취소하기</button>
</section>

<%@ include file="../include/footer.jsp"%></html>