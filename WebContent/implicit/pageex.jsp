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
	// pageContext 객체는 다른 기본 객체에 접근할 수 있는 메소드를 제공한다. (가져온 객체는 기본 내장 객체와 동일하다.)
	HttpServletRequest httpRequest = (HttpServletRequest)pageContext.getRequest();
%>
request 기본객체와 pageContext.request의 동일 여부: 
<%= request == httpRequest %> <br>
pageContext.getOut() 메소드를 사용한 데이터 출력 <br>
<% pageContext.getOut().println("안녕하세요!"); %>
</body>
</html>