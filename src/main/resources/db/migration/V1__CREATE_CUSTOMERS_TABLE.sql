CREATE TABLE customers (
  id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(60) NOT NULL,
	email VARCHAR(255) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (id)
) engine=InnoDB default charset=utf8;