/**
 * 
 */
/*
1. DOM(body를 포함) 트리 구조가 완성된 이후 실행되는 코드 : DOMContentLoaded
2. calForm submit 이벤트 중단 (=동기요청중단)
3. 비동기 요청 전송
	line	: action, method
	header	: accept(응답데이터형태), contnet-type
	body	: Form's inputs, (parameter: queryString)
*/
// case1 : aprameter 전송 후 HTML 응답수신
// case2 : parameter 전송 후 JSON 응답수신

// HTML 문서의 로딩이 완료된 후 실행되는 함수
// $(document).on('ready'function(){
//})
// $(document.ready(function(){
//})
document.addEventListener("DOMContentLoaded",()=>{
	// 폼 요소에서 submit 이벤트를 감지하는 함수
	calForm.addEventListener("submit",(event)=>{
		// 이벤트의 기본 동작을 중단시킴 (페이지 새로고침 방지)
		event.preventDefault();
		
		// 현재 이벤트가 발생한 폼 요소를 변수에 저장
		let form = event.target;
		
		// 폼의 속성 정보를 가져옴
		let url = form.action;
		let method = form.method;
		let contentType = form.enctype;
		
		// 서버 응답으로 받고자 하는 데이터 형식
		let accept = "application/json";
		
		// 폼 데이터를 FormData 객체로 가져옴 - 키 벨류 형태
		let formData = new FormData(form);
		
		// FormData 객체를 URLSearchParams 객체로 변환 - 쿼리스트링 형태
		let urlSearchParams = new URLSearchParams(formData);
		
		// 서버에 HTTP 요청을 보내는 fetch 함수를 사용
		let fetchPromise = fetch(url, {
			method : method,
			headers : {
				"content-type" : contentType,
				"Accept" : accept
			}, body : urlSearchParams
		});
		fetchPromise.then(resp=>{	// 서버 응답을 처리하는 Promise 체인
			if(resp.ok){
				console.log(resp)
				return resp.json(); // 언마샬링됨, 서버 응답이 정상인 경우 JSON으로 변환
			}else {
				throw new Error(`상태코드 : ${resp.status} 발생`,{cause:resp}); // 서버 응답이 에러인 경우 에러 처리
			}
		}).then(jsonObj=>{
			// JSON 데이터에서 필요한 정보를 추출하여 결과를 화면에 표시
			resultArea.innerHTML = jsonObj.expression;
			
		}).catch(err =>{
			console.error(err.message);
			if(err.cause) {
				// 서버 응답이 에러인 경우 에러 메시지를 표시
				let resp = err.cause;
				resp.text().then(ep=>resultArea.innerHTML=ep );
			}
		});
		// 폼의 기본 동작을 중단시키고 페이지 이동을 막음
		return false;
	});
});