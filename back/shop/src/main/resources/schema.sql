CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT,
                         product_code VARCHAR(255) NOT NULL,
                         product_name VARCHAR(255) NOT NULL,
                         product_description VARCHAR(1000) NOT NULL,
                         product_price DOUBLE NOT NULL,
                         product_quantity INT NOT NULL,
                         inventory_status VARCHAR(255) NOT NULL,
                         product_category VARCHAR(255) NOT NULL,
                         product_image VARCHAR(255),
                         product_rating INT,
                         PRIMARY KEY (id),
                         CONSTRAINT UK_product_code UNIQUE (product_code)
);

