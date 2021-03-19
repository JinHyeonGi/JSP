<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberid = (String)session.getAttribute("MEMBERID");
	String name = (String)session.getAttribute("NAME");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
세션에 정보를 로드하였습니다.
Member ID: <%= memberid %> <br>
Name : <%= name %>
</body>
</html>