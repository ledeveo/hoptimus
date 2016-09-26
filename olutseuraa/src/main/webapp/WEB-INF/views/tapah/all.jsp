<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Olutseuraa - Hoptimus</title>
<link rel="stylesheet" type="text/css" href="resources/styles/tyylit.css">
</head>
<body>

	<h1>Olutseuraa - Hoptimus</h1>
	<p>Olutseuraa vuodesta 2016 lähtien</p>

	<table>
		<caption>Tapahtumat</caption>
		<thead>
			<tr>
				<th>id</th>
				<th>nimi</th>
				<th>pvm</th>
				<th>aika</th>
				<th>paikka</th>
				<th>teema</th>
				<th>osallistujat</th>
				<th>isanta</th>
				<th>kuvaus</th>
			<tr>
		</thead>
		<tbody>
			<c:forEach items="${tapahtumat}" var="tap">
				<tr>
					<td><c:out value="${tap.id}" /></td>
					<td><c:out value="${tap.nimi}" /></td>
					<td><c:out value="${tap.pvm}" /></td>
					<td><c:out value="${tap.aika}" /></td>
					<td><c:out value="${tap.paikka}" /></td>
					<td><c:out value="${tap.teema}" /></td>
					<td><c:out value="${tap.osallistujat}" /></td>
					<td><c:out value="${tap.isanta}" /></td>
					<td><c:out value="${tap.kuvaus}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
