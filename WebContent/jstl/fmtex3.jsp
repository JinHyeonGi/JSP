<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
number : <fmt:formatNumber value="12345.678" type="number" /> <br>
currency : <fmt:formatNumber value="12345.678" type="currency" currencySymbol="\\" />
percent : <fmt:formatNumber value="12345.678" type="percent" /> <br>
pattern(.0) : <fmt:formatNumber value="12345.678" pattern=".0"/> <br>
<c:set var="now" value="<%= new java.util.Date() %>"/>
<c:out value="${now}"/>
date : <fmt:formatDate value="${now}" type="date"/>
time : <fmt:formatDate value="${now}" type="time"/>
both : <fmt:formatDate value="${now}" type="both"/>
</body>
</html>
