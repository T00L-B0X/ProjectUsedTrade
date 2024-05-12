<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/resources/article/css/articles.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script type="text/javascript" src="/resources/article/js/modify.js"></script>

<%@ include file="../include/header.jsp"%>

<section>
	<input type="hidden" id="csrfToken" value="${_csrf.token }">
	<input type="hidden" id="anumber" value="${articleVO.anumber }">
	<input type="hidden" id="userid" value="${articleVO.userid }">
	<input type="text" id="artitle" name="artitle" value="${articleVO.artitle }" placeholder="제목을 입력해주세요." required="required" />
	
	<select id="locatns" name="locatns">
		<option value="전국" <c:if test="${articleVO.locatns eq '전국' }">selected</c:if>>전국</option>
		<option value="강원권" <c:if test="${articleVO.locatns eq '강원권' }">selected</c:if>>강원권</option>
		<option value="광주권" <c:if test="${articleVO.locatns eq '광주권' }">selected</c:if>>광주권</option>
		<option value="대구권" <c:if test="${articleVO.locatns eq '대구권' }">selected</c:if>>대구권</option>
		<option value="대전권" <c:if test="${articleVO.locatns eq '대전권' }">selected</c:if>>대전권</option>
		<option value="부산울산권" <c:if test="${articleVO.locatns eq '부산울산권' }">selected</c:if>>부산울산권</option>
		<option value="제주권" <c:if test="${articleVO.locatns eq '제주권' }">selected</c:if>>제주권</option>
		<option value="수도권" <c:if test="${articleVO.locatns eq '수도권' }">selected</c:if>>수도권</option>
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
				initialValue : `${articleVO.content }`,
				previewStyle : 'vertical'
			});
	</script>
	
	<p id="noti">소통해요 게시판을 이용해주셔서 감사합니다.</p>
	<button class="insert" onclick="modifyArticle();">수정하기</button>
	<button class="cancel" onclick="history.back()">취소하기</button>
</section>

<%@ include file="../include/footer.jsp"%></html>