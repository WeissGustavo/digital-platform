CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    name VARCHAR(150),
    surname VARCHAR(150),
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(50)
);

CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE account (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    balance NUMERIC(19,4) NOT NULL DEFAULT 0,
    type VARCHAR(50),
    status VARCHAR(50)
);

CREATE TABLE user_account (
    user_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, account_id),
    CONSTRAINT fk_user_account_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_account_account FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE transaction (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    sending_account_id BIGINT,
    receiving_account_id BIGINT,
    send_time TIMESTAMP,
    receive_time TIMESTAMP,
    value NUMERIC(19,4),
    external BOOLEAN,
    external_account_identifier VARCHAR(255),
    currency VARCHAR(10),
    status VARCHAR(50),
    CONSTRAINT fk_transaction_sending FOREIGN KEY (sending_account_id) REFERENCES account(id),
    CONSTRAINT fk_transaction_receiving FOREIGN KEY (receiving_account_id) REFERENCES account(id)
);

CREATE INDEX idx_transaction_sending ON transaction(sending_account_id);
CREATE INDEX idx_transaction_receiving ON transaction(receiving_account_id);

CREATE TABLE document (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    user_id BIGINT,
    transaction_id BIGINT,
    type VARCHAR(50),
    number VARCHAR(100),
    file_name VARCHAR(255),
    CONSTRAINT fk_document_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_document_transaction FOREIGN KEY (transaction_id) REFERENCES transaction(id)
);

CREATE INDEX idx_document_user ON document(user_id);
CREATE INDEX idx_document_transaction ON document(transaction_id);
