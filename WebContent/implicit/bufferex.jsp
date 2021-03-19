<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int bufferSize = out.getBufferSize();
	int remainSize = out.getRemaining();
	int usedSize = bufferSize - remainSize;
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
버퍼 전체 크기: <%= bufferSize %> <br>
사용한 버퍼크기: <%= usedSize %> <br> <!-- 한번 채우고 그 다음 들어온 버퍼인지 아닌지 모름 -->
남은 버퍼: <%= remainSize %> <br> 
</body>
</html>