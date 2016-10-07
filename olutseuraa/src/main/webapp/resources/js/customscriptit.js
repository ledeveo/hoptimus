/**
 * sekalaisia scriptejä sivuston eri toimintoja varten
 */

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
    var parts = x.split("/");
    var result = parts[parts.length - 1];
    $("#"+result).addClass("is-active");
});


// Hakufilttereiden togglaaminen
$(document).ready(function() {
$("#showFilters").click(function() {
	$("#filtterit").toggle("slow", function() {
	});
});
});



// sulkee avoimet liittymisformit
$(document).ready(function() {
$("body, .peruuta").click(function() {
	$(".expandable").slideUp('fast');
});

$(".event").click(function(e) { 
	e.stopPropagation();
});
});


//Tervehdysviesti liittyjälle, tästä luovuttaneen
//$(document).ready(function() {
//	$('#success').fadeIn("fast", function() {
//	});
//});


//Valittaa nimien puutteesta formissa, tästäkin luovuttaneen
$(document).ready(function() {
$(".subcheck").click(function(e) {
	var id = $(this).attr('id');
	var enimi = $.trim($("#enimi" + id).val());
	var snimi = $.trim($("#snimi" + id).val());

	if (enimi.length < 1 || snimi.length < 1) {
		event.preventDefault();
		$("#inputvirhe").fadeIn("fast").delay(1500).fadeOut("slow");
	}
});
});





