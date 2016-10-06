
<%@ include file="head-include.jsp"%>
<body>
<%@ include file="header.jsp"%>

<div class="row">
		<div class="small-12 medium-8 medium-offset-2 columns main-content">

			<c:forEach items="${tapahtumat}" var="event" varStatus="iteration">

				<c:set var="paiva" value="${event.pvm}" />
				<c:set var="fmtpaiva" value="${fn:substring(paiva, 0, 2)}" />

				<div class="small-12 medium-9 small-centered">

					<div class="event small-12 medium-12 columns">

						<article>
							<div class="event-date">
								<p class="event-month">Lokakuu</p>
								<p class="event-day">
									<c:out value="${fmtpaiva}" />
								</p>
							</div>

							<div class="event-desc">
								<h4 class="event-desc-header">
									<c:out value="${event.nimi}" />
								</h4>
								<p class="event-desc-detail">
									<span class="event-desc-time"></span>${event.paikka}
								</p>
								<button type="button" id="<c:out value="${iteration.count}" />"
									class="button small success tutustu">Beer me, Marge!</button>
							</div>
						</article>

						<div class="small-12 medium-6 columns">
							<h5>
								Päivämäärä:
								<c:out value="${event.pvm}" />
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
						<div class="small-6 columns">
							<h5>
								Teema:
								<c:out value="${event.teema}" />
							</h5>
							
							<h5>
								Osallistujat: <span data-tooltip aria-haspopup="true"
									class="has-tip" data-disable-hover="false"
									title="<c:if test="${empty event.osallistujat}" >Ole ensimmäinen tapahtumaan ilmoittautuva!</c:if>
									<c:forEach items="${event.osallistujat}" varStatus="loop" var="i" ><c:out value="${i}"/><c:if test="${!loop.last}">, </c:if></c:forEach>">
									<c:out
										value="${fn:length(event.osallistujat)}/${event.maxOsallistujamaara}" />
								</span>
							</h5>
						

							<h5>
								Isäntä:
								<c:out value="${event.isanta}" />
							</h5>
						</div>
						<h4 class="text-center">"<c:out value="${event.kuvaus}" />"
						</h4>
						<article class="row expandable"
							id="<c:out value="${'exp'}${iteration.count}"/>">
							<hr>
							<h5 id="ilmo">Ilmoittaudu tapahtumaan</h5>
							<form:form method="POST" action="liity">
								<div class="row">
									<fieldset>
										<div class="medium-6 columns">

											<label>Etunimi <input type="text" name="etunimi"
												id="<c:out value="${'snimi'}${iteration.count}"/>"
												placeholder="Kirjoita tähän etunimesi">
											</label>
										</div>
										<div class="medium-6 columns">
											<label>Sukunimi <input type="text" name="sukunimi"
												id="<c:out value="${'enimi'}${iteration.count}"/>"
												placeholder="...ja tähän sukunimesi">
											</label>
										</div>

										<div class="medium-12 columns">
											<label>Sähköposti <input type="text" name="sposti"
												placeholder="...sähköpostiosoitteesi">
											</label> <input type="hidden" name="eventid"
												value="<c:out value="${event.id}"/>" />

										</div>
									</fieldset>
								</div>
								<button type="submit" class="button success float-center subcheck"
									id="<c:out value="${iteration.count}" /> ">
									Ilmoittaudu <i class="fi-check"></i>
								</button>
							</form:form>

						</article>
					</div>
				</div>
			</c:forEach>



		</div>
	</div>	
	


	<%@ include file="footer.jsp"%>
	