<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="vo" class="jstl.MemberVO"/>
<c:set target="${vo}" property="name" value="홍길동"/>
<c:set target="${vo}" property="email">
hong@hanmail.net
</c:set>
<c:set var="age" value="30"/>
<c:set target="${vo}" property="age" value="${age}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<li>${vo.name}</li>
	<li>${vo.email}</li>
	<li>${vo.age}</li>
</ul>
</body>
</html>