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
<div>
	<c:choose>
		<c:when test="${allTables == null || allColumns == null}">
			<a href="${pageContext.request.contextPath}/this/tableInfo"><button id="tableInfo" type="button">테이블정보불러오기</button></a>
		</c:when>
		<c:otherwise>HttpSession < 테이블 정보 있음</c:otherwise>
	</c:choose>
</div>
<br><br>

<div>
	<button id="validation" type="button">유효성검사</button>
</div>
<br><br>
<div id="input-form">
	<div>
		<label for="number">입력라인수</label>
		<input id="number" type="text" name="number">
	</div>
	
	<form id="input3" action ="${pageContext.request.contextPath}/test/input3" method="post">
		<table border="1" id="dataTable">
			<thead>
				<tr>
					<th style="width: 10%;">no.</th>
					<th style="width: 25%;">key1</th>
					<th style="width: 20%;">key2</th>
					<th style="width: 25%;">key3</th>
					<th style="width: 20%;">key4</th>
				</tr>
			</thead>
			<tbody >
				<tr id="data-0">
					<td>1</td>
					<td><input type="text" name="key1"></td>
					<td><input type="text" name="key2"></td>
					<td><input type="text" name="key3"></td>
					<td><input type="text" name="key4"></td>
				</tr>
				<tr id="data-1">
					<td>2</td>
					<td><input type="text" name="key1"></td>
					<td><input type="text" name="key2"></td>
					<td><input type="text" name="key3"></td>
					<td><input type="text" name="key4"></td>
				</tr>
				<tr id="data-2">
					<td>3</td>
					<td><input type="text" name="key1"></td>
					<td><input type="text" name="key2"></td>
					<td><input type="text" name="key3"></td>
					<td><input type="text" name="key4"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<br>
<div>
	<button type="button" id="input-button">입력:String</button>
	<button type="button" id="input2-button">입력2:Class</button>
	<button type="button" id="input3-button">입력3</button>
</div>
<br>

<div id="appHeader"></div>

<br>


<div id="eval"></div>

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
	
	var n = $("[name = 'number']").val()
	
	var eval = []
	for(var i=0; i<n; i++){
		var keys = {
			key1 : $("#data-"+i+" [name = 'key1']").val(),
			key2 : $("#data-"+i+" [name = 'key2']").val(),
			key3 : $("#data-"+i+" [name = 'key3']").val(),
			key4 : $("#data-"+i+" [name = 'key4']").val()
		}
		
		eval.push(keys)
	}
	
	var inputMap = {
		appHeader : appHeader,
		eval : eval
	}
	
	console.log("inputMap: "+inputMap)
	
	$.ajax({
		url : "${pageContext.request.contextPath}/Msg/input",
		type : "post",
		contentType : "application/text",
		data : JSON.stringify(inputMap),
		dataType : "json",
		
		//Map<String, List<String>>
		success : function(result){
			//console.log("result: "+result)
			var obj = JSON.parse(result)
			console.log(obj)
			appHeader = obj.appHeader
			eval = obj.eval
			//console.log("result_appHeader: "+appHeader)
			//console.log("result_eval: "+eval)
			
			render_a(appHeader)
			
			for(var i=0; i<eval.length; i++){
				render_b(eval[i], i)
			}
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
		}
	})
	
})


function render_a(appHeader){
	var str = '<table border="1">'
	str	+= '	<thead>'
	str	+= '		<tr>'
	str	+= '			<th colspan="2">appHeader</th>'
	str	+= '		</tr>'
	str	+= '	</thead>'
	str	+= '	<tbody>'

	for(key in appHeader){
		str += '		<tr>'
		str += '			<td>'+key+'</td>'
		str += '			<td>'+appHeader[key]+'</td>'
		str += '		</tr>'
	}
	
	str += '	</tbody>'
	str	+= '</table>'
		
	$("#appHeader").append(str)
}

function render_b(eval, i){
	var str = '<br>' 
	str += '<table border="1">'
	str += '	<thead>'
	str += '		<tr>'
	str += '			<th colspan="2"> eval '+i+'</th>'
	str += '		</tr>'
	str += '	</thead>'
	str += '	<tbody>'

	for(key in eval){
		str += '	<tr>'
		str += '		<td>'+key+'</td>'
		str += '		<td>'+eval[key]+'</td>'
		str += '	</tr>'
	}
	
	str += '	</tbody>'
	str += '</table>'
	
	$("#eval").append(str)
}


$("#input2-button").on("click", function(){
	console.log("입력2 버튼 클릭")
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
	
	var n = $("[name = 'number']").val()
	
	var eval = []
	for(var i=0; i<n; i++){
		var keys = {
			key1 : $("#data-"+i+" [name = 'key1']").val(),
			key2 : $("#data-"+i+" [name = 'key2']").val(),
			key3 : $("#data-"+i+" [name = 'key3']").val(),
			key4 : $("#data-"+i+" [name = 'key4']").val()
		}
		
		eval.push(keys)
	}
	
	var inputMap = {
		appHeader : appHeader,
		eval : eval
	}
	
	console.log("inputMap_appHeader: "+inputMap.appHeader)
	console.log("inputMap_eval: "+inputMap.eval)
	
	$.ajax({
		//url : "${pageContext.request.contextPath}/Msg/input2",
		url : "${pageContext.request.contextPath}/this/validation",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(inputMap),
		dataType : "json",
		
		success : function(result){
			if(result.success=="ok"){
				console.log("result: "+result)
				appHeader = result.appHeader
				eval = result.eval
				console.log("result_appHeader: "+appHeader)
				console.log("result_eval: "+eval)
				
				render_a(appHeader)
				
				for(var i=0; i<eval.length; i++){
					render_b(eval[i], i)
				}
				
			}else{
				alert(result.success)
			}
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
		}
	})
})


/*
$("#validation").on("click", function(){
	console.log("유효성검사 버튼 클릭")
	
	var n = $("[name = 'number']").val()
	
	var eval = []
	for(var i=0; i<n; i++){
		var keys = {
			key1 : $("#data-"+i+" [name = 'key1']").val(),
			key2 : $("#data-"+i+" [name = 'key2']").val(),
			key3 : $("#data-"+i+" [name = 'key3']").val(),
			key4 : $("#data-"+i+" [name = 'key4']").val()
		}
		
		eval.push(keys)
	}
	
	var inputMap = {eval : eval}
	console.log("inputMap_eval: "+inputMap.eval)
	
	$.ajax({
		url : "${pageContext.request.contextPath}/Msg/validation",
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(inputMap),
		dataType : "json",
		
		success : function(result){
			console.log("result: "+result)
		}, 
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error)
		}
	})
})
*/

</script>
</html>