function idCheck(id) {
	if(id == "") {
		alert("아이디를 입력해 주세요.");
		document.regForm.id.focus();
	}else {
		url="member.mdo?cmd=idCheck&id="+id;
		window.open(url, "post", "width=300, height=150");
	}
}

function zipCheck() {
	url="member.mdo?cmd=zipCheck&check=y";
	window.open(url, "post", "width=500, height=300, toolbar=no, directories=no, status=yes, scrollbars=yes, menubar=no");
}

function inputCheck() {
	var theForm = document.regForm;
	if(!theForm.id.value) {
		alert("아이디를 입력해 주세요.");
		theForm.id.focus();
		return;
	}
	if(!theForm.pass.value) {
		alert("비밀번호를 입력해 주세요.");
		document.regForm.pass.focus();
		return;
	}
	if(!theForm.repass.value) {
		alert("비밀번호를 확인해 주세요.");
		theForm.repass.focus();
		return;
	}
	if(theForm.repass.value != theForm.pass.value) {
		alert("비밀번호가 일치하지 않습니다");
		theForm.repass.focus();
		return;
	}
	if(!theForm.name.value) {
		alert("이름을 입력해 주세요.");
		theForm.name.focus();
		return;
	}
	if(!theForm.phone1.value) {
		alert("통신사를 입력해 주세요.");
		theForm.phone1.focus();
		return;
	}
	if(!theForm.phone2.value) {
		alert("전화번호를 입력해 주세요.");
		theForm.phone2.focus();
		return;
	}
	if(!theForm.phone3.value) {
		alert("전화번호를 입력해 주세요.");
		theForm.phone3.focus();
		return;
	}
	if(!theForm.email.value) {
		alert("이메일을 입력해 주세요.");
		theForm.email.focus();
		return;
	}
	
	 var str = theForm.email.value;
	 var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
     if(!reg_email.test(str)){
        alert('E-mail주소 형식이 잘못되었습니다.\n\r다시 입력해 주세요!');
		theForm.email.focus();
		return;         
     }                            
	
	if(!theForm.zipcode.value) {
		alert("우편번호를 입력해 주세요.");
		theForm.zipcode.focus();
		return;
	}
	if(!theForm.address1.value) {
		alert("주소를 입력해 주세요.");
		theForm.address1.focus();
		return;
	}
	if(!theForm.address2.value) {
		alert("세부주소 입력해 주세요.");
		theForm.address2.focus();
		return;
	}
	
	if(theForm.idDuplication.value.length == 0) {
		alert("아이디 중복검사를 해주세요.");
		theForm.id.focus();
		return;
	}
	
	theForm.submit();
}

function updateCheck() {
	if(document.regForm.pass.value == "") {
		alert("비밀번호를 입력해 주세요.");
		document.regForm.pass.focus();
		return;
	}
	if(document.regForm.repass.value == "") {
		alert("비밀번호를 확인해 주세요.");
		document.regForm.repass.focus();
		return;
	}
	if(document.regForm.repass.value != document.regForm.pass.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.regForm.repass.focus();
		return;
	}
	if(document.regForm.phone1.value == "") {
		alert("통신사를 입력해 주세요.");
		document.regForm.phone1.focus();
		return;
	}
	if(document.regForm.phone2.value == "") {
		alert("전화번호를 입력해 주세요.");
		document.regForm.phone2.focus();
		return;
	}
	if(document.regForm.phone3.value == "") {
		alert("전화번호를 입력해 주세요.");
		document.regForm.phone4.focus();
		return;
	}
	if(document.regForm.email.value == "") {
		alert("이메일을 입력해 주세요.");
		document.regForm.email.focus();
		return;
	}
	
	 var str = document.regForm.email.value;
	 var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
     if(reg_email.test(str));
     else {                       
        alert('E-mail주소 형식이 잘못되었습니다.\n\r다시 입력해 주세요!');
		document.regForm.email.focus();
		return;         
     }                            
	
	if(document.regForm.zipcode.value == "") {
		alert("우편번호를 입력해 주세요.");
		document.regForm.zipcode.focus();
		return;
	}
	if(document.regForm.address1.value == "") {
		alert("주소를 입력해 주세요.");
		document.regForm.address1.focus();
		return;
	}
	if(document.regForm.address2.value == "") {
		alert("세부주소 입력해 주세요.");
		document.regForm.address2.focus();
		return;
	}
	
	document.regForm.submit();
}