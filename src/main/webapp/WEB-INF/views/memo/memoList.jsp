<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("btn").click(function(){
			$.get("./jtest1", function(data){ 			//이 부분 완성  시키기
				
			});
		});
		$.ajax({
			url : "./select",
			type : "GET",
			data : {
				num : 1
			},
			success : function(data) { 					//서버단이 다 뿌려주면 jsp에서 
				$.each(data,function(index, obj){
					alert(obj.wrter);					//html을 만들어서 넣어주삼
				});
				/*1. $("#list").html(data); */
				/*2. data = data.trim();
				data = JSON.parse(data); //data->파싱해서 바꿔주기
				alert(data.writer); */
				/*3. alert(data.writer); */
			}
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>MeMo </h1>
	<div id="list">
	</div>
	<button id="btn">JSON1</button>
</body>
</html>