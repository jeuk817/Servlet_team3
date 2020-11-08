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
				<jsp:include page="/common/Top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
				<jsp:include page="/common/Left.jsp"></jsp:include>
			</td>
			<td style="width: 700px">
				<% 
					String userId = (String)session.getAttribute("userId");
				%>
				<c:set var="userid" value="<%= userId %>" />
				<c:choose>
					<c:when test="${ userid != null }">
						<b>${ userid } 회원님 방가방가^^</b><br>
						<c:if test="${ userid == 'admin' }">
							<a href='Controller.do'>회원관리</a>
						</c:if>
					</c:when>
					<c:otherwise>
						사이트 방문을 환영합니다 ^^ <br>회원가입 좀 하지 ...
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>


