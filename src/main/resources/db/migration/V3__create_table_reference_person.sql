CREATE TABLE tb_reference_person(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60),
    phone VARCHAR(15),
    zip_code VARCHAR(8),
    address VARCHAR(60),
    number VARCHAR(10),
    neighborhood VARCHAR(20),
    complement VARCHAR(20),
    city VARCHAR(20),
    state VARCHAR(2),
    observation TEXT,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES tb_customer(id) ON DELETE CASCADE
)