<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- multipart(나눠서 보내겠다)/form-data(name값) -->
<form method="POST" enctype="multipart/form-data"> 
	<input type="file" name="uploadFile"/>
	<input type="text" name="uploader"/>
	<input type="number" name="count"/>
	<button type="submit">업로드</button>
</form>
</body>
</html>