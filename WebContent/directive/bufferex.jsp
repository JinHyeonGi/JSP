<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    buffer="1kb" 
    autoFlush="false"%>
<%-- 
	buffer : JSP 출력 버퍼크기를 설정, default는 8KB, none으로 설정되면 버퍼를 이용하지 않는다.
			  그냥 안건드리는게 좋다.
	autoFlush : 자동으로 버퍼를 비울건지 안비울건지를 지정한다. default는 true
				false일 경우 버퍼가 꽉차 넘치면 버퍼 오버플로우 에러가 발생한다.
				이것도 그냥 건드리지 말자
	
	isThreadSafe : 싱글 스레드 모드를 지원할 것인가를 결정, default는 true (true가 구현하지 않는것)
				   jsp는 사용자가 많아지면 자동으로 쓰레드를 추가생성한다.
				       싱글 스레드 모드가 필요하면 이것보다는 synchronized() 사용을 권장한다.
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// java.io.IOException: 오류: JSP 버퍼 오버플로우
	for(int i=0; i<1000; i++) {
%>
	1234
<%		
	}
%>
</body>
</html>