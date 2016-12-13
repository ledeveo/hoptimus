/**
 *  laskee alkamisajat tapahtumille minuutin välein
 */

function asetaVari(days) {
	
	  var colorStep = 5;
	  var red       = colorStep * days;
	  var green     = 200 - colorStep * days;
	  var blue      = 50;
	  
	  //pysytään värirajoilla
	  if(green <= 0) {
		  green = 0;
	  }
	  if(red <= 0) {
		  red = 0;
	  }
	  if(blue <= 0) {
		  blue = 0;
	  }
	  if(green >= 200) {
		  green = 200;
	  }
	  if(red >= 200) {
		  red = 200;
	  }
	  if(blue >= 200) {
		  blue = 200;
	  }
	  
	  var color = "'rgb(" + red + "," + green + "," + blue + ")'";
	  
	  return color;
}

function laskeAikaaJaljella(aika, element){
	
  var t = Date.parse(aika) - Date.parse(new Date());
  var minutes = Math.floor( (t/1000/60) % 60 );
  var hours = Math.floor( (t/(1000*60*60)) % 24 );
  var days = Math.floor( t/(1000*60*60*24) );
  
  var tekstirimpsu = "";
  
  hours = -2; //2h pois => suomenaika
  
  // tapahtumaa ennen.
  if(days > 0) {
	  tekstirimpsu += days + "p ";
  }
  if(hours > 0) {
	  tekstirimpsu += hours + "t ";
  }
  if(minutes > 0) {
	  tekstirimpsu += minutes + "min";
  }
  
  // 8 tuntia tapahtuman aikana
  if(days < 1 && hours < 1 && minutes < 1 && hours > -8) {
	  tekstirimpsu = "on alkanut!";	  
  }
  
  // 8 tunnin jälkeen tapahtumasta
  if(days < 1 && hours < 1 && minutes < 1 && hours < -8) {
	  tekstirimpsu = "tapahtuma ohi.";
  }
  
  // laita väri tekstille
  var color = asetaVari(days);
  $(element).css('color', color);
  
  //DEBUG
  console.log("tekstirimpsu: [" + tekstirimpsu + "]");
  console.log("color: [" + color + "]");
  console.log("element: [" + element + "]");
  console.log("");
  $(element).text(tekstirimpsu);
  // DEBUG
  //console.log("elementti: " + element);
  //console.log("aika: " + tekstirimpsu);

}

function naytaAjat(ajat, elementit) {
	
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
	
	//näytä ajat
	naytaAjat(ajat, elementit);
	//näytä ajat väliajoin
	var ajanlaskenta = setInterval(function(){
		naytaAjat(ajat, elementit);
		pyoritaKuva(); //pyöritä oluttuoppia aina kun ajat päivittyy
	}, 1000*60);
	
	
}