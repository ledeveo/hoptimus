<%@ include file="head-include.jsp"%>



<%@ include file="header.jsp"%>

<sec:authorize access="!hasRole('ROLE_ADMIN')">
	<c:redirect url="tapahtumat"></c:redirect>
</sec:authorize>

<div>
	<h2 class="center white" style="margin-bottom: 1rem;">Palaute<small id="maara" style="font-size:1rem;">...viimeiset viisi</small> 
	<button id="palauteAjax" class="button float-center small success">Hae kaikki palautteet <i class="fi-asterisk small"></i></button></h2>
	
</div>
<div class="row" style="margin-bottom: 6rem;">

	<div class="small-12 medium-8 medium-offset-2 columns">



		<ul class="accordion center" data-accordion data-allow-all-closed="true">
			<c:forEach var="palaute" items="${palautteet}">
				<li class="accordion-item" data-accordion-item><a href="#"
					class="accordion-title">
						<h5>${palaute.aikaleima}</h5>
				</a>
					<div class="accordion-content" data-tab-content>
						<table>
							
							<tbody>
							<tr><th>Palautteen antaja</th></tr>
								<tr>
									<td><c:out value="${palaute.palautteenAntaja}"></c:out></td>

								</tr>
								<tr><th>Palaute</th></tr>
									<tr>
									<td><c:out value="${palaute.palaute}"></c:out></td>

								</tr>
								
								<tr><th>Sähköposti</th></tr>
								<tr>
									<td><c:out value="${palaute.sposti}"></c:out></td>
								</tr>

							</tbody>
						</table>
					</div></li>
			</c:forEach>
		</ul>
	</div>
</div>

<div id="ajaxdiv"></div>

<%@ include file="footer.jsp"%>