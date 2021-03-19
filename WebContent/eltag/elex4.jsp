<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("name", "이승재");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
요청 URI : ${pageContext.request.requestURI}
request의 name : ${requestScope.name}
code 파라미터 : ${param.code}
</table>
</body>
</html>