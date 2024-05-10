<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.btn-group-vertical {
	position: fixed;
	top: 50%; /* 필요에 따라 조절하세요 */
	transform: translateY(-50%);
	left: 20px; /* 필요에 따라 조절하세요 */
}

.profile {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
}

.profile img {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-right: 20px;
}

.user-info {
    flex-grow: 1;
}

.user-info div {
    margin-bottom: 5px;
}

.user-info div#grade {
    font-weight: bold;
}

.user-info {
    max-width: calc(100% - 120px); /* user-info의 최대 너비 설정 */
}



</style>
</head>
<body>

	<h1>mypage.jsp</h1>

	user = ${user} <br>







	<!-- <div class="btn-group-vertical">
		<button type="button" class="btn btn-default">Top</button>
		<button type="button" class="btn btn-default">Middle</button>
		<button type="button" class="btn btn-default">Bottom</button>
	</div> -->

	<div class="profile">
		<img
			src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAL0AyAMBIgACEQEDEQH/xAAaAAEAAwEBAQAAAAAAAAAAAAAAAgMFBAEH/8QALxABAAIBAgIIBAcBAAAAAAAAAAECAwQREjEUM0FSYXFyoSEyUbETIkKBkaLxY//EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD6cAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACdcWS/y0mf2BAXdFzdz3hC2LJT5qTH7AgAAAAAAAAAAAAAAAAAAAAsxYrZbbVjzn6I46TkvFa85amLHXHSK1/0FeLTY8fZxW+srgAABTl02PJ2cNvrDhy4rYrbWjyn6tRHLjrkpNbf4DJEslJx3mtucIgAAAAAAAAAAAAAAAA7dBj2rOSec/CHWr00bYKR4brAAAAAAAcmvx71jJHOPhLiampjfBePDdlgAAAAAAAAAAAAAAAA1cE74aemE3Pob8WLh7ay6AAAAAAAQzzthv6ZZTQ11+HFw9tpZ4AAAAAAAAAAAAAAAALdPl/CyRbs5S04mJiJid4ljujTaicX5bfGn2BoDyl63jesxMPQAACZiImZnaIeXvWkb2mIhwajUTl/LX4U+4IajL+Lkm3ZyhUAAAAAAAAAAAAAAAAAAc1lcOW3Klv4BGl7UneszE+C+usyR80RZDoubue8HRc3c94Bd07/n/ZC2syT8sRVDoubue8HRc3c94BXe9rzvaZmfFFd0XN3PeEbYctedLfwCsOQAAAAAAAAAAAAAC7T4LZp35VjnIK6Utknakby7MWjrHxyTv4Q6KUrjrw1jaEgRpStI2rWI8kgAAAAAABG9K3ja1Ynzc+XR1n44528JdQDJvS2OdrxtKLWvSuSvDaN4Z+owWwzvzrPKQUgAAAAAAAA9iN52jnILNPhnLfb9Mc5aVaxWIisbRCGDHGLHFe3tWAAAAAAAAAAAAAPLVi0TFo3iXoDM1GGcN9v0zylU1M+OMuOa9vYzJjadp5wDwAAAAAB0aKnFl4p5V+7naGhrth370g6AAAAAAAAAAAAAAAAGfracOXijlb7tBz66u+HfuyDPAAAAAAamnjbBTyZbVw9Tj9MAmAAAAAAAAAAAAAAAAr1Eb4L+SxDN1N/TIMoAAAAABq4epx+mGU1cPU4/TAJgAAAAAAAAAAAAAAAIZupv6ZTQzdTf0yDKAAAB/9k="
			alt="프로필 이미지">
		<div class="user-info">
			<c:forEach var="grade" items="${user.gradeList}">
			
				<div id="info-item">등급 : ${grade.ugrade}<br>
				페이 <br>
				상품등록수<br>
				판매수량<br>
				구매수량<br>
				소개글 : ${user.uintro}<br>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<a href="/user/modify">내 정보 수정</a>



	<%@include file="../include/footer.jsp"%>
</body>
</html>