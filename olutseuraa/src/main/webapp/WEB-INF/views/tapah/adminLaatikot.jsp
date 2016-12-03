
	<div class="small-12 medium-12 small-centered event">
	<form action="muokkaaTapahtuma" method="POST">
			
		<article>
			<div class="event-date">
				<p class="event-month"><c:out value="${kuukaudet[kk-1]}" /></p>
				<p class="event-day">
					<fmt:formatDate pattern="dd" value="${pvm}" />
				</p>
				alkuun aikaa: <h5 class="alkamiseenAikaa center" >${event.pvm}T${event.aika}:00Z</h5>
			</div>
	
			<div class="event-desc">
				<h4 class="event-desc-header">
					<input type="text" name="nimi" path="nimi" value="${event.nimi}"/>
				</h4>
				<p class="event-desc-detail">
					<i class="step fi-marker size-36"></i> <span class="event-desc-time"></span>${event.paikka}
				</p>
	
			</div>
		</article>
	
		<div class="small-12 medium-6 columns">
			<h5>
				<i class="step fi-calendar size-24"></i> P�iv�m��r�:
				<input type="date" name="pvm" id="datepicker" path="pvm" value="${pvm}"/>
			</h5>
			<h5>
				<i class="step fi-clock size-24"></i> Kellonaika:
				<input type="text" name="aika" id="eventTime" class="time ui-timepicker-input" value="${event.aika}"/>
			</h5>
			<h5>
				<i class="step fi-marker size-36"></i>  Ravintola:
				<input type="text" name="paikka" class="time ui-timepicker-input" value="${event.paikka}"/>
			</h5>
		</div>
		<div class="small-12 medium-6 columns">
			<h5>
				<i class="step fi-burst size-36"></i> Teema:
				<input type="text" name="teema" value="${event.teema}"/>
			</h5>
	
			<h5>
				<i class="step fi-torsos size-24"></i> <span data-tooltip aria-haspopup="true"
					class="has-tip" data-disable-hover="false"
					title="<c:if test="${empty event.osallistujat}" >Ole ensimm�inen tapahtumaan ilmoittautuva!</c:if>
						<c:forEach items="${event.osallistujat}" varStatus="loop" var="i" ><c:out value="${i}"/><c:if test="${!loop.last}">, </c:if></c:forEach>">

					<c:out value="${fn:length(event.osallistujat)}/${event.maxOsallistujamaara}" />

						</span>
					
					<input type="number" name="maxOsallistujamaara2" value="${event.maxOsallistujamaara}"/>
			</h5>
	
			<h5>
				<i class="step fi-foot size-36"></i> Is�nt�:
				<input type="text" name="isanta" value="${event.isanta}"/>
			</h5>
		</div>
	
		<div class="small-12 columns">
			<h4 class="text-center" style="margin: 0; padding: 0;">
				"
				<input type="text" name="kuvaus" value="${event.kuvaus}"/>
				"
			</h4>
		</div>
		
		<div>
			<input type="hidden" name="tapahId" value="${event.id }"/>
			<button type="submit" name="toiminto" value="poista" class="button small alert float-left"><i class="step fi-x size-36"></i> poista</button>
			<button type="submit" name="toiminto" value="tallenna" class="button small float-right"><i class="step fi-check size-36"></i> tallenna</button>
		</div>
		
	</form>
	</div>