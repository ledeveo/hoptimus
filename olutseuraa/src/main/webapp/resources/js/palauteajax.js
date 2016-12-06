$(function() {

	var url = "viimeisetViisi";
	
	$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				success : function(responseData, textStatus, jqXHR) {

				
					$
							.each(
									responseData,
									function(index, palaute) {
										
										var date = (DateFormat.format.date(palaute.aikaleima, "dd.MM.yyyy  HH.mm"));
									
										
									$("#listitem").append("<li class='accordion-item' data-accordion-item >" 
										+	"<a href='#'class='accordion-title'><h5>" + palaute.otsikko + "</h5><small style='font-size:1.2rem;'>" + date + "</small></a>"
										+	"<div class='accordion-content' data-tab-content><table>"
										+	"<tbody><tr><th>Palautteen antaja</th></tr>"
										+	"<tr><td>" +palaute.palautteenAntaja +"</td></tr><tr><th>Sähköposti</th></tr>"
										+		"<tr><td>"+palaute.sposti + "</td></tr><tr><th>Palaute</th></tr>"
										+	"<tr><td>"+palaute.palaute + "</td></tr></tbody></table></div></li>");

									})
									Foundation.reInit('accordion');
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("Error" + errorThrown);

				}				
			})		
})
				
				
$("#kaikkip, #five").click(function() {

	var url;
	if(this.id == "five"){
		url = "viimeisetViisi";
		$("#maara").text(" /viimeiset viisi");
	}else{
		url = "kaikkiPalautteet";
		$("#maara").text(" /kaikki");
	}
	
	

	$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				success : function(responseData, textStatus, jqXHR) {

						$("#kaikkip").toggle();
						$("#five").toggle();
						$("#listitem").empty();
				
					$
							.each(
									responseData,
									function(index, palaute) {
										
										
										var date = (DateFormat.format.date(palaute.aikaleima, "dd.MM.yyyy  HH.mm"));
										
										
										
									$("#listitem").append("<li class='accordion-item' data-accordion-item >" 
										+	"<a href='#'class='accordion-title'><h5>" + palaute.otsikko + "</h5><small style='font-size:1.2rem;'>" + date + "</a>"
										+	"<div class='accordion-content' data-tab-content><table>"
										+	"<tbody><tr><th>Palautteen antaja</th></tr>"
										+	"<tr><td>" +palaute.palautteenAntaja+"</td></tr><tr><th>Sähköposti</th></tr>"
										+		"<tr><td>"+palaute.sposti + "</td></tr><tr><th>Palaute</th></tr>"
										+	"<tr><td>"+palaute.palaute + "</td></tr></tbody></table></div></li>");

									})
									Foundation.reInit('accordion');
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("Error" + errorThrown);

				}				
			})		
})
