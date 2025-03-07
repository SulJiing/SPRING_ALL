/**
 * 
1. DOM(body를 포함) 트리구조가 완성된 이후 실행되는 코드 : DOMContentLoaded
2. calForm submit 이벤트 중단
3. 비동기 요청 전송
   line : action, method
   header : accept, content-type
   body : form's inputs, (parameter: queryString)
*/
//  case1 2 3 공통점 : 비동기요청
//   case1: parameter 전송 후 HTML 응답수신
//   case2: parameter 전송 후 JSON 응답수신
//   case3: parameter 전송 후 radio에서 응답방법 선택


document.addEventListener("DOMContentLoaded", () => {
   calForm.addEventListener("submit", (event) => {
      event.preventDefault();
      let form = event.target;
      let url = form.action;
      let method = form.method;
      
      let contentType = "application/json" //1
      
      let accept = "text/html";
      let formData = new FormData(form);
      let nativeData = {
         leftOp : parseFloat(formData.get("left")),
         rightOp : parseFloat(formData.get("right")),
         operatorType : formData.get("operator")
      };
      let jsonStr = JSON.stringify(nativeData)//marshalling , unmarshalling = .parses
      
      let fetchPromise = fetch(url, {
         method: method,
         headers: {
            "Content-Type": contentType,
            "Accept": accept
         }, body: jsonStr // 2
      });
      fetchPromise.then(resp => {
         if (resp.ok) {
            return resp.text();
         } else {
            throw new Error(`상태코드 : ${resp.status} 발생`, { cause: resp });
         }
      }).then(text => {
	console.log(text);
         resultArea.innerHTML = text;
      }).catch(err => {
         console.log(err.message);
         if (err.cause) {
            let resp = err.cause;
            resp.text().then(ep => resultArea.innerHTML = ep);
         }
      });
      return false;
   });
});