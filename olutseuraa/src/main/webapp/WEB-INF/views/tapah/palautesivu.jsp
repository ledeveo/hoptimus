<%@ include file="head-include.jsp"%>



<%@ include file="header.jsp"%>



<div>
<h2 class="center white" style="margin-bottom:1rem;">Palaute</h2>
</div>
<div class="row" style="margin-bottom:6rem;">

<div class="small-12 med-8 columns text-center">



<ul class="accordion" data-accordion data-allow-all-closed="true">
	<c:forEach var="palaute" items="${palautteet}">
		<li class="accordion-item" data-accordion-item><a href="#"
			class="accordion-title"> <h5> ${palaute.aikaleima}</h5></a>
			<div class="accordion-content" data-tab-content>
				<c:out value="${palaute.id}"></c:out>
				<c:out value="${palaute.palautteenAntaja}"></c:out>
				<c:out value="${palaute.palaute}"></c:out>
				<c:out value="${palaute.sposti}"></c:out>
				<c:out value="${palaute.aikaleima}"></c:out>
			</div></li>
	</c:forEach>
</ul>
</div>
</div>

<%@ include file="footer.jsp"%>