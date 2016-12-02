</div>

<div id="audioklipit" style="display: none;">
	<audio controls id="CrumpleBeer">
		<source src="resources/sound/crumple.wav" type="audio/wav">
	</audio>
	<audio controls id="popBeer">
		<source src="resources/sound/popbeer.wav" type="audio/wav">
	</audio>
	<audio controls id="snapOpenBeer">
		<source src="resources/sound/snapopen.wav" type="audio/wav">
	</audio>
	<audio controls id="pourBeer">
		<source src="resources/sound/pourbeer.wav" type="audio/wav">
	</audio>
</div>

<footer class="footer">

<div class="row expanded">
	<div id="social" class="columns small-12 medium-2 center lineheight">
		<!-- <p class="about subheader">Beer me, Marge</p>-->
		<span class="inline-list social"> <a href="#"><i
				class="fi-social-facebook"></i></a> <a href="#"><i
				class="fi-social-twitter"></i></a> <a href="#"><i
				class="fi-social-linkedin"></i></a> <a href="#"><i
				class="fi-social-github"></i></a>
		</span>
		
		
</div>
<div class="text-center columns small-12 medium-8 lineheight"><button data-toggle="contact" class="button center small warning">Anna palautetta <i class="fi-megaphone small"></i></button></div>
	
		<div class="columns small-12 medium-2 lineheight">
	<h6 data-tooltip class="info white center"
		title="Anssi, Joonas, Juho, Leo, Sebastian & Yousuf">HoptimusPrime
		2016</h6>
	</div>
		
</div>
</footer>
</div>



<!--  <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script src="resources/js/form-validation.js"></script>


<!-- Alla jQuery ver. 2.2, form-validation toimii vain 1.x -versioilla -->
<!--<script src="../resources/js/vendor/jquery.js"></script>-->

<script src="resources/js/vendor/what-input.js"></script>
<script src="resources/js/vendor/foundation.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="resources/js/jquery.timepicker.js"></script>
<script src="resources/js/jquery.scrollTo.min.js"></script>


<!-- Määrittelee patternin Pvm -kentän inputille -->
<script>
	$(function() {
		dateFormat: "dd/MM/yyyy"
		$("#datepicker").datepicker("option", "dateFormat" );
	});
</script>

<!-- Timepicker -plugini Aika -kentälle -->
<script>
	$(function() {
		$('#eventTime').timepicker({
			'timeFormat' : 'H:i'
		});
	});
</script>

<!-- Estää kirjainten kirjoittamisen Aika -kenttään -->
<script>
if(document.querySelector("#eventTime")) {
	document.querySelector("#eventTime").addEventListener("keypress", function (evt) {
	    if (evt.which < 48 || evt.which > 57)
	    {
	        evt.preventDefault();
	    }
	});
}


</script>






<script src="resources/js/app.js"></script>
<script src="resources/js/laskeAikaaJaljella.js"></script>
<script type="text/javascript" src="resources/js/instafeed.js"></script>
<script type="text/javascript" src="resources/js/instagrammeli.js"></script>
<script type="text/javascript" src="resources/js/lataa.js"></script>
<script src="resources/js/customscriptit.js"></script>
<script src="resources/js/ajaxkikkailu.js"></script>





<c:if test="${param.UserExists eq true}">
	<script> alert("Kirjaudu sisään liittyäksesi tapahtumaan!");</script>
</c:if>
<c:if test="${param.UserExists eq true}">
	<script> alert("Säköpostilla oleva tili on jo olemassa. Kirjaudu sisään liittyäksesi tapahtumaan!");</script>
</c:if>
<c:if test="${param.userNotActivated eq true}">
	<script> alert("Sähköpostiisi on lähetetty aktivointilinkki! Posti saattaa olla roskaposteissa.");</script>
</c:if>
<c:if test="${param.submitSuccessActivate eq true}">
	<script> alert("Osallistuminen onnistui! Tarkista sähköpostisi tilin aktivointia varten.");</script>
</c:if>


</body>
</html>