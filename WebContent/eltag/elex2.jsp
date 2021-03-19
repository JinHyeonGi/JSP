<%@page import="actiontag.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<String> singer = new ArrayList<>();
	singer.add("소녀시대");
	singer.add("원더걸스");
	request.setAttribute("singer", singer);
	
	Customer[] customer = new Customer[2];
	customer[0] = new Customer();
	customer[0].setName("손오공");
	customer[0].setEmail("son@hanmail.net");
	customer[0].setPhone("010-1234-5768");
	customer[1] = new Customer();
	customer[1].setName("홍길동");
	customer[1].setEmail("hong@hanmail.net");
	customer[1].setPhone("010-9876-5768");
	request.setAttribute("customer", customer);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<li>${singer[0]}, ${singer[1]}</li>
</ul>
<ul>
	<li>이름 : ${customer[0].name}</li>
	<li>메일 : ${customer[0].email}</li>
	<li>전화 : ${customer[0].phone}</li>
</ul>
<ul>
	<li>이름 : ${customer[1].name}</li>
	<li>메일 : ${customer[1].email}</li>
	<li>전화 : ${customer[1].phone}</li>
</ul>
</body>
</html>