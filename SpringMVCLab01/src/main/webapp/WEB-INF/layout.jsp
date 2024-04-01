<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/> </title>
<%-- <jsp:include page="/WEB-INF/includee/preScript.jsp" /> --%>
<tiles:insertAttribute name="preScript"/>
</head>

<body data-context-path="${pageContext.request.contextPath }">
<%-- <jsp:include page="/WEB-INF/includee/headerMenu.jsp" /> --%>
<tiles:insertAttribute name="headerMenu" />

<hr />

<%-- <jsp:include page="${contentPage }" /> --%>
<tiles:insertAttribute name="contentPage" /> <!-- views -->

<hr />

<%-- <jsp:include page="/WEB-INF/includee/postScript.jsp" /> --%>
<tiles:insertAttribute name="postScript" />
</body>
</html>














