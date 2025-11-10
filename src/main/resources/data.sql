-- ROLES
INSERT INTO roles (id, name, created_at, updated_at)
VALUES 
  (1, 'ROLE_ADMIN', NOW(), NOW()),
  (2, 'ROLE_USER', NOW(), NOW());

-- ACCOUNTS
INSERT INTO account (id, balance, type, status, created_at, updated_at)
VALUES
  (1, 5000.00, 'CHECKING', 'ACTIVE', NOW(), NOW()),
  (2, 15000.00, 'SAVINGS', 'ACTIVE', NOW(), NOW());

-- USERS
INSERT INTO users (id, name, surname, username,email, password, status, created_at, updated_at)
VALUES
  (1, 'Gustavo', 'Weiss', 'gustavo','teste@teste.com', '$2a$05$Ie6NBFJqxE5zgBj18xqWpeQSEQTfnw9bnvn7ZENqTM0WuS7MSOOHK', 'ACTIVE', NOW(), NOW()),
  (2, 'Gustavo2', 'Weiss', 'gustavo2','teste2@teste.com', '$2a$05$Ie6NBFJqxE5zgBj18xqWpeQSEQTfnw9bnvn7ZENqTM0WuS7MSOOHK', 'ACTIVE', NOW(), NOW());

-- USER_ROLE
INSERT INTO user_role (user_id, role_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 2);

-- USER_ACCOUNT
INSERT INTO user_account (user_id, account_id)
VALUES
  (1, 1),
  (2, 2);
