/*
1. 페이지가 완성되면,
2. 비동기 요청(/10/mbti)을 전송하고,
3. json content를 수신한 후,
4. select의 option태그를 동적 구성함.
*/

/*
사용자가 MBTI유형을 선택하면, 해당 MBTI의 컨텐츠를 현재 페이지 하단에 출력해라
1. select change 이벤트 처리 -> submit이벤트 트리거 시키고 있음.
2. submit 이벤트의 고유액션을 중단.
3. 동기를 비동기로 전환하여 전송(나머지 모든 조건(form)은 동일).
4. HTML 컨텐츠 수신.
5. div를 생성하고, form next sibling으로 추가
6. div의 children으로 수신한 HTML 컨텐츠를 출력
EDD(Event Driven Development)방법론- 
TDD(Test Driven Development)방법론-JUnit
*/

// 일반함수
//function resolve(){
//   
//}
// 익명함수
//var resolve = function(){
//   
//};
// 화살표함수
//JSON 객체를 받아서 HTML 옵션 요소를 동적으로 생성하여 페이지에 추가하는 역할을 하는 resolve 함수
var fnResolveEntrySet = (jsonObj) => {
   // JSON 객체의 entrySet 메소드 호출, 결과를 mapArray에 저장
   let mapArray = jsonObj.entrySet;
   console.log("jsonObj",jsonObj);
   let options = "";
   // mapArray를 순회하면서 각 associativeArray에 대해 처리
   options += mapArray.map((associativeArray) => { // 16번 실행
      console.log(associativeArray);
      let option = "";
      for (let propName in associativeArray) { // 1번 실행
         let entrykey = propName;
         let entryvalue = associativeArray[entrykey];
         option += `<option value="${entrykey}">${entryvalue}</option>`
      }
      return option;
   }).join("\n");
   document.querySelector("[name='type']").innerHTML = options;
};

var findCookie = (name) => {
	let matches = document.cookie.match(`${name}=([^;]+)`);
	return matches ? matches[1] : undefined;
}

const select = document.querySelector("[name='type']");

var fnResolveEntryMap = (jsonObj) => {
	// JSON 객체의 entrySet 메소드 호출
	let associativeArray = jsonObj.entryMap;
	console.log("associativeArray", associativeArray);
	let options = "";
	for (let propName in associativeArray) { // 16번 실행
		let entrykey = propName;
		let entryvalue = associativeArray[entrykey];
		options += `<option value="${entrykey}">${entryvalue}</option>`
	}
	select.innerHTML = options;

//	let savedType = findCookie("mbtiCookie");
	let savedType = select.dataset.initValue;
	if (savedType) {
		select.value = savedType;
		select.form.requestSubmit();
	}
};

var fnOptionLoad = (event) => {
   cPath = document.body.dataset.contextPath;
   fetch(`${cPath}/10/mbti`, {
      headers: {
         "Accept": "application/json"
      }
   }).then(resp => {
      if (resp.ok) {
         return resp.json();
      } else {
         throw new Error(`상태코드 ${resp.status} 수신`, { cause: resp });
      }
   }).then(fnResolveEntryMap).catch(err => console.error(err));
};

var fnMbtiLoad = (event) => {
   if (event.target.tagName !== "FORM") return false;

   let form = event.target;
   event.preventDefault();

   // 쿼리 스트링 가져오는 구조
   let formData = new FormData(form);
   let urlSearchParams = new URLSearchParams(formData);
   fetch(`${form.action}?${urlSearchParams}`, {
      method: form.method,
      headers: {
         "Accept": "text/html"
      }, cache:"no-cache" // 브라우저 캐시데이터가 있어도 사용하지 않겠다하는 뜻
   }).then(resp => {
      if (resp.ok) {
         return resp.text();
      } else {
         throw new Error(`상태코드 ${resp.status}`, { cause: resp });
      }
   }).then(html => {
      if (!window['resultArea']) {
         let div = document.createElement("div");
         div.id = "resultArea";
         form.after(div);
      }
      // 새로운 돔트리구조
      let parser = new DOMParser();
      let newDoc = parser.parseFromString(html, "text/html");
      let preTag = newDoc.body.innerHTML;
      resultArea.innerHTML = preTag;
   }).catch(err => console.error(err));

   return false;
};

document.addEventListener("DOMContentLoaded",fnOptionLoad);

document.addEventListener("submit",fnMbtiLoad);