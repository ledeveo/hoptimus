/**
 * 
 */

function laskeAikaaJaljella(aika, element){
	
  var ajanloppu = aika;
  
  var t = Date.parse(ajanloppu) - Date.parse(new Date());
  var minutes = Math.floor( (t/1000/60) % 60 );
  var hours = Math.floor( (t/(1000*60*60)) % 24 );
  var days = Math.floor( t/(1000*60*60*24) );
  
  var tekstirimpsu = days + "d, " + hours + "h, " + minutes + "min";
  
  $(element).text(tekstirimpsu);

}

function haeElementit() {
	
	var elementit = document.getElementsByClassName("alkamiseenAikaa");
	//alert(elementit[0].innerHTML);
	//aseta ajat elementeille
	for(i = 0; i < elementit.length; i++) {
		aika = elementit[i].innerHTML;
		laskeAikaaJaljella(aika, elementit[i]);
	}
	
}