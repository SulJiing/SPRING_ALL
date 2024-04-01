<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<a href="?lang=ko">한글</a>
<a href="?lang=en">영어</a>

<form:form method="post" modelAttribute="cart" enctype="multipart/form-data">
	<input type="file" name="image"/>
	
	<form:input path="cartMember" />
	<form:errors path="cartMember"/>
	
	<form:input path="cartProd" />
	<form:errors path="cartProd"/>
	
	<form:input path="cartNo"/>
	<form:errors path="cartNo"/>
	
	<form:input path="cartQty" type="number"/>
	<form:errors path="cartQty"/>
	<form:button type="submit">전송</form:button>
</form:form>
</body>
</html>