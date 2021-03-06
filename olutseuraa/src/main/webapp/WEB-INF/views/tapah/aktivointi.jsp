<%@ include file="head-include.jsp"%>
<body>
	<%@ include file="header.jsp"%>

	<div class="row">
		<div
			class="event small-12 medium-8 medium-offset-2 columns main-content">

			<div class="submitError">
				<c:if test="${param.submitError eq true}">
					<h4>Tapahtui virhe. Tarkista kaikki kentät!</h4>
				</c:if>
				<c:if test="${param.userNotActivated eq true}" >
					<h4>Käyttäjää ei ole aktivoitu, tarkista sähköpostisi aktivointilinkkiä varten!</h4>
				</c:if>
			</div>

			<p>
			Aktivoi tunnuksesi antamalla sama sähköposti jolla ilmoittauduit tapahtumaan ja luo itsellesi salasana.
			</p>
			
			<form method="POST" action="aktivoi<c:out value="${id}"></c:out>">
			
				<label>Anna Sähköposti: </label>
				<input type="email" name="sahkoposti" required></input>
				
				<label >Anna salasana: </label>
				<input type="password" name="salasana" required></input>
				
				<label >Anna salasana uudelleen: </label>
				<input type="password" name="salasana2" required></input>
				
				<button class="button success float-center" type="submit">Aktivoi!</button>
				
			</form>
 
		</div>
	</div>

	<script src="../resources/js/luoTapahtuma.js"></script>

	<%@ include file="footer.jsp"%>