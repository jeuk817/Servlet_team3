<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="Main">Main</a>&nbsp;&nbsp;&nbsp;||
<a href="Login">Login</a>&nbsp;&nbsp;&nbsp;||
<a href="JoinForm">Register</a>&nbsp;&nbsp;&nbsp;||
<a href="#">Intro</a>&nbsp;&nbsp;&nbsp;||
<a href="#">Intro</a>&nbsp;&nbsp;&nbsp;
<%
	String userId = (String)session.getAttribute("userId");
	
%>
<c:set var="userid" value="<%= userId %>" />
<c:choose>
	<c:when test="${ userId != null }">
		<b>[ ${userid} ]</b> 로그인 상태
		<a href='Logout'>[ 로그아웃 ]</a>
	</c:when>
	<c:otherwise>
		<b>[로그인 하지 않으셨네요]</b>
		<a href='Login'>[ 로그인 ]</a>
	</c:otherwise>
</c:choose>
