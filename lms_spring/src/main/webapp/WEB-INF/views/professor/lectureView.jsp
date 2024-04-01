<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>${sub.subName }</h1>
<div id="items">
<table id="table">
	 <thead>
	<tr>
		<th>학번</th>
		<th>이름</th>
		<th>학점</th>
		<th>#</th>
	</tr>
	</thead>
	<c:if test="${not empty studentList }">
		<tbody class="table-group-divider">
		<c:forEach items="${studentList }" var="student">
			<tr>
				<td class='stdNo'>${student.stdNo }</td>
				<td class='stdName'>${student.stdName }</td>
				<td class='clsScore'>${student.clazz.clsScore }</td>
				<td><button name="clazzBtn" class="btn btn-outline-info">학점등록</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty studentList }">
		<tbody class="table-group-divider">
		<tr>
			<td>아직 수강하는 학생이 없습니다.</td>
		</tr>
		</tbody>
	</c:if>
</table>
</div>
<form id="updateForm" action="?" method="post">
	<input type="hidden" name="stdNo">
	<input type="hidden" name="clsScore">
	<input type="hidden" name="score">
</form>
<script type="text/javascript">
	$("[name='clazzBtn']").on("click",(event)=>{
		var td = $(event.target).closest('td');
		var clsScore = td.closest('tr').find('.clsScore').text();
		var stdNo = td.closest('tr').find('.stdNo').text();
		
		var score = prompt("학점을 입력해주세요.", clsScore);
		
		$(updateForm).find('input[name="clsScore"]').val(clsScore);
		$(updateForm).find('input[name="stdNo"]').val(stdNo);
		$(updateForm).find('input[name="score"]').val(score);
		
		$(updateForm).submit();
		
	});
</script>