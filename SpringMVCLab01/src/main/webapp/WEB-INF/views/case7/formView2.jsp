<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div style="border : 1px solid red;">
	${org.springframework.validation.BindingResult.MODEL_KEY_PREFIX.sample }
</div>
<!-- sample 얘가 이 안에서 모든 것들을 정의함 - VO -->
<form:form method="post" modelAttribute="sample">
<pre>
<%-- 	<input type="text" name="strParam" value="${sample.strParam }"/> --%>
<!-- 	input의 id,name 을 넣으면 동적으로 정해짐, value="${sample.strParam }"이 자동으로 됨 -->
	<form:input path="strParam"/>
<!-- 	span 태그를 동적으로 만듦 -->
	<form:errors path="strParam" element="span" cssClass="error"/>
	
<%-- 	<input type="number" name="numParam" value="${sample.numParam }"/> --%>
	<form:input path="numParam" type="number"/>
	<form:errors path="numParam" element="span" cssClass="error"/>
	
<%-- 	<input type="text" name="chParam" value="${sample.chParam }"/> --%>
	<form:input path="chParam" />
	<form:errors path="chParam" element="span" cssClass="error"/>
	
<%-- 	<input type="date" name="dateParam" value="${sample.dateParam }"/> --%>
	<form:input path="dateParam" type="date"/>
	<form:errors path="dateParam" element="span" cssClass="error"/>
	
<%-- 	<input type="datetime-local" name="dateTimeParam" value="${sample.dateTimeParam }"/> --%>
	<form:input path="dateTimeParam" type="datetime-local"/>
	<form:errors path="dateTimeParam" element="span" cssClass="error"/>
	
<%-- 	<input type="number" name="optionParam" value="${sample.optionParam }"/> --%>
	<form:input path="optionParam" type="number"/>
	<form:errors path="optionParam" element="span" cssClass="error"/>
	
<!-- 	<button type="submit">전송</button> -->
	<form:button type="submit">전송</form:button>
	
<!-- 	has a -->
<!-- 	<input type="text" name="innerParam1"/> -->
	<form:input path="inner.innerParam1"/>
	<form:errors path="inner.innerParam1"/>
<!-- 	<input type="number" name="innerParam2"/> -->
	<form:input path="inner.innerParam2" type="number"/>
	<form:errors path="inner.innerParam2"/>
	
<!-- 	has many -->
<!-- 	innerList[0] - 한세트가 되는 것 -->
<!-- value가 자동으로 들어가짐 -->
	<form:input type="text" path="inner.innerList[0].innerParam3"/>
	<form:errors path="inner.innerList[0].innerParam3"/>
	
	<form:input path="inner.innerList[0].innerParam4"/>
	<form:errors path="inner.innerList[0].innerParam4"/>
	
	<form:input path="inner.innerList[1].innerParam3"/>
	<form:errors path="inner.innerList[1].innerParam3"/>
	
	<form:input path="inner.innerList[1].innerParam4"/>
	<form:errors path="inner.innerList[1].innerParam4"/>
</pre>
</form:form>