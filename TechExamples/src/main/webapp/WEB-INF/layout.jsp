<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<style type="text/css">
	header td{
		float: left;
		padding-left: 16px;
		padding-right: 16px;
	}
</style>
<tiles:insertAttribute name="preScript"/>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>
</head>
<body data-context-path="${pageContext.request.contextPath }">
<header>
<tiles:insertAttribute name="headerMenu" />
</header>
<main class="row container-fluid">
<tiles:insertAttribute name="contentPage"/>
</main>
<hr />
<tiles:insertAttribute name="postScript"/>
</body>
</html>














