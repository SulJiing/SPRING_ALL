<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<pre>
${message }
${errors }
</pre>
<form method="post">
	<table>
		<tr>
			<th>상품명</th>
			<td><input type="text" name="prodName" 
				value="${prod.prodName}" class="form-control" /><span
				class="text-danger">${errors.prodName}</span></td>
		</tr>
		<tr>
			<th>분류코드</th>
			<td><select name="prodLgu"  class="form-control">
					<option value>분류선택</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
					</c:forEach>
				</select></td>
		</tr>
		<tr>
			<th>제조사코드</th>
			<td><select name="prodBuyer"  class="form-control">
					<option value>제조사선택</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
					</c:forEach>
				</select></td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><input type="number" name="prodCost"  value="${prod.prodCost}" class="form-control" />
			<span class="text-danger">${errors.prodCost}</span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input type="number" name="prodPrice"  value="${prod.prodPrice}" class="form-control" />
			<span class="text-danger">${errors.prodPrice}</span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input type="number" name="prodSale" 
				value="${prod.prodSale}" class="form-control" /><span
				class="text-danger">${errors.prodSale}</span></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><input type="text" name="prodOutline" 
				value="${prod.prodOutline}" class="form-control" /><span
				class="text-danger">${errors.prodOutline}</span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input type="text" name="prodDetail"
				value="${prod.prodDetail}" class="form-control" /><span
				class="text-danger">${errors.prodDetail}</span></td>
		</tr>
		<tr>
			<th>이미지경로</th>
			<td><input type="text" name="prodImg" 
				value="${prod.prodImg}" class="form-control" /><span
				class="text-danger">${errors.prodImg}</span></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><input type="number" name="prodTotalstock" 
				value="${prod.prodTotalstock}" class="form-control" /><span
				class="text-danger">${errors.prodTotalstock}</span></td>
		</tr>
		<%-- 		<tr><th>입고일</th><td><input type="date" name="prodInsdate"  value="${prod.prodInsdate}" class="form-control" /><span class="text-danger">${errors.prodInsdate}</span></td></tr> --%>
		<tr>
			<th>적정재고</th>
			<td><input type="number" name="prodProperstock" 
				value="${prod.prodProperstock}" class="form-control" /><span
				class="text-danger">${errors.prodProperstock}</span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input type="text" name="prodSize" value="${prod.prodSize}"
				class="form-control" /><span class="text-danger">${errors.prodSize}</span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input type="text" name="prodColor"
				value="${prod.prodColor}" class="form-control" /><span
				class="text-danger">${errors.prodColor}</span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input type="text" name="prodDelivery"
				value="${prod.prodDelivery}" class="form-control" /><span
				class="text-danger">${errors.prodDelivery}</span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input type="text" name="prodUnit" value="${prod.prodUnit}"
				class="form-control" /><span class="text-danger">${errors.prodUnit}</span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input type="number" name="prodQtyin"
				value="${prod.prodQtyin}" class="form-control" /><span
				class="text-danger">${errors.prodQtyin}</span></td>
		</tr>
		<tr>
			<th>출고량</th>
			<td><input type="number" name="prodQtysale"
				value="${prod.prodQtysale}" class="form-control" /><span
				class="text-danger">${errors.prodQtysale}</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="prodMileage"
				value="${prod.prodMileage}" class="form-control" /><span
				class="text-danger">${errors.prodMileage}</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">전송</button>
				<button type="reset">취소</button>
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
   let $prodBuyer = $('select[name="prodBuyer"]').val("${prod.prodBuyer}"); //name-prodBuyer 두개여서 select 붙여줌 //select하면서 값을 초기화하고 있음
   $('select[name="prodLgu"]').on("change",(event)=>{ 
      let $prodLgu = $(event.target); 
      let selectedLgu = $prodLgu.val(); //let selectedLgu = event.target.value; //val(), value 하면 선택되어있는 옵션의 값이 나옴.
      $prodBuyer.find("option:gt(0)").each((idx, op)=>{ //:gt 보다 큰 //여러개의 option을 하나하나 처리
         let $op = $(op);
         let showFlag = (selectedLgu && $op.hasClass(selectedLgu)) || (!selectedLgu);
         // 없을떄      :  없으니까 false   뒤에는 계산 안하고                  없으니까 true
         // 있을떄      :  있으니까 true  (분류코드)를 클래스로 가지고 있으면 true  있으니까 false
         //                           (분류코드)를 클래스로 가지고 있지 않으면 false 
         $op.toggle(showFlag);
         //flase면 hidden
      });
   }).val("${prod.prodLgu}").trigger("change"); //이벤트가 끝난 후에 초기값을 정해줌 // 트리거 : 이벤트 발생시키겠다.
</script>