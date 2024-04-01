<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.login {
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    height: 50%;
    width: 50%;
}
#btn, #id, #pass {
    margin: 0 auto; /* 수평 가운데 정렬을 위해 추가 */
    display: block; /* 수평 가운데 정렬을 위해 추가 */
    width: 400px;
}

#btn {
    margin-top: 20px; /* 버튼과 다른 입력 요소 간의 간격을 조절하기 위해 추가 */
}
</style>
</head>
<body>
<div class="login">
	<h1>로그인</h1>
	<hr/>
	<form method="post">
		<input type="radio" value="student" name="gubun" required checked="checked"/>학생&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" value="professor" name="gubun" required/>교수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" value="staff" name="gubun" required/>교직원
		<br/>
		<br/>
		<input type="text" name="id" placeholder="id" class="form-control size" id="id" required/>
		<br/>
		<input type="password" name="pw" placeholder="pw" class="form-control size" id="pass" required/>
		<br/>
		<button type="submit" class="btn btn-primary" id="btn">전송</button>
	</form>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/app/common/main.js"></script>
<link href="${pageContext.request.contextPath}/resources/assets/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>