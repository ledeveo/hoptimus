/**
 * 
 */

//formatoi ajan javascriptille kivaksi
function formatoiAikaJaKello(pvm, klo) {
	var formatoituaika = pvm + "T" + klo + ":00Z";
	return formatoituaika;
}

function laskeAikaaJaljella(pvm, klo, element){
	
  var ajanloppu = formatoiAikaJaKello(pvm, klo);
  
  var t = Date.parse(ajanloppu) - Date.parse(new Date());
  var minutes = Math.floor( (t/1000/60) % 60 );
  var hours = Math.floor( (t/(1000*60*60)) % 24 );
  var days = Math.floor( t/(1000*60*60*24) );
  
  var tekstirimpsu = days + " päivää, " + hours + "tuntia, " + minutes + " minuuttia";
  //alert(tekstirimpsu);
  $(element).text(tekstirimpsu);

}