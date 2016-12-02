<div class="small-12 medium-12 small-centered">
	<table style="width: 100%;">
		<thead>
			<tr>
				<th class="center">tapahtuma</th><th class="center">alkaa</th><th class="center">osallistujia</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tapahtumat}" var="t">
				<tr class="center">
					<td>
						<p><a onclick="skrollaakontenttiin(${t.id})"><c:out value="${t.nimi}"></c:out></a></p>
					</td>
					<td class="center">
					
						<p class="alkamiseenAikaa center" >${t.pvm}T${t.aika}:00Z</p>
						<div class="row center">
						
						</div>
					</td>
					<td class="center">
						<c:out value="${fn:length(t.osallistujat)}"></c:out>/<c:out value="${t.maxOsallistujamaara}"></c:out> <i class="step fi-torsos size-18"></i>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>