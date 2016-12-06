<%@ include file="head-include.jsp"%>



<%@ include file="header.jsp"%>

<sec:authorize access="!hasRole('ROLE_ADMIN')">
	<c:redirect url="tapahtumat"></c:redirect>
</sec:authorize>

<div>
	<h2 class="center white">Palaute<small id="maara" style="font-size:1rem;"> /viimeiset viisi</small></h2> 
	<div class="center"><button id="kaikkip" class="button small success">Hae kaikki palautteet <i class="fi-asterisk small"></i></button>
	<button class="button small warning" style="display:none;"  id="five">Viimeiset viisi</button></div>
	
</div>
<div class="row" style="margin-bottom: 6rem;">


 
	<div class="small-12 medium-8 medium-offset-2 columns">

		<ul class="accordion center" data-accordion data-allow-all-closed="true" id="listitem">
			
				
			
		</ul>
	</div>

	<!-- <div class="small-12 medium-8 medium-offset-2 columns" id="ajaxdiv"></div>-->

</div>


<%@ include file="footer.jsp"%>