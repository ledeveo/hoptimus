
<%@ include file="head-include.jsp"%>

<body>

	<%@ include file="header.jsp"%>


	<div class="row">
	
		<!-- Joko modaaleihin tai alertteihin -->
		<div class="columns small-12 medium-11 center" style="margin-bottom: 15px;">
		
			<c:if test="${param.UserExists eq true}" >
				<div data-alert class="center alert-box alert">
			  		Kirjaudu sisään liittyäksesi tapahtumaan!
			  		<b class="float-right"><a style="color: white;" href="#" onclick="$(this).parent().parent().parent().hide()"> sulje &times;</a></b>
			  	</div>
			</c:if>
			<c:if test="${param.cantJoin eq true}" >
				<div data-alert class="center alert-box alert">
			  		Tapahtumaan liittyminen ei onnistunut!
			  		<b class="float-right"><a style="color: white;" href="#" onclick="$(this).parent().parent().parent().hide()"> sulje &times;</a></b>
			  	</div>
			</c:if>
			<c:if test="${param.submitError eq true}" >
				<div data-alert class="center alert-box alert">
			  		Tapahtui virhe liittyessä tapahtumaan!
			  		<b class="float-right"><a style="color: white;" href="#" onclick="$(this).parent().parent().parent().hide()"> sulje &times;</a></b>
			  	</div>
			</c:if>
			<c:if test="${param.userNotExists eq true}" >
				<div data-alert class="center alert-box alert">
			  		Käyttäjää ei löytynyt!
			  		<b class="float-right"><a style="color: white;" href="#" onclick="$(this).parent().parent().parent().hide()"> sulje &times;</a></b>
			  	</div>
			</c:if>
		
			<c:if test="${param.submitSuccessActivate eq true}">
				<div data-alert class="center alert-box success" style="color: black;">
			  		Osallistuminen onnistui! Tarkista sähköpostisi tilin aktivointia varten.
			  		<b class="float-right"><a style="color: black;" href="#" onclick="$(this).parent().parent().parent().hide()"> sulje &times;</a></b>
			  	</div>
			</c:if>
			
		</div>
	</div>


	<div class="row align-middle">
	
		<div class="column small-12 medium-4 center" data-sticky-container>
	    	<div class="sticky small-12 medium-12" data-sticky data-sticky-on="medium" data-anchor="stickyanchor" >
				<%@ include file="tapahtumalistaus.jsp"%>
				<a class="twitter-timeline" href="https://twitter.com/Hoptimusprimet1">Tweets by Hoptimusprimeteam</a> <script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
			</div>
		</div>
	
		<div class="column small-12 medium-5"  id="stickyanchor">
		
			<c:forEach items="${tapahtumat}" var="event" varStatus="iteration">

				<c:set var="pvm" value="${event.pvm}" />
				<fmt:formatDate pattern="MM" value="${pvm}" var="kk" />
				<c:set var="osallistujat" value="${fn:length(event.osallistujat)}" />
				<c:set var="maxMaara" value="${event.maxOsallistujamaara}" />

				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<%@ include file="adminLaatikot.jsp"%>
				</sec:authorize>
				
				<sec:authorize access="!hasRole('ROLE_ADMIN')">
					<%@ include file="kayttajaLaatikot.jsp"%>
				</sec:authorize>
				
			</c:forEach>

		</div>

	
	<!-- Dynaaminen pohja-ankkuri instafeedille eli mihin kohtaan se pysähtyy alaspäin scrollattaessa -->
		<c:set var="ankkuri" scope="page" value="${fn:length(tapahtumat)*440}"></c:set>
		 
	<div class="column small-12 medium-3 center" data-sticky-container>
    	<div style="min-height:550px;" id="instafeed" class="sticky" data-sticky data-sticky-on="medium" data-top-anchor="stickyanchor" data-btm-anchor="${ankkuri}"><a href="http://www.instagram.com/hoptimusprimeteam"><small data-tooltip title="Seuraa meitä Instagramissa"><img alt="ig" src="resources/img/instagram_logo.png" width="150" height="150"></small></a></div>

    	

	</div>

	
	</div>
	
	<%@ include file="footer.jsp"%>