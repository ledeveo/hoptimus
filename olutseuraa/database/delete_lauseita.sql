DELETE FROM Tapahtuma WHERE nimi ="Testitapahtuma";

TRUNCATE TABLE randomOsallistuja;

// poista vain yksi yhteyssuhde
DELETE FROM tapOsallistuja WHERE henkiloId=72 AND tapahtumaID=10 LIMIT 1;
