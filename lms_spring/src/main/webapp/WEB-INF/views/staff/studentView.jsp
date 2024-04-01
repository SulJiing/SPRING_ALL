<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<style>
#clazz{
	width: 1000px;
	margin: 0 auto;
}
#detail{
	width: 1000px;
	margin: 0 auto;
}
#modBtn{
	margin-left: 20px;
}
#line{
	display: flex; 
	align-items: center;
}
</style>
<h1>${student.stdName } 학생 상세조회</h1>
<hr/>
<div id="detail">
	<div id="line">
	<h4>학생기본정보</h4>
	<button id="modBtn" class="btn btn-primary">수정</button>
	</div>
	<ul>
		<li><h6>학&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;번 : ${student.stdNo }</h6></li>
		<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : ${student.stdName }</h6></li>
		<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 : ${student.stdTelno }</h6></li>
		<li><h6>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소 : ${student.stdAddress }</h6></li>
		<li><h6>주민번호 : ${student.stdId }</h6></li>
		<li><h6>지도교수 : ${student.professor.proName }</h6></li>
	</ul>
</div>
<br/>
<div id="clazz">
	<h4>학생수강정보</h4>
	<table class="table table-bordered">
		<tr>
			<th>수강과목</th>
			<th>학점</th>
		</tr>
		<c:if test="${not empty student.clazzList }">
			<c:forEach items="${student.clazzList }" var="clazz">
				<tr>
					<td>${clazz.subject.subName }</td>
					<td>${clazz.clsScore }</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty student.clazzList }">
			<tr>
	            <td colspan="2">수강 정보 없음.</td>
	         </tr>
		</c:if>
	</table>
</div>
<script type="text/javascript">
$(modBtn).on("click",function(){
	location.href="<c:url value ='/staff/studentUpdate/${student.stdNo }'/>";
});
</script>