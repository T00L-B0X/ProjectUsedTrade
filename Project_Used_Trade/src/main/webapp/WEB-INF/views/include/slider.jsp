<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<script>
      $(document).ready(function(){
        $(".bxslider").bxSlider({
        	auto: true,
        	autoControls: true,
            stopAutoOnClick: true,
            pager: true,
            slideWidth: 1000,
        });
      });
</script>

<div class="bxslider">
	<div><img src="/resources/article/img/itwillbs.png" style="width: 100%;" /></div>
	<div><img src="/resources/article/img/baekjoon.png" style="width: 100%;" /></div>
	<div><img src="/resources/article/img/itwillbs.png" style="width: 100%;" /></div>
</div>