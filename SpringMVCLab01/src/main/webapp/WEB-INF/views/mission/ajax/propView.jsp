<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="radio" name="contentType" value="application/x-www-form-urlencoded"/>parameter
<input type="radio" name="contentType" value="application/json"/>json
<!-- modelAttribute form태그 안에서만 작용함 -->
<form method="POST" id="insertForm">
	<input type="text" name="propertyName" required placeholder="propertyName"/>
	<input type="text" name="propertyValue" required placeholder="propertyValue"/>
	<input type="text" name="description" placeholder="description"/>
	<input type="date" name="propDate" placeholder="propDate"/>
	<input type="datetime-local" name="propTimestamp" placeholder=propTimestamp/>
	<button id="go" type="submit">전송</button>
</form>

<!-- 	이벤트 처리 과정 -->
<!-- 	1. 타겟 결정 -->
<!-- 	2. 이벤트 종류 결정 -->
<!-- 	3. 이벤트를 처리할 수 있는 핸들러 -->
<!-- 	4. 해당 이벤트를 타겟에 부착 -->

<!-- 1. 등록의 모든 절차는 비동기로 처리할 것 -->
<!-- 2. 등록에 사용되는 모든 컨텐츠는 json으로 송수신할 것 -->
<!-- 3. contentType 라디오 버튼에 따라 전송 컨텐츠의 종류가 달라짐 -->
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
/*function go() {
		let $form = $('#insertForm');
		propertyVO = {
	        propertyName: $form.find('input[name="propertyName"]').val(),
	        propertyValue: $form.find('input[name="propertyValue"]').val(),
	        description: $form.find('input[name="description"]').val(),
	        propDate: $form.find('input[name="propDate"]').val(),
	        propTimestamp: $form.find('input[name="propTimestamp"]').val()
	    };
		
	 	var contentType = $('input[name="contentType"]:checked').val();
	    var formData;
		var contextPath = '${pageContext.request.contextPath }'+'/mission/ajax/propView';
	    if (contentType === 'application/json') {
	        // JSON 데이터로 직렬화
	        formData = JSON.stringify(propertyVO);
	    } else {
	        // 기존 폼 데이터로 직렬화
	    	formData = $('#frm').serialize();
	    }
	    $.ajax({
	        url: contextPath,
	        type: 'POST',
	        dataType: 'json',
	        contentType: contentType,
	        data: formData,
	        success: function(response) {
				let message = response['message'];
				alert(message);
	        },
	        error: function(xhr, status, error) {
	            console.error(status + ' - ' + error);
	        }
	    });
		return false;
	}
	
	$('#go').on('click', function(event) {
        event.preventDefault();
	    go(); 
	});*/
	
	// fetch(promise&chaining)
	insertForm.addEventListener("submit",(event)=>{
		event.preventDefault();
		
		let form = event.target;
		
		// optional chaining 구조 "?"
		let contentType = document.querySelector('[name="contentType"]:checked')?.value ?? form.enctype;; 
		
		let url = form.action;
		let formData = new FormData(form); // form 태그 안의 모든 입력태그를 key&value형태로 보관
		
		let body = null;
		if(contentType.indexOf("json")>=0) {
			let data = {};
			for(let k of formData.keys()){
				data[k] = formData.get(k);
			}
			body = JSON.stringify(data) // 마샬링
		} else {
			body = new URLSearchParams(formData).toString(); // 여러개의 파라미터로 구성된 쿼리로 만듦(toString())
		}
		
		let options = {
				method:form.method //post
				, headers:{
					"content-type":contentType	// 클라이언트
					, "accept":"application/json" 	// 서버
				},
				body:body
		};
		fetch(url, options)
			.then(resp=>{
				if(resp.ok){
					resp.json();
					alert(resp.json());
				} else {
					throw new Error(`\${url}처리 중 에러 발생`, {cause:resp});
				}
			}).then(jsonObj=>{
				console.log(jsonObj);
			}).catch(err=>{console.error(err)});
	});
</script>
</body>
</html>