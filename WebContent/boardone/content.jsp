<%@page import="boardone.BoardVO"%>
<%@page import="boardone.BoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="view/color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<!-- 나중에 새글 답변글 구분하는 코드 추가 <1> -->
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	try {
		BoardDAO dbPro = BoardDAO.getInstance();
		if(request.getParameter("hit").equals("y")) dbPro.upHit(num);
		BoardVO article = dbPro.getArticle(num);
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
%>
<body bgcolor="<%= bodyback_c %>">
<center><b>글 내용 보기</b></center><br>
<form>
	<table border="1" align="center" bgcolor="<%= bodyback_c %>">
		<tr>
			<td width="125" bgcolor="<%= value_c %>" align="center">글번호</td>
			<td width="125" align="center">
				<%= article.getNum() %>
			</td>
			<td width="125" bgcolor="<%= value_c %>" align="center">조회수</td>
			<td width="125" align="center">
				<%= article.getReadcount() %>
			</td>
		</tr>
		<tr>
			<td width="125" bgcolor="<%= value_c %>" align="center">작성자</td>
			<td width="125" align="center">
				<%= article.getWriter() %>
			</td>
			<td width="125" bgcolor="<%= value_c %>" align="center">작성일</td>
			<td width="125" align="center">
				<%= sdf.format(article.getRegdate()) %>
			</td>
		</tr>
		<tr>
			<td width="125" bgcolor="<%= value_c %>" align="center">글제목</td>
			<td width="375" align="center" colspan="3">
				<%= article.getSubject() %>
			</td>
		</tr>
		<tr>
			<td width="125" bgcolor="<%= value_c %>" align="center">글내용</td>
			<td width="375" align="center" colspan="3">
				<pre><%= article.getContent() %></pre>
			</td>
		</tr>
		<tr>
			<td colspan="4" bgcolor="<%= value_c %>" align="right">
				<input type="button" value="글수정" 
					onclick="document.location.href='updateForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글삭제" 
					onclick="document.location.href='deleteForm.jsp?num=<%= article.getNum() %>&pageNum=<%= pageNum %>'">&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 수정 1 -->
				<input type="button" value="답글쓰기" 
					onclick="document.location.href='writeForm.jsp?num=<%= article.getNum()%>&ref=<%= ref %>&step=<%= step %>&depth=<%= depth %>'">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="글목록" onclick="window.location='list.jsp?pageNum=<%= pageNum %>'">
			</td>
		</tr>
	</table>
<%
	}catch(Exception e){}
%>
</form>
</body>
</html>