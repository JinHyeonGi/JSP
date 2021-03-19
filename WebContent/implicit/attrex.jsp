<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- # Scope -->
<%
	request.setCharacterEncoding("utf-8");
	
	String name = request.getParameter("name");
	String tel = request.getParameter("tel");
	String email = request.getParameter("email");
	String company = request.getParameter("company");
	//pageContext Scope : 현재 페이지 영역
	pageContext.setAttribute("pageAttribute", name);
	//pageContext.setAttribute("pageAttribute", "홍길동", PageContext.PAGE_SCOPE);

	//request Scope : 한번의 요청과 응답 영역
	request.setAttribute("requestAttribute", tel);
	//pageContext.setAttribute("pageAttribute", "010-1234-5678", PageContext.REQUEST_SCOPE);
	
	//Session Scope : 브라우저 영역 (브라우저의 종료 전까지)
	session.setAttribute("sessionAttribute", email);
	//pageContext.setAttribute("pageAttribute", "hong@hong.com", PageContext.SESSION_SCOPE);
	
	//Application Scope : 애플리케이션 영역 (애플리케이션의 종료 전까지 유지)
	application.setAttribute("applicationAttribute", company);
	//pageContext.setAttribute("pageAttribute", "KG Eduone", PageContext.APPLICATION_SCOPE);
	
	response.sendRedirect("attrex3.jsp");
	//RequestDispatcher view = request.getRequestDispatcher("attrex3.jsp");
	//view.forward(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <ul>
	<li>이름 : <%= pageContext.getAttribute("pageAttribute") %></li>
	<li>전화 : <%= request.getAttribute("requestAttribute") %></li>
	<li>메일 : <%= session.getAttribute("sessionAttribute") %></li>
	<li>회사 : <%= application.getAttribute("applicationAttribute") %></li>
</ul> --%>
</body>
</html>