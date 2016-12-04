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
	var quarter = screen.height/4;
	var elementtiin = $("#" + elementtiID);
	$('body').scrollTo(elementtiin.offset().top - quarter ,1000);
	
	//lisää valittuu seuraavaan
	elementtiin.addClass("valittu");
}

function soitaPoistoKlippi() {
	var audio = document.getElementById("CrumpleBeer");
	audio.play();
}

function soitaLisaysKlippi() {
	//valitse satunnaisesti klippi joka soitetaan
	var audio1 = document.getElementById("popBeer");
	var audio2 = document.getElementById("snapOpenBeer");
	var audio3 = document.getElementById("pourBeer");
	
	var random = Math.floor((Math.random() * 3) + 1); 
	switch(random) {
		case 1:
			audio1.play();
			break;
		case 2:
			audio2.play();
			break;
		case 3:
			audio3.play();
			break;
		default:
			audio1.play();
	}
	   
}

//klikkaa olut-kuvaa
function suurennaKuva() {
	
	soitaLisaysKlippi();
	
	$("#olutKuva").addClass("suurennaKuva");
	setTimeout(function() {
		$("#olutKuva").removeClass("suurennaKuva");}, 500);
}

//klikkaa olut-kuvaa
function pienennaKuva() {
	
	soitaPoistoKlippi();
	
	$("#olutKuva").addClass("pienennaKuva");
	setTimeout(function() {
		$("#olutKuva").removeClass("pienennaKuva");}, 500);
}

function pyoritaKuva() {
	$("#olutKuva").addClass("pyoritaKuva");
	setTimeout(function() {
		$("#olutKuva").removeClass("pyoritaKuva");
		$("#olutKuva").addClass("pyoritaKuva2");}, 300);
	setTimeout(function() {
		$("#olutKuva").removeClass("pyoritaKuva2");}, 700);
	
}

function randomAnimaatio() {
	var random = Math.floor((Math.random() * 2) + 1);
	switch(random) {
		case 1:
			pienennaKuva();
			break;
		case 2:
			suurennaKuva();
			break;
		case 3:
			soitaLisaysKlippi();
			pyoritaKuva();
			break;
		default:
			suurennaKuva();
	}
}


function bodyOffset(){
var bodyOffset = window.innerWidth - $(window).width();
alert(bodyOffset);
if(bodyOffset > 0){
	$('body').css('padding-right', bodyOffset + 'px');
}
}



 $(document).on('closed.zf.reveal', '[data-reveal]', function() {
	 $('body').css('padding-right', '');
    }); 

  

