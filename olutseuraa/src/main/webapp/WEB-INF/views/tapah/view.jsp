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
<link rel="stylesheet" type="text/css" href="../resources/styles/tyylit.css">
</head>
<body>

	<h1>Olutseuraa - Hoptimus</h1>
	<p>Olutseuraa vuodesta 2016 lähtien</p>

	<table>
		<caption>Tapahtuma</caption>
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
				<tr>
					<td><c:out value="${tapahtuma.id}" /></td>
					<td><c:out value="${tapahtuma.nimi}" /></td>
					<td><c:out value="${tapahtuma.pvm}" /></td>
					<td><c:out value="${tapahtuma.aika}" /></td>
					<td><c:out value="${tapahtuma.paikka}" /></td>
					<td><c:out value="${tapahtuma.teema}" /></td>
					<td><c:out value="${tapahtuma.osallistujat}" /></td>
					<td><c:out value="${tapahtuma.isanta}" /></td>
					<td><c:out value="${tapahtuma.kuvaus}" /></td>
				</tr>
		</tbody>
	</table>
</body>
</html>
