<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String[] movieList = {"프리즌 브레이크", "스파르타쿠스", "히어로즈"};
	request.setAttribute("movieList", movieList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<c:forEach begin="1" end="10" step="1" varStatus="num">
		${num.count}
	</c:forEach>
</ul>
</body>
</html>