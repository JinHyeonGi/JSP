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
<c:forTokens var="car" items="Sprinter Trueno AE86, RX-7 SAVANA, LANCER Evolution" delims=",">
자동차 이름 : <c:out value="${car}"/>
</c:forTokens>
</body>
</html>