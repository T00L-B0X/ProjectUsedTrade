<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<style>
/* 프로필 섹션 스타일 */
.profile {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 20px;
    background-color: #f8f9fa; /* 배경색 추가 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 추가 */
}

.profile img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-right: 20px;
    border: 2px solid black; /* 이미지 주변에 파란색 테두리 추가 */
}

.user-info {
    flex-grow: 1;
}

.user-info div {
    margin-bottom: 10px; /* 각 정보 아이템 간격 조정 */
}

.user-info div#grade {
    font-weight: bold;
}

/* 등급 정보 스타일 */
#info-item {
    padding: 10px; /* 각 등급 정보 아이템 안의 내용에 여백 추가 */
    border-bottom: 1px solid #dee2e6; /* 각 등급 정보 아이템 사이에 구분선 추가 */
}

/* 내 정보 수정 링크 스타일 */
.modify-link {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.modify-link:hover {
    background-color: #0056b3; /* 호버 효과 색상 변경 */
}
</style>
</head>
<body>

<h1>My Page</h1>

<!-- 유저 정보 출력 -->
<div class="profile">
    <img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAL0AyAMBIgACEQEDEQH/xAAaAAEAAwEBAQAAAAAAAAAAAAAAAgMFBAEH/8QALxABAAIBAgIIBAcBAAAAAAAAAAECAwQREjEUM0FSYXFyoSEyUbETIkKBkaLxY//EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD6cAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACdcWS/y0mf2BAXdFzdz3hC2LJT5qTH7AgAAAAAAAAAAAAAAAAAAAAsxYrZbbVjzn6I46TkvFa85amLHXHSK1/0FeLTY8fZxW+srgAABTl02PJ2cNvrDhy4rYrbWjyn6tRHLjrkpNbf4DJEslJx3mtucIgAAAAAAAAAAAAAAAA7dBj2rOSec/CHWr00bYKR4brAAAAAAAcmvx71jJHOPhLiampjfBePDdlgAAAAAAAAAAAAAAAA1cE74aemE3Pob8WLh7ay6AAAAAAAQzzthv6ZZTQ11+HFw9tpZ4AAAAAAAAAAAAAAAALdPl/CyRbs5S04mJiJid4ljujTaicX5bfGn2BoDyl63jesxMPQAACZiImZnaIeXvWkb2mIhwajUTl/LX4U+4IajL+Lkm3ZyhUAAAAAAAAAAAAAAAAAAc1lcOW3Klv4BGl7UneszE+C+usyR80RZDoubue8HRc3c94Bd07/n/ZC2syT8sRVDoubue8HRc3c94BXe9rzvaZmfFFd0XN3PeEbYctedLfwCsOQAAAAAAAAAAAAAC7T4LZp35VjnIK6Utknakby7MWjrHxyTv4Q6KUrjrw1jaEgRpStI2rWI8kgAAAAAABG9K3ja1Ynzc+XR1n44528JdQDJvS2OdrxtKLWvSuSvDaN4Z+owWwzvzrPKQUgAAAAAAAA9iN52jnILNPhnLfb9Mc5aVaxWIisbRCGDHGLHFe3tWAAAAAAAAAAAAAPLVi0TFo3iXoDM1GGcN9v0zylU1M+OMuOa9vYzJjadp5wDwAAAAAB0aKnFl4p5V+7naGhrth370g6AAAAAAAAAAAAAAAAGfracOXijlb7tBz66u+HfuyDPAAAAAAamnjbBTyZbVw9Tj9MAmAAAAAAAAAAAAAAAAr1Eb4L+SxDN1N/TIMoAAAAABq4epx+mGU1cPU4/TAJgAAAAAAAAAAAAAAAIZupv6ZTQzdTf0yDKAAAB/9k=" alt="Profile Image">
    <div class="user-info">
        <c:forEach var="grade" items="${user.gradeList}">
            <div id="info-item">
                등급: ${grade.ugrade}<br>
                페이<br>
                상품 등록 수<br>
                판매 수량<br>
                구매 수량<br>
                소개글: ${user.uintro}<br>
            </div>
        </c:forEach>
    </div>
</div>

<!-- 내 정보 수정 링크 -->
<a href="/user/modify" class="modify-link">내 정보 수정</a>

<%@ include file="../include/footer.jsp"%>
</body>
</html>