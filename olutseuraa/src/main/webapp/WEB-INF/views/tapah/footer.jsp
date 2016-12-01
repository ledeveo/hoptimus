</div>
<footer class="footer">


	<div id="social">
		<!-- <p class="about subheader">Beer me, Marge</p>-->
		<span class="inline-list social"> <a href="#"><i
				class="fi-social-facebook"></i></a> <a href="#"><i
				class="fi-social-twitter"></i></a> <a href="#"><i
				class="fi-social-linkedin"></i></a> <a href="#"><i
				class="fi-social-github"></i></a>
		</span>
	</div>
		
	<h6 data-tooltip class="info float-right white" title="Anssi, Joonas, Juho, Leo, Sebastian & Yousuf">HoptimusPrime 2016</h6>
	
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





<script src="resources/js/customscriptit.js"></script>

<script src="resources/js/app.js"></script>

<c:if test="${param.UserExists eq true}" ><script> alert("Säköpostilla oleva tili on jo olemassa. Kirjaudu sisään liittyäksesi tapahtumaan!");</script></c:if>
<c:if test="${param.userNotActivated eq true}" ><script> alert("Sähköpostiisi on lähetetty aktivointilinkki! Posti saattaa olla roskaposteissa.");</script></c:if>
<c:if test="${param.submitSuccessActivate eq true}" ><script> alert("Osallistuminen onnistui! Tarkista sähköpostisi tilin aktivointia varten.");</script></c:if>

</body>
</html>