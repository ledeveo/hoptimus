/**
 * 
 */

function Lisaa(nappula, tID) {
	
	$(nappula).hide();
	suurennaKuva();
	//alert("lisaa");
    $.ajax({
        url: 'LisaaLiittyminen',
        type:'POST',
        data:
        {
        	tapahtumaId: tID
        },
        success: function(msg)
        {
            //alert('Lisäys onnistui!');
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
	//alert("poista");
    $.ajax({
        url: 'PoistaLiittyminen',
        type:'POST',
        data:
        {
        	tapahtumaId: tID
        },
        success: function(msg)
        {
            //alert('Poisto onnistui!');
        	setTimeout(function() {location.reload()}, 800);
        }               
    });
}
