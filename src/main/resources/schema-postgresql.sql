DROP TABLE IF EXISTS umowione_spotkania;

CREATE TABLE  umowione_spotkania(
	id serial NOT NULL,
	kod varchar(100),
	region varchar(100),
	przedstawiciel varchar(100),
	email varchar(100),
	data_spotkania date,
	data_umowienia date,
	PRIMARY KEY (id)
);


