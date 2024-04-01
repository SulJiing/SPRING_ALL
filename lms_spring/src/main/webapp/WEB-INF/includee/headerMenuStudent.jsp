<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="headMenu">      
	<div id="headLine"><span class="schoolName">##대학교포털 |</span><span class="userName"> ${student.stdName } 학생님</span></div>
	<a href="${pageContext.request.contextPath }/student/home" ><span class="menuText">HOME</span></a>
	<a href="${pageContext.request.contextPath }/student/lectureList"><span class="menuText">수강신청</span></a>
	<a href="${pageContext.request.contextPath }/student/classView"><span class="menuText">학점조회</span></a>
	<a href="javascript:;" id="logout"><span class="menuText">로그아웃</span></a>
</div>