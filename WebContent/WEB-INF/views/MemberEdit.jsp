<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.bit.utils.Singleton_Helper"%>
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
	<table style="width: 900px; height: 500px; margin-left: auto; margin-right: auto;">
		<tr>
			<td colspan="2"><jsp:include page="./common/Top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td style="width: 200px"><jsp:include page="./common/Left.jsp"></jsp:include></td>
			<td style="width: 700px">
			<c:set var ="user" value='<%= request.getAttribute("user") %>' ></c:set>
            <c:if test="${user != null}">
				<form action="MemberEdit.do" method="post">
					<h3 style="text-align: center;">회원정보 수정</h3>
					<table style="width: 400px; height: 200px; margin-left: auto; margin-right: auto;">
						<tr>
							<td>아이디</td>
							<td><input type="text" name="id" value="${user.id}" readonly></td>
						</tr>
						<tr>
							<td>비번</td>
							<td>${user.pwd}</td>							</tr>
						<tr>
							<td>이름</td>
							<td>
								<input type="text" name="name" value="${user.name}" style="background-color: yellow">
							</td>
						</tr>
						<tr>
							<td>나이</td>
							<td>
								<input type="text" name="age" value="${user.age}" style="background-color: yellow">
							</td>
						</tr>
						<tr>
							<td>성별</td>
							<td>
								<c:choose>
									<c:when test="${ user.gender = '남' }">
										<input type="radio" name="gender" id="gender" value="남" checked>
										남자
										<input type="radio" name="gender" id="gender" value="여">
										여자
									</c:when>
									<c:otherwise>
										<input type="radio" name="gender" id="gender" value="남">
										남자
										<input type="radio" name="gender" id="gender" value="여" checked>
										여자
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>이메일</td>
							<td>
								<input type="text" name="email" value="${user.email}" style="background-color: yellow">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="수정하기">
								<a href='MemberList.do'>목록가기</a>
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include page="./common/Bottom.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>
