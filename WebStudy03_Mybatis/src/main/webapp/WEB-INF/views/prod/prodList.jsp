<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="javascript:;" data-go-link="/prod/prodInsert.do">신규상품등록</a>
<table class="table table-bordered">
	<thead>
		<tr>
			<td>일련번호</td>
			<td>상품명</td>
			<td>상품분류</td>
			<td>제조사</td>
			<td>판매가</td>
			<td>마일리지</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty prodList }">
			<%-- for( tmp : list ) --%>
			<c:forEach items="${prodList }" var="prod">
				<tr>
					<td>${prod.rnum }</td>
					<td>
						<!-- c로 ContextPath와 파라미터를 넘겨주는 방법 -->
						<c:url value="/prod/prodView.do" var="viewUrl">
							<c:param name="what" value="${prod.prodId }"/>
							<c:param name="param" value="paramvalue"/>
						</c:url>
						<a href="${viewUrl }">${prod.prodName }</a>
					</td>
					<td>${prod.prodLgu }</td>
					<td>${prod.prodBuyer }</td>
					<td>${prod.prodPrice }</td>
					<td>${prod.prodMileage }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<tr>
				<td colspan="6">상품 없음</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm" action="<c:url value='/prod/prodList.do'/>">
					<input type="text" name="page" /> 
					<input type="text" name="prodLgu" value="${condition.prodLgu }"/>
					<input type="text" name="prodBuyer" value="${condition.prodBuyer }"/>
					<input type="text" name="prodName" value="${condition.prodName }"/>
				</form>
				<div data-pg-role="searchUI" data-pg-target="#searchForm">
				
					<select name="prodLgu" data-pg-init-value="${condition.prodLgu }">
						<option value>분류선택</option>
						<c:forEach items="${lprodList }" var="lprod">
							<option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
						</c:forEach>
					</select>
					
					<select name="prodBuyer" data-pg-init-value="${condition.prodBuyer }">
						<option value>제조사선택</option>
						<c:forEach items="${buyerList }" var="buyer">
							<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
						</c:forEach>
					</select>
					
					<input type="text" name="prodName" data-pg-init-value="${condition.prodName }" placeholder="상품명"/>
					<button type="button" data-pg-role="searchBtn">검색</button>
				</div>
				${pagingHTML }
			</td>
		</tr>
	</tfoot>
</table>
<script src="<c:url value='/resources/js/app/common/paging.js'/>"></script>
<script type="text/javascript">
		let $prodBuyer = $('select[name="prodBuyer"]');
		$('select[name="prodLgu"]').on("change",(event)=>{
		    let $prodLgu = $(event.target);
			let selectedLgu = $prodLgu.val(); // 자동으로 옵션의 값이 들어감
			$prodBuyer.find("option:gt(0)").each((i,op)=>{
				let $op = $(op);
// 				if(selectedLgu) {
// 					showFlag = $op.hasClass(selectedLgu);
// 				} else {
// 					showFlag = true;
// 				}
				let showFlag = (selectedLgu && $op.hasClass(selectedLgu)) || (!selectedLgu);
				// 없을 때		: 없으니까 false 뒤에는 안넘어감	없으니까 true
				// 있을 때		: 있으니까 true				있으니까 false
// 									- 분류코드를 클래스로 가지고 있으면 true 아니면 false
				$op.toggle(showFlag); // flase면 hidden
			});
// 			$prodBuyer.find(`option:\${selectedLgu}`).show();
		});
</script>
<!-- 검색조건 : 상품분류코드, 제조사코드, 상품명, 전체(searchType) -->
<!-- 검색단어(searchWord) -->