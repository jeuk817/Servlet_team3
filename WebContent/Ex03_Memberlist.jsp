<%@page import="kr.or.bit.utils.Singleton_Helper"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   

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
			<td colspan="2">
				<jsp:include page="/common/Top.jsp"></jsp:include>
			</td>
      	</tr>
      	<tr>
			<td style="width: 200px">
		  		<jsp:include page="/common/Left.jsp"></jsp:include>
			</td>
         	<td style="width: 700px">
	            <div>
	               <h2>Member List</h2>
	            </div>
            <c:set var="list" value='<%= request.getAttribute("memberList") %>'/>
            <table style="width: 400px;height: 100px;margin-left: auto;margin-right: auto">
            <tr><th colspan="4">회원리스트</th></tr>
	            <c:forEach var="user" items="${ list }" varStatus="index">
	                 <c:if test="${user != null}">
	                    <tr>                       
	                        <td width="100px"><a href='Ex03_MemberDetail.jsp?id=${user.id}'><h4>${user.id}</h4></a></td>                                                       
	                     <td width="100px">     <small>${user.ip}</small>    </td>                    
	                       
	                        <td><a href='Ex03_MemberEdit.jsp?id=${user.id}'><span>수정</span></a> </td> 
	                        <td><a href='Ex03_MemberDelete.jsp?id=${user.id}'><span>삭제</span></a> </td> 
	                    </tr>
	                 </c:if> 
	            </c:forEach>
			</table>
			<p>회원조회</p>
            <form id="searchForm" action="Ex03_MemberSearch.jsp" method="post">
				<div>                                         
				     <input type="search" name="search" placeholder="회원명으로 찾기">
				     <button type="submit"  formmethod="post" name="search">검색</button>
				</div>
           	</form>
			<tr>
			   <td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include></td>
		</tr>
</body>
</html>