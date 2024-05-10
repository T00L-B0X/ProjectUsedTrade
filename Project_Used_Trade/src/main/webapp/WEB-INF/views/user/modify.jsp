<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@include file="../include/header.jsp" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>modify.jsp</h1>
	
	${user} <br>
	

	<form action="" method="POST">
	
		이름 : <input type="text" name="usernm" value="${user.usernm}" readonly="readonly"> <br>
		아이디 : <input type="text" name="userid" value="${user.userid}" id="userid" readonly="readonly"> <br>
		
		현재비밀번호 : <input type="text" id="nowpw" required="required"> <br>
			<span class="pwchk1" style="color : red; display: none;">비밀번호가 틀립니다.</span>
			
			
		새비밀번호 : <input type="text" name="userpw" id="newpw"> <br>
			<span class="pwchk" style="color : red; display: none;">8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</span>
			<span class="samepw" style="color: red; display: none;">현재 비밀번호와 동일합니다.</span>
			
		생년월일 : <input type="text" name="ubirth" value="${user.ubirth}" readonly="readonly"> <br>
		성별 : <input type="text" name="gender" value="${user.gender}" readonly="readonly"><br>
		
		휴대폰 번호 : <input type="text" name="uphone" value="${user.uphone}" id="newphone"> <br>
			<span class="phoneNumberchk" style="color : red; display: none">숫자만 입력해주세요.</span>
		
		이메일 : <input type="text" name="uemail" value="${user.uemail}" id="email"> <br>
			<!-- <button type="button" id="sendEmail">메일보내기</button>
			<button type="button" id="sendEmail1" style="display: none">재전송</button><br> -->
			<span class="emailchk" style="color: red; display: none">이메일 형식에 맞게 입력해 주세요.</span> 
				
			 <!--<input type="hidden" id="checkedEmail">
		
		인증코드 : <input type="text" id="checkEmail">
			 <button type="button" id="confirmEmail">인증확인</button><br>
			<span class="confirmE" style="color: green; display: none">인증성공</span>
			<span class="confirmE1" style="color: red; display: none">인증번호를 확인해주세요</span> -->
		
		소개글 : <textarea rows="5" cols="20" name="uintro"></textarea> <br>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit" value="수정하기">
	
	</form>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
			
		function checkUserPw(){
			$('#nowpw').blur(function(){
				
				var nowpw = $('#nowpw').val();
				var RegPw = /^[a-zA-Z0-9!@#$%^&*]{8,16}$/;
				
				if(!RegPw.test($.trim(nowpw))){
					 $(".pwchk1").css({'display':'block'});
					return false;
				}else {
					$.ajax({
						url : '/checkPw',
						beforeSend : function(xhr){
		         			xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		         		},
		         		type : 'post',
		         		data : {nowpw : nowpw},
		         		success : function(result){
		         			if(result != 1 && nowpw.length > 0 ){	
		         			 $(".pwchk1").css({'display':'block'});
		         			 
		         			} else if (result == 1 && nowpw.length > 0){
		         				$(".pwchk1").css({'display':'none'});
		         				return true;
		         			} else{
		         				$('.pwchk1').css('display','block');
		         			}
 
		         		}, error: function(xhr, status, error) {
		                    // 실패 응답 받은 경우
		                    console.error('Error:', error); // 콘솔에 에러 메시지 출력
		                    alert(xhr.responseText); // 서버에서 반환한 내용을 알림창으로 표시
		         		}

					}); // ajax 끝
					
				}
				
			});

		}// checkUserPw 끝
		checkUserPw();
		
		function newUserPw(){
			$('#newpw').blur(function(){
				
				var newpw = $('#newpw').val();
				var RegPw = /^[a-zA-Z0-9!@#$%^&*]{8,16}$/;
				var nowpw = $('#nowpw').val();
				
				if(!RegPw.test($.trim(newpw))){
					$(".pwchk").css({'display':'block'});
					$(".samepw").css({'display':'none'});
					
					return false;
				}else{
					$.ajax({
						url : '/updatePw',
						beforeSend : function(xhr){
		         			xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		         		},
		         		type : 'post',
		         		data : { newpw : newpw},
		         		success : function(result){
		         			if(result != 1 && newpw.length > 0 ){	
			         			$(".pwchk").css({'display':'none'});
			         			$(".samepw").css({'display':'none'});
			         			return true;
			         			 
			         			} else if (result == 1 && newpw.length > 0){
			         				$(".samepw").css({'display':'block'});
			         				$(".pwchk").css({'display':'none'});
			         				
			         			} else{
			         				$('.pwchk').css('display','block');
			         				$(".samepw").css({'display':'none'});
			         			}
		         		}, error: function(xhr, status, error){
		         			console.error('Error:',error);
		         			alert(xhr.reponseText);
		         		}
		         		
					}); // ajax 끝
				}
				
			});
			
		} // newUserPw() 끝
		newUserPw();
		
		function phoneNB(){
			$('#newphone').blur(function(){
			
			var newphone = $('#newphone').val();
			var RegPhoneNumber = /^[0-9]{11}$/;
			
			if(!RegPhoneNumber.test($.trim(newphone))){
	    		$(".phoneNumberchk").css({'display':'block'})
	    		return false;
	    	}else{
	    		$('.phoneNumberchk').css({'display':'none'})
	    		return true;
	    	}
			});
		}// phoneNB() 끝
		phoneNB()
		
		function email() {
			$('#email').blur(function(){
				
			
	        var RegEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	        var email = $('#email').val();

	        if (!RegEmail.test($.trim(email))) {
	            $(".emailchk").css({'display':'block'});
	            return false;
	        } else {
	            $(".emailchk").css({'display':'none'});
	            return true;
	        	}
			});
	    } //email() 끝
		email()
	    
		/*function sendEmail() {
	    	$('#sendEmail').click(function(){

	        var uemail = $('#email').val();

	        $.ajax({
	            url: "/sendEmail",
	            beforeSend: function(xhr) {
	                xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	            },
	            data: { uemail: uemail },
	            type: 'post',
	            success: function(response) {
	                // 성공 응답 받은 경우
	                alert('해당 이메일로 발송하였습니다.');
	                $('#checkedEmail').val(response);
	                $('#sendEmail').css({'display':'none'})
	                $('#sendEmail1').css({'display' : 'inline-block'})
	                
	            },
	            error: function(xhr, status, error) {
	                // 실패 응답 받은 경우
	                console.error('Error:', error); // 콘솔에 에러 메시지 출력
	                alert(xhr.responseText); // 서버에서 반환한 내용을 알림창으로 표시
	                $('#sendEmail').css({'display':'none'})
	                $('#sendEmail1').css({'display':'inline-block'})
	                
	            }
	        }); // ajax 끝
	    	});
	    } //sendEmail 끝
		sendEmail()	    
	    
	    
	     function emailChecked() {
	    	$('#confirmEmail').click(function(){
	
	        var num1 = $('#checkEmail').val();
	        var num2 = $('#checkedEmail').val();
	              
	        if (num1 === num2 && num1 != "") {
	            $('.confirmE').css({'display' : 'block'});
	            $('.confirmE1').css({'display':'none'});
	            return true;
	        } else {
	            $('.confirmE').css({'display' : 'none'});
	            $('.confirmE1').css({'display':'block'});
	            return false;
	        }
	    });
	}; // emailChecked() 끝
	emailChecked() */
	
	/* function checkAll(){
		var acheckUserPw = checkUserPw();
		var anewUserPw = newUserPw();
		var aphoneNB = phoneNB();
		var aemail = email();
		var asendEmail = sendEmail();
		var aemailChecked = emailChecked();
		
		return acheckUserPw && anewUserPw && aphoneNB && aemail && asendEmail && aemailChecked
	}
	
	 $('#myForm').submit(function(event) {
         return checkAll(); 
     }); */
	}); 

	</script>
	
<%@include file="../include/footer.jsp" %>
</body>
</html>