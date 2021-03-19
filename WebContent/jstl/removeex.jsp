<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
browser 변수값 설정<br>
<c:set var="browser" value="${header['User-Agent']}" scope="request"/>
<c:out value="${browser}"/> <br>
browser 변수값 제거 후 <br>
<c:remove var="browser"/>
<c:out value="${browser}"/>
</body>
</html>