<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="fileVO" method="POST" enctype="multipart/form-data"> <!-- multipart(나눠서 보내겠다)/form-data(name 갯수) -->
	<input type="file" name="uploadFile" />
	<form:errors path="uploadFile"/>
	<input type="file" name="uploadFile"/> 
	<form:errors path="uploadFile"/>
	<!-- value값(초기값) 설정이 불가능함 - 파일은 문자열이 아님 -->
	
<!-- 	<input type="text" name="uploader"/> -->
	<form:input path="uploader"/>
	<form:errors path="uploader"/>
	
<!-- 	<input type="number" name="count"/> -->
	<form:input path="count" type="number"/>
	<form:errors path="count"/>
	<button type="submit">업로드</button>
</form:form>
</body>
</html>