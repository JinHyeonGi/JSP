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
<ul>
<%
	// application : 웹 애플리케이션 영역, 웹 애플리케이션당 하나씩 존재한다. (ServletContext)
	Enumeration<String> enumData = application.getInitParameterNames();
	while(enumData.hasMoreElements()) {
		String initParamName = enumData.nextElement();
%>
	<li>
		<%= initParamName %> = <%= application.getInitParameter(initParamName) %> <br>
	</li>
<%
	}
%>
</ul>
</body>
</html>