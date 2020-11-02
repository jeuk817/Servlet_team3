<%@page import="kr.or.bit.utils.Singleton_Helper" %>
<%@ page import="javax.lang.model.element.NestingKind" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <td style="width: 200px">
            <jsp:include page="./common/Left.jsp"></jsp:include>
        </td>
        <td style="width: 700px">
            <c:set var ="user" value='<%= request.getAttribute("user") %>' ></c:set>
            <c:if test="${user != null}">
                   <table style="width: 400px;height: 100px;margin-left: auto;margin-right: auto;">
                       <tr>
                           <td style="width:100px">아이디</td>
                           <td style="width:100px">${user.id}</td>                           
                       </tr>
                       <tr>
                           <td style="width:100px">비번</td>
                           <td style="width:100px">${user.pwd}</td>
                       </tr>
                       <tr>
                           <td style="width:100px">이름</td>
                           <td style="width:100px">${user.name}</td>
                       </tr>
                       <tr>
                           <td style="width:100px">나이</td>
                           <td style="width:100px">${user.age}</td>
                       </tr>
                       <tr>
                           <td style="width:100px">성별</td>
                           <td style="width:100px">${user.gender}</td>
                       </tr>
                       <tr>
                           <td style="width:100px">이메일</td>
                           <td style="width:100px">${user.email}</td>
                       </tr>
                   </table>
                   <a href="MemberList.do">목록가기</a>
			</c:if>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <jsp:include page="./common/Bottom.jsp"></jsp:include>
        </td>
    </tr>
</table>
</body>
</html>