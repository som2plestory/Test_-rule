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
	<input type="text" name="name" placeholder="이름">
	<input type="text" name="nickname" placeholder="닉네임">
	<input type="text" name="phone" placeholder="번호">
	<button type="submit" id="testButton1">TEST1</button>
	<button type="submit" id="testButton2">TEST2</button>
	<button type="submit" id="testButton3">TEST3</button>
	<button type="submit" id="testButton4">TEST4</button>
</div>

</body>

<script type="text/javascript">

/* 1번클릭 */
$("#testButton1").on("click", function(){
	console.log("1번클릭")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("이름 입력안됨")
		return false
	}
	
	if(nickname==""){
		alert("닉네임 입력안됨")
		return false
	}
	
	if(phone==""){
		alert("번호 입력안됨")
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
			
			if(!confirm("이름: " + result[0])){
				return false
			}
			
			if(!confirm("닉네임: " + result[1])){
				return false
			}
			
			if(!confirm("번호: " + result[2])){
				return false
			}
			
			alert("테스트 확인 완료")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})


/* 2번클릭 */
$("#testButton2").on("click", function(){
	console.log("2번클릭")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("이름 입력안됨")
		return false
	}
	
	if(nickname==""){
		alert("닉네임 입력안됨")
		return false
	}
	
	if(phone==""){
		alert("번호 입력안됨")
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
			
			if(!confirm("이름: " + result[0])){
				return false
			}
			
			if(!confirm("닉네임: " + result[1])){
				return false
			}
			
			if(!confirm("번호: " + result[2])){
				return false
			}
			
			alert("테스트 확인 완료")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})


/* 3번클릭 */
$("#testButton3").on("click", function(){
	console.log("3번 클릭")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("이름 입력안됨")
		return false
	}
	
	if(nickname==""){
		alert("닉네임 입력안됨")
		return false
	}
	
	if(phone==""){
		alert("번호 입력안됨")
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
			
			if(!confirm("이름: " + result.name)){
				return false
			}
			
			if(!confirm("닉네임: " + result.nickname)){
				return false
			}
			
			if(!confirm("번호: " + result.phone)){
				return false
			}
			
			alert("테스트 확인 완료")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})


/* 4번클릭 */
$("#testButton4").on("click", function(){
	console.log("4번 클릭")
	
	var name = $("[name='name']").val()
	var nickname = $("[name='nickname']").val()
	var phone = $("[name='phone']").val()
	
	if(name==""){
		alert("이름 입력안됨")
		return false
	}
	
	if(nickname==""){
		alert("닉네임 입력안됨")
		return false
	}
	
	if(phone==""){
		alert("번호 입력안됨")
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
			
			if(!confirm("결과: " + result[0])){
				return false
			}
			
			if(!confirm("이름: " + result[0].name)){
				return false
			}
			
			if(!confirm("닉네임: " + result[0].nickname)){
				return false
			}
			
			if(!confirm("번호: " + result[0].phone)){
				return false
			}
			
			alert("테스트 확인 완료")
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
			
		}
		
	})
	
})
</script>
</html>