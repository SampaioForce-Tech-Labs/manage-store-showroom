CREATE TABLE tb_product(
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    name VARCHAR(60) NOT NULL,
    price DOUBLE NOT NULL,
    amount INT NOT NULL,
    size_item VARCHAR(20),
    stock INT NOT NULL,
    category VARCHAR(20) NOT NULL,
    subcategory VARCHAR(20) NOT NULL,
    create_at DATETIME,
    description TEXT
)
