<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
서버 정보: <%= application.getServerInfo() %> <br>
서블릿 규약 메이저 버전: 
<%= application.getMajorVersion() %> <br>
서블릿 규약 마이너 버전: 
<%= application.getMinorVersion() %> <br>
웹 어플리케이션 내에서 지정한 경로의 실제 자원 경로:
<%= application.getRealPath("/") %> <br>
</body>
</html>