<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
	- Directive 속성 
	extends : 상속받을 서블릿을 지정 => 거의 안쓰니 버리자 
	import : import할 패키지를 명시 
	session : default는 true, 세션객체를 사용할 것인지 결정 
	=> 기본적으로 세션은 내장객체라 JSP에서는 항상 null이 아니다. 
	      따라서 session != null 같은 조건식을 사용할 수 없다.
	      세션 값을 꺼내서 그 값이 null인지 검사하는 방향으로 가야한다.
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Calendar cal = Calendar.getInstance();
%>
오늘은
<%= cal.get(Calendar.YEAR) %>년
<%= cal.get(Calendar.MONTH) + 1 %>월
<%= cal.get(Calendar.DATE) %>일
입니다
</body>
</html>