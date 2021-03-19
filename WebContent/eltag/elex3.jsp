<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr><td>${2 + 5}</td></tr>
	<tr><td>${4 / 5}</td></tr>
	<tr><td>${5 mod 6}</td></tr>
	<tr><td>${5 < 6}</td></tr>
	<tr><td>${(5 > 3) ? 5 : 3}</td></tr>
	<tr><td>${header["host"]}</td></tr>
	<tr><td>${header.host}</td></tr>
</table>
</body>
</html>