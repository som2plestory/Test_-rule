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
<div id="input-form">
	<br><label for="transactionKey">1. 트랜잭션키</label><input type="text" id="transactionKey">
	<br><label for="interfaceId">2. 인터페이스ID</label><input type="text" id="interfaceId">
	<br><label for="reqOrgCode">3. 요청기관코드</label><input type="text" id="reqOrgCode">
	<br><label for="reqSysCode">4. 요청시스템코드</label><input type="text" id="reqSysCode">
	<br><label for="reqSysIp">5. 요청시스템IP</label><input type="text" id="reqSysIp">
	<br><label for="reqNodeId">6. 요청시스템NodeId</label><input type="text" id="reqNodeId">
	<br><label for="reqMsgDt">7. 전문요청일시</label><input type="text" id="reqMsgDt">
	<br><label for="resOrgCode">8. 응답기관코드</label><input type="text" id="resOrgCode">
	<br><label for="resSysCode">9. 응답시스템코드</label><input type="text" id="resSysCode">
	<br><label for="resSysIp">10. 응답시스템IP</label><input type="text" id="resSysIp">
	<br><label for="resNodeId">11. 응답시스템NodeId</label><input type="text" id="resNodeId">
	<br><label for="resMsgDt">12. 전문응답일시</label><input type="text" id="resMsgDt">
	<br><label for="resCode">13. 응답코드</label><input type="text" id="resCode">
	<br><label for="resMsg">14. 응답메세지</label><input type="text" id="resMsg">
	<br><label for="reqResFlag">15. 요청응답구분</label><input type="text" id="reqResFlag">
	<br><label for="innExtFlag">16. 대내대외구분</label><input type="text" id="innExtFlag">
	<br><label for="userNo">17. 사용자 ID</label><input type="text" id="userNo">
	<br><label for="brnNo">18. 영업점 코드</label><input type="text" id="brnNo">
	<br><label for="prodCd">19. 상품코드</label><input type="text" id="prodCd">
	<br><label for="custNo">20. 고객번호</label><input type="text" id="custNo">
	<br><label for="cnslNo">21. 상담번호</label><input type="text" id="cnslNo">
	<br><label for="loanNo">22. 대출번호</label><input type="text" id="loanNo">
	<br><label for="carNo">23. 차량번호</label><input type="text" id="carNo">
	<br><label for="vinNo">24. 차대번호</label><input type="text" id="vinNo">
	<br><label for="custResiNo">25. 주민번호</label><input type="text" id="custResiNo">
	<br><label for="traceId">26. traceId</label><input type="text" id="traceId">
	<br><label for="macNo">27. macNo</label><input type="text" id="macNo">
</div>
<br><br>
<div id=button-area>
	<button type="button" id="input-button">입력1</button> 
	<button type="button" id="input-button2">입력2</button> 
</div>
<br><br>
<div>결과</div>
<div id="output-form">
</div>
</body>

<script type="text/javascript">

$("#input-button").on("click", function(){
	console.log("입력 버튼 클릭")
	
	var appHeader = {
		 "transactionKey":"2022110317314374668640000"
		 ,"interfaceId":"IRCNN00001"
		 ,"reqOrgCode":""
		 ,"reqSysCode":""
		 ,"reqSysIp":"asdfasdf"
		 ,"reqNodeId":""
		 ,"reqMsgDt":"20221103173144400"
		 ,"resOrgCode":""
		 ,"resSysCode":""
		 ,"resSysIp":""
		 ,"resNodeId":""
		 ,"resMsgDt":""
		 ,"resCode":""
		 ,"resMsg":""
		 ,"reqResFlag":""
		 ,"innExtFlag":""
		 ,"userNo":"empNO"
		 ,"brnNo":""
		 ,"prodCd":""
		 ,"custNo":""
		 ,"cnslNo":""
		 ,"loanNo":""
		 ,"carNo":""
		 ,"vinNo":""
		 ,"custResiNo":""
		 ,"traceId":"!!!!!!!!!!!!!!!!!"
		 ,"macNo":""
	}
	
	console.log("appHeader: "+appHeader)
	
	$.ajax({
		url : "${pageContext.request.contextPath}/Msg/evalList",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(appHeader),
		dataType : "json",
		
		//Map<String, List<String>>
		success : function(evalList){
			console.log(evalList)
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
		}
	})
})

$("#input-button2").on("click", function(){
	console.log("입력 버튼 클릭")
	
	var appHeader = {
		 "transactionKey":"2022110317314374668640000"
		 ,"interfaceId":"IRCNN00001"
		 ,"reqOrgCode":""
		 ,"reqSysCode":""
		 ,"reqSysIp":"asdfasdf"
		 ,"reqNodeId":""
		 ,"reqMsgDt":"20221103173144400"
		 ,"resOrgCode":""
		 ,"resSysCode":""
		 ,"resSysIp":""
		 ,"resNodeId":""
		 ,"resMsgDt":""
		 ,"resCode":""
		 ,"resMsg":""
		 ,"reqResFlag":""
		 ,"innExtFlag":""
		 ,"userNo":"empNO"
		 ,"brnNo":""
		 ,"prodCd":""
		 ,"custNo":""
		 ,"cnslNo":""
		 ,"loanNo":""
		 ,"carNo":""
		 ,"vinNo":""
		 ,"custResiNo":""
		 ,"traceId":"!!!!!!!!!!!!!!!!!"
		 ,"macNo":""
	}
	
	console.log("appHeader: "+appHeader)
	
	$.ajax({
		url : "${pageContext.request.contextPath}/Msg/evalList2",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(appHeader),
		dataType : "json",
		
		//Map<String, List<String>>
		success : function(evalList){
			console.log(evalList)
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
		}
	})
})



</script>

</html>