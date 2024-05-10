<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
	/* Chrome, Safari, Edge, Opera */
	input::-webkit-outer-spin-button,
	input::-webkit-inner-spin-button {
	  -webkit-appearance: none;
	  margin: 0;
	}
	
	/* Firefox */
	input[type=number] {
	  -moz-appearance: textfield;
	}
    .container input[type="text"],
    .container input[type="number"],
    .container select {
        height: 30px; /* 원하는 너비로 설정하세요 */
    }
    #imgDiv {
    display: flex; /* 부모 요소를 플렉스 박스로 설정하여 내부 요소를 가로로 정렬합니다. */
  	}
  	#imgDiv > div {
    flex: 1; /* 내부 div 요소들이 동일한 너비를 가지도록 설정합니다. */
    margin-right: 10px; /* 각 div 요소 사이의 간격을 지정합니다. */
  	}
  	#registerForm > div	 {
    margin-bottom: 30px;
	}
	.upload-boxes > div {
	margin-bottom: 30px;
	}
	.upload-box {
        position: relative;
    }
    .file-label {
        position: absolute;
        top: 0;
        left: 0;
        z-index: 1;
        cursor: pointer;
    }
    .file-label, .file-name {
        display: none;
    }
    .RepPreview {
        width: 250px; /* 원하는 너비로 설정 */
        height: auto; /* 너비에 맞게 자동으로 높이 조절 */
    }
    .preview {
        width: 150px; /* 원하는 너비로 설정 */
        height: auto; /* 너비에 맞게 자동으로 높이 조절 */
    }
    .upload-boxes {
        display: flex;
        justify-content: space-between; /* 요소들을 동일한 간격으로 가로로 정렬 */
        align-items: center; /* 요소들을 수직 가운데 정렬 */
    }
    .upload-box {
        position: relative;
    }
    .file-label {
        position: absolute;
        top: 0;
        left: 0;
        z-index: 1;
        cursor: pointer;
    }
    .file-label, .file-name {
        display: none;
    }
</style>
<script type ="text/javascript">
// 메인 사진 미리보기
function previewMainPhoto(input) {
    var file = input.files[0];
    var reader = new FileReader();
    reader.onload = function(e) {
        document.getElementById('mainPhotoPreview').src = e.target.result;
    }
    reader.readAsDataURL(file);
    input.nextElementSibling.innerHTML = file.name;
}

//추가 사진 미리보기
function previewAdditionalPhoto(index, input) {
    var file = input.files[0];
    var reader = new FileReader();
    reader.onload = function(e) {
        document.getElementById('additionalPhoto' + index + 'Preview').src = e.target.result;
    }
    reader.readAsDataURL(file);
    input.nextElementSibling.innerHTML = file.name;
}

$(document).ready(function(){
    $(".cancel-button").click(function(e){
    	e.preventDefault();
        // 파일 선택 input을 초기화합니다.
        $('#mainPhotoInput').val("");

        // 미리보기 이미지를 숨깁니다.
        $('#mainPhotoPreview').attr('src', '#');
        $('#mainPhotoInput').css('display', 'block');
    });
    $(".addi-cancel-button1").click(function(e){
    	e.preventDefault();
    	$('#additionalPhoto1Input').val("");
    	$('#additionalPhoto1Preview').attr('src', '');
    	$('#additionalPhoto1Input').css('display', 'block');
    });    
    $(".addi-cancel-button2").click(function(e){
    	e.preventDefault();
    	$('#additionalPhoto2Input').val("");
    	$('#additionalPhoto2Preview').attr('src', '');
    	$('#additionalPhoto2Input').css('display', 'block');
    });
    $(".addi-cancel-button3").click(function(e){
    	e.preventDefault();
    	$('#additionalPhoto3Input').val("");
    	$('#additionalPhoto3Preview').attr('src', '');
    	$('#additionalPhoto3Input').css('display', 'block');
    });
    $(".addi-cancel-button4").click(function(e){
    	e.preventDefault();
    	$('#additionalPhoto4Input').val("");
    	$('#additionalPhoto4Preview').attr('src', '');
    	$('#additionalPhoto4Input').css('display', 'block');
    });
    
    // 추가 사진 업로드 시 대표 사진 유무 체크
    $("#additionalPhoto1Input").click(function(e){
        if($('#mainPhotoPreview').attr('src') == '#'){
            alert('대표 사진 등록을 먼저 해주세요');
            e.preventDefault();
            return false;
        }
    });
    $("#additionalPhoto2Input").click(function(e){
        if($('#mainPhotoPreview').attr('src') == '#'){
            alert('대표 사진 등록을 먼저 해주세요');
            e.preventDefault();
            return false;
        }
    });
    $("#additionalPhoto3Input").click(function(e){
        if($('#mainPhotoPreview').attr('src') == '#'){
            alert('대표 사진 등록을 먼저 해주세요');
            e.preventDefault();
            return false;
        }
    });
    $("#additionalPhoto4Input").click(function(e){
        if($('#mainPhotoPreview').attr('src') == '#'){
            alert('대표 사진 등록을 먼저 해주세요');
            e.preventDefault();
            return false;
        }
    });
    
    // 시작가, 즉구가 입력값 (숫자만 허용)
    $("#inputStartPrice, #inputInstantPrice").keyup(function() {
	    chk_input_filter("numOnly", $(this));
	});

	function chk_input_filter(type, obj) {
	    var str = $(obj).val();

	    if (type == 'numOnly') {
	        // 숫자만 허용
	        var filteredStr = str.replace(/[^\d]/g,"");
	        
	        if (filteredStr !== str) {
	            // 입력된 값에 숫자 이외의 문자가 포함되어 있다면
	            $(obj).val(filteredStr); // 필터링된 문자열로 입력값을 대체
	            alert("숫자만 입력하세요.");
	        }
	    }
	}
	
	// 복붙도 안되게
	$("#inputStartPrice, #inputInstantPrice").on("input", function() {
	    chk_input_filter($(this));
	});

	function chk_input_filter(obj) {
	    var str = $(obj).val();
	    var filteredStr = str.replace(/[^\d]/g, ""); // 숫자 이외의 문자를 제거

	    if (str !== filteredStr) {
	        $(obj).val(filteredStr);
	        alert("숫자만 입력하세요.");
	    }
	}
    
    // 카테고리 선택 안한 경우
    $("#goods_submit").click(function(e){
    	var selectedCategory = document.getElementById('selectCate').value;
    	var inputedStartPrice = parseInt(document.querySelector("#inputStartPrice").value);
       	var inputedInstantPrice = parseInt(document.querySelector("#inputInstantPrice").value);
    	
    	if (!selectedCategory) {
            // 선택된 옵션의 값이 없는 경우
            alert('카테고리를 선택해주세요.');
            e.preventDefault(); // 폼 제출을 막음
            return false;
        }
    	
       	if(inputedStartPrice%100 != 0){
       		alert('경매시작가는 100원단위로 입력해주세요.');
       		e.preventDefault();
       		return false;
       	}
       	
       	if(inputedStartPrice < 3000){
       		alert('경매시작가는 3000원이상의 금액을 입력해주세요.');
       		e.preventDefault();
       		return false;
       	}
       	
       	if(inputedInstantPrice%100 != 0){
       		alert('즉시구매가는 100원단위로 입력해주세요.');
       		e.preventDefault();
       		return false;
   		}
   		
		if(inputedStartPrice > inputedInstantPrice){
       		alert('경매시작가는 즉시구매가 이하의 금액을 입력해주세요');
       		e.preventDefault();
       		return false;
		}
    		
    });
    
    
    //2,3,4 만들기
});

</script>

<div class="container">
	<h2>판매 글 등록</h2><br>
	<form role="form" method="post" enctype="multipart/form-data">
	<div id="registerForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="photo-upload-container">
	    	<!-- 대표 사진 업로드 영역 -->
		    <div class="main-photo-upload">
			    <h3>대표 사진 업로드</h3>
			    <div class="upload-box">
			    	<label for="mainPhotoInput" class="file-label">대표 사진 선택</label>
			        <img id="mainPhotoPreview" class="RepPreview" src="#" alt="">
			        <input type="file" id="mainPhotoInput" accept="image/*" name="Img0" onchange="previewMainPhoto(this)" required>
			        <span class="file-name">파일 선택됨</span><br><br>
			        <button class="cancel-button">취소</button>
			    </div>
			</div>

			<!-- 추가 사진 업로드 영역 -->
			<div class="additional-photo-upload">
			    <h3>추가 사진 업로드 (최대 4장)</h3>
			    <div class="upload-boxes">
			        <div class="upload-box">
			        	<label for="additionalPhoto1Input" class="file-label">추가 사진 선택</label>
			            <img id="additionalPhoto1Preview" class="preview" src="#" alt="">
			            <input type="file" id="additionalPhoto1Input" accept="image/*" name="Img1" onchange="previewAdditionalPhoto(1, this)">
			            <span class="file-name">파일 선택됨</span>
			            <button class="addi-cancel-button1">취소</button>
			        </div>
			        <div class="upload-box">
			        	<label for="additionalPhoto2Input" class="file-label">추가 사진 선택</label>
			            <img id="additionalPhoto2Preview" class="preview" src="#" alt="">
			            <input type="file" id="additionalPhoto2Input" accept="image/*" name="Img2" onchange="previewAdditionalPhoto(2, this)">
			            <span class="file-name">파일 선택됨</span>
			            <button class="addi-cancel-button2">취소</button>
			        </div>
			        <div class="upload-box">
			        	<label for="additionalPhoto3Input" class="file-label">추가 사진 선택</label>
			            <img id="additionalPhoto3Preview" class="preview" src="#" alt="">
			            <input type="file" id="additionalPhoto3Input" accept="image/*" name="Img3" onchange="previewAdditionalPhoto(3, this)">
			            <span class="file-name">파일 선택됨</span>
			            <button class="addi-cancel-button3">취소</button>
			        </div>
			        <div class="upload-box">
			        	<label for="additionalPhoto4Input" class="file-label">추가 사진 선택</label>
			            <img id="additionalPhoto4Preview" class="preview" src="#" alt="">
			            <input type="file" id="additionalPhoto4Input" accept="image/*" name="Img4" onchange="previewAdditionalPhoto(4, this)">
			            <span class="file-name">파일 선택됨</span>
			            <button class="addi-cancel-button4">취소</button>
			        </div>
			    </div>
			</div>
		</div>
		
		<div>
		<label for="category">카테고리</label> 
		<select name="category" id="selectCate" required>
			<option value="" disabled selected style="display: none;">카테고리</option>
			<option value="패션">패션</option>
			<option value="뷰티">뷰티</option>
			<option value="전자제품">전자제품</option>
			<option value="리빙/생활">리빙/생활</option>
			<option value="출산/육아">출산/육아</option>
			<option value="반려동물용품">반려동물용품</option>
			<option value="레저/스포츠">레저/스포츠</option>
			<option value="도서/음반">도서/음반</option>
			<option value="문구">문구</option>
			<option value="티켓/쿠폰">티켓/쿠폰</option>
			<option value="공구/산업용품">공구/산업용품</option>
		</select>
		</div>
		<div>상품명 : <input type="text" name="goods_title" required></input></div>
		<div>경매 시작가 : <input id="inputStartPrice" type="text" name="start_price" required></input></div>
		<div>즉시 구매가 : <input id="inputInstantPrice" type="text" name="instant_price" required></input></div>
		<div>경매 시간 : <select name="auction_time" required><option value="24">24</option><option value="48">48</option></select> 시간 </div>
		<div>
		거래 방법 : <select name="transact_type" required>
		<option value="" disabled selected style="display: none;">거래 방법</option>
		<option value="택배 배송">택배 배송</option>
		<option value="직거래">직거래</option>
		</select>
		</div>
		<div>거래 희망 지역 : 
		<select name="area" required>
			<option value="" disabled selected style="display: none;">지역 선택</option>
			<option value="전국">전국</option>
			<option value="부산/울산">부산/울산</option>
			<option value="대구권">대구권</option>
			<option value="광주권">광주권</option>
			<option value="강원권">강원권</option>
			<option value="제주권">제주권</option>
		</select>
		</div>
		<div>
		상품 상태 : 
		<select name="status" required>
			<option value="" disabled selected style="display: none;">제품 상태</option>
			<option value="개봉">개봉</option>
			<option value="미개봉">미개봉</option>
		</select><br>
		</div>
		<div>
			<span>상품 설명</span><br>
			<textarea class="form-control" rows="20" name="goods_info" placeholder="내용을 입력하세요 (최대 5000자)" maxlength="5000" required></textarea>
		</div>
	</div>
	<!-- 전국, 부산울산권, 대구권, 대전권, 광주권, 강원권, 제주권 항목 -->
	<button id="goods_submit" type="submit">등록하기</button>
	<button type="button" onclick="location.href='/goods/goodsMain'">작성 취소</button>
	</form>
</div>

<%@ include file="../include/footer.jsp"%>