<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>${stfId}님 환영합니다.</h2>
<ul>
	<li><a href='<c:url value="/staff/staffStudentList.do" />'>학생조회</a></li>
	<li><a href='<c:url value="/professor/professorList.do" />'>교수조회</a></li>
	<li><a>내정보</a></li>
</ul> 