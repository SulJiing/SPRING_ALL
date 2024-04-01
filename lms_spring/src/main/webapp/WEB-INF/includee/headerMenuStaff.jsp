<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="headMenu">
	<div id="headLine"><span class="schoolName">##대학교포털 |</span><span class="userName"> ${staff.id } 직원님</span></div>
	<a href="${pageContext.request.contextPath }/staff/home" ><span class="menuText">HOME</span></a>
	<a href="${pageContext.request.contextPath }/staff/professorList"><span class="menuText">교수관리</span></a>
	<a href="${pageContext.request.contextPath }/staff/studentList"><span class="menuText">학생관리</span></a>
	<a href="javascript:;" id="logout"><span class="menuText">로그아웃</span></a>
</div>