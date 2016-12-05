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
    var originalState = $("#contactbtndiv").clone();

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
        	        	  $("#contactbtndiv").replaceWith(originalState);
        	  });        	  
        	  		      			        	  
          },
          error: function(jqXHR, textStatus, errorThrown) {
              console.log(errorThrown);
              $('#contact').foundation('close');
              $("#contactbtndiv").hide().html("<button class='button center small alert'>" +
  	  		"Palaute ei mennyt perille. Yritä myöhemmin uudestaan. <i class='fi-alert small' ></i></button>").fadeIn(1500).delay(2500).queue(function(){
  	        	  $("#contactbtndiv").replaceWith(originalState);
  	  });  
          }
      })
})

$("#palauteAjax").click(function(){
	
	
	$("#five").toggle();
	$("#palauteAjax").toggle();

		
    $.ajax({
          type: "get",
          url: "kaikkiPalautteet",
          dataType: "json",
          success: function(responseData, textStatus, jqXHR) {
              
        	    
        	  
        	  $.each(responseData, function( index, palaute ) {
        		  
        		  $( "#ajaxdiv" ).append( "<ul class='accordion center' data-accordion data-allow-all-closed='true'>");
        		  $("#ajaxdiv").append("<li class='accordion-item' data-accordion-item><a href='#' class='accordion-title'><h5>Kakka</h5></a>");
        		  $("#ajaxdiv").append ("<div class='accordion-content' data-tab-content>");
        		  $( "#ajaxdiv" ).append( "<table><tbody><tr>");	
        		  $( "#ajaxdiv" ).append( "<th>Palautteen antaja </th></tr>" );
        		  $( "#ajaxdiv" ).append( "<tr><td>" + palaute.palautteenAntaja + "</td></tr>" );
        		  $( "#ajaxdiv" ).append( "<tr><th>Palaute </th></tr>" );
        		  $( "#ajaxdiv" ).append( "<tr><td>" + palaute.palaute + "</td></tr>" );
        		  $( "#ajaxdiv" ).append( "<tr><th>Sähköposti </th></tr>" );
        		  $( "#ajaxdiv" ).append( "<tr><td>" + palaute.sposti + "</td></tr>" );
        		  $( "#ajaxdiv" ).append( "</tbody></table></div></li></ul>");
        		 
        		  
        		})     
        
        	  	    
        	  
          },
          error: function(jqXHR, textStatus, errorThrown) {
              console.log("Error" + errorThrown);
          
          }
      })
})


