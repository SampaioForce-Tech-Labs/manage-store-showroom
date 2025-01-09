CREATE TABLE tb_product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    price DOUBLE NOT NULL,
    amount INT NOT NULL,
    size_item VARCHAR(60),
    stock INT NOT NULL,
    category VARCHAR(50) NOT NULL,
    create_at DATETIME,
    description TEXT
)
