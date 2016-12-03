-- MUISTA! console encoding latin1

INSERT INTO Henkilo
	(etunimi, sukunimi, sahkoposti)
VALUES
	('Matti', 'Meikäläinen', 'mattimeikalainen@roskaposti.fi'),
	('Mikko', 'Mallikas', 'mikkomallikas@roskaposti.fi'),
	('Outi', 'Opiskelija', 'outi69@roskaposti.fi'),
	('Tiina', 'Tikolainen', 'touhutiina@roskaposti.fi');
	
INSERT INTO Tapahtuma
	(nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus, maxOsallistujamaara)
VALUES
	('Korjaussarja', '2016-11-07', '13:00', 'Nurkka', 'IT', 0, 'Heimo Vesa', 'Sillä se lähtee millä se on tulluki', 5);

	INSERT INTO Tapahtuma
	(nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus, maxOsallistujamaara)
VALUES
	('Testitapahtuma', '2016-11-12', '01:00', 'Nurkka', 'Ohjelmointi', 0, 'Erkki Esimerkki', 'Testitapahtuma, ei siis oikea tapahtuma.', 10);
	

	
INSERT INTO 
	tapOsallistuja (henkiloId, tapahtumaId)
VALUES
	(1, 1);

INSERT INTO palaute (nimi, sposti, palaute)
VALUES ('Jorma Palauttaja', 'jormanposti@meisseli.fi', 'Ihan paska tämä teidän palvelunne!');