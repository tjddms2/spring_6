/**
 * 
 */

			var count=0;
			var index=0;
			
			function setCount(c) {
				count=c;
			}
			
			$("#addFile").on("click",".del", function() {
				var f = $(this).attr("title");
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