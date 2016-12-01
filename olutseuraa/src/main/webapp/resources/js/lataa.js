/**
 * sivua ladattaessa scriptit
 */

window.onload = function() {
	
    //jos feedi löytyy sivulta
    if(document.getElementById("instafeed")) {
        feed.run();
    }
    //jos sivulla on ajanlasku
    if(document.getElementsByClassName("alkamiseenAikaa")) {
    	haeElementit();
    }
    
    
}