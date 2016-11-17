<%@ include file="head-include.jsp"%>
<body>
	<%@ include file="header.jsp"%>

	<div class="row">
		<div
			class="event small-12 medium-8 medium-offset-2 columns main-content">

			<!-- TODO: tulosta käyttäjän tiedot ja muokkausmahdollisuus -->
			<sec:authentication property="principal.username"/></h3>

			<sec:authorize access="hasRole('ROLE_USER')">
				<h2>Hei <c:out value="${henkilo.etunimi}"></c:out>!</h2>
				<p>
				Tapahtumasi johon olet liittynyt:
				</p>
				
				<!-- TODO: tulosta tapahtumat joihin käyttäjä on liittynyt ja anna mahdollisuus perua liittyminen -->
				<table>
					<thead>
						<tr>
							<td>tapahtuman nimi</td><td>alkamisaika</td><td>henkilöitä</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tapahtumat}" var="t">
							<tr>
								<td>
									<c:out value="${t.nimi}"></c:out>
								</td>
								<td>
									<c:out value="${t.pvm}"></c:out>
									<c:out value="${t.aika}"></c:out>
								</td>
								<td>
									<c:out value="${t.osallistujamaara}"></c:out>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</sec:authorize>
			
		</div>
	</div>

	<script src="../resources/js/luoTapahtuma.js"></script>

	<%@ include file="footer.jsp"%>