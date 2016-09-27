
<header class="header show-for-medium">
	<h1 class="headline">
		Olutseuraa <small>by HoptimusPrime</small>
	</h1>
	<ul class="header-subnav">
		<li class="small-offset-1"><a href="#" class="is-active">Etsi
				tapahtumia</a></li>
		<li><a>Luo tapahtuma</a></li>
		<li class="float-right"><a><i class="fi-arrow-right"></i><small>
					Kirjaudu</small></a></li>
	</ul>
</header>

 <c:if test="${not empty sessionScope.tervetuloa}">
	<div id="success" class="callout success">
		<p>
			Tervetuloa kaljalle, <c:out value="${tervetuloa}" />!
			<img id="beer" src="img/beer.png" alt="beer" /> 
		</p>
	</div>
</c:if>



	
<div id="inputvirhe" class="callout warning">
<p class="text-center">Lis‰‰ sek‰ etu- ett‰ sukunimi!<p>
</div>
