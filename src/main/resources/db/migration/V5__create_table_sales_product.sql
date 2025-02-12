CREATE TABLE tb_sales_product(
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    discount DOUBLE,
    price DECIMAL(10,2) NOT NULL,
    amount INT NOT NULL,
    price_with_discount DECIMAL(10,2) NOT NULL,
    sales_product_sales_id INT NOT NULL,
    FOREIGN KEY (sales_product_sales_id) REFERENCES tb_sales(id) ON DELETE CASCADE ON UPDATE CASCADE
)