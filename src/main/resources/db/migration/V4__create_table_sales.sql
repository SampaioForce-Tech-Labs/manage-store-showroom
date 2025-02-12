CREATE TABLE tb_sales(
    id INT AUTO_INCREMENT PRIMARY KEY,
    total_items INT NOT NULL,
    discount_total_sum DECIMAL(10,2),
    total_sum DECIMAL(10,2) NOT NULL,
    total_with_discount DECIMAL(10,2) NOT NULL,
    create_at DATETIME NOT NULL,
    sales_customer_id INT NOT NULL,
    FOREIGN KEY (sales_customer_id) REFERENCES tb_customer(id) ON DELETE CASCADE ON UPDATE CASCADE
)