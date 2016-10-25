-- MUISTA! console encoding latin1

INSERT INTO
	authority
VALUES
	(2,'ROLE_ADMIN'),
	(1,'ROLE_USER');
	

INSERT INTO
	webuser2
VALUES
	(1,'hoptimus','dafa02efa9012df0c2ed6fc3d69022b02c191100783bea078f7bf90e5ab452b34ebdba4282046ded',1,'Hoptimus','Admin');
	
	
	
INSERT INTO
	webuser2_authority
VALUES
	
	(1,1,2),
	(2,1,1);