/*
1. DOM(body를 포함) 트리 구조가 완성된 이후 실행되는 코드 : DOMContentLoaded
2. calForm submit 이벤트 중단
3. 비동기 요청 전송
   line : action, method
   header : accept, content-type
   body : from's inputs, (parameter: queryString)
*/

/*@@@@@@@@@@ case1 : parameter 전송 후 HTML 응답 수신 @@@@@@@@@@@@*/

/*이 코드는 웹 페이지가 완전히 로드된 후(DOMContentLoaded 이벤트)에 실행되는 스크립트입니다. 
  이 스크립트는 폼(submit) 제출 이벤트에 대한 처리를 담당합니다.*/


/*1. 이벤트 리스너 등록:*/
document.addEventListener("DOMContentLoaded",()=>{/*문서가 로드되면 실행할 콜백 함수를 등록합니다.*/
/*2. 폼 제출 이벤트 리스너 등록:*/   
   calForm.addEventListener("submit",(event)=>{/*calForm이라는 폼에 대한 제출 이벤트가 발생하면 실행할 콜백 함수를 등록합니다.*/
/*3. 폼 제출 기본 동작 방지:*/      
      event.preventDefault();/*기본 폼 제출 동작을 막습니다. 즉, 페이지가 다시 로드되는 것을 방지합니다.*/
/*4. 폼 속성 추출:*/      
      let form = event.target;/*event.target은 이벤트가 발생한 요소를 나타냅니다. */
      let url = form.action;/*form.action은 폼이 제출될 때 사용되는 URL*/
      let method = form.method;/*method 변수에는 폼이 사용하는 HTTP 메서드가 할당됩니다.(예: "GET" 또는 "POST").*/
      let contentType = form.enctype;/*form.enctype은 폼 데이터를 서버로 제출할 때 사용되는 콘텐츠 유형을 나타냅니다.*/
/*5. 헤더 및 데이터 설정:      */   
      let accept = " text/html";/*accept 변수는 서버로부터 받아들일 응답의 콘텐츠 타입을 지정합니다.*/
      let formData = new FormData(form);/*new FormData(form)는 form 요소의 데이터를 담고 있는 FormData 객체를 생성합니다. 이를 통해 폼에 입력된 데이터를 쉽게 추출할 수 있습니다.*/
      let urlSearchParams = new URLSearchParams(formData);/*new URLSearchParams(formData)는 FormData 객체를 기반으로 쿼리 문자열을 생성합니다. 이 쿼리 문자열은 폼 데이터를 URL 매개변수 형식으로 변환합니다.*/
      /*^^^^이렇게 변환된 데이터는 나중에 fetch 함수를 사용하여 서버로 전송될 것입니다 ^^^^*/
/*6. Fetch API를 사용하여 데이터 전송:   */   
      let fetchPromise = fetch(url,{/*fetch 함수는 네트워크 요청을 만들어 반환하는 JavaScript API입니다. 함수의 첫 번째 매개변수는 요청을 보낼 URL */
         method:method,/*이 부분은 요청의 HTTP 메서드를 지정합니다. method 변수에는 폼에서 가져온 HTTP 메서드가 할당되어 있습니다.*/
         headers:{/*headers: { "Content-Type": contentType, "Accept": accept }: headers 속성은 HTTP 요청 헤더를 설정합니다.*/
            "Content-Type" : contentType,/*폼 데이터를 URL 매개변수로 전송하고 있으므로 application/x-www-form-urlencoded와 유사한 형식을 사용하고 있습니다.*/
            "Accept" :accept/*Accept 헤더는 클라이언트가 서버로부터 받아들일 응답의 콘텐츠 타입을 나타냅니다. 여기서는 "text/html"로 설정되어 있습니다.*/
         },body : urlSearchParams/*body 속성은 요청의 본문 데이터를 설정합니다. urlSearchParams에는 폼 데이터가 URL 매개변수 형식으로 변환된 데이터가 담겨 있습니다.*/
      });
/*7. 응답 처리:*/      
      fetchPromise.then(resp =>{/*then 메서드는 fetchPromise가 성공적으로 해결되었을 때 실행됩니다.
                           resp 매개변수는 서버 응답 객체를 나타냅니다*/
         if(resp.ok){
            return resp.text();/* resp.text()를 호출하여 응답 본문을 텍스트로 변환하고 이를 반환*/
         }else{
            throw new Error(`상태 코드 ${resp.status} 이 응답으로 전송됨.`, {cause:resp});
            /*응답이 성공하지 않았다면, Error 객체를 throw하여 프라미스 체인을 거부합니다. 이 때 에러 메시지에는 상태 코드와 응답 객체가 포함됩니다.*/
         }
      }).then(text=>{/*성공적으로 응답을 받았을 때 실행됩니다. */
         resultArea.innerHTML = text;/*앞서 반환한 텍스트를 가져와서 resultArea라는 HTML 엘리먼트의 내부 HTML로 설정합니다. 이를 통해 화면에 서버 응답을 표시합니다.*/
      }).catch(err=>{/*err 매개변수는 앞서 throw로 전달한 Error 객체를 나타냅니다*/
         console.log(err.message);
         if(err.cause){/*만약 에러 객체에 cause 프로퍼티가 존재하면,*/
            let resp = er.cause;/*throw로 거부된 응답 객체를 나타냅니다.*/
            resp.text().then(ep=>resultArea.innerHTML = ep);/*해당 응답 객체의 본문을 텍스트로 변환하고, 그 결과를 다시 resultArea에 표시합니다*/
         }
      });
      return false;
   });
});

/*document.addEventListener("DOMContentLoaded",function(){
   
   calForm.addEventListener("submit",(e)=>{
   e.preventDefault();
   let url = e.target.action;
   
   let method = e.target.method; //post
   let formData = new FormData(calForm);
   let urlSearchParams = new URLSearchParams(formData);
   
   let fetchPromise = fetch(url,{
      method: method,
      headers:{
         "Content-Type" : calForm.enctype
      }, body : urlSearchParams
   });
   
   fetchPromise.then(resp=>{
         if(resp.ok){
            return resp.text();
         }else{
            throw new Error(`상태 코드 \${resp.status} 이 응답으로 전송됨.`, {cause:resp});
         }
      }).then(html=>{
         
      })
});
});*/