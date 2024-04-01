<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
    <script>
	    function setActionBasedOnRole(role) {
	        var form = document.getElementById("loginForm");
	        var url = '/login/loginProcess.do'; // Default URL
	
	        switch (role) {
	            case "professor":
	                url = 'loginProcess/pro.do';
	                break;
	            case "student":
	                url = 'loginProcess/std.do';
	                break;
	            case "staff":
	                url = 'loginProcess/stf.do';
	                break;
	        }
	
	        form.action = url;
	    }
</script>
</head>
<body>
<h2>Login Form</h2>

<c:if test="${not empty sessionScope.message}">
    <p style="color: red;">${sessionScope.message}</p>
    <c:remove var="message" scope="session" />
</c:if>


<form id ="loginForm" action="<c:url value='/login/loginProcess.do' />" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label></label>
    <input type="radio" name="role" value="student" onclick="setActionBasedOnRole('student')" required> Student
    <input type="radio" name="role" value="professor" onclick="setActionBasedOnRole('professor')" required> Professor
    <input type="radio" name="role" value="staff" onclick="setActionBasedOnRole('staff')" required> Staff<br>

    <button type="submit">Login</button>
</form>

</body>
</html>