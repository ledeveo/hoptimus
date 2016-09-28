SELECT id, nimi, pvm, aika, paikka, teema, isanta,
kuvaus from Tapahtuma INNER JOIN randomOsallistuja ON tapahtumaId = id;

SELECT etunimi, sukunimi, nimi from Tapahtuma JOIN randomOsallistuja ON tapahtumaId = id;