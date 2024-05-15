<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script> -->
<style>
	.searchKeyword{
		display:flex;
	}
	
	.searchKeyword input{
		margin-left: auto;
	}

	.cate-link{
		text-decoration: none;
	}
	
	.cate-all{
		text-decoration: none;
	}
	
	.cate-link.active {
	    font-weight: bold; /* 활성화된 카테고리 텍스트 굵게 표시 */
	    color: blue; /* 활성화된 카테고리 텍스트 색상 변경 */
	}
	.cate-all.active {
	    font-weight: bold; /* 활성화된 카테고리 텍스트 굵게 표시 */
	    color: blue; /* 활성화된 카테고리 텍스트 색상 변경 */
	}
	#cateList li{
		display: inline;
		padding-right: 20px;
	}
	
	#goodsCard {
    display: flex;
    align-items: center; /* 요소들을 세로 중앙 정렬 */
    max-width: 1200px;
    max-height: 168px;
	}
	
	#imgLink {
	    max-width: 168px;
	    max-height: 168px;
	}
	.col-md-9 {
    flex: 1; /* 남은 공간 모두를 차지하도록 설정 */
	}
	.card-body {
	    padding: 1rem;
	    display: flex;
	    flex-direction: column;
	    justify-content: center;
	}
	
	.card-title {
	    margin-bottom: .75rem;
	}
	
	.card-text {
	    margin-bottom: .75rem;
	    width: 600px;
	}
	
	.goodsEnd {
	    font-size: .875em;
	}
	#goodsBox {
    display: flex;
    flex-direction: column;
	}
	#searchKeyword {
    text-align: right; /* 텍스트를 오른쪽으로 정렬 */
	}
	#registerBtn {
    margin-right: 10px; /* 왼쪽으로 이동하고 10px의 간격을 부여합니다. */
	}
</style>

<script type="text/javascript">
$(document).ready(function() {
    function appendCard(item) {
        // 새로운 card 생성
        var cardHtml = `
        <div id="goodsCard" class="card mb-3" style="max-width: 1200px; max-height: 168px;">
                <div class="row g-0">
                    <div id="imgLink" class="col-md-3 d-flex justify-content-center align-items-center" style="max-width: 168px; max-height: 168px;">
                        <a><img class="goodsImg" target="_blank" style="max-width: 95%; height: 95%;" alt="..."></a>
                    </div>
                    <div class="col-md-9">
                        <div class="card-body">
                            <h5 id="titleLink" class="card-title">
                                <a target="_blank"><span class="goodsTitle"></span></a>
                            </h5>
                            <p class="card-text">
                            	현재입찰가 : <span class="goodsCurrent"></span>
                        	</p>
                        	<p class="card-text">
                            	즉시구매가 : <span class="goodsInstant"></span>
                        	</p>
                            <p class="card-text">
                                <small class="goodsEnd text-body-secondary"></small>
                            </p>
                        </div>
                    </div>
                </div>
            </div>`;

        // 새로운 카드를 goodsBox에 추가
        var $newCard = $(cardHtml).appendTo("#goodsBox");
        
        // 새로운 카드에 데이터 적용
        $newCard.find(".goodsImg").attr("src", "/displayImages?goods_id=" + item.goods_id + "&imageNo=0");
        $newCard.find("#imgLink a").attr("href", "/goods/read?goods_id=" + item.goods_id);
        $newCard.find(".goodsTitle").text(item.goods_title);
        $newCard.find("#titleLink a").attr("href", "/goods/read?goods_id=" + item.goods_id);
        $newCard.find(".goodsCurrent").text(item.current_price + "원");
        $newCard.find(".goodsInstant").text(item.instant_price + "원");

        var date = new Date(item.regdate);
        
        var regdate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
		
        $newCard.find(".goodsReg").text(regdate);
		
        // 종료 시간 설정
        var startTime = new Date(item.regdate);
        var endTime = new Date(startTime.getTime()+ (item.auction_time * 60 * 60 * 1000));
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


        var currentTime = new Date();
        var timeDiff = endTime.getTime() - currentTime.getTime();
     // 남은 시간 계산 (밀리초를 일, 시, 분, 초로 변환)
		var remaindays = Math.floor(timeDiff/ (1000 * 60 * 60 * 24));
		var remainhours = Math.floor((timeDiff % (1000 * 60 * 60 * 24))/ (1000 * 60 * 60));
		var remainminutes = Math.floor((timeDiff % (1000 * 60 * 60))/ (1000 * 60));
		var remainseconds = Math.floor((timeDiff % (1000 * 60)) / 1000);
		if (item.current_price == item.instant_price || timeDiff < 0) {
			console.log("경매 종료");
			var remainingTime = "경매 종료";
		} else {
			// 출력 포맷에 맞게 문자열 조합
			var remainingTime = remaindays
					+ "일 "
					+ remainhours
					+ "시간 "
					+ remainminutes
					+ "분";
		}

        

        $newCard.find(".goodsEnd").html('<span style="color: black;">경매마감 ' + formattedEndTime + '</span>'+' (' + remainingTime + ')');
    }
    
    function selectList() {
        // 디비에서 최신글부터 조회
        $.ajax({
            url: "/goods/list",
            method: "GET",
            success: function(data) {
                console.log(data);
                totalItemCount = data.length;
                console.log("길이"+data.length);
                
                $(data).each(function(idx, item) {
                    appendCard(item);
                });
            }
        });
    }
    $(document).ready(function() {
        // Enter로 검색가능하게
        $('input[name="keyword"]').keydown(function(event) {
            // Enter의 keyCode는 13
            if (event.keyCode === 13) {
                // 검색 버튼 클릭
                $('#btnSearch').click();
            }
        });
    });
    
    $(document).ready(function() {
        $("#btnSearch").click(function() {
            var keyword = $("input[name='keyword']").val();
            var category = $('.active').data('category'); // 활성화된 카테고리 가져오기
           	// AJAX 요청
           	if (keyword.trim() === '') {
            alert("검색어를 입력해주세요.");
            return; // 검색 중지
        	}
           	if(!category){
            	category = "";
            }
           	
            $.ajax({
                url: "/goods/list/search",
                method: "GET",
                data: {
                    category: category,
                    keyword: keyword
                },
                success: function(data) {
                    $('#goodsBox').empty(); // 검색 결과를 표시할 요소 초기화
                    if (data.length >= 1) {
                        // 검색 결과가 있을 경우 각 아이템에 대해 카드 생성
                        data.forEach(function(item) {
                            appendCard(item);
                        });
                    } else {
                        // 검색 결과가 없을 때 메시지 출력
                        $('#goodsBox').append("<p>검색 결과가 없습니다.</p>");
                    }
                },
                error: function(xhr, status, error) {
                    console.error("AJAX request error:", status, error);
                    $('#goodsBox').empty().append("<p>검색 중 오류가 발생했습니다.</p>");
                }
            });
        });
    });
        
        $(document).ready(function() {
            $(".cate-link").click(function(e) {
            	// 모든 카테고리 링크에서 active 클래스 제거
                $(".cate-link").removeClass("active");
                $(".cate-all").removeClass("active");
            	// 카테고리 링크 클릭시 검색란 비우기
            	$("input[name='keyword']").val('');
                // 클릭된 카테고리 링크에 active 클래스 추가
                $(this).addClass("active");
                e.preventDefault();
                var category = $(this).data('category');
                var keyword = $('input[name="keyword"]').val();
                // AJAX 요청
                $.ajax({
                    url: "/goods/list/search",
                    method: "GET",
                    data: {
                        category: category,
                        keyword: keyword
                    },
                    success: function(data) {
                        $('#goodsBox').empty(); // 검색 결과를 표시할 요소 초기화
                        if (data.length >= 1) {
                            // 검색 결과가 있을 경우 각 아이템에 대해 카드 생성
                            data.forEach(function(item) {
                                appendCard(item);
                            });
                        } else {
                            // 검색 결과가 없을 때 메시지 출력
                            $('#goodsBox').append("<p>검색 결과가 없습니다.</p>");
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("AJAX request error:", status, error);
                        $('#goodsBox').empty().append("<p>검색 중 오류가 발생했습니다.</p>");
                    }
                });
            });
            
            $(".cate-all").click(function(e){
            	// 모든 카테고리 링크에서 active 클래스 제거
                $(".cate-link").removeClass("active");
            	// 카테고리 링크 클릭시 검색란 비우기
            	$("input[name='keyword']").val('');
                // 클릭된 카테고리 링크에 active 클래스 추가
                $(this).addClass("active");
                $('#goodsBox').empty();
                selectList();
            });
            
            $("#registerBtn").click(function(e){
            	var url = "/goods/register";
            	window.location.href = url;
            });
        });
        
        

    
    
    // 페이지 로딩 시 초기 데이터 가져오기
    selectList();
    

});
</script>
	<div class="container">
	<div id="goodsList">
		<div class="pageTitle">
		<h2>경매 상품</h2>
		</div>
		<div>
			<ul id="cateList">
				<li><a href="#" class="cate-all">전체</a></li>
				<li><a href="#" class="cate-link" data-category="패션">패션</a></li>
				<li><a href="#" class="cate-link" data-category="뷰티">뷰티</a></li>
				<li><a href="#" class="cate-link" data-category="전자제품">전자제품</a></li>
				<li><a href="#" class="cate-link" data-category="리빙/생활">리빙/생활</a></li>
				<li><a href="#" class="cate-link" data-category="출산/육아">출산/육아</a></li>
				<li><a href="#" class="cate-link" data-category="반려동물용품">반려동물용품</a></li>
				<li><a href="#" class="cate-link" data-category="레저/스포츠">레저/스포츠</a></li>
				<li><a href="#" class="cate-link" data-category="도서/음반">도서/음반</a></li>
				<li><a href="#" class="cate-link" data-category="문구">문구</a></li>
				<li><a href="#" class="cate-link" data-category="티켓/쿠폰">티켓/쿠폰</a></li>
				<li><a href="#" class="cate-link" data-category="공구/산업용품">공구/산업용품</a></li>
			</ul>
		</div>
		<div id="searchKeyword">
			<input type="text" name="keyword" placeholder="검색"></input>
			<input type="button" value="검색" id="btnSearch">
			<sec:authorize access="isAuthenticated()">
			<button id="registerBtn" style="float: right; margin-left: 10px;">글 등록 하기</button>
			</sec:authorize>
		</div>
	</div>
	</div>
	
	<div class="container" id="goodsBox"></div>

<%@ include file="../include/footer.jsp"%>
