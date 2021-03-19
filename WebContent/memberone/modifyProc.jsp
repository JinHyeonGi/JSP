<%@page import="memberone.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%-- <jsp:useBean id="dao" class="memberone.StudentDAO"/> --%>
<%
	StudentDAO dao = StudentDAO.getInstance();
%>
<jsp:useBean id="vo" class="memberone.StudentVO"/>
<jsp:setProperty name="vo" property="*"/>
<%
	String loginID = (String)session.getAttribute("loginID");
	vo.setId(loginID);
	dao.updateMember(vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- meta : 구성요소 -->
<meta http-equiv="Refresh" content="3;url=login.jsp">
<title>회원가입 처리</title>
</head>
<body>
	입력하신 내용대로 <b>회원정보가 수정되었습니다.</b><br>
	3초후에 Login Page로 이동합니다.
</body>
</html>