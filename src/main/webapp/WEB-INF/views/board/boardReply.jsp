<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${board} Reply</h1>

<form action="./${board}reply" method="Post">
<input type="hidden" name="num" value="${num}">
	<input type="text" name="title">
	<input type="text" name="writer">
	<textarea  name="Contents" rows="" cols=""></textarea>
	<button>Reply</button>
</form>
</body>
</html>