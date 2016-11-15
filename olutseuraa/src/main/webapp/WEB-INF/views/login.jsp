<%@ include file="tapah/head-include.jsp"%>
<body>
	<%@ include file="tapah/header.jsp"%>

<div id="sisalto">
	<h1>KIRJAUDU SISÄÄN</h1>

	<c:if test="${not empty loginerror}">
		<p class="Error">Sisäänkirjautuminen epäonnistui. Käyttäjätunnus tai salasana on syötetty väärin.</p>
	</c:if>

	<c:if test="${not empty loggedout}">
		<p class="Info">Uloskirjautuminen onnistui</p>
	</c:if>
	
	<form action="j_spring_security_check" method="post">
		<fieldset>
			<table>
				<tr><td>USERNAME:</td><td><input type='text' name='j_username' value='' required></td></tr>
				<tr><td>PASSWORD:</td><td><input type='password' name='j_password' required/></td></tr>
				<tr><td>&nbsp;</td><td><button type="submit">Kirjaudu</button></td></tr>
			</table>
		</fieldset>
	</form>
</div>
<%@ include file="tapah/footer.jsp"%>