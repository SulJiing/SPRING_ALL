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
<input type="radio" name="contentType" value="application/x-form-urlencoded"/>parameter
<input type="radio" name="contentType" value="application/json"/>json
<!-- modelAttribute form태그 안에서만 작용함 -->
<form:form method="post" modelAttribute="property">
	<form:input path="propertyName" required="true"/>
	<form:errors path="propertyName" element="span" cssClass="error"/>
	
	<form:input path="propertyValue" required="true"/>
	<form:errors path="propertyValue" element="span" cssClass="error"/>
	
	<form:input path="description"/>
	<form:errors path="description" element="span" cssClass="error"/>
	
	<form:input path="propDate" type="date"/>
	<form:errors path="propDate" element="span" cssClass="error"/>
	
	<form:input path="propTimestamp" type="datetime-local"/>
	<form:errors path="propTimestamp" element="span" cssClass="error"/>
	
	<form:button type="submit">전송</form:button>
</form:form>
<!-- 1. 등록의 모든 절차는 비동기로 처리할 것 -->
<!-- 2. 등록에 사용되는 모든 컨텐츠는 json으로 송수신할 것 -->
<!-- 3. contentType 라디오 버튼에 따라 전송 컨텐츠의 종류가 달라짐 -->
</body>
</html>