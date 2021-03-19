<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<c:choose>
	<c:when test="${loginID != null}">
		<table border="1" width="300">
			<tr>
				<td colspan="3" align="center">${loginID}님 환영합니다.</td>
			</tr>
			<tr>
				<td align="center" width="100">
					<a href="member.mdo?cmd=modifyForm">정보수정</a>
				</td>
				<td align="center" width="100">
					<a href="member.mdo?cmd=deleteForm">회원탈퇴</a>
				</td>
				<td align="center" width="100">
					<a href="member.mdo?cmd=logout">로그아웃</a>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<c:if test="${check == 0}">
			<script type="text/javascript">alert('비밀번호가 틀렸습니다.');</script>
		</c:if>
		<c:if test="${check == -1}">
			<script type="text/javascript">alert('아이디가 존재하지 않습니다.');</script>
		</c:if>
		<form method="post" action="member.mdo?cmd=loginProc">
			<table width="300" border="1">
				<tr>
					<td colspan="2" align="center">회원 로그인</td>
				</tr>
				<tr>
					<td width="100" align="right">아이디: </td>
					<td width="200">&nbsp;&nbsp;<input type="text" name="id" size="20"></td>
				</tr>
				<tr>
					<td width="100" align="right">비밀번호: </td>
					<td width="200">&nbsp;&nbsp;<input type="password" name="pass" size="20"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인">&nbsp;&nbsp;
						<input type="button" value="회원가입" 
							onclick="window.location='member.mdo?cmd=regForm">
					</td>
				</tr>
			</table>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>