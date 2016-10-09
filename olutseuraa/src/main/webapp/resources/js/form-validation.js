/**
 * 
 */


	$(".tutustu").click(function(e){
		var id = $(this).attr('id');
	
  $("#liityform" + id).validate({
    rules: {
      etunimi: "required",
      sukunimi: "required",
      sahkoposti: {
        required: true,
        email: true
      },
    },
    messages: {
      etunimi: "Kirjoita etunimi!",
      sukunimi: "Kirjoita sukunimi!",
      sahkoposti: "Kirjoita validi sähköpostiosoite!"
    },
    submitHandler: function(form) {
      form.submit();
    }
  });
	});
