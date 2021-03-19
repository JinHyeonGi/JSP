<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
	- JSP 내장객체 
	PageContext pageContext (다른 객체를 얻거나 흐름을 제어하는데 쓰는 객체)
	ServletContext application
	ServletConfig config
	HttpServletRequest request
	HttpServletResponse response
	JspWriter out
	HttpSession session (default는 사용 - 쓸지 안쓸지 정할 수 있다.) 
	Throwable exception (default는 사용 안함 - 쓸지 안쓸지 정할 수 있다.)
	Object page
	
	경로 : C:\myProject\pmJSP\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\myWeb\org\apache\jsp\sample
 -->
<%!
	private int numOne = 0; // 전역 -> 재사용된다. (쓰레드로 동작하기 때문에 다른 클라이언트가 접속해도 해당 쓰레드로 동작 => 초기화되지 않음)
	public void jspInit() {
		System.out.println("jspInit()");
	}
	public void jspDestroy() {
		System.out.println("jspDestroy()");
	}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int numTwo = 0; // 지역
	numOne++;
	numTwo++;
%>
<ul>
	<li>Number One : <%= numOne %></li>
	<li>Number Two : <%= numTwo %></li>
</ul>

<h1>구구단</h1>
<table border="1">
<%
	for(int i=1; i<10; i++) {
%>
	<tr>
<%
		for(int j=2; j<10; j++) {
%>
		<td><%= j %> x <%= i %> = <%= i*j %></td>
<%
		}
%>
	</tr>
<%
	}
%>
</table>
</body>
</html>