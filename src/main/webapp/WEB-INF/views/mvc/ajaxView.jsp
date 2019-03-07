<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajaxView</title>
<script src="${cp}/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("ajaxView.jsp");
		$("#jsonReqBtn").on("click",function(){
			//jsonView();
 			//responseBody();
			requestBody();
		});
		
	})
	
	function requestBody(){
		var data = {userId : "brown", userNm : "브라운"};
		$.ajax({
			url : "${cp}/ajax/requestBody",
			method : "post",
	 		//data : "userId=brown&userNm=브라운",
			//data : $("#frm").serialize(),
			data : JSON.stringify(data),
			dataType : "json",	//server에게 희망하는 리턴타입을 명시
			contentType : "application/json; charset=utf-8", 	//client 전송하는 데이터 타입
			success : function(data){
				console.log("data : "+data);
				$("#jsonRecvTbody").html("<tr>"+"<td>"+data.userId+"</td>"+"</tr>");
// 				var totalHtml="";
// 				for(var i =0; i < data.length; i++){
// 					totalHtml+="<tr>"+"<td>"+data[i]+"</td>"+"</tr>";
// 				}
// 				$("#jsonRecvTbody").html(totalHtml);
			}
		});
	};
	
	function responseBody(){
		$.ajax({
			url : "${cp}/ajax/responseBody",
			method : "post",
			dataType : "json",	//server에게 희망하는 리턴타입을 명시
			success : function(data){
				console.log("data : "+data);
				var totalHtml="";
				for(var i =0; i < data.length; i++){
					totalHtml+="<tr>"+"<td>"+data[i]+"</td>"+"</tr>";
				}
				$("#jsonRecvTbody").html(totalHtml);
			}
		});
	};
	
	function jsonView(){
		$.ajax({
			url : "${cp}/ajax/jsonView",
			method : "post",
			success : function(data){
				console.log("data : "+data);
				var totalHtml="";
				for(var i =0; i < data.rangerList.length; i++){
					totalHtml+="<tr>"+"<td>"+data.rangerList[i]+"</td>"+"</tr>";
				}
				$("#jsonRecvTbody").html(totalHtml);
			}
		});
	};
	
</script>
</head>
<body>
	<form id="frm">
		<input type="text" name="userId" value="brown"/>
		<input type="text" name="userNm" value="브라운"/>
	</form>
	<h2>ajaxView.jsp</h2>
	<h3>json 수신</h3>
	<div>
		<button id="jsonReqBtn">jsonData 요청</button>
		<div id="jsonRecv">
			<table border="1">
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				<tbody id="jsonRecvTbody">
					
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>