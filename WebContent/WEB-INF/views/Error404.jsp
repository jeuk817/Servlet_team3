<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<jsp:include page="./common/Top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td style="width: 700px">
				<% 
					String userId = request.getRequestURI();
				%>
				<h1>Error 404</h1>
				<h2>요청하신 페이지를 찾을 수 없습니다.</h2>
				<h4>요청하신 주소: <%=request.getRequestURL() %></h4>
				<h4><a href="Main.do">메인으로 돌아가기</a></h4>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include page="./common/Bottom.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>


