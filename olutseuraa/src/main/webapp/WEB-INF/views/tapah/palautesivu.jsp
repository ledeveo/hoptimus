<%@ include file="head-include.jsp"%>



<%@ include file="header.jsp"%>

<sec:authorize access="!hasRole('ROLE_ADMIN')">
	<c:redirect url="tapahtumat"></c:redirect>
</sec:authorize>

<div>
	<h2 class="white" style="position:absolute; left:43%;">Palaute<small id="maara" style="font-size:1rem;" class="show-for-medium"> /viimeiset viisi</small></h2> 
	</div>
	
	<div class="center" style="margin-top:4em;"><button id="kaikkip" class="button small success">Hae kaikki palautteet <i class="fi-asterisk small"></i></button>
	<button class="button small warning" style="display:none;"  id="five">Hae viimeiset viisi <i class="fi-die-five small"></i></button></div>
	

<div class="row" style="margin-bottom: 6rem;">


 
	<div class="small-12 medium-8 medium-offset-2 columns">

		<ul class="accordion center" data-accordion data-allow-all-closed="true" id="listitem">	
			<!-- Ajax-sisältö ladataan tähän -->
		</ul>
	</div>
</div>



<script src="resources/js/palauteajax.js"></script>
<%@ include file="footer.jsp"%>