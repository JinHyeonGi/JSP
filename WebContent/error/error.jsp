<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
<% 
	/*
		# 웹 상태코드 Status
		100 - 클라이언트의 요청
		200 - 요청 수락
		300 - 요청 처리중
		-------------------------------
		400 - 클라이언트 잘못 / 404 : 없는 파일 요청 시 FileNotFound, 405 : get인데 post요청 또는 그 반대 시, 403 : 권한이 없을 시
		500 - 서버 잘못 (코드에러 등)
		
		isErrorPage : Throwable객체인 exception을 사용할 것인지 여부를 지정, default는 false
	*/
	
	// 기본적으로 브라우저는 에러가 발생하면 그 뒤에 코드는 다 무시하고 자체 에러페이지를 보여준다.
	// - 이 코드는 현재 페이지가 정상 응답되는 페이지임을 지정하는 코드
    // - 이 코드가 생략되면 웹 브라우저는 자체적으로 제공하는 화면을 표시
	response.setStatus(HttpServletResponse.SC_OK);
%>
<!-- isErrorPage : true면 현재 페이지를 에러페이지로 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
요청 처리 과정에서 예외가 발생하였습니다.<br>
빠른 시간내에 문제를 해결하도록 하겠습니다.
<br><br>
에러 타입 : <%= exception.getClass().getName() %> <br>
에러 메시지: <%= exception.getMessage() %>
</body>
</html>