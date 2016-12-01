
<%@ include file="head-include.jsp"%>

<body onload="haeElementit()">

	<%@ include file="header.jsp"%>

	<div class="row">
		<div class="event small-12 medium-8 medium-offset-2 columns main-content">
			
			<div class="submitSuccess">
				<c:if test="${param.tapahtumaAdded eq true}" >
					  <h4>Tapahtumaan liittyminen lisätty!</h4>
				</c:if>
				<c:if test="${param.tapahtumaRemoved eq true}" >
					  <h4>Tapahtumaan liittyminen poistettu!</h4>
				</c:if>
			</div>
			
			<!-- TODO: tulosta käyttäjän tiedot ja muokkausmahdollisuus -->
			<span class="float-right">tili: <sec:authentication property="principal.username"/></span>

			<sec:authorize access="hasRole('ROLE_USER')">
				<h2 class="center">Hei <c:out value="${henkilo.etunimi}"></c:out> <c:out value="${henkilo.sukunimi}"></c:out>!</h2>
				
				<h5 class="center">Tapahtumasi johon olet liittynyt:</h5>
				
				<table>
					<thead>
						<tr>
							<th class="center">tapahtuman nimi</th><th class="center">alkaa</th><th colspan="3" class="center">osallistumiset</th><th class="center">osallistujat</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tapahtumat}" var="t">
							<tr class="center">
								<td>
									<h5><c:out value="${t.nimi}"></c:out></h5>
								</td>
								<td class="center">
								
									<h5 style="color: green;" class="alkamiseenAikaa center" >${t.pvm}T${t.aika}:00Z</h5>
									<div class="row center">
									<i class="step fi-calendar size-18"></i> <fmt:formatDate pattern="dd.MM.yyyy" value="${t.pvm}" /> <i class="step fi-clock size-18"></i> <c:out value="${t.aika}"></c:out>
									</div>
								</td>
								<td class="center">
									<form action="PoistaLiittyminen" method="POST">
											<input type="hidden" name="tapahtumaId" value="${t.id}"/>
										<button data-tooltip title="Vähennä kavereita tai itsesi liittymisistä." class="button alert" type="submit">-</button>
									</form>
								</td>
								<td class="center">
									1<c:out value=" + ${t.osallistujamaara - 1}"></c:out> <i class="step fi-male size-18"></i>
								</td>
								<td class="center">
									<c:if test="${t.maxOsallistujamaara > fn:length(t.osallistujat)}">
										<form action="LisaaLiittyminen" method="POST">
											<input type="hidden" name="tapahtumaId" value="${t.id}"/>
											<button data-tooltip title="Lisää kaveri mukaan!" class="button success" type="submit">+</button>
										</form>
									</c:if>
								</td>
								<td class="center">
									<c:out value="${fn:length(t.osallistujat)}"></c:out>/<c:out value="${t.maxOsallistujamaara}"></c:out> <i class="step fi-torsos size-18"></i>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</sec:authorize>
			
		</div>
	</div>
	
	<%@ include file="footer.jsp"%>