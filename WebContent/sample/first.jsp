<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSP Script 예제</h2>
	<% // Scriptlet - 연산, 처리, 사용될 곳 위쪽에 선언되어야 한다. => 메소드 영역(지역)
		String scriptlet = "스크립트릿 입니다.";
		String comment = "주석문 입니다";
		out.println("내장 객체를 이용한 출력" + declation + "<br>");
	
	%>
	
	선언문 출력하기(변수) : <%= declation %><br> <!-- Expression - 값의 출력 -->
	선언문 출력하기(메소드) : <%= declationMethod() %><br>
	스크립트릿 출력하기 : <%= scriptlet %><br>
	<!-- HTML 주석문 : <%= comment %> --><!-- HTML 주석문으로 자바를 막을 수 없다. -->
	<%-- JSP 주석문 : <%= comment %> --%>
	
	<%! // Declation - 변수, 메소드 선언, 어느 위치에 선언해도 상관없다. => 클래스 멤버 영역(전역)
		String declation = "선언문 입니다.";
		public String declationMethod(){
			return declation;
		}
	%>
</body>
</html>