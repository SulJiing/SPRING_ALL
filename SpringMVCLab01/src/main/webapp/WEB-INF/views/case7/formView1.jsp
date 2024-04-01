<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="border : 1px solid red;">
${requestScope }
</div>
<form method="post">
<pre>
	<input type="text" name="strParam" value="${sample.strParam }"/>
	<input type="number" name="numParam" value="${sample.numParam }"/>
	<input type="text" name="chParam" value="${sample.chParam }"/>
	<input type="date" name="dateParam" value="${sample.dateParam }"/>
	<input type="datetime-local" name="dateTimeParam" value="${sample.dateTimeParam }"/>
	<input type="number" name="optionParam" value="${sample.optionParam }"/>
	<button type="submit">전송</button>
</pre>
</form>