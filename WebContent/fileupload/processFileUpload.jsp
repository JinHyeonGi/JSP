<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	if(FileUpload.isMultipartContent(request)) {
		String temporaryDir = "C:\\temp";
		DiskFileUpload fileUpload = new DiskFileUpload();
		fileUpload.setRepositoryPath(temporaryDir);
		// 최대 1메가 까지 없로드
		fileUpload.setSizeMax(1024*1024);
		// 최대 100k까지는 메모리에 저장
		fileUpload.setSizeThreshold(1024*100);
		
		List<FileItem> fileItemList = fileUpload.parseRequest(request);
		for(int i=0; i<fileItemList.size(); i++) {
			FileItem fileItem = (FileItem)fileItemList.get(i);
			if(fileItem.isFormField()) {
%>
	폼 파라미터: <%= fileItem.getFieldName() %> = <%= fileItem.getString("utf-8") %><br>
<%
			}else {
%>
	파일: <%= fileItem.getFieldName() %> = <%= fileItem.getName() %>(<%= fileItem.getSize() %> bytes)<br>
			<%	if(fileItem.isInMemory()) { %>
				메모리에 저장<br>
			<% } else { %>
				디스크에 저장<br>
			<% } %>
<%
					String filePath = application.getRealPath("upload");
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
					String fileName = fileItem.getName().substring(idx + 1);
					try {
						File uploadedFile = new File(filePath, fileName);
						fileItem.write(uploadedFile);
					}catch(Exception e){
						
					}
				}
			}
		}
	}else {
%>
	인코딩 타입이 multipart/form-data가 아님.
<%
	}
%>
</body>
</html>