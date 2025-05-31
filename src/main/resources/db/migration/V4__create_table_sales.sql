CREATE TABLE tb_sales(
    id INT AUTO_INCREMENT PRIMARY KEY,
    discount_percentage DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_items INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(30),
    number_installments INT,
    create_at DATETIME NOT NULL,
    sales_customer_id INT NOT NULL,
    FOREIGN KEY (sales_customer_id) REFERENCES tb_customer(id) ON DELETE CASCADE ON UPDATE CASCADE
)