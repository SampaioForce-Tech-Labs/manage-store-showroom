CREATE TABLE tb_sales_product(
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    price_Unit DECIMAL(10,2) NOT NULL,
    price_Total DECIMAL(10,2) NOT NULL,
    quantity_In_Stock INT NOT NULL,
    sales_product_sales_id INT NOT NULL,
    FOREIGN KEY (sales_product_sales_id) REFERENCES tb_sales(id) ON DELETE CASCADE ON UPDATE CASCADE
)