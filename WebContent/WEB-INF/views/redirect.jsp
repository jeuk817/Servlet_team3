<%--
  Created by IntelliJ IDEA.
  User: soheelim
  Date: 2020/10/31
  Time: 6:43 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String msg = (String)request.getAttribute("board_msg");
    String url = (String)request.getAttribute("board_url");

    if(msg != null && url != null){
%>
<script>
    alert('<%= msg %>');
    location.href='<%=url%>';
</script>

<%
    }
%>