<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<jsp:useBean id="customer" class="actiontag.Customer"/>
<!-- * 속성을 사용해서 모든 매핑되는 파라미터를 한번에 받을 수 있다. -->
<jsp:setProperty name="customer" property="*"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>
			이름: <jsp:getProperty property="name" name="customer"/>
		</li>
		<li>
			이메일: <jsp:getProperty property="email" name="customer"/>
		</li>
		<li>
			전화: <jsp:getProperty property="phone" name="customer"/>
		</li>
	</ul>
</body>
</html>