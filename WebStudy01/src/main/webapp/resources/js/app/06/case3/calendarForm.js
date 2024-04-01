$(function(){
	let $inputs = $(calForm).find(":input[name]");
	$inputs.each((idx, el)=>{ // 화살표 함수에서는 this가 window를 가리킴
		let $el = $(el);
		let name = $el.attr("name");
		let initValue = $el.data("initValue");
		$el.val(initValue);
	}).on("change",function(event){
		$(this.form).submit();
	});
	
	$(document).on("click", ".control-a",function(){
		let $el = $(this);
		$(calForm).find('[name=year]').val($el.data("year"));
		$(calForm).find('[name=month]').val($el.data("month"));
		$(calForm).submit();
	});
	
	let $calForm = $(calForm).on("submit", function(event){
		event.preventDefault();
		// $(this) === $calForm
		let url = this.action;
		let method = $(this).attr("method");
		let queryString = $(this).serialize();
		let settings = {
			url : url,
			method : method,
			data : queryString,
			dataType : "html", // accept
			success:function(resp){
				$(calArea).html(resp);
			}, error : function(jqXHR,errStatus,textError){
				console.log(jqXHR,errStatus,textError);				
			}, complete : function(){
				console.log("비동기 요청 처리 최종완료");
			}
		};
		$.ajax(settings);
	}).submit();
});