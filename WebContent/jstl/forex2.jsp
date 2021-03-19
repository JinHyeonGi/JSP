<%@page import="java.util.ArrayList"%>
<%@page import="jstl.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO vo1 = new MemberVO("손오공", "AAA", 500);
	MemberVO vo2 = new MemberVO("저팔계", "BBB", 200);
	MemberVO vo3 = new MemberVO("사오정", "CCC", 300);
	ArrayList<MemberVO> memberList = new ArrayList<>();
	memberList.add(vo1);
	memberList.add(vo2);
	memberList.add(vo3);
	request.setAttribute("memberList", memberList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>메일</td>
		<td>나이</td>
	</tr>
	<c:forEach var="vo" items="${memberList}" varStatus="num">
		<tr>
			<td>${num.count}</td>
			<td>${vo.name}</td>
			<td>${vo.email}</td>
			<td>${vo.age}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>