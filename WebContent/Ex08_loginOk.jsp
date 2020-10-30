<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Class.forName("oracle.jdbc.OracleDriver");
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","bituser","1004");
//out.print(conn.isClosed());

stmt = conn.createStatement();
String sql= "select empno ,ename , sal , job , comm from emp";

rs = stmt.executeQuery(sql);
	request.setCharacterEncoding("UTF-8");
	
	String user_id = request.getParameter("user_id");
	String user_pwd = request.getParameter("user_pwd");
	
	String result = null;
	if(user_id.equals("bit") && user_pwd.equals("1004")){
		result = "success";
	}else{
		result = "fail";
	}
%>
<%=result%>