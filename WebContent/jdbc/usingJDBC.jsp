<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String id = "";
	String passwd = "";
	String name = "";
	String mem_num1 = "";
	String mem_num2 = "";
	String e_mail = "";
	String phone = "";
	String zipcode = "";
	String address = "";
	String job = "";
	int counter = 0;
	try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from tempmember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFCC">
<h2>JSP 스크립틀릿에서 데이터베이스 연동</h2>
<h3>회원정보</h3>
<table border="1" bordercolor="#0000ff">
<tr>
	<td><strong>ID</strong></td>
	<td><strong>PASSWD</strong></td>
	<td><strong>NAME</strong></td>
	<td><strong>MEM_NUM1</strong></td>
	<td><strong>MEM_NUM2</strong></td>
	<td><strong>E_MAIL</strong></td>
	<td><strong>PHONE</strong></td>
	<td><strong>ZIPCODE/ADDRESS</strong></td>
	<td><strong>JOB</strong></td>
</tr>
<%
	if(rs != null) {
		while(rs.next()) {
			id = rs.getString("id");
			passwd = rs.getString("passwd");
			name = rs.getString("name");
			mem_num1 = rs.getString("mem_num1");
			mem_num2 = rs.getString("mem_num2");
			e_mail = rs.getString("e_mail");
			phone = rs.getString("phone");
			zipcode = rs.getString("zipcode");
			address = rs.getString("address");
			job = rs.getString("job");
%>
<tr>
	<td><%= id %></td>
	<td><%= passwd %></td>
	<td><%= name %></td>
	<td><%= mem_num1 %></td>
	<td><%= mem_num2 %></td>
	<td><%= e_mail %></td>
	<td><%= phone %></td>
	<td><%= zipcode %>/<%= address %></td>
	<td><%= job %></td>
</tr>
<%
			counter++;
		} // end while
	} // end if
%>
</table><br>
total records : <%= counter %>
<%
	}catch(SQLException sqlException) {
		sqlException.printStackTrace();
		System.out.println("sql exception");
	}catch(Exception exception) {
		System.out.println("exception");
	}finally {
		try {
			if(rs != null)
			rs.close();
		}catch(Exception e){}
		try {
			if(stmt != null)
			stmt.close();
		}catch(Exception e){}
		try {
			if(conn != null)
			conn.close();
		}catch(Exception e){}
	}
%>
</body>
</html>