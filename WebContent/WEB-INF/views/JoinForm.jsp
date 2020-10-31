<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
table {
	border: solid 2px black;
	border-collapse: collapse;
}

tr {
	border: solid 1px blue;
	background-color: white;
	color: black;
}

td {
	border: solid 1px red;
}
</style>


<script type="text/javascript">


 
 
 
</script>
<!--  
CREATE TABLE koreaMember
(
    id VARCHAR2(50) PRIMARY KEY ,
    pwd VARCHAR2(50) NOT NULL,
    NAME VARCHAR2(50) NOT NULL,
    age NUMBER ,
    gender CHAR(4),
    email VARCHAR2(50),
    ip   VARCHAR2(50)
)
-->

</head>
<body>



	<table
		style="width: 1200px; height: 700px; margin-left: auto; margin-right: auto;">
		<tr>
			<td colspan="2">
				<jsp:include page="./common/Top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
				<jsp:include page="./common/Left.jsp"></jsp:include>
			</td>
			<td style="width: 1000px">
				<form action="SignUp.do" method="post" name="joinForm" id="joinForm">
					<h3 style="text-align: center;">회원가입</h3>
					<div>
						<table
							style="width: 400px; height: 200px; margin-left: auto; margin-right: auto;">
							<tr>
								<th>ID:</th>
								<td><input type="text" name="id" id="id" placeholder="4~20자리의 영문 및 숫자"><span id="spid"></span></td>								
								<td><button id="cid" name="cid">중복확인</button><span id="msg"></span></td>
							</tr>
							<tr>
								<th>PWD:</th>
								<td><input type="password" name="pwd" id="pwd"><span id="sppw"></span></td>
								<td><input type="text" name="cpwd" id="cpwd" > <span id="sppwck"></span></td>
							</tr>
							<tr>
								<th>Name:</th>
								<td><input type="text" name="name" id="mname"></td>
							</tr>
							<tr>
								<th>age:</th>
								<td><input type="text" name="age" id="age" maxlength="3"></td>
							</tr>
							<tr>
								<th>Gender:</th>
								<td><input type="radio" name="gender" id="gender" value="여"
									checked>여자 <input type="radio" name="gender"
									id="gender" value="남">남자</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td><input type="text" name="email" id="email"> <span id=spemail></span></td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="submit" value="회원가입">
									<input type="reset" value="취소"></td>
							</tr>
						</table>

					</div>
				</form>
			</td>
		</tr>
		<tr>
		
			<td colspan="2">
				<jsp:include page="./common/Bottom.jsp"></jsp:include>
			</td>
				
		</tr>
	</table>
</body>
<script type="text/javascript">

$(function(){
	$('#cid').click(function(){		
		var data = $('#id').val()		
		console.log('#id : ' + data)
		//비동기 함수 
		$.ajax(
				{
					url:"Join",
					type:"post",
					dataType:"text",
					data:"id=" + data,
					success:function(value){
						console.log(value);
						var result = value;
						if(data==""){
							$('#msg').html("<p style='color:red;'>공백은 사용불가</p>");
					    	$('#id').focus();
						}else if(value=='ok'){
							$('#msg').html("<p style='color:green;'>사용가능</p>");
					    	$('#id').focus();
						}else{
							$('#msg').html("<p style='color:red;'>사용불가</p>");
					    	$('#id').focus();
						}
						}					
					}
	
		);
	});	
	 let idpw_pattern = /^[a-z0-9_]{4,20}$/;
	    //a~z,0~9까지 입력가능 4자~20자 이내로
	    let email_pattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	     //0부터9a부터zA부터Z까지 (-, _, . 가 있어도 되고 없어도 되고, 0부터9a부터zA부터Z까지)반복횟수 상관없이가능
	     //@기호포함  (-, _, . 가 있어도 되고 없어도 되고, 0부터9a부터zA부터Z까지)반복횟수 상관없이가능 .기호포함 2자~3자 이내 대소문자 구분안함
	    
	    let id_check = false;
	    let pw_check = false;
	    let pwck_check = false;
	    let email_check = false;
	    //ID 확인 userId : keyup 
	    $('#id').keyup(function(){
	        if(idpw_pattern.test($(this).val()) != true ){
	            $('#spid').text("아이디가 조건에 일치하지 않습니다.");
	            id_check=false;
	        }else{
	            $('#spid').text("아이디가 조건과 일치합니다.");
	            id_check=true;
	        }
	    });
	    
	    //PW확인 userPass : keyup 
	    $('#pwd').keyup(function(){
	        if(idpw_pattern.test($(this).val()) != true ){
	            $('#sppw').text("패스워드가 조건에 일치하지 않습니다.");
	            pw_check=false;
	        }else{
	            $('#sppw').text("패스워드가  조건과 일치합니다.");
	            pw_check=true;
	        }
	    });
	    
	    //PW입력동일 확인 userPassCheck : keyup
	    $('#cpwd').keyup(function(){
	        if( $('#pwd').val() != $('#cpwd').val()){
	            $('#sppwck').text("입력한 비밀번호와 일치하지 않습니다.");
	            pwck_check=false;
	        }else{
	            $('#sppwck').text("입력한 비밀번호와 일치합니다.");
	            pwck_check=true;
	        }
	    });
	    
	    //email확인  userEmail : keyup
	    $('#email').keyup(function(){
	        if(email_pattern.test($(this).val()) != true){
	            $('#spemail').text("이메일 형식에 맞지 않습니다.");
	            email_check=false;
	        }else{
	            $('#spemail').text("이메일 형식에 맞습니다.");
	            email_check=true;
	        }
	    });
	    
	 $('#joinForm').submit(function() {
		   //alert("가입");   
		   if ($('#pwd').val() == "") { // 비밀번호 검사
			   alert('PWD를 입력해 주세요.');
			   $('#pwd').focus();
			   return false;
		  }else if ($('#mname').val() == "") { // 이름 검사
			   alert('mname를 입력해 주세요.');
			   $('#mname').focus();
			   return false;
		  }else if ($('#age').val() == "") { // 나이 검사
			   alert('age를 입력해 주세요.');
			   $('#age').focus();
			   return false;
		  }else if ($('#email').val() == "") { // 우편번호
			   alert('email를 입력해 주세요.');
			   $('#email').focus();
			   return false;
		  }
	  
	 });
}); 
//jquery 로 간단하게 유효성 check 하기


</script>
</html>