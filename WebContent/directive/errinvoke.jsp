<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- errorPage="/error/error.jsp"%> -->
<!-- errorPage : 에러발생 시 이동할 에러 페이지 지정 (ContextPath 붙이면 에러, 내부 경로에서 찾는듯)  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
name 파라미터 값 :
<%= request.getParameter("name").toUpperCase() %>
</body>
</html>