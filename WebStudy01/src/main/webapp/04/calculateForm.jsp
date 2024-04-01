<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<!-- 비동기 처리 기반의 사칙연산기. -->
<label><input type="radio" name="dataType" value="json" />JSON</label>
<label><input type="radio" name="dataType" value="xml" />XML</label>
<label><input type="radio" name="dataType" value="html" />HTML</label>
<form id="calculatorForm" action="<%=request.getContextPath() %>/calculate.do">
	<input type="number" name="leftOp" />
	<select name="operator">
		<option value>연산자</option>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" value="="/>
<div id="resultArea">
</div>
 <script type="text/javascript">
 let resultFromServer = '<%= request.getAttribute("result") %>';
 $("#resultArea").html(resultFromServer);
 	let successes = {
			json:function(resp){
			 	$("#resultArea").html(resp.expression);	
		    },
			html:function(resp){
				$("#resultArea").html(resp);
			},
			xml:function(resp){
				$("#resultArea").html(resp);
			}
	}
 	
// 	let $resultArea = $(resultArea);
 	
 	$("#calculatorForm").submit(function (event) { 
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let queryString = $(this).serialize();
		let settings = {
				url:url,
				method:method,
				data:queryString,
				error:function(jqxhr,status,err){
				}
			};
		
		settings.dataType = $("input[name=dataType]:checked").val() ?? "html";
// 		console.log(dataType);
		settings.success = successes[settings.dataType];
		$.ajax(settings);
		return false;
	});
    </script>
</form>
</body>
</html>