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
<form action="attrex.jsp" method="get">
	이름 : <input type="text" name="name"><br>
	전화 : <input type="text" name="tel"><br>
	메일 : <input type="text" name="email"><br>
	회사 : <input type="text" name="company"><br>
	<input type="submit" value="전송">
</form>
</body>
</html>