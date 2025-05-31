CREATE TABLE tb_rate(
    id INT AUTO_INCREMENT PRIMARY KEY,
    rate_name VARCHAR(60),
    rate_amount DECIMAL(10,2),
    number_installments INT
)