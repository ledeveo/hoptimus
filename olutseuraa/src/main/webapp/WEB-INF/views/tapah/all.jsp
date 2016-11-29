
<%@ include file="head-include.jsp"%>
<body>
	<%@ include file="header.jsp"%>

	<div class="row align-middle">
	
	
	
		<div class="column small-12 medium-6 medium-offset-3">
		
		<p class="submitError"><c:if test="${param.UserExists eq true}" >Kirjaudu sis‰‰n liitty‰ksesi tapahtumaan!</c:if></p>

			<c:forEach items="${tapahtumat}" var="event" varStatus="iteration">



				<c:set var="pvm" value="${event.pvm}" />
				<fmt:formatDate pattern="MM" value="${pvm}" var="kk" />
				<c:set var="osallistujat" value="${fn:length(event.osallistujat)}" />
				<c:set var="maxMaara" value="${event.maxOsallistujamaara}" />

				<div class="small-12 medium-12 small-centered event">



					<article>
						<div class="event-date">
							<p class="event-month"><c:out value="${kuukaudet[kk-1]}" /></p>
							<p class="event-day">
								<fmt:formatDate pattern="dd" value="${pvm}" />
							</p>
						</div>

						<div class="event-desc">
							<h4 class="event-desc-header">
								<c:out value="${event.nimi}" />
							</h4>
							<p class="event-desc-detail">
								<span class="event-desc-time"></span>${event.paikka}
							</p>

							<c:choose>
								<c:when test="${osallistujat == maxMaara}">
									<button type="button" id="<c:out value="${iteration.count}" />"
										class="button small alert" data-tooltip aria-haspopup="true"
										class="has-tip" data-disable-hover="false"
										title="T‰m‰ tapahtuma on t‰ynn‰!">D'oh!</button>
								</c:when>

								<c:otherwise>
									<sec:authorize access="hasRole('ROLE_USER')">
										<form action="kirjautunutLiity" method="POST">
											<input type="hidden" name="username" value="<sec:authentication property="principal.username"/>"/>
											<input type="hidden" name="eventid2" value="<c:out value="${event.id}"/>"/>
											osallistujia:
											<input type="number" name="osallistujamaara" min="1" max="${event.maxOsallistujamaara - osallistujat}" value="1">
											<button type="submit" class="button small success tutustu">Count me in!</button>
										</form>
									</sec:authorize>
									<sec:authorize access="!hasRole('ROLE_USER')">
										<button type="button" id="<c:out value="${iteration.count}" />"
										class="button small success tutustu">Beer me, Marge!</button>
									</sec:authorize>
									
								</c:otherwise>
							</c:choose>
						</div>
					</article>

					<div class="small-12 medium-6 columns">
						<h5>
							P‰iv‰m‰‰r‰:
							<fmt:formatDate pattern="dd.MM.yyyy" value="${pvm}" />
						</h5>
						<h5>
							Kellonaika:
							<c:out value="${event.aika}" />
						</h5>
						<h5>
							Ravintola:
							<c:out value="${event.paikka}" />
						</h5>
					</div>
					<div class="small-12 medium-6 columns">
						<h5>
							Teema:
							<c:out value="${event.teema}" />
						</h5>

						<h5>
							Osallistujat: <span data-tooltip aria-haspopup="true"
								class="has-tip" data-disable-hover="false"
								title="<c:if test="${empty event.osallistujat}" >Ole ensimm‰inen tapahtumaan ilmoittautuva!</c:if>
									<c:forEach items="${event.osallistujat}" varStatus="loop" var="i" ><c:out value="${i}"/><c:if test="${!loop.last}">, </c:if></c:forEach>">
								<c:out
									value="${fn:length(event.osallistujat)}/${event.maxOsallistujamaara}" />
							</span>
						</h5>

						<h5>
							Is‰nt‰:
							<c:out value="${event.isanta}" />
						</h5>
					</div>

					<div class="small-12 columns">
						<h4 class="text-center" style="margin: 0; padding: 0;">
							"
							<c:out value="${event.kuvaus}" />
							"
						</h4>
					</div>

					<div class="row">
						<article class="expandable small-12 columns"
							id="<c:out value="${'exp'}${iteration.count}"/>">
							<hr>
							<h5>Ilmoittaudu tapahtumaan</h5>
							<form:form method="POST" action="liity" modelAttribute="henkilo"
								id="liityform${iteration.count}">
								<fieldset>


									<div class="small-6 columns">

										<form:label path="etunimi">
											<spring:message code="henk.create.firstname" />
										</form:label>

										<form:input path="etunimi" id="etunimi" />

									</div>

									<div class="small-6 columns">

										<form:label path="sukunimi">
											<spring:message code="henk.create.lastname" />
										</form:label>

										<form:input path="sukunimi" id="sukunimi" />


									</div>


									<div class="small-12 columns">

										<form:label path="sahkoposti">
											<spring:message code="henk.create.email" />
										</form:label>

										<form:input path="sahkoposti" id="sahkoposti" />
										<label path="osallistujamaara"><spring:message code="henk.create.osallistujat" /></label>
										
										<input path="osallistujamaara" type="number" name="osallistujamaara" min="1" max="${event.maxOsallistujamaara - osallistujat}" value="1">
										
										<input type="hidden" name="eventid"
											value="<c:out value="${event.id}"/>" />
									</div>


								</fieldset>

								<br>
								<button type="submit" class="button success float-center"
									id="<c:out value="${iteration.count}" /> ">
									Ilmoittaudu <i class="fi-check"></i>
								</button>
							</form:form>

						</article>
					</div>
				</div>

			</c:forEach>

		</div>
		
	<div id="instafeed" class="show column small-12 medium-3"><img border="0" alt="ig" src="resources/img/instagram_logo.png" width="150" height="150"><br></div>
	</div>

	<%@ include file="footer.jsp"%>