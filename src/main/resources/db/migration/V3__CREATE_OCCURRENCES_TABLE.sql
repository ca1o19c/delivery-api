CREATE TABLE occurrences (
    id BIGINT NOT NULL AUTO_INCREMENT,
    delivery_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    registration_date DATETIME NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (id)
) engine=InnoDB default charset=utf8;

ALTER TABLE occurrences ADD CONSTRAINT fk_occurrence_delivery
FOREIGN KEY(delivery_id) REFERENCES deliveries (id)