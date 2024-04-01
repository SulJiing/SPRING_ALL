<%@page import="kr.or.ddit.vo.PropertyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 드라이버를 빌드패스(classpath)에 추가
	2. 드라이버 로딩(driver class loading)
		- (A a = new A(); - A클래스가 상수 메모리에 저장, A를 힙 메모리에 저장, 그 주소를 a에 가져다 줌 => 이렇게 안쓰려고 Class)
	3. 드라이버를 통해 Connection 수립
	4. 쿼리 객체(statement)
		1) Statement
		2) PreparedStatement
		3) CallableStatement
	5. 쿼리 작성(DDL(create, alter, drop), DML(insert,update,delete,select), TCL(commit,rollback)) -> 실행
		- ResultSet executeQuery: select
		- int executeUpdate		: insert,update,delete
	6. 실행 결과 집합의 사용 : cursor 기반의 데이터(record, tuple)를 포인터를 통해 접근함
	7. 사용한 모든 자원의 release. - try-with-resource구문
</pre>
<%
	List<PropertyVO> propList = (List) request.getAttribute("propList");
%>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>	
	</thead>
	<tbody>
	<%
		for(PropertyVO propVO : propList) {
	%>	
		<tr>
			<td><%=propVO.getPropertyName() %></td>
			<td><%=propVO.getPropertyValue() %></td>
			<td><%=propVO.getDescription() %></td>
		</tr>
	<%
		}
	%>
		
	</tbody>
</table>
<script>
	console.log($);
</script>