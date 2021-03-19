<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	/*
		# 자바빈 (= VO)
		- 객체 생성과 동일
		- 매칭되는 빈 클래스가 필요하다.
		- getter, setter가 존재해야한다.
		- 기본자료형, String 까지 자동매핑된다. 날짜처리같은건 따로 해야한다.
		
		- 넘어오는 폼데이터들을 클래스(객체)로 담아서 보낼 수 있다!
		
		scope 속성으로 사용되는 범위를 지정할 수 있다.
		page < request < sessoin < application
	*/
%>
<jsp:useBean id="msg" class="sample.SimpleData"/>
<!-- property : 해당 빈클래스의 멤버변수와 매핑 -->
<jsp:setProperty name="msg" property="message"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>간단한 자바빈즈 프로그램 결과</h1>
<hr color="red">
메세지 : <jsp:getProperty name="msg" property="message"/>
</body>
</html>