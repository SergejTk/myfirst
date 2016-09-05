DROP TABLE IF EXISTS workers;

CREATE TABLE workers (
  name      VARCHAR(20) NOT NULL,
  firstname VARCHAR(20) NOT NULL,
  lastname  VARCHAR(40) NOT NULL,
  groups    INT,
  adr       VARCHAR(40),
  kurs      INT,
  ball      INT,
  numberinv INT,
  abc       DATE,
  def       INT,
  PRIMARY KEY (def)


);


COPY workers FROM 'C:\\workers.csv' WITH (
  FORMAT CSV );


CREATE SEQUENCE worker_idseq START 10001;