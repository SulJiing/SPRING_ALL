<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="headMenu">
	<div id="headLine"><span class="schoolName">##대학교포털 |</span><span class="userName"> ${professor.proName } 교수님</span></div>
	<a href="${pageContext.request.contextPath }/professor/home"><span class="menuText">HOME</span></a>
	<a href="${pageContext.request.contextPath }/professor/lectureListPro"><span class="menuText">내강의조회</span></a>
	<a href="javascript:;" id="logout"><span class="menuText">로그아웃</span></a>
</div>