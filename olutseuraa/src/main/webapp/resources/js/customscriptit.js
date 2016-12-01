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











