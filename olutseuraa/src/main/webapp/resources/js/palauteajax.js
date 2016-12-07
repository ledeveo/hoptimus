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
										//Haetaan luettu-muuttujaan luettu-boolean palaute-taulusta
										var otsikkorivi;
										
										
										if(palaute.luettu){
										 otsikkorivi = "<a href='#'class='accordion-title'><h5>" + palaute.otsikko + "</h5><small style='font-size:1.2rem;'>" + date + "</small></a>";
										}else{
											otsikkorivi = "<a href='#'class='accordion-title'><i class='fi-alert mediumicon' id='" + palaute.id + "ico'></i><h5>" + palaute.otsikko + "</h5><small style='font-size:1.2rem;'>" + date + "</small></a>";
										}
									
										
									$("#listitem").append("<li class='accordion-item' data-accordion-item id='" + palaute.id +"'>" 
										+	otsikkorivi
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
										
										var otsikkorivi;
					
										
										if(palaute.luettu){
										 otsikkorivi = "<a href='#'class='accordion-title'><h5>" + palaute.otsikko + "</h5><small style='font-size:1.2rem;'>" + date + "</small></a>";
										}else{
											otsikkorivi = "<a href='#'class='accordion-title'><i class='fi-alert mediumicon' id='" + palaute.id + "ico'></i><h5>" + palaute.otsikko + "</h5><small style='font-size:1.2rem;'>" + date + "</small></a>";
										}
										
										
									$("#listitem").append("<li class='accordion-item' data-accordion-item id='" + palaute.id +"'>" 
										+	otsikkorivi
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


 $(window).on('down.zf.accordion', function(){
	 
	 var id = $("li.is-active").attr("id");
	 
	 if($("li.is-active").has("i").length){ 
	 
	 $.ajax({
			type : "post",
			url : "merkkaaLuetuksi",
			data : { id: id },
			accepts : "json",
			success : function(responseData, textStatus,
					jqXHR) {
				console.log(responseData);
				$("#" + id + "ico").remove();
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			
			}
	 })
	 }else{
		 return false;
	 }
 })

