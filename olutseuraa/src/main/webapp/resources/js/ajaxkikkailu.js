/**
 * 
 */

function Lisaa(nappula, tID) {

	$(nappula).hide();
	suurennaKuva();
	// alert("lisaa");
	$.ajax({
		url : 'LisaaLiittyminen',
		type : 'POST',
		data : {
			tapahtumaId : tID
		},
		success : function(msg) {
			// alert('Lisäys onnistui!');
			// tän vois tehrä paremminki. esim.
			// muuntaa elementissä numeron kokoo vain jotenki?
			setTimeout(function() {
				location.reload()
			}, 800);
		}
	});
}

function Poista(nappula, tID) {
	$(nappula).hide();
	pienennaKuva();
	// alert("poista");
	$.ajax({
		url : 'PoistaLiittyminen',
		type : 'POST',
		data : {
			tapahtumaId : tID
		},
		success : function(msg) {
			// alert('Poisto onnistui!');
			setTimeout(function() {
				location.reload()
			}, 800);
		}
	});
}

$("#palauteform")
		.submit(
				function(event) {

					/* stop form from submitting normally */
					event.preventDefault();
					var originalState = $("#contactbtndiv").clone();

					var postData = $("#palauteform").serialize();
					$
							.ajax({
								type : "post",
								url : "palautetta",
								data : postData,
								contentType : "application/x-www-form-urlencoded",
								accepts : "text/plain",
								success : function(responseData, textStatus,
										jqXHR) {
									console.log(responseData);
									$('#contact').foundation('close');
									$("#contactbtndiv")
											.hide()
											.html(
													"<button class='button center small success'>"
															+ "Kiitos palautteesta! <i class='fi-like small' ></i></button>")
											.fadeIn(1500)
											.delay(1500)
											.queue(
													function() {
														$("#contactbtndiv")
																.replaceWith(
																		originalState);
													});

								},
								error : function(jqXHR, textStatus, errorThrown) {
									console.log(errorThrown);
									$('#contact').foundation('close');
									$("#contactbtndiv")
											.hide()
											.html(
													"<button class='button center small alert'>"
															+ "Palaute ei mennyt perille. Yritä myöhemmin uudestaan. <i class='fi-alert small' ></i></button>")
											.fadeIn(1500)
											.delay(2500)
											.queue(
													function() {
														$("#contactbtndiv")
																.replaceWith(
																		originalState);
													});
								}
							})
				})


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
										+	"<a href='#'class='accordion-title'><h5>" + date + "</h5></a>"
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
										+	"<a href='#'class='accordion-title'><h5>" + date + "</h5></a>"
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
