<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered">

	<tr>
		<th>아이디</th>
		<td>${buyer.buyerId}</td>
	</tr>
	<tr>
		<th>거래처명</th>
		<td>${buyer.buyerName}</td>
	</tr>
	<tr>
		<th>구분코드</th>
		<td>${buyer.buyerLgu}</td>
	</tr>
	<tr>
		<th>은행</th>
		<td>${buyer.buyerBank}</td>
	</tr>
	<tr>
		<th>은행번호</th>
		<td>${buyer.buyerBankno}</td>
	</tr>
	<tr>
		<th>은행이름</th>
		<td>${buyer.buyerBankname}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${buyer.buyerZip}</td>
	</tr>
	<tr>
		<th>주소1</th>
		<td>${buyer.buyerAdd1}</td>
	</tr>
	<tr>
		<th>주소2</th>
		<td>${buyer.buyerAdd2}</td>
	</tr>
	<tr>
		<th></th>
		<td>${buyer.buyerComtel}</td>
	</tr>
	<tr>
		<th>팩스</th>
		<td>${buyer.buyerFax}</td>
	</tr>
	<tr>
		<th>메일</th>
		<td>${buyer.buyerMail}</td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>${buyer.buyerCharger}</td>
	</tr>
	<tr>
		<th>내선번호</th>
		<td>${buyer.buyerTelext}</td>
	</tr>
	<tr>
		<th></th>
		<td>${buyer.lprod.lprodNm}</td>
	</tr>
	<tr>
	
	<th>거래품목</th>
		<td>
			<table class="table table-bordered">
				<thead>
					<tr>
						<td>상품명</td>
						<td>구매가</td>
						<td>판매가</td>
						<td>세일가</td>
						<td>마일리지</td>
					</tr>
				</thead>
				<tbody>
					<c:set var="prodList" value="${buyer.prodList }"></c:set>
					<c:choose>
						<c:when test="${not empty prodList }">
							<c:forEach items="${prodList }" var="prod">
								<tr>
									<td>
										<c:url value="/prod/prodView.do" var="viewUrl">
											<c:param name="what" value="${prod.prodId }" />
										</c:url> <a href="${viewUrl }">${prod.prodName }</a>
									</td>
									<td>${prod.prodCost }</td>
									<td>${prod.prodPrice }</td>
									<td>${prod.prodSale }</td>
									<td>${prod.prodMileage }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">거래 품목 없음</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
	<td colspan="2">
		<c:url value="/buyer/buyerUpdate.do" var="updateUrl">
			<c:param name="what" value="${buyer.buyerId }"></c:param>
		</c:url>
		<a href="${ updateUrl}">제조사 수정</a>
	</td>
</tr>
</table>