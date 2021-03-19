<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="script.js"></script>
<script>
	function dongCheck() {
		if(document.zipForm.dong.value == "") {
			alert("동이름을 입력하세요");
			document.zipForm.dong.focus();
			return;
		}
		document.zipForm.submit();
	}
	
	function sendAddress(zipcode, sido, gugun, dong, ri, bunji) {
		console.log(zipcode, sido, gugun, dong, ri, bunji);
		var address = sido + " " + gugun + " " + dong + " " + ri + " " + bunji;
		opener.document.regForm.zipcode.value = zipcode;
		opener.document.regForm.address1.value = address;
		self.close();
	}
</script>
</head>
<body bgcolor="#FFFFCC">
<b>우편번호 찾기</b>
<form name="zipForm" method="post" action="member.mdo?cmd=zipCheck">
	<table>
		<tr>
			<td>동이름 입력:
				<input name="dong" type="text">
				<input type="button" value="검색" onclick="dongCheck()">
			</td>
		</tr>
	</table>
	<input type="hidden" name="check" value="n">
</form>
<table border="1">
	<c:if test="${check == 'n'}">
		<c:if test="${zipcodeList.isEmpty()}">
			<tr>
				<td align="center">검색된 결과가 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!zipcodeList.isEmpty()}">
			<tr>
				<td align="center">* 검색 후, 아래 우편번호를 클릭하면 자동으로 입력됩니다.</td>
			</tr>
			<c:forEach var="vo" items="${zipcodeList}">
				<tr>
					<td align="left">
						<a href="javascript:sendAddress('${vo.zipcode}', '${vo.sido}', '${vo.gugun}', 
						'${vo.dong}', '${vo.ri}', '${vo.bunji}')">
							${vo.zipcode}&nbsp;${vo.sido}&nbsp;${vo.gugun}&nbsp;${vo.dong}&nbsp;
							${vo.ri}&nbsp;${vo.bunji}
						</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</c:if>
	<tr>
		<td align="center">
			<a href="javascript:this.close();">닫기</a>
		</td>
	</tr>
</table>
</body>
</html>