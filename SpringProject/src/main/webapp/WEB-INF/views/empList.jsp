<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 사원 조회</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>사원번호</th>
				<th>이름</th>
				<th>이메일</th>
				<th>입사일자</th>
				<th>업무</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${empList }" var="info">
				<tr>
					<td>${info.employeeId }</td>
					<td>${info.lastName }</td>
					<td>${info.email }</td>
					<td>${info.hireDate }</td>
					<td>${info.jobId }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>