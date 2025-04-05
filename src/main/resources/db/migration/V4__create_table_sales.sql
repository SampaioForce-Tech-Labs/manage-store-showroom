CREATE TABLE tb_sales(
    id INT AUTO_INCREMENT PRIMARY KEY,
    discount_percentage DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_items INT NOT NULL,
    price_with_discount DECIMAL(10,2),
    total_price DECIMAL(10,2) NOT NULL,
    create_at DATETIME NOT NULL,
    sales_customer_id INT NOT NULL,
    FOREIGN KEY (sales_customer_id) REFERENCES tb_customer(id) ON DELETE CASCADE ON UPDATE CASCADE
)