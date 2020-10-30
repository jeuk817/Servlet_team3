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


            <div class="koreaMember">
                <table style="width: 400px;height: 100px;margin-left: auto;margin-right: auto;">
                    <tr>

                        <td style="width:100px">아이디</td>
                    </tr>
                    <tr>

                        <td style="width:100px">비번</td>
                    </tr>
                    <tr>

                        <td style="width:100px">이름</td>
                    </tr>
                    <tr>

                        <td style="width:100px">나이</td>
                    </tr>
                    <tr>

                        <td style="width:100px">성별</td>
                    </tr>
                    <tr>

                        <td style="width:100px">이메일</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a href="Ex03_Memberlist.jsp">목록가기</a>
                        </td>
                    </tr>
                </table>
            </div>


</table>


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