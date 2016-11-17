<div class="container">
<header class="header show-for-medium">
	<h1 class="headline  pressStart"><a href="/olutseuraa/tapahtumat">
		Olutseuraa <small>by HoptimusPrime</small></a>
	</h1>
	<ul class="header-subnav">
		<li class="small-offset-1">
			<a href="tapahtumat" id="kaikki">Hae tapahtumia</a></li>
		
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="uusi" id="uusi">Luo tapahtuma</a></li>
			</sec:authorize>
			
			
			<sec:authorize access="!hasRole('ROLE_USER')">
				<li class="float-right"><a href="login"><i class="fi-arrow-right"></i><small>Kirjaudu</small></a></li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_USER') && !hasRole('ROLE_ADMIN')">
				<li style="color: white;"><a href="userpage">Käyttäjäsivu</a></li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_USER')">
				<li class="float-right"><a href="j_spring_security_logout"><i class="fi-arrow-right"></i><small>Kirjaudu ulos</small></a></li>
			</sec:authorize>

	</ul>
</header>
<div id="body">

<c:if test="${not empty tervehdittava}">
	<div id="success" class="callout success" data-closable>
		<div>
		<p>
			Tervetuloa tapahtumaan <b><c:out value="${tapahtuma.nimi}" /></b>,
			<c:out value="${tervehdittava.etunimi}" />!
			<br> Lähetämme tapahtumasta vielä muistutuksen sähköpostiisi!
		</p>
		</div>
		<div><button class="close-button" aria-label="Dismiss alert" type="button"
			data-close>
			<span aria-hidden="true">&times;</span>
		</button></div>
	</div>
</c:if>

<div id="inputvirhe" class="callout warning">
	<p class="text-center">Lisää sekä etu- että sukunimi!
	<p>
</div>


