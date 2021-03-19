<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c}">
<center><b>글쓰기</b></center><br>
<form method="post" name="writeForm" action="/myWeb/board/updatePro.do?pageNum=${pageNum}" onsubmit="return writeSave()">
	<table border="1" align="center" bgcolor="${bodyback_c}">
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">이름</td>
			<td width="330">
				<input type="text" size="12" maxlength="12" name="writer" value="${article.writer}">
				<input type="hidden" name="num" value="${article.num}">
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">이메일</td>
			<td width="330">
				<input type="text" size="30" maxlength="30" name="email" value="${article.email}">
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">제목</td>
			<td width="330">
				<input type="text" size="50" maxlength="50" name="subject" value="${article.subject}">
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">내용</td>
			<td width="330">
				<textarea rows="13" cols="50" name="content">${article.content}</textarea>
			</td>
		</tr>
		<tr>
			<td width="70" bgcolor="${value_c}" align="center">비밀번호</td>
			<td width="330">
				<input type="password" size="10" maxlength="10" name="pass">
			</td>
		</tr>
		<tr>
			<td colspan="2" bgcolor="${value_c}" align="center">
				<input type="submit" value="글수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" onclick="window.location='/myWeb/board/list.do?pageNum=${pageNum}'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>