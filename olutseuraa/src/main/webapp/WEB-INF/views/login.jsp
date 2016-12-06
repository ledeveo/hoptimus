<%@ include file="tapah/head-include.jsp"%>
<body>
	<%@ include file="tapah/header.jsp"%>

<div class="row">
	<!-- Joko modaaleihin tai alertteihin -->
	<div class="columns small-12 medium-11 center" style="margin-bottom: 15px;">
	
		<c:if test="${not empty loginerror}" >
			<div data-alert class="center alert-box alert">
		  		Sisäänkirjautuminen epäonnistui. Käyttäjätunnus tai salasana on syötetty väärin.
		  		<b class="float-right"><a style="color: white;" href="#" onclick="$(this).parent().parent().parent().hide()"> sulje &times;</a></b>
		  	</div>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="small-12 medium-6 medium-offset-3 columns">
		<h1>KIRJAUDU SISÄÄN</h1>
		
		<form action="j_spring_security_check" method="post">
			<fieldset>
				<table>
					<tr><td>USERNAME:</td><td><input type='text' name='j_username' value='' required></td></tr>
					<tr><td>PASSWORD:</td><td><input type='password' name='j_password' required/></td></tr>
					<tr><td>&nbsp;</td><td><button class="button" type="submit">Kirjaudu</button></td></tr>
				</table>
			</fieldset>
		</form>
	</div>
</div>
<%@ include file="tapah/footer.jsp"%>