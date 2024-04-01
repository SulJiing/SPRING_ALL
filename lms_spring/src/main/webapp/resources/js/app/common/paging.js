/*
	특정 비지니스에 종속되지 않는 형태로,
	목록 조회(페이징)처리에 공통 적용할 모듈.
	
	1. 페이징 처리는 hidden form을 기반으로 함.(ex. #searchForm)
	2. 컨트롤러에서는 FormBasePaginationRenderer를 사용함. - 생성자를 통해 target form을 결정함.
	3. data-pg 라는 접두어로 시작되는 데이터 속성을 기반으로 셀렉팅함.
		>> data-pg-role : pageLink(페이지 네이게이션 a 태그), searchUI(검색조건 입력 UI), searchBtn(검색 버튼)
		>> data-pg-page : 페이지 네이게이션 a 태그에서 페이지 번호 설정에 사용.
		>> data-pg-target : 페이지 네비게이션에 사용할 target form의 셀렉터 설정에 사용.
		>> data-pg-init-value : 검색 조건 초기화에 사용.
	
*/
$(":input[data-pg-init-value]").each(function(index, input){
	let $input = $(input);
	let initValue = $input.data("pgInitValue");
	$input.val(initValue);
}); 

//검색 클릭하면
$("[data-pg-role='searchUI']").on("click","[data-pg-role='searchBtn']",function(event){ //이벤트 버블링 : 버튼을 클릭했어도 그 부모인 UI까지 전파됨
				//   검색버튼 // 검색버튼의 부모중 아래의 조건인 놈 찾기
	let $searchUI = $(this).parents("[data-pg-role='searchUI']");
	
	console.log("오잉 여기로 왔네");
	
	// 컨트롤러로 보내는 폼을 타겟으로 잡아놨었고 >> DATA로, 그거를 찾는겅미
	let targetFormSelector = $searchUI.data("pgTarget");
	let $targetForm = $(targetFormSelector);		
	
	//searchUI 자식중 input에 name이 있는 놈들 전부! 하나하나 접근
	$searchUI.find(":input[name]").each(function(index, input){
		let name = input.name;  // 이름 구함 >> prodLgu
		let value = $(input).val(); // 그것의 값을 구함 >> ${condition.prodLgu } 
		$targetForm.find(`[name="${name}"]`).val(value); // name이 prodLgu이거인놈을 찾음...>> 그리고 값을 넣음 >> 지금 검색정보를 컨트롤러에 보낼 폼에 넣어둠
	});						//"" 이거안할때 특수문자있으면 문제가 생김.
	
	$targetForm.submit(); // 그리고 컨트롤러로 보냄
});


// 아래 페이지 클릭하면
$(document).on("click","a[data-pg-role='pageLink']", function(event){
	event.preventDefault();
	
	let page = $(this).data("pgPage");  // 현재클릭한 currentPage에 대한 정보를 얻어옴 .. data를 활용해서
// 	location.href = "?page=" + page;
	let targetFormSelector = $(this).data("pgTarget"); // form을 선택
	let $targetForm = $(targetFormSelector);  
	$targetForm.find('[name="page"]').val(page); // form 에 정보를 넣음
	$targetForm.submit(); // 보냄  >> 다른 정보들은 바뀌지 않음...
	
	return false;
});