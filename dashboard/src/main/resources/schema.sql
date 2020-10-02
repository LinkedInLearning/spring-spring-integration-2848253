DROP TABLE IF EXISTS DEVICE;

CREATE TABLE DEVICE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  display VARCHAR(250) NOT NULL
);

--https://howtodoinjava.com/spring-boot2/h2-database-example/
