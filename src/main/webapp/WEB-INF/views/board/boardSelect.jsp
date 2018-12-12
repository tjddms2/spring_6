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
	var msg ='${msg}';
	if(msg !=''){
		alert(msg);
	}
	$("#del").click(function(){ 														//del을 만들고 클릭을 한다면
		$("#frm").submit(); 															//Frm을 누르면 submit을 이벤트 발생하도록 하겠습니다.
	});
});
</script>
</head>
<body>
	<h1>${board} Select</h1>
	
	<h3>title : ${dto.title}</h3>
	<h3>writer : ${dto.writer}</h3>
	<h3>contents : ${dto.contents}</h3>
	
	<!-- qna랑  notice로 가고싶을때, ${board}로 쓰면 둘중 하나만 가게 한다. -->
	<a href="./${board}List">List</a> 
	
	<a href="./${board}Update?num=${dto.num}">Update</a>
	
	<span id="del">Delete</span>
	
	<form id="frm" action="./${board}Delete" method="Post">
		<input type="hidden" value="${dto.num}" name="num">
		<!-- 버튼을 안만들고 스크립트를 만들면 된다! 
		그뒤에 부트스크랩? 이것도 참고하고 -->
	</form>
	
	<c:if test="${board ne 'notice' }">
	<a href="./${board}Reply?num=${dto.num}">reply</a>														<!-- ~한다면 if!! -->
	</c:if>
</body>
</html>