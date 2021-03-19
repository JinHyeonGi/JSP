<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="elex5.jsp" method="post">
	이름 : <input type="text" name="name" value="${param.name}">
	<input type="submit" value="확인">
</form>
이름은 : ${param.name} 입니다.
</body>
</html>