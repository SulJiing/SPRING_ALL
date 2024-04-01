/**
 * 
 */
document.addEventListener("DOMContentLoaded",()=>{
	console.log(bootstrap); //2
	
	$("[data-log-out]").on("click", (event)=>{
		let $aTag = $(event.target);
		let formSelector = $aTag.data("logOut");
		$(formSelector).submit(); //jquery객체니까 submit만해도 그냥 submit이벤트 발생됨; requestSubmit안해도됨
		
	});
	
});
