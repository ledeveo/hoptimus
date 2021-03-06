SELECT h.etunimi, h.sukunimi, h.sahkoposti, h.id as henkId, t.id as TapId
	FROM Henkilo h
	LEFT JOIN randomOsallistuja o ON h.id = o.henkiloId
	LEFT JOIN Tapahtuma t ON t.id = o.tapahtumaId 
WHERE o.tapahtumaId = ?;


SELECT Tapahtuma.id, nimi, pvm, aika, paikka, teema, osallistujat, isanta, kuvaus, maxOsallistujamaara
FROM Tapahtuma 
LEFT JOIN tapOsallistuja o 
ON Tapahtuma.id = o.tapahtumaId
WHERE o.henkiloId=?;


//hae kannasta k�ytt�j�n tapahtuma idll� ja vain yksi rivi.

SELECT id, henkiloId, tapahtumaId
FROM tapOsallistuja
WHERE henkiloId=67 AND tapahtumaId=16
ORDER BY id LIMIT 1;
