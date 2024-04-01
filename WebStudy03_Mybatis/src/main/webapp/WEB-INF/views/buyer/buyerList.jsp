<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a href="javascript:;" data-go-link="/buyer/buyerInsert.do">신규제조사등록</a>
${buyerList }
<table class="table table-bordered">
	<thead>
		<tr>
			<td>일련번호</td>
			<td>제조사명</td>
			<td>주소</td>
			<td>전화번호</td>
			<td>이메일</td>
			<td>담당자</td>
		</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${not empty buyerList }">
			<c:forEach items="${buyerList }" var="buyer">
				<tr>
					<td>${buyer.rnum }</td>
						<c:url value="/buyer/buyerView.do" var="viewUrl">
							<c:param name="what" value="${buyer.buyerId }"></c:param>
						</c:url>
						<td><a href="${viewUrl }">${buyer.buyerName}</a>
						<td>${buyer.buyerAdd1 }</td>
					<td>${buyer.buyerComtel }</td>
					<td>${buyer.buyerMail }</td>
					<td>${buyer.buyerCharger }</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="6">조건에 맞는 제조사 없음</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="submitForm" action="">
					<input type="text" name="page"/>
					<input type="text" name="buyerLgu" value="${condition.buyerLgu }"/>
					<input type="text" name="buyerId" value="${condition.buyerId }"/>
					<input type="text" name="buyerAdd1" value="${condition.buyerAdd1 }"/>
				</form>
				
				<div data-pg-role="searchUI" data-pg-target="#submitForm">
				
					<select name="buyerLgu" data-pg-init-value="${condition.buyerLgu }">
						<option value>품목코드</option>
						<c:forEach items="${lprodList }" var="lprod">
							<option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
						</c:forEach>
					</select>
						
					<select name="buyerId" data-pg-init-value="${condition.buyerId  }">
						<option value>제조사명</option>
						<c:forEach items="${aBuyerList }" var="buyer2">
							<option value="${buyer2.buyerId }" class="${buyer2.buyerLgu }" >${buyer2.buyerName }</option>
						</c:forEach>
					</select>
					
					<input type="text" name="buyerAdd1" data-pg-init-value="${condition.buyerAdd1 }"/>
					<button type="button" data-pg-role="searchBtn">검색</button>
				</div>
				${pagingHTML }
			</td>
		</tr>
	</tfoot>
</table>
<script src="<c:url value='/resources/js/app/common/paging.js'/>"></script>
<script type="text/javascript">
   let $buyerId = $('select[name="buyerId"]'); //name-prodBuyer 두개여서 select 붙여줌
   $('select[name="buyerLgu"]').on("change",(event)=>{ 
      let $buyerLgu = $(event.target); 
      let selectedLgu = $buyerLgu.val(); //let selectedLgu = event.target.value; //val(), value 하면 선택되어있는 옵션의 값이 나옴.
      $buyerId.find("option:gt(0)").each((idx, op)=>{ //:gt 보다 큰 //여러개의 option을 하나하나 처리
         let $op = $(op);
         let showFlag = (selectedLgu && $op.hasClass(selectedLgu)) || (!selectedLgu);
         // 없을떄      :  없으니까 false   뒤에는 계산 안하고                  없으니까 true
         // 있을떄      :  있으니까 true  (분류코드)를 클래스로 가지고 있으면 true  있으니까 false
         //                           (분류코드)를 클래스로 가지고 있지 않으면 false 
         $op.toggle(showFlag);
         //flase면 hidden
      });
   });
</script> 