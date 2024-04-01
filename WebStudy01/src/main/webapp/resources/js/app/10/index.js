/**
 * 
 */
document.addEventListener("DOMContentLoaded",()=>{
	console.log(bootstrap); // 2 - ready가 있기 때문에 bootstrap을 찾을 수 있음
	
	$("[data-log-out]").on("click",function(event){
		// 클릭된 요소의 jQuery 객체 생성
		let $aTag = $(event.target);
		// data-log-out 속성 값에서 폼의 선택자를 가져오기
		let formSelector = $aTag.data("logOut");
		// 가져온 폼 선택자를 사용하여 해당 폼 제출(submit)하기
		$(formSelector).submit();
	});
})
