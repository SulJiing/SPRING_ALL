/**
 * ${} => 템플릿 문자열
 * GET 방식
 */
const cPath = document.body.dataset.contextPath;

document.addEventListener("DOMContentLoaded",()=>{
	const refreshFileArea = jsonObj=>{
		let ulTag = document.createElement("ul"); // <ul></ul>
		ulTag.innerHTML = jsonObj.map(n=>`
								<li><a href="${cPath}/mission/file/${n}">${n}<a/></li>
							`).join("\n");
		document.querySelector("#fileArea").replaceChildren(ulTag);
	};
	
	fetch(location.href, {
		headers:{
			"accept":"application/json"
		}
	}).then(resp=>{
		if(resp.ok){
			return resp.json();
		} else {
			throw new Error("요청 처리 과정에서 문제 발생",{cause:resp});
		}
	}).then(refreshFileArea).catch(err=>console.log(err));
	
	fileForm.addEventListener("submit",(event)=>{
		event.preventDefault();
		let form = event.target;
		let formData = new FormData(form);
		fetch(form.action, {
			method:form.method,
			headers:{ // contentType은 설정하지 않아도 서버에서 자동으로 해줌
				"accept":"application/json"
			},
			body:formData
		}).then(resp=>{
			if(resp.ok){
				return resp.json();
			} else {
				throw new Error("요청 처리 과정에서 문제 발생",{cause:resp});
			}
		}).then(refreshFileArea).catch(err=>console.log(err));
	});
});