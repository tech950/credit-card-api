DROP TABLE IF EXISTS credit_card;

CREATE TABLE credit_card (
  id VARCHAR(19) PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  balance  DECIMAL(20, 2) DEFAULT 0.0,
  max_limit  DECIMAL(20, 2) DEFAULT 0.0,
  currency VARCHAR(3) NOT NULL
);
