<%@ include file="head-include.jsp"%>
<body>
	<%@ include file="header.jsp"%>

	<div class="row">
		<div
			class="event small-12 medium-8 medium-offset-2 columns main-content">

			<c:if test="${param.submitError eq true}">
				<p class="submitError">Tapahtui virhe.
					Tarkista kaikki kentät!</p>
			</c:if>

			<p>
			Aktivoi tunnuksesi antamalla sama sähköposti jolla ilmoittauduit tapahtumaan ja luo itsellesi salasana.
			</p>
			<form:form method="POST" action="aktivoi" modelAttribute="henkilo">
			
				<label:label path="sahkoposti">Anna Sähköposti: </label:label>
				<input:input type="email" path="sahkoposti" id="sahkoposti"></input:input>
				
				<label:label path="salasana">Anna salasana: </label:label>
				<input:input type="password" path="salasana" id="salasana"></input:input>
				
				<label:label path="salasana2">Anna salasana uudelleen: </label:label>
				<input:input type="password" path="salasana2" id="salasana2"></input:input>
				
				<button class="button success float-center" type="submit">Aktivoi!</button>
			</form:form>

 
		</div>
	</div>

	<script src="../resources/js/luoTapahtuma.js"></script>

	<%@ include file="footer.jsp"%>