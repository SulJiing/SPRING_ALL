	  /*calForm.year.value = "<%=thisMonth.getYear() %>";
      calForm.month.value = "<%=thisMonth.getMonthValue() %>";
      calForm.locale.value = "<%=locale.toLanguageTag()%>"*/
	
	calForm.querySelectorAll("[name]").forEach((el,idx)=>{
  	 	 /*data-init-value 속성으로부터 엘리먼트 초기값 로딩.
		data-* 속성의 키값 규칙성, 카멜 표기법을 표현할 때 '-'으로 대체함.
		ex) data-role : key(role)
			data-init-value : key(initValue)*/
		let name = el.name;
		calForm[name].value = el.dataset.initValue;
	});

	calForm.querySelectorAll("[name]").forEach((input,index)=>{
  	 	 console.log(input.dataset); 
	});
    calForm.addEventListener('change', (event) => {
         event.target.form.requestSubmit();
    });
      
//       $(selector).on("click",function(){}) // 정적엘리먼트
//       $(document).on("click",'.control-a',function(){}) // 동적 엘리먼트 : 이벤트 버블링 구조 활용
      
//       document.querySelectorAll('.control-a').forEach((el, idx) =>{
         document.addEventListener("click",(event)=>{
        	 if(event.target.classList.contains("control-a")){
	        	console.log(event.target);
	        	let el = event.target;
	        	console.log(el.dataset);
	            calForm.year.value = el.dataset.year;
	            calForm.month.value = el.dataset.month;
	            calForm.requestSubmit();
        	 }
         });
//       });
      
     calForm.addEventListener("submit",(event)=>{
		event.preventDefault();
		let url = event.target.action;
		let method = event.target.method;
		let formData = new FormData(calForm);
		let urlSearchParams = new URLSearchParams(formData);
		let fetchPromise = fetch(url, {
			method: method,
			headers: {
				"Content-Type" : calForm.enctype
			}, body: urlSearchParams
		});
		fetchPromise.then(resp=>{ // 성공시 실행 => Promise 객체의 resolve로 연결됨
			if(resp.ok){
				return resp.text();
			}else {
				throw new Error(`상태코드 \${resp.status}이 응답으로 전송됨`,{cause:resp}); // 예외가 발생했을 때 catch로 넘겨주고자 함
			}
		}).then(html=>{
			calArea.innerHTML = html; // 얘가 동작하는 시기는 응답데이터가 완전히 동작된 후
		}).catch(err =>{ // 실패시 실행 => Promise 객체의 reject로 연결됨 / 위 에러를 받아옴
			console.log(err);
			console.log(err.cause);
		}).finally(()=>{ // 성공실패 여부 상관없이 실행
			
		});
     });
      calForm.requestSubmit();