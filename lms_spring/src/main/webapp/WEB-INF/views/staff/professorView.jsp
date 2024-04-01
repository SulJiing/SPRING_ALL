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
<h1>${professor.proName } 교수 상세조회</h1>
<hr/>
<div id="detail">
	<div id="line">
	<h4>교수기본정보</h4>
	<button id="modBtn" class="btn btn-primary">수정</button>
	</div>
	<ul>
		<li><h6>교수번호 : ${professor.proNo }</h6></li>
		<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : ${professor.proName }</h6></li>
		<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공 : ${professor.proMajor }</h6></li>
		<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 : ${professor.proTelno }</h6></li>
	</ul>
</div>
<br/>
<div id="clazz">
	<h4>개설강의정보</h4>
	<table class="table table-bordered">
		<tr>
			<th>과목명</th>
			<th>강의시간</th>
			<th>강의요일</th>
			<th>강의실</th>
		</tr>
		<c:if test="${not empty professor.lectureList }">
			<c:forEach items="${professor.lectureList }" var="lecture">
				<tr>
					<td>${lecture.subject.subName }</td>
					<td>${lecture.lecTime }</td>
					<td>${lecture.lecDay }</td>
					<td>${lecture.lecRoom }</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty professor.lectureList }">
			<tr>
	            <td colspan="2">수강 정보 없음.</td>
	         </tr>
		</c:if>
	</table>
</div>
<script type="text/javascript">
$(modBtn).on("click",function(){
	location.href="<c:url value ='/staff/professorUpdate/${professor.proNo}'/>";
});
</script>