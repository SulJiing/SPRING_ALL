<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<h1>${clazzList[0].subName }</h1>
<hr/>
<div id="clazz">
	<table class="table table-bordered">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>학점</th>
		</tr>
		<c:if test="${not empty clazzList }">
			<c:forEach items="${clazzList }" var="clazz">
				<tr>
					<td class='stdNo'>${clazz.stdNo }</td>
					<td>${clazz.stdName }</td>
					<td>
						<span class="clsScore">${clazz.clsScore }</span>
						<button class="btn btn-primary" >학점</button>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty clazzList }">
			<tr>
	            <td colspan="3">수강 정보 없음.</td>
	         </tr>
		</c:if> 
	</table>
</div>
<form id="updateForm" action="?" method="post">
	<input type="hidden" name="stdNo">
	<input type="hidden" name="clsScore">
	<input type="hidden" name="subCd">
	<input type="hidden" name="score">
</form>
<script type="text/javascript">
var message = '${message}';
if(message){
	alert(message);
}
$('.btn.btn-primary').on("click",function(event){
	
	btn = event.target
	var td = $(this).closest('td');
	var clsScore = td.closest('tr').find('.clsScore').text();
	var stdNo = td.closest('tr').find('.stdNo').text();
	var subCd = '${clazzList[0].subCd }';
		
	var score = prompt("학점을 입력해주세요.", clsScore);
	
	$(updateForm).find('input[name="clsScore"]').val(clsScore);
	$(updateForm).find('input[name="stdNo"]').val(stdNo);
	$(updateForm).find('input[name="subCd"]').val(subCd);
	$(updateForm).find('input[name="score"]').val(score);
	
	$(updateForm).submit();
	
});
</script>