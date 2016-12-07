UPDATE Tapahtuma
SET maxOsallistujamaara = 10 
WHERE id = 2;

UPDATE Tapahtuma
SET pvm = "12.10.2016"
WHERE id = 1;

UPDATE webuser2
SET password_encrypted = "8e3c77a2c6d4f1025650f7e4a33802d66194661e87ab7e68c9285a8362c1a85c110cc1ca99fea10a"
WHERE id = 1;

UPDATE palaute
SET luettu = true
WHERE id = 1;