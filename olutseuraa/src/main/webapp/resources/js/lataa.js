/**
 * sivua ladattaessa scriptit
 */

window.onload = function() {
	
    //jos sivulla on ajanlasku
    if(document.getElementsByClassName("alkamiseenAikaa")) {
    	haeElementit();
    }
    
    
}

$(document).ready(function(){
	//jos feedi löytyy sivulta
    if(document.getElementById("instafeed")) {
        feed.run();
    }
});