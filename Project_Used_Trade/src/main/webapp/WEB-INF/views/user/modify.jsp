<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@include file="../include/header.jsp" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
            margin: 0;
            padding: 0;
        }

        .a {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border-radius: 8px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 5px;
            display: none;
        }

        @media (max-width: 768px) {
            .a {
                padding: 10px;
            }
        }
    </style>
</head>
<body>

<div class="a">
    <h1>회원 정보 수정</h1>

    <form action="" method="POST">

        <label for="usernm">이름</label>
        <input type="text" id="usernm" name="usernm" value="${user.usernm}" readonly="readonly" style="background-color: #eee">

        <label for="userid">아이디</label>
        <input type="text" id="userid" name="userid" value="${user.userid}" readonly="readonly" style="background-color: #eee">

        <label for="nowpw">현재 비밀번호</label>
        <input type="password" id="nowpw" required>
        <span class="error-message pwchk1" style="color : red; display: none;">비밀번호가 틀립니다.</span>

        <label for="newpw">새 비밀번호</label>
        <input type="password" id="newpw" name="userpw" required>
        <span class="error-message pwchk" style="color : red; display: none;">8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</span>
        <span class="error-message samepw" style="color: red; display: none;">현재 비밀번호와 동일합니다.</span>

        <label for="ubirth">생년월일</label>
        <input type="text" id="ubirth" name="ubirth" value="${user.ubirth}" readonly="readonly" style="background-color: #eee">

        <label for="uphone">휴대폰 번호</label>
        <input type="tel" id="uphone" name="uphone" value="${user.uphone}">
        <span class="error-message phoneNumberchk" style="color : red; display: none;">숫자만 입력해주세요.</span>

        <label for="uemail">이메일</label>
        <input type="email" id="uemail" name="uemail" value="${user.uemail}">
        <span class="error-message emailchk" style="color: red; display: none;">이메일 형식에 맞게 입력해 주세요.</span> <br>

        <label for="uintro">소개글</label>
        <textarea id="uintro" name="uintro" rows="5" cols="20"></textarea>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="수정하기">
    </form>
</div>
	
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