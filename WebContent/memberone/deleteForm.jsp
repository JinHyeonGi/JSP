<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginID = (String)session.getAttribute("loginID");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link href="style.css" type="text/css" rel="stylesheet"/>
</head>
<script type="text/javascript">
	function begin() {
		document.myForm.pass.focus();
	}
	
	function checkIt() {
		if(!document.myForm.pass.value) { // null인지 검사
			alert("비밀번호를 입력하지 않았습니다.");
			document.myForm.pass.focus();
			return false;
		}
	}
</script>
<body onload="begin()">
<form action="deleteProc.jsp" method="post" name="myForm" onsubmit="return checkIt()">
<table width="260" border="1" align="center">
	<tr>
		<td colspan="2" align="center">
			<b>회원 탈퇴</b>
		</td>
	</tr>
	<tr>
		<td width="150"><b>비밀번호입력</b></td>
		<td width="110"><input type="password" name="pass" size="15"></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="회원탈퇴">
			<input type="button" value="취 소" onclick="window.location='login.jsp'">
		</td>
	</tr>
</table>
</form>
</body>
</html>