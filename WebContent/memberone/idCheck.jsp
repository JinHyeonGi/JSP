<%@page import="memberone.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <jsp:useBean id="dao" class="memberone.StudentDAO"/> --%>
<%
	StudentDAO dao = StudentDAO.getInstance();
	String id = request.getParameter("id");
	boolean check = dao.idCheck(id);
	// csv : 쉼표로 구분한 파일
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID CHECK</title>
<link href="style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC">
<br>
<b><%= id %></b>
<%
	if(check) {
%>
		는 이미 존재하는 ID입니다.<br>
<% 
	}else {
%>
		는 사용 가능합니다.<br>
	<script type="text/javascript">
		opener.regForm.idDuplication.value = "ok";
	</script>
<% 
	}
%>
<a href="#" onclick="javascript:self.close()">닫기</a>
</body>
</html>