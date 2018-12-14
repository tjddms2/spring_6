<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
var oEditors = [];
$(function(){								//로딩이 끝난다음에 시작하세요
      nhn.husky.EZCreator.createInIFrame({	//여기서 가지고오는 메소드를 호출합니다.
          oAppRef: oEditors,		
          //textarea의 ID
          elPlaceHolder: "contents",				
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "../resources/SE2/SmartEditor2Skin.html",  //상대경로로 쓰고 싶다면 : ../resources/ 절대경로로 쓰고 싶다면:${pageContext.request.contextPath}
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          } 
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();	//폼을 서브밋하자
      });    
});

</script>
</head>
<body>
<h1>${board} WRITE</h1>
	<!-- 현재폴더는 qna,notice -->
	
	<form id="frm"action="./${board}Write" method="Post" enctype="multipart/form-data">
	<input type="text" name="title">	
	<input type="text" name="writer">
	<textarea rows="" cols="" id="contents" name="contents"></textarea>
	<input type="button" value="ADD" id="btn">
		<div id="addfile">
		
		</div>
		<input type="button" id="save" value="WRITE">
	</form>
<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
</body>
</html>