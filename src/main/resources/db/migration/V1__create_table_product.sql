CREATE TABLE tb_product(
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    name VARCHAR(60) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    price_with_discount DECIMAL(10,2),
    discount_percentage DECIMAL(10,2),
    amount INT NOT NULL,
    size_item VARCHAR(60),
    category VARCHAR(20) NOT NULL,
    subcategory VARCHAR(20) NOT NULL,
    create_at DATETIME,
    description TEXT
)
