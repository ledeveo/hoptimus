-- MUISTA! console encoding latin1

INSERT INTO Henkilo
	(etunimi, sukunimi, sahkoposti)
VALUES
	('Matti', 'Meikäläinen', 'mattimeikalainen@roskaposti.fi'),
	('Mikko', 'Mallikas', 'mikkomallikas@roskaposti.fi'),
	('Outi', 'Opiskelija', 'outi69@roskaposti.fi'),
	('Tiina', 'Tikolainen', 'touhutiina@roskaposti.fi');
	
INSERT INTO Tapahtuma
	(nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus)
VALUES
	('Korjaussarja', '22.9.2016', '13:00', 'Nurkka', 'IT', 0, 'Joonas Heikkinen', 'Sillä se lähtee millä se on tulluki');

	INSERT INTO Tapahtuma
	(nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus)
VALUES
	('Testitapahtuma', '01.01.2017', '01:00', 'Nurkka', 'Ohjelmointi', 0, 'Erkki Esimerkki', 'Testitapahtuma, ei siis oikea tapahtuma.');
	

	
INSERT INTO 
	randomOsallistuja (henkiloId, tapahtumaId)
VALUES
	(1, 1);