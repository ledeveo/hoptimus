/**
 * 
 */

function Lisaa(tID) {
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
        	setTimeout(function() {location.reload()}, 800);
        }               
    });
}

function Poista(tID) 
{
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
