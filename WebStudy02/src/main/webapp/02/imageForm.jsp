<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset='UTF-8'>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style type="text/css">
	form {
		background-color: yellow;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/image.do">
	<select name="image"> 
		${options}
	</select>
	<input type="submit" value="전송"/>
</form>
<div id="imageArea">
div 영역
</div>
<script type="text/javascript">
	let $imgArea = $(imageArea);
	$("select[name='image']").on('change',function(event){
// 		this // HtmlElement
// 		$(this) // JQuery
// 		$(this)[0] // HtmlElement
// 		this.form.submit(); // HtmlElement.submit()
		$(this.form).submit(); // JQuery.submit()
	});
	$("form:first").on("submit",function(){
		event.preventDefault();
		let imageName = $(this.image).val();
		let $imgTag = $("<img>").addClass("imgArea")
							.attr("src",`http://localhost/WebStudy01/image.do?image=\${imageName}`);
// 		$(".imgArea").remove();
		$imgArea.empty();
		$imgArea.append($imgTag);
		return false;
	});
</script>
</body>
</html>