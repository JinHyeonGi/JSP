<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b>request.getParameter() 메소드 사용</b>
name 파라미터 = <%= request.getParameter("name") %>
address 파라미터 = <%= request.getParameter("address") %>
<br>
<b>request.getParameterValues() 메소드 사용</b>
<%
	String[] values = request.getParameterValues("pet");
	if(values != null) {
		for(int i=0; i<values.length; i++) {
			
%>
	<%= values[i] %>
<%
		}
	}
%>
<br>
<!-- 
	getParameterNames , getParameterMap는 보낸데이터의 이름을 모를 때 사용한다. 
	getParameterNames는 name값만 가져온다. (checkbox같은 배열값은 첫값만 가져올 수 있다. 따라서 이럴때 Map을 사용!)
	getParameterMap로 값을 배열로 받을 수 있다.
-->
<b>request.getParameterNames() 메소드 사용</b>
<%
	Enumeration<String> enumData = request.getParameterNames();
	while(enumData.hasMoreElements()) {
		String name = enumData.nextElement();
%>
	<%= name %>, <%= request.getParameter(name) %>
<%
	}
%>
<br>
<b>request.getParameterMap() 메소드 사용</b>
<%
	// Map은 key값은 String(name으로 지정한 값), value값은 String배열로 받는다.
	Map<String, String[]> parameterMap = request.getParameterMap();
	
	for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
    	System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
    }
	
	String[] nameParam = parameterMap.get("name"); // 배열로 받아야 됨, 꺼내서 사용해야한다...
	if(nameParam != null) {
%>
	name = <%= nameParam[0] %>
<%
	}
%>
</body>
</html>