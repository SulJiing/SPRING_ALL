<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO member = (MemberVO) request.getAttribute("member");
%>
<table class="table table-bordered">
<tr><th>회원아이디</th><td><%=member.getMemId() %></td></tr>
<tr><th>비밀번호</th><td><%=member.getMemPass() %></td></tr>
<tr><th>이름</th><td><%=member.getMemName() %></td></tr>
<tr><th>주민번호1</th><td><%=member.getMemRegno1() %></td></tr>
<tr><th>주민번호2</th><td><%=member.getMemRegno2() %></td></tr>
<tr><th>생일</th><td><%=member.getMemBir() %></td></tr>
<tr><th>우편번호</th><td><%=member.getMemZip() %></td></tr>
<tr><th>주소1</th><td><%=member.getMemAdd1() %></td></tr>
<tr><th>주소2</th><td><%=member.getMemAdd2() %></td></tr>
<tr><th>집전번</th><td><%=member.getMemHometel() %></td></tr>
<tr><th>회사전번</th><td><%=member.getMemComtel() %></td></tr>
<tr><th>휴대폰</th><td><%=member.getMemHp() %></td></tr>
<tr><th>이메일</th><td><%=member.getMemMail() %></td></tr>
<tr><th>직업</th><td><%=member.getMemJob() %></td></tr>
<tr><th>취미</th><td><%=member.getMemLike() %></td></tr>
<tr><th>기념일</th><td><%=member.getMemMemorial() %></td></tr>
<tr><th>기념일자</th><td><%=member.getMemMemorialday() %></td></tr>
<tr><th>마일리지</th><td><%=member.getMemMileage() %></td></tr>
<tr><th>탈퇴여부</th><td><%=member.isMemDelete() %></td></tr>
	
</table>