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
<%
	// 요청 헤더 정보
	Enumeration<String> enumData = request.getHeaderNames();
	while(enumData.hasMoreElements()) {
		String headerName = (String)enumData.nextElement();
		String headerValue = request.getHeader(headerName);
%>
	<%= headerName %> = <%= headerValue %> <br>
<%
	}
%>
</body>
</html>