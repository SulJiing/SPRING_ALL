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
li{
	display: flex; 
}
.form-control{
	width: 200px;
}
#updateForm{
	margin-top: 30px;
}
</style>
<h1>${professor.proName } 교수 수정</h1>
<hr/>
<div id="detail">
	<div id="line">
	<h4>교수기본정보</h4>
	<button id="saveBtn" class="btn btn-primary">저장</button>
	</div>
	<form method="post" id="updateForm">
		<ul>
			<li><h6>교수번호 :&nbsp; </h6><input type="text" name="proNo" readonly class="form-control" value="${professor.proNo }" /></li>
			<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 :&nbsp; </h6><input type="text" name="proName" class="form-control" value="${professor.proName }"/></li>
			<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공 :&nbsp; </h6><input type="text" name="proMajor" class="form-control" value="${professor.proMajor }"/></li>
			<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 :&nbsp; </h6><input type="text" name="proTelno" class="form-control" value="${professor.proTelno }"/></li>
		</ul>
	</form>
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
<table>
	<tr>
		<th></th>
		<td>
			<form:input type="text" path="proNo" cssClass="form-control" />
			<form:errors path="proNo" element="span" cssClass="text-danger" />
		</td>
	</tr>
	<tr>
		<th></th>
		<td>
			<form:input type="text" path="proName" cssClass="form-control" /> 
			<form:errors path="proName" element="span" cssClass="text-danger" />
		</td>
	</tr>
	<tr>
		<th></th>
		<td>
			<form:input type="text" path="proMajor" cssClass="form-control" /> 
			<form:errors path="proMajor" element="span" cssClass="text-danger" />
		</td>
	</tr>
	<tr>
		<th></th>
		<td>
			<form:input type="text" path="proTelno" cssClass="form-control" /> 
			<form:errors path="proTelno" element="span" cssClass="text-danger" />
		</td>
	</tr>
</table>
<script type="text/javascript">
$(saveBtn).on("click",function(event){
	$(updateForm).submit();
});
</script>