<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<li>
		<a href="<%=request.getContextPath() %>">INDEX</a>
	</li>
	<li>
		회원관리
	</li>
	<%
		
	%>
	<li>
		<a href="${pageContext.request.contextPath }/09/propView.do">프로퍼티관리</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath }/12/jdbcDesc.do">프로퍼티목록조회</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath }/10/mbtiForm.do">MBTI조회</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath }/address/addressForm.do">주소록</a>
	</li>
	<li>
		<a href="${pageContext.request.contextPath }/message/messageForm.do">메세지작성</a>
	</li>
</ul>