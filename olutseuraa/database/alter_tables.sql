
ALTER TABLE randomOsallistuja CHANGE sposti `sahkoposti` VARCHAR(255) NOT NULL;

ALTER DATABASE a1500925 CHARACTER SET utf8 COLLATE utf8_unicode_ci;
ALTER TABLE tablename CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE randomOsallistuja;
SELECT @@character_set_database, @@collation_database;



