<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Cookie cookie = new Cookie("hour", "1time");
	cookie.setMaxAge(60);
	response.addCookie(cookie);
%>
유효시간이 1시간인 hour 쿠키 생성
</body>
</html>