CREATE TABLE `account` (
    id BIGINT auto_increment,
    money_amount DECIMAL(19, 5) NOT NULL,
    created_at TIMESTAMP without time zone not null default CURRENT_TIMESTAMP(),
    updated_at TIMESTAMP without time zone not null default CURRENT_TIMESTAMP(),
    deleted_at TIMESTAMP without time zone not null default CURRENT_TIMESTAMP(),
    PRIMARY KEY (id)
);