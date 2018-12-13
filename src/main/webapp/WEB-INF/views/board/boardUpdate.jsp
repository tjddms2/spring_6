<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	
	var count =${files.size()};
	var index= 0;
	$("#addFile").on("click",".del",function(){
		$("#"+f).remove();
		count--;
	});
	$("#btn").click(function() {
		if(count<5){
			var file='<div id="a'+index+'"><input type="file" name="f1"><span title="a'+index+'" class="del">X</span></div>';
			$("#addFile").append(file);
			/* var f = $("#f").html();
			$("#addFile").append(f); */
			count++;
			index++;
		}else {
			alert('파일은 최대 5개');
		}
	});
	$(".files").click(function() {
		var id=$(this).attr("id");
		var del=$(this).attr("title");
		$.ajax({
			url:"../file/delete",
			type:"GET",
			data:{
				fnum:id
			},
			success:function(data){
				data=data.trim();
				if(data==1){
					$("#"+del).remove();
					count--;
				}else {
					alert("File Delete Fail");
				}
			}
		});
	});
});
</script>
<style type="text/css">
.files,.del{
	color: red;
	cursor:pointer;
}
#f{
display:none;
}

</style>
</head>
<body>
<h1>${board} Update</h1>
<form action="./${board}Update" method="Post">
	<input type="hidden" name="num" value="${dto.num}">
	<input type="text" name="title" value="${dto.title}">
	<input type="text" name="writer" value="${dto.writer}">
	<textarea name="Contents" rows="" cols="">${dto.contents}</textarea>
	
	
	<div id="addfile">
	
	</div>
	<div>
		<c:forEach items="${files}" var="file" varStatus="i">
			<div id="f${i.index}">
				<span>${file.oname}</span><span title="f${i.index}" class="files" id="${file.fnum}">X</span>
			</div>	
		</c:forEach>
	</div>
	<input type="button" id="btn" value="ADD">
	<button>update</button>
</form>
<!-- 	<div id="f">
		<div id="a1">
		<input type="file" name="f1"><span>X</span>
		</div>
	</div> -->

</body>
</html>