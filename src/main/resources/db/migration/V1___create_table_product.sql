CREATE TABLE tb_product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(60) NOT NULL,
    name_product VARCHAR(60) NOT NULL,
    price DOUBLE NOT NULL,
    amount INT NOT NULL,
    size_item VARCHAR(60),
    stock INT NOT NULL,
    description TEXT
)
