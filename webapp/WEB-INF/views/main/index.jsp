<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<title>Test</title>
</head>
<body>

<div id="test">
	<input type="text" name="name" placeholder="�̸�">
	<input type="text" name="nickname" placeholder="�г���">
	<input type="text" name="phone" placeholder="��ȣ">
	<button type="submit" id="testButton1">TEST1</button>
	<button type="submit" id="testButton2">TEST2</button>
	<button type="submit" id="testButton3">TEST3</button>
	<button type="submit" id="testButton4">TEST4</button>
</div>

</body>

<script type="text/javascript">

/* 1��Ŭ�� */
$("#testButton1").on("click", function(){
	console.log("1��Ŭ��")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("�̸� �Է¾ȵ�")
		return false
	}
	
	if(nickname==""){
		alert("�г��� �Է¾ȵ�")
		return false
	}
	
	if(phone==""){
		alert("��ȣ �Է¾ȵ�")
		return false
	}
	
	
	var info = []
	info.push(name)
	info.push(nickname)
	info.push(phone)
	
	console.log(info)
	
	$.ajax({
		
		url : "${pageContext.request.contextPath}/test/test1",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(info),
		dataType : "json",
		
		success : function(result){
			console.log(result)
			
			if(!confirm("�̸�: " + result[0])){
				return false
			}
			
			if(!confirm("�г���: " + result[1])){
				return false
			}
			
			if(!confirm("��ȣ: " + result[2])){
				return false
			}
			
			alert("�׽�Ʈ Ȯ�� �Ϸ�")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})


/* 2��Ŭ�� */
$("#testButton2").on("click", function(){
	console.log("2��Ŭ��")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("�̸� �Է¾ȵ�")
		return false
	}
	
	if(nickname==""){
		alert("�г��� �Է¾ȵ�")
		return false
	}
	
	if(phone==""){
		alert("��ȣ �Է¾ȵ�")
		return false
	}
	
	
	var info = []
	info.push(name)
	info.push(nickname)
	info.push(phone)
	
	console.log(info)
	
	$.ajax({
		
		url : "${pageContext.request.contextPath}/test/test2",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(info),
		dataType : "json",
		
		success : function(result){
			console.log(result)
			
			if(!confirm("�̸�: " + result[0])){
				return false
			}
			
			if(!confirm("�г���: " + result[1])){
				return false
			}
			
			if(!confirm("��ȣ: " + result[2])){
				return false
			}
			
			alert("�׽�Ʈ Ȯ�� �Ϸ�")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})


/* 3��Ŭ�� */
$("#testButton3").on("click", function(){
	console.log("3�� Ŭ��")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("�̸� �Է¾ȵ�")
		return false
	}
	
	if(nickname==""){
		alert("�г��� �Է¾ȵ�")
		return false
	}
	
	if(phone==""){
		alert("��ȣ �Է¾ȵ�")
		return false
	}
	
	
	var info = {
		name: name,
		nickname: nickname,
		phone: phone
	}
	
	console.log(info)
	
	$.ajax({
		
		url : "${pageContext.request.contextPath}/test/test3",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(info),
		dataType : "json",
		
		success : function(result){
			console.log(result)
			
			if(!confirm("�̸�: " + result.name)){
				return false
			}
			
			if(!confirm("�г���: " + result.nickname)){
				return false
			}
			
			if(!confirm("��ȣ: " + result.phone)){
				return false
			}
			
			alert("�׽�Ʈ Ȯ�� �Ϸ�")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})


/* 4��Ŭ�� */
$("#testButton4").on("click", function(){
	console.log("4�� Ŭ��")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("�̸� �Է¾ȵ�")
		return false
	}
	
	if(nickname==""){
		alert("�г��� �Է¾ȵ�")
		return false
	}
	
	if(phone==""){
		alert("��ȣ �Է¾ȵ�")
		return false
	}
	
	
	var info = []
	var infoArr = {
		name: name,
		nickname: nickname,
		phone: phone	
	}
	info.push(infoArr)

	console.log(info)
	
	$.ajax({
		
		url : "${pageContext.request.contextPath}/test/test4",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(info),
		dataType : "json",
		
		success : function(result){
			console.log(result)
			
			if(!confirm("���: " + result[0])){
				return false
			}
			
			if(!confirm("�̸�: " + result[0].name)){
				return false
			}
			
			if(!confirm("�г���: " + result[0].nickname)){
				return false
			}
			
			if(!confirm("��ȣ: " + result[0].phone)){
				return false
			}
			
			alert("�׽�Ʈ Ȯ�� �Ϸ�")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})
</script>
</html>