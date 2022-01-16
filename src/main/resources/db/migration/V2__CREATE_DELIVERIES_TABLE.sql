CREATE TABLE deliveries (
    id BIGINT NOT NULL AUTO_INCREMENT,
	customer_id BIGINT NOT NULL,
	delivery_fee DECIMAL(10,2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	request_date DATETIME NOT NULL,
	finished_date DATETIME,

	receiver_name VARCHAR(60) NOT NULL,
	receiver_street VARCHAR(255) NOT NULL,
	receiver_number VARCHAR(30) NOT NULL,
	receiver_complement VARCHAR(60),
	receiver_neighbourhood VARCHAR(60) NOT NULL,

	PRIMARY KEY (id),
	UNIQUE (id)
) engine=InnoDB default charset=utf8;

ALTER TABLE deliveries ADD CONSTRAINT fk_customer_delivery
FOREIGN KEY(customer_id) REFERENCES customers (id)