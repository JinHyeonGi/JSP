<%@page import="memberone.StudentDAO"%>
<%@ page import="memberone.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%-- <jsp:useBean id="dao" class="memberone.StudentDAO"/> --%>
<%
	StudentDAO dao = StudentDAO.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPDATE</title>
<link href="style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<%
	String loginID = (String)session.getAttribute("loginID");
	StudentVO vo = dao.getMember(loginID);
%>
<c:set var="phone1" value="<%= vo.getPhone1() %>"/>
<form name="regForm" method="post" action="modifyProc.jsp">
<table border="1">
	<tr>
		<td colspan="2" align="center">회원 수정 정보 입력</td>
	</tr>
	<tr>
		<td align="right">아이디: </td>
		<td><%= vo.getId() %></td>
	</tr>
	<tr>
		<td align="right">패스워드: </td>
		<td><input type="password" name="pass"></td>
	</tr>
	<tr>
		<td align="right">패스워드 확인: </td>
		<td><input type="password" name="repass"></td>
	</tr>
	<tr>
			<td align="right">이름: </td>
			<td><%= vo.getName() %></td>
		</tr>
		<tr>
			<td align="right">전화번호: </td>
			<td>
				<select name="phone1">
					<option value="02" <c:if test="${phone1 == '02'}">selected</c:if>>02</option>
					<option value="010" <c:if test="${phone1 == '010'}">selected</c:if>>010</option>
					<option value="011" <c:if test="${phone1 == '011'}">selected</c:if>>011</option>
					<option value="016" <c:if test="${phone1 == '016'}">selected</c:if>>016</option>
					<option value="017" <c:if test="${phone1 == '017'}">selected</c:if>>017</option>
					<option value="018" <c:if test="${phone1 == '018'}">selected</c:if>>018</option>
					<option value="019" <c:if test="${phone1 == '019'}">selected</c:if>>019</option>
				</select> -
				<%-- <input type="text" name="phone1" size="5" value="<%= vo.getPhone1() %>"> - --%>
				<input type="text" name="phone2" size="5" value="<%= vo.getPhone2() %>"> -
				<input type="text" name="phone3" size="5" value="<%= vo.getPhone3() %>">
			</td>
		</tr>
		<tr>
			<td align="right">이메일: </td>
			<td><input type="text" name="email" value="<%= vo.getEmail() %>"></td>
		</tr>
		<tr>
			<td align="right">우편번호: </td>
			<td>
				<input type="text" name="zipcode" value="<%= vo.getZipcode() %>">
				<input type="button" value="찾기" onclick="zipCheck()">
			</td>
		</tr>
		<tr>
			<td align="right">주소1: </td>
			<td><input type="text" name="address1" size="50" value="<%= vo.getAddress1() %>"></td>
		</tr>
		<tr>
			<td align="right">주소2: </td>
			<td><input type="text" name="address2" size="50" value="<%= vo.getAddress2() %>"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="정보수정" onclick="updateCheck()"/>
				<input type="button" value="취소" onclick="window.location='login.jsp'"/>
			</td>
		</tr>
</table>
</form>
</body>
</html>