
ALTER TABLE randomOsallistuja CHANGE sposti `sahkoposti` VARCHAR(255) NOT NULL;

ALTER DATABASE a1500925 CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE tablename CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE randomOsallistuja;
SELECT @@character_set_database, @@collation_database;

ALTER TABLE Tapahtuma
MODIFY COLUMN pvm DATE NOT NULL;

ALTER TABLE Tapahtuma 
AUTO_INCREMENT = 1;



ALTER TABLE Henkilo
ADD COLUMN salasana varchar(255) NOT NULL;

ALTER TABLE palaute 
ADD COLUMN otsikko VARCHAR(30) AFTER sposti;

ALTER TABLE palaute 
MODIFY COLUMN otsikko VARCHAR (50);