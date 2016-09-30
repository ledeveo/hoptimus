
<header class="header show-for-medium">
	<h1 class="headline">
		Olutseuraa <small>by HoptimusPrime</small>
	</h1>
	<ul class="header-subnav">
		<li class="small-offset-1">
			<a href="kaikki" class="is-active">Etsi tapahtumia</a></li>
		<li>
			<a href="uusi">Luo tapahtuma</a></li>
		<li class="float-right"><a><i class="fi-arrow-right"></i><small>
					Kirjaudu</small></a></li>
	</ul>
</header>

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
