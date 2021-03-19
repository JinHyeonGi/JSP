<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>include 지시어를 활용한 예제</h2>
	<%
		// # 디렉티브 include
		// 반복되는 부분을 재사용하기 위해 사용
		// file 속성값으로 포함될 문서 또는 url경로를 지정한다.
		// 포함 파일의 내용이 변환 시점에 그대로 복사되서 포함된다 => 포함코드 전에 선언된 변수를 같이 사용할 수 있다.
		// 동적으로 페이지 할당을 할 수는 없다. == 정적페이지이다, 컴파일 시 하나가 됨 (jsp액션태그 include는 동적처리가 가능, 컴파일 자체가 각각의 개인)
		String name = "SeungJae Lee";
	%>
	<%@ include file="top.jsp" %>
	
	포함하는 페이지 지시어(include) 예제의 내용입니다.
	
	<%@ include file="bottom.jsp" %>
	
	<!-- taglib : 태그 라이브러리 : 외부에서 정의한 태그를 사용하기 위해 선언  -->
</body>
</html>