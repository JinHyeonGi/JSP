<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복체크</title>
<link href="css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body bgcolor="#FFFFCC">
<b>${id}</b>
<c:if test="${check}">
	는 이미 존재하는 ID입니다. <br>
</c:if>
<c:if test="${!check}">
	는 사용 가능 합니다. <br>
</c:if>
<a href="#" onclick="self.close()">닫기</a>
</body>
</html>