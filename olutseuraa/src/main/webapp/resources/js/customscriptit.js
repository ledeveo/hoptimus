/**
 * sekalaisia scriptejä sivuston eri toimintoja varten
 */

// Hakufilttereiden togglaaminen
$(document).ready(function() {
$("#showFilters").click(function() {
	$("#filtterit").toggle("slow", function() {
	});
});
});

// Togglaa liittymisformia
$(document).ready(function() {
$(".tutustu").click(function(e) {
	var id = $(this).attr('id');
	$("#exp" + id).slideToggle("slow", function() {
		$('html, body').animate({
			scrollTop : $("#ilmo").offset().top
		}, 2000);
	});
});
});

// sulkee liittymisformin
$(document).ready(function() {
$("body, .peruuta").click(function() {
	$(".expandable").slideUp('fast');
});

$(".event").click(function(e) { 
	e.stopPropagation();
});
});

$(document).ready(function() {
	$('#success').fadeIn("fast", function() {
	});
});

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




