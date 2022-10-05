CREATE TABLE users (
                     id BIGSERIAL PRIMARY KEY,
                     nickname TEXT NOT NULL,
                     email TEXT NOT NULL,
                     password TEXT NOT NULL,
                     unique (id)
);
