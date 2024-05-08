<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@include file="../include/header.jsp" %>
<!DOCTYPE html>
<html>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>join.jsp</h1>

	<fieldset>
		<legend>회원가입</legend>
		<form action="" method="POST" onsubmit="return checkAll()" id="myForm">
			<input type="text" name="userid" placeholder="아이디" id="id" required="required"  autofocus="autofocus"> <br>
				<span class="idchk" style="color: red; display: none;">5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</span>
				<span class="id_ok" style="color: green; display: none;">사용 가능한 아이디 입니다.</span>
				<span class="id_already" style="color: red; display: none;">중복된 아이디 입니다.</span>
				
			<input type="password" name="userpw" placeholder="비밀번호" id="pw" required="required"> <br>
				<span class="pwchk" style="color : red; display: none;">8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</span>
				
			<input type="text" name="usernm"placeholder="이름" id="name" required="required"> <br>
				<span class="namechk" style="color: red; display: none">한글, 영문 대/소문자를 사용해 주세요. (특수기호, 공백 사용 불가)</span>
				
			<input type="text" name="ubirth" placeholder="생년월일" id="birthday" required="required"> <br>
				<span class="birthdaychk" style="color: red; display: none; ">생년월일은 8자리 숫자로 입력해 주세요.</span>
			
			<input type="radio" name="gender" value="남" >남
			<input type="radio" name="gender" value="여" >여 <br>
			
			<input type="text" name="uphone" placeholder="휴대폰번호" id="phoneNumber" required="required"> <br>
				<span class="phoneNumberchk" style="color : red; display: none">숫자만 입력해주세요.</span>
			
			<input type="email" name="uemail"placeholder="이메일" id="email" required="required"> 
			<button type="button" id="sendEmail">메일보내기</button>
			<button type="button" id="sendEmail1" style="display: none">재전송</button><br>
				<span class="emailchk" style="color: red; display: none">이메일 형식에 맞게 입력해 주세요.</span>
				
				<input type="hidden" id="checkedEmail">
				
			<input type="text" id="checkEmail" placeholder="인증번호" required="required">
			<button type="button" id="confirmEmail">인증확인</button><br>
				<span class="confirmE" style="color: green; display: none">인증성공</span>
				<span class="confirmE1" style="color: red; display: none">인증번호를 확인해주세요</span>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="submit" value="회원가입">
			
			<a href="/user/login">로그인</a>
			
		</form>
	</fieldset>

<script type="text/javascript">
    $(document).ready(function() {
        $('#myForm').submit(function(event) {
            return validateAll(); 
        });
        $('#id').blur(validateId);
        $('#pw').blur(validatePw);
        $('#email').blur(validateEmail);
        $('#name').blur(validateName);
        $('#birthday').blur(validateBirthday);
        $('#phoneNumber').blur(validatePhoneNumber);
        $('#sendEmail').click(sendEmail);
        $('#sendEmail1').click(sendEmail);
        $('#confirmEmail').click(emailChecked);
    });
    
    var csrfHeaderName = "${_csrf.headerName}";
    var csrfTokenValue = "${_csrf.token}";
    
    function validateId() {
        var RegId = /^[a-z0-9_-]{5,20}$/;
        var userid = $('#id').val();

        if (!RegId.test($.trim(userid))) {
            $('.idchk').css({'display': 'block'}); 
            $('.id_ok').css({"display":"none"});
            $('.id_already').css({"display":"none"});
            return false;
        } else {
            $('.idchk').css({'display': 'none'});
            
            // AJAX 요청을 동기적으로 처리
            var isValidId = false;
            
            $.ajax({
                url: '/ConfirmId',
         		beforeSend : function(xhr){
         			xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
         		},
                data: { userid : userid },
                type: 'post',
                async: false, // 동기적으로 설정
                success: function(cnt) {
                    if (cnt != 1 && userid.length > 0) {
                        $('.id_ok').css({"display":"block"});
                        $('.id_already').css({"display":"none"});
                        isValidId = true;
                    } else if (cnt == 1 && userid.length > 0) {
                        $('.id_already').css({"display":"block"});
                        $('.id_ok').css({"display":"none"});
                    } else {
                        $('.id_ok').css({"display":"none"});
                        $('.id_already').css({"display":"none"});
                    }
                }
            }); // ajxa 끝

            return isValidId;
        }
    }


    function validatePw() {
        var RegPw = /^[a-zA-Z0-9!@#$%^&*]{8,16}$/;
        var pw = $('#pw').val();

        if (!RegPw.test($.trim(pw))) {
            $(".pwchk").css({'display':'block'});
            return false;
        } else {
            $(".pwchk").css({'display':'none'});
            return true;
        }
    }
    
    function validatePhoneNumber(){
    	var RegPhoneNumber = /^[0-9]{11}$/;
    	var phoneNumber = $('#phoneNumber').val();
    	
    	if(!RegPhoneNumber.test($.trim(phoneNumber))){
    		$(".phoneNumberchk").css({'display':'block'})
    		return false;
    	}else{
    		$('.phoneNumberchk').css({'display':'none'})
    		return true;
    	}
    	
    }

    function validateEmail() {
        var RegEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        var email = $('#email').val();

        if (!RegEmail.test($.trim(email))) {
            $(".emailchk").css({'display':'block'});
            return false;
        } else {
            $(".emailchk").css({'display':'none'});
            return true;
        }
    }
    
    function sendEmail() {
        console.log("Send email button clicked");
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
        });
    }
    
    function emailChecked() {
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
    }

    function validateName() {
        var RegName = /^[가-힣a-zA-Z]+$/;
        var name = $('#name').val();

        if (!RegName.test($.trim(name))) {
            $('.namechk').css({'display':'block'});
            return false;
        } else {
            $('.namechk').css({'display':'none'});
            return true;
        }
    }

    function validateBirthday() {
        var RegBirthday = /^\d{8}$/;
        var birthday = $('#birthday').val();

        if (!RegBirthday.test($.trim(birthday))) {
            $(".birthdaychk").css({'display':'block'});
            return false;
        } else {
            $(".birthdaychk").css({'display':'none'});
            return true;
        }
    }
    
    function validateGender(){
        var gender = document.getElementsByName('gender');
        flag = false;
        
        for(i=0; i<gender.length; i++){
            if(gender[i].checked){
                flag = true;
                break;
            }
        }
        return flag;
    }

    function checkAll() {
        return validateAll(); 
    }

    function validateAll() {
        var isValid = validateId() && validatePw() && validateEmail() && validateName() && validateBirthday() && validateGender() && emailChecked() && validatePhoneNumber();

        return isValid;
    }
</script>
<%@include file="../include/footer.jsp" %>
</body>
</html>
