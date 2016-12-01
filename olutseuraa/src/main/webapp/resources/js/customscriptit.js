/**
 * sekalaisia scriptejä sivuston eri toimintoja varten
 */

// sulkee avoimet liittymisformit
$(document).ready(function() {
$("body, .peruuta").click(function() {
	$(".expandable").slideUp('fast');
});

$(".event").click(function(e) { 
	e.stopPropagation();
});
});


//Togglaa liittymisformia
$(document).ready(function() {
$(".tutustu").click(function(e) {
	var id = $(this).attr('id');
	$("#exp" + id).slideToggle("slow")
});
});


//Korostaa aktiivisen tabin subnavissa, urlin perusteella
$(document).ready(function() {
    var x = document.URL;
    var parts = x.replace(/\?.*/,'');
    var x = parts.split("/");
    var result = x[x.length - 1];
    $("#"+result).addClass("is-active");
});


// Hakufilttereiden togglaaminen
$(document).ready(function() {
$("#showFilters").click(function() {
	$("#filtterit").toggle("slow", function() {
	});
});
});


function skrollaakontenttiin(elementtiID) {
	
	//poista valittu edellisestä
	var valitut = document.getElementsByClassName("valittu");
	if(valitut) {
		for(i = 0; i < valitut.length; i++) {
			//alert(valitut[i]);
			$(valitut[i]).removeClass("valittu");
		}
	}
	
	//skrollaa elementtiin
	var elementtiin = $("#" + elementtiID);
	$('body').scrollTo(elementtiin,1000);
	
	//lisää valittuu seuraavaan
	elementtiin.addClass("valittu");
}

//klikkaa olut-kuvaa
function suurennaKuva() {
	$("#olutKuva").addClass("suurennaKuva");
	setTimeout(function() {
		$("#olutKuva").removeClass("suurennaKuva");}, 500);
}

//klikkaa olut-kuvaa
function pienennaKuva() {
	$("#olutKuva").addClass("pienennaKuva");
	setTimeout(function() {
		$("#olutKuva").removeClass("pienennaKuva");}, 500);
}






