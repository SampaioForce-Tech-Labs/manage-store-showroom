CREATE TABLE tb_client(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    cpf VARCHAR(11) UNIQUE,
    email VARCHAR(60),
    phone VARCHAR(15),
    date_birth DATE,
    zip_code VARCHAR(8),
    address VARCHAR(60),
    number_address VARCHAR(10),
    neighborhood VARCHAR(20),
    complement VARCHAR(20),
    city VARCHAR(20),
    state VARCHAR(2),
    create_at DATETIME
)