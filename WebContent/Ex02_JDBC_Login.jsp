<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
</head>
<body>
	<table
		style="width: 900px; height: 500px; margin-left: auto; margin-right: auto;">
		<tr>
			<td colspan="2">
				<jsp:include page="/common/Top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
				<jsp:include page="/common/Left.jsp"></jsp:include>
			</td>
			<td style="width: 700px">
				<h3 style="text-align: center;">Login Page</h3>
				<div>
					<table
						style="width: 400px; height: 100px; margin-left: auto; margin-right: auto;">
						<tr>
							<th>아이디:</th>
							<td><input type="text" name="id" id="id"></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="pwd" id="pwd"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" id="loginBtn" value="로그인"> <input
								type="reset" value="취소"></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
<script>
document.getElementById('loginBtn').addEventListener('click', async e=>{
	console.log('click')
	const id = document.getElementById('id').value;
	const pwd = document.getElementById('pwd').value;
	const jsonData = { id, pwd };
	const data = JSON.stringify(jsonData);
	const response = await fetch('Login', {
	    method: 'POST',
	    headers: {
	      'Content-Type': 'application/json'
	    },
	    redirect: 'follow',
	    referrerPolicy: 'no-referrer',
	    body: data
	  });
	const test = await response.text();
	if(test==='success'){
		location.href = 'Main';
	} else {
		// not found : 회원정보 없는거 -> 경고창
		// 가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.
	}
})
</script>
</html>