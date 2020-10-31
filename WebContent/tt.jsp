<%@page import="kr.or.bit.dto.KoreaMember"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.bit.utils.Singleton_Helper"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
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
<%
   List<KoreaMember> memberList = (List<KoreaMember>)request.getAttribute("memberList");
   KoreaMember km = memberList.get(0);
   System.out.println(km.getId());
   System.out.println(km.getEmail());
%>
<c:set var="user" value="<%= km %>" />
km : ${ user }
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
         <!--  
            회원 목록(리스트) 출력
            목록 (select id, ip from koreamember)
         -->   
            <%
               
               Connection conn = null;
               PreparedStatement pstmt = null;
               ResultSet rs = null;
               try{
                  conn = Singleton_Helper.getConnection("oracle");
                  String sql="select id, ip from koreamember";
                  pstmt = conn.prepareStatement(sql);
                  rs = pstmt.executeQuery(); 
            %>   
               <table style="width: 400px;height: 100px;margin-left: auto;margin-right: auto">
                     <tr><th colspan="4">회원리스트</th></tr>
                  <% while(rs.next()){ %>
                     <tr>
                        <td width="100px">
                           <a href='searchByIdServlet?id=<%=rs.getString("id")%>'><%=rs.getString("id")%></a>
                           <!-- <a href='Ex03_MemberDetail.jsp?id=<%=rs.getString("id")%>'><%=rs.getString("id")%></a>-->
                        </td>
                        <td width="100px"><%=rs.getString("ip")%></td>
                        <td>
                           <a href="DeleteMember?id=<%=rs.getString("id")%>">[삭제]</a>
                           <!-- <a href="Ex03_MemberDelete.jsp?id=<%=rs.getString("id")%>">[삭제]</a> -->
                        </td>
                        <td>
                           <a href="Ex03_MemberEdit.jsp?id=<%=rs.getString("id")%>">[수정]</a>
                        </td>
                     </tr> 
                  <% } %>
               </table>
               <hr>
                  <form action="Ex03_MemberSearch.jsp" method="post">
                     회원명:<input type="text" name="search">
                     <input type="submit" value="이름검색하기">
                  </form>
               <hr>               
            <%   
               }catch(Exception e){
                  
               }finally{
                  Singleton_Helper.close(rs);
                  Singleton_Helper.close(pstmt);
               }
            %>
         
         </td>
      </tr>
      <tr>
         <td colspan="2"><jsp:include page="/common/Bottom.jsp"></jsp:include></td>
      </tr>
   </table>
</body>
</html>