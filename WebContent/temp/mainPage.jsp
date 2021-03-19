<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("PAGETITLE", "정보보기");
%>
<jsp:forward page="/temp/template/template.jsp">
	<jsp:param name="CONTENTPAGE" value="info_view.jsp" />
</jsp:forward>

<%-- 
	# jsp:include vs include
	
	jsp:include는 필요할 때 가져와 쓰는 각각의 모듈 개념, 동적인 처리가 가능하다. 
	
	include는 정적인 페이지 개념으로 하나로 합쳐진 후 컴파일 된다. (결국 같은 페이지)
--%>