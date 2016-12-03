/**
 * 
 */

function Lisaa(nappula, tID) {
	
	$(nappula).hide();
	suurennaKuva();
	// alert("lisaa");
    $.ajax({
        url: 'LisaaLiittyminen',
        type:'POST',
        data:
        {
        	tapahtumaId: tID
        },
        success: function(msg)
        {
            // alert('Lisäys onnistui!');
        	// tän vois tehrä paremminki. esim.
        	// muuntaa elementissä numeron kokoo vain jotenki?
        	setTimeout(function() {location.reload()}, 800);
        }               
    });
}

function Poista(nappula, tID) 
{
	$(nappula).hide();
	pienennaKuva();
	// alert("poista");
    $.ajax({
        url: 'PoistaLiittyminen',
        type:'POST',
        data:
        {
        	tapahtumaId: tID
        },
        success: function(msg)
        {
            // alert('Poisto onnistui!');
        	setTimeout(function() {location.reload()}, 800);
        }               
    });
}


$("#palauteform").submit(function(event) {

    /* stop form from submitting normally */
    event.preventDefault();

    var postData = $("#palauteform").serialize();
    $.ajax({
          type: "post",
          url: "palautetta",
          data: postData,
          contentType: "application/x-www-form-urlencoded",
          accepts: "text/plain",
          success: function(responseData, textStatus, jqXHR) {
        	  console.log(responseData);
        	  $('#contact').foundation('close');
        	  $("#contactbtndiv").hide().html("<button class='button center small success'>" +
        	  		"Kiitos palautteesta! <i class='fi-like small' ></i></button>").fadeIn(1500).delay(1500).queue(function(){
        	  			$("#contactbtndiv").html("<button class='button center small warning'>" +
            	  		"Anna palautetta <i class='fi-megaphone small'></i></button>");
        	  		});
        	  		      			        	  
          },
          error: function(jqXHR, textStatus, errorThrown) {
              console.log(errorThrown);
          }
      })
})