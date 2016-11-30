/**
 *  laskee alkamisajat tapahtumille minuutin välein
 */
function laskeAikaaJaljella(aika, element){
    
  var t = Date.parse(aika) - Date.parse(new Date());
  var minutes = Math.floor( (t/1000/60) % 60 );
  var hours = Math.floor( (t/(1000*60*60)) % 24 );
  var days = Math.floor( t/(1000*60*60*24) );
  
  
  var tekstirimpsu = "";
  if(days >= 1) {
      tekstirimpsu += days + "d, ";
  }
  if(hours >= 1) {
      tekstirimpsu += hours + "h, ";
  }
  if(minutes >= 1) {
      tekstirimpsu += minutes + "min";
  }
  
  $(element).text(tekstirimpsu);
  // DEBUG
  //console.log("elementti: " + element);
  //console.log("aika: " + tekstirimpsu);
}
function haeAjat(ajat, elementit) {
    
    for(i = 0; i < elementit.length; i++) {
        laskeAikaaJaljella(ajat[i], elementit[i]);
    }
}
function haeElementit() {
    
    var elementit = document.getElementsByClassName("alkamiseenAikaa");
    var ajat = [];
    
    //aseta ajat elementeille
    for(i = 0; i < elementit.length; i++) {
        var aika = elementit[i].innerHTML;
        ajat.push(aika);
    }
    
    /*// DEBUG
    for(i = 0; i < elementit.length; i++) {
        console.log("aika " + i + ": " + ajat[i]);
    }
    */
    
    //hae ajat
    haeAjat(ajat, elementit);
    //hae ajat väliajoin
    var ajanlaskenta = setInterval(function(){ haeAjat(ajat, elementit) }, 1000*60);
    
    
}