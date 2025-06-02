ALTER TABLE tb_sales
ADD COLUMN start_date DATE,
ADD COLUMN end_date DATE,
ADD COLUMN rate_amount DECIMAL(10,2),
ADD COLUMN rate_name VARCHAR(60);
