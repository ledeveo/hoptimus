<%@ include file="head-include.jsp"%>
<body>
	<%@ include file="header.jsp"%>

	<div class="row">
		<div class="small-12 medium-8 medium-offset-2 columns main-content">

			<form:form modelAttribute="tapahtuma" method="POST">
				<fieldset>
					<legend>Tapahtuman tiedot</legend>
					<p>
						<form:label path="nimi">Tapahtuman nimi</form:label>
						<form:input path="nimi" />
					</p>
					<p>
						<form:label path="pvm">p‰iv‰m‰‰r‰</form:label>
						<form:input path="pvm" />
					</p>
					<p>
						<form:label path="aika">ajankohta</form:label>
						<form:input path="aika" />
					</p>
					<p>
						<form:label path="paikka">paikka</form:label>
						<form:input path="paikka" />
					</p>
					<p>
						<form:label path="teema">teema</form:label>
						<form:input path="teema" />
					</p>
					<p>
						<form:label path="isanta">is‰nt‰</form:label>
						<form:input path="isanta" />
					</p>
					<p>
						<form:label path="kuvaus">kuvaus tapahtumalle</form:label>
						<form:input path="kuvaus" />
					</p>
					<p>
						<form:label path="maxOsallistujamaara">enint‰‰n osallistujia</form:label>
						<form:input path="maxOsallistujamaara" />
					</p>
					<p>
						<button type="submit">Luo tapahtuma</button>
					</p>
				</fieldset>
			</form:form>

		</div>
	</div>

	<%@ include file="footer.jsp"%>