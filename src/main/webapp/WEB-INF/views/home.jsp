<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
		
		 $("#frm").click(function(){
			 var f = $("#f")[0];
			 var formDate = new FormDate(f);
			 
		$.ajax({
			url:"./notice/noticewrite", 
			type: "POST",
			processData:false,
			contentType:false,
			enctype:"multipart/form-data",
			data:formData
			/* data: {
				title:$("#title").val(),
				write:$("#write").val().
				contents:$("#contents").val()
			}, */
			success():function(data){
				alert(data);
			},
			error:function(xhr){
				alert(xhr.status);
			}
		}); 
		});
});
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<div>
	<form action="./notice/noticeWrite" method="POST">	<!-- //Ajax로 통해서~ -->
		<input type="text" id="write" name="write">
		<input type="text" id="title" name="title">
		<input type="file" name="f1">
		<textarea id="contents" rows="" cols="" name="contents"></textarea>
		<input type="button" value="write" id="frm">  
	</form>
	
</div>

<P>  The time on the server is ${serverTime}. </P>
<a href="./notice/noticeList">noticeList</a>
<a href="./qna/qnaList">QnaList</a>
<a href="./memo/memoList">Memo</a>

<%-- <c:choose>
	<c:when test="${not empty member}">
		<p>
			<a href="./member/logOut">logout</a>
			<a href="./member/myPage">myPage</a>
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<a href="./member/join">Join</a>
			<a href="./member/login">login</a>
		</p>
	</c:otherwise>
</c:choose> --%>
</body>
</html>
