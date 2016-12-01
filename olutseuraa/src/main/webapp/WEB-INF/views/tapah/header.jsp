<div class="container">
<header class="header">
	<h1 class="headline  pressStart"><a onclick="suurennaKuva()"><img id="olutKuva"  alt="olut" src="resources/img/oluttuoppi.png" width="130" height="130"></a><a href="/olutseuraa/tapahtumat">
		Olutseuraa <small data-tooltip title="Anssi, Joonas, Juho, Leo, Sebastian & Yousuf">by HoptimusPrime</small></a>
	</h1>
	<ul class="header-subnav">
		<li class="small-offset-1">
			<a href="tapahtumat" id="kaikki"><i class="step fi-marker size-36"></i> Tapahtumat</a></li>
		
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="uusi" id="uusi"> <i class="step fi-pencil size-36"></i> Luo uusi tapahtuma</a></li>
			</sec:authorize>
			
			
			<sec:authorize access="!hasRole('ROLE_USER')">
				<li><a href="login"><i class="fi-arrow-right"></i> Kirjaudu sisään</a></li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_USER') && !hasRole('ROLE_ADMIN')">
				<li style="color: white;"><a href="userpage"><i class="step fi-bookmark size-36"></i> Osallistumiset</a></li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_USER')">
				<li><a href="j_spring_security_logout"><i class="fi-arrow-right"></i> Kirjaudu ulos</a></li>
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




