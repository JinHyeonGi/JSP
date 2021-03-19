<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Hello JSP</h2>
	현재 날짜와 시간은 : <%= new Date() %>
	<!-- 
		JSP도 서블릿처럼 처음 실행 단 한번만 초기화,컴파일 된다.
		JSP는 서블릿으로 변환 -> 컴파일 -> 메모리에 로드 된다.
		두번째 동작부터는 JSP는 쓰레드로 동작하기 때문에 이미 메모리에 로드되어있어서 로드 작업은 생략된다.
		서블릿이 삭제되거나 수정되면 다시 메모리에 로드되는 작업이 발생한다.
		변환된 서블릿은 HttpJspBase를 상속받고 있다. HttpJspBase는 HttpServlet을 상속받고 있다.  
	 -->
</body>
</html>