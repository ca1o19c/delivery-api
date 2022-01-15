CREATE TABLE deliveries (
    id BIGINT NOT NULL AUTO_INCREMENT,
	customer_id BIGINT NOT NULL,
	delivery_fee DECIMAL(10,2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	request_date DATETIME NOT NULL,
	finished_date DATETIME,

	addressee_name VARCHAR(60) NOT NULL,
	addressee_street VARCHAR(255) NOT NULL,
	addressee_number VARCHAR(30) NOT NULL,
	addressee_complement VARCHAR(60),
	addressee_neighbourhood VARCHAR(30) NOT NULL,

	PRIMARY KEY (id),
	UNIQUE (id)
) engine=InnoDB default charset=utf8;

ALTER TABLE deliveries ADD CONSTRAINT fk_customer_delivery
FOREIGN KEY(customer_id) REFERENCES customers (id)