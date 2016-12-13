# hoptimus
Olutseuraa

# Setting up:
add db_connection.properties file to olutseuraa/src/main/resources/
with text:
  ```
  db.driver=org.mariadb.jdbc.Driver
  db.url=jdbc:mariadb://localhost/DATABASE_NAME
  db.username=DATABASE_USERNAME
  db.password=DATABASE_PASSWORD
  ```
 
and

mail_connection.properties with text:
  ```
  mail.smtp=smtp.gmail.com
  mail.port=587
  mail.username=YOUR_MAIL_ADDRESS@gmail.com
  mail.password=YOUR_MAIL_PASSWORD
  ```

# Screencaps

Front page
![alt tag](https://github.com/ledeveo/hoptimus/blob/master/olutseuraa/demo/olutseuraa_etusivu.PNG)

Front page as admin. Admin can change information about events.
![alt tag](https://github.com/ledeveo/hoptimus/blob/master/olutseuraa/demo/olutseuraa_etusivu_admin.PNG)

Creating new events page for admin.
![alt tag](https://github.com/ledeveo/hoptimus/blob/master/olutseuraa/demo/olutseuraa_tapahtuman_luonti.PNG)

Page for user to see events they attended to.
![alt tag](https://github.com/ledeveo/hoptimus/blob/master/olutseuraa/demo/olutseuraa_kayttajan_osallistumiset.PNG)


 
