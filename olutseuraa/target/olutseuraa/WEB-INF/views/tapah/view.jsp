
<%@ include file="head-include.jsp"%>
<body>
<%@ include file="header.jsp"%>

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
					<td><c:out value="${tapahtuma.maxOsallistujamaara}" /></td>
					<td><c:out value="${tapahtuma.isanta}" /></td>
					<td><c:out value="${tapahtuma.kuvaus}" /></td>
				</tr>
		</tbody>
	</table>
	
	
	
	
		<%@ include file="footer.jsp"%>

