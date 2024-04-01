<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<tiles:insertAttribute name="preScript"/>
<style type="text/css">
.menuText{
	font-size: 25px;
	color: white;
	margin: 50px;
}
.menuText:hover {
    color: black;
    transition: color 0.3s;
}
#headMenu {
    padding-top: 70px;
    width: 100%;
    height: 120px;
    background-color: olive;
    text-align: center;
}
#headLine{
	position: absolute;
	top: 10px;
	left: 10px;
	color: silver;
}
.schoolName{
	font-size: 40px;
}
.userName{
	font-size: 20px;
}
#table{
	width: 1200px;
	margin-top: 50px;
	text-align: center;
}
#items{
	width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert('${message}');
	</script>
</c:if>
</head>
<body data-context-path="${pageContext.request.contextPath }">
<tiles:insertAttribute name="headerMenu"/>
<tiles:insertAttribute name="content"/>
<tiles:insertAttribute name="postScript"/>
<form id="logoutForm" action="${pageContext.request.contextPath }/logout" method="post"></form>
<script type="text/javascript">
	$("#logout").on("click", (event)=>{
		event.preventDefault();

        $("#logoutForm").get(0).submit();

		return false;
	});
</script>
</body>
</html>