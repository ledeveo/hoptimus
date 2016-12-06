

<div class="reveal" id="contact" data-reveal data-close-on-click="true" data-animation-in="scale-in-up" data-animation-out="scale-out-down">
  <h4>Kerro, miten voisimme kehittää palveluamme.</h4>
  <form action="palautetta" method="POST" id="palauteform">
  <div class="row">
    <div class="small-6 columns">
      <label>Sähköposti
        <input type="email" maxlength="50" placeholder="...ei ole pakko täyttää" name="sposti">
      </label>
    </div>
    <div class="small-6 columns">
      <label>Nimi
        <input type="text" maxlength="50" name="nimi" placeholder="...eikä tätäkään">
      </label>
    </div>
     <div class="small-12 columns">
      <label>Otsikko
        <input type="text" maxlength="50" name="otsikko" placeholder="Mitä palautteesi koskee?">
      </label>
    </div>
    <div class="small-12 columns">
    <label>Palaute
    <textarea placeholder="Kirjoita palautteesi tähän laatikkoon (max 500 merkkiä)" name="palaute" maxLength="500"></textarea>
    </label>
    </div>
  </div>
  <button type="submit" class="button float-center small success">Lähetä <i class="fi-upload small"></i></button>
</form>
  <button class="close-button" data-close aria-label="Close reveal" type="button">
    <span aria-hidden="true">&times;</span>
  </button>
</div>

