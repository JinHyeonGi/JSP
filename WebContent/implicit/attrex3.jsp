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
	<li>이름 : <%= pageContext.getAttribute("pageAttribute") %></li>
	<li>전화 : <%= request.getAttribute("requestAttribute") %></li>
	<li>메일 : <%= session.getAttribute("sessionAttribute") %></li>
	<li>회사 : <%= application.getAttribute("applicationAttribute") %></li>
</ul>
</body>
</html>