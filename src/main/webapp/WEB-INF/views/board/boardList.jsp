<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var msg='${msg}';		//데이터가 있을때,
if(msg !=''){
	alert(msg);
}
</script>
</head>
<body>
	<h1>${board}List</h1>
	
	<table>
		<tr>
			<td>NUM</td><td>title</td><td>writer</td><td>Contents</td><td>Date</td><td>hit</td>							
			
			</tr>
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.num}</td>
				<td><a href="${board}Select?num=${dto.num}">
				<c:catch><c:forEach begin="1" end="${dto.depth}">--</c:forEach></c:catch>
				${dto.title}</a></td>
				<td>${dto.writer}</td>
				<td>${dto.reg_date}</td>
				<td>${dto.hit}</td>
			</tr>
			</c:forEach>
		
	</table>
	<c:if test="${pager.curBlock>1}">		<!-- 2페이지니까 6-1 -->
	<a href="./${board}List?curPage=${pager.startNum-1}">[이전]</a>
	</c:if>
	<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
	<a href="./${board}List?curPage=${i}">${i}</a>
	</c:forEach>	
	<c:if test="${pager.curBlock<pager.totalBlock}">
	<a href="./${board}List?curPage=${pager.lastNum+1}">[다음]</a>
	</c:if>
	
	<!-- qna,notice 둘 중 하나가 온다  -->
	<!-- 두개의 리스트주소가 와야하는데,${board}를 쓰면 qna->qna, notice->notice로 오게된다.! -->
	<a href="./${board}List">List</a>
	<a href="./${board}Write">Write</a>
</body>
</html>