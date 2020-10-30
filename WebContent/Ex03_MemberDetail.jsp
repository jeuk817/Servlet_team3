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
            <jsp:include page="/common/Top.jsp"></jsp:include>
        </td>
    </tr>
    <tr>
        <td style="width: 200px">
            <jsp:include page="/common/Left.jsp"></jsp:include>
        </td>
        <td style="width: 700px">
            <!-- 회원 아이디 입력시 회원정보 하단 출력  -->
            <%
                String userId = (String)session.getAttribute("userId");
            %>

            <c:set var ="userid" value="<%= userId %>" ></c:set>
            <c:set var ="user" value='<%= request.getAttribute("KoreaMember") %>' ></c:set>
            <c:if test="${user != null}">
                <form action = "Ex03_MemberDetailForm.jsp" >
                    <div class="koreaMember">
                        <div><label>아이디 : </label>${user.id}</div>
                        <div><label>비번: </label>${user.pwd}</div>
                        <div><label>이름: </label>${user.name}</div>
                        <div><label>나이: </label>${user.age}</div>
                        <div><label>성별: </label>${user.gender}</div>
                        <div><label>이메일: </label>${user.email}</div>
                        <a href="Ex03_Memberlist.jsp">목록가기</a>
                    </div>
                </form>
            </c:if>
            
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <jsp:include page="/common/Bottom.jsp"></jsp:include>
        </td>
    </tr>
</table>
</body>
</html>