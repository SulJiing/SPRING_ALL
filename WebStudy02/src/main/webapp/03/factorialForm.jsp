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
<!-- ex) 3! = 6 -->
<!-- 1. operand라는 유일 파라미터(1이상의 양수)를 입력받고(factorialFoem.jsp), -->
<!-- 	유효하지 않는 파라미터에 대해서는 bad request를 응답으로 전송함. -->
<!-- 2. factorial.jsp를 이용해 파라미터를 받고 재귀호출(recursive call) 구조를 활용하여 팩토리얼 연산 처리. -->
<!-- 3. 해당 연산의 결과는 다음과 같은 메세지로 출력 -->

	<h4>3! = 6</h4>
	<!-- onsubmit="return submitHandler(event);" -->
<input type="radio" name="accept" value="json">json
<input type="radio" name="accept" value="html" checked>html
<form action="<%=request.getContextPath() %>/case2/factorial.do">
<input type="number" name="operand" min="1" placeholder="3!" method="get"/>
<button type="submit">전송</button>
</form>
<div id="resultArea"></div>
<script type="text/javascript">
// function submitHandler(event) {
// 	event.preventDefault();
// 	return flase;
// }
	// 라디오 버튼으로 데이터 타입 바꾸기 다른 방법
	let successes = {
			json:function(resp){
				$resultArea.html(resp.expression);
			},
			html:function(resp){
				$resultArea.html(resp);
			}
	}
	let $resultArea = $(resultArea);
	$("form:first").on('submit',function(event){ // 첫번째 form태그
		event.preventDefault(); // 안전하게 한번 더 중단
		// 동기요청을 비동기요청으로 전환
		let url = this.action;
		let method = this.method;
		let queryString = $(this).serialize();
		console.log("serialized query string : ",queryString);
		let settings = {
				url:url,
				method:method,
				data:queryString, // 서버로 전송할 데이터 "key1=value1&key2=value2"
				// => query String 형태의 문자열로 전환됨.(serializing)
				// 여기까지 요청에 대한 설정
				error:function(jqxhr,status,err){
				}
			};
		
		settings.dataType = $("input[name=accept]:checked").val() ?? "html";
// 		settings.dataType = $("input[name=accept]:checked").val() ? $("input[name=accept]:checked").val() : "html",
		settings.success = successes[settings.dataType];
		$.ajax(settings);
		return false;
	});
</script>
</body>
</html>