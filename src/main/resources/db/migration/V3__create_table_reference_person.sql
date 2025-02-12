CREATE TABLE tb_reference_person(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60),
    phone VARCHAR(15),
    zip_code VARCHAR(9),
    address VARCHAR(60),
    number VARCHAR(10),
    references_point VARCHAR(60),
    complement VARCHAR(20),
    city VARCHAR(20),
    state VARCHAR(2),
    reference_person_customer_id INT NOT NULL,
    FOREIGN KEY (reference_person_customer_id) REFERENCES tb_customer(id) ON DELETE CASCADE ON UPDATE CASCADE
)