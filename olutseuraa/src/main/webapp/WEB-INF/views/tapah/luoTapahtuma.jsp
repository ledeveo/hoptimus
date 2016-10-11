<%@ include file="head-include.jsp"%>
<body>
	<%@ include file="header.jsp"%>

	<div class="row">
		<div
			class="event small-12 medium-8 medium-offset-2 columns main-content">

			<c:if test="${param.submitError eq true}">
				<p class="submitError">Tapahtui virhe lis‰tt‰ess‰ tapahtumaa.
					Tarkista kaikki kent‰t!</p>
			</c:if>

			<form:form modelAttribute="tapahtuma" method="POST">
				<fieldset>

					<spring:hasBindErrors name="tapahtuma">
						<p class="Virheotsikko">
							<spring:message code="tapah.create.errors" />
							:
						</p>
						<div class="Virheblokki">
							<form:errors path="*" />
						</div>
					</spring:hasBindErrors>

					<h4 class="text-center">Tapahtuman tiedot</h4>
					<p>
						<form:label class="lead" path="nimi">Tapahtuman nimi</form:label>
						<form:input path="nimi"  cssErrorClass="VirheellinenKentta"/>
						<form:errors path="nimi" cssClass="Virheteksti" />
					</p>
					<div class="row">
						<p class="small-6 columns">
							<form:label class="lead" path="pvm">P‰iv‰m‰‰r‰</form:label>
							<form:input type="text" id="datepicker" path="pvm"/>
						</p>
						
						<p class="small-6 columns">
							<form:label class="lead" path="aika">Kellonaika</form:label>
							<form:input type="time" path="aika" />
						</p>
					</div>
					<div class="row">
						<p class="small-6 columns">
							<form:label class="lead" path="paikka">Paikka</form:label>
							<form:input path="paikka" />
						</p>
						<p class="small-6 columns">
							<form:label class="lead" path="teema">Teema</form:label>
							<form:input path="teema" />
						</p>
					</div>
					<div class="row">
						<p class="small-6 columns">
							<form:label class="lead" path="isanta">Is‰nt‰</form:label>
							<form:input path="isanta" />
						</p>
						<p class="small-6 columns">
							<form:label class="lead" path="maxOsallistujamaara">Enint‰‰n osallistujia</form:label>
							<form:select path="maxOsallistujamaara">
								<form:option value="0" label="0" />
								<form:options items="${osallistujat}" />
							</form:select>
						</p>


					</div>
					<p>
						<form:label class="lead" path="kuvaus">Kuvaus tapahtumalle</form:label>
						<form:input path="kuvaus" />
					</p>

					<p>
						<button type="submit" class="button success float-center">Luo
							tapahtuma</button>
					</p>
				</fieldset>
			</form:form>

 
		</div>
	</div>

	<script src="../resources/js/luoTapahtuma.js"></script>

	<%@ include file="footer.jsp"%>