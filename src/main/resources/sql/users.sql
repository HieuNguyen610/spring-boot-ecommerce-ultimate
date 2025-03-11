Create database ShopApp;

CREATE TABLE users (
    id SERIAL PRIMARY KEY ,
    fullname VARCHAR(100) DEFAULT '',
    phone_name VARCHAR(10) NOT NULL ,
    address VARCHAR(200) DEFAULT '',
    password varchar(100) NOT NULL default '',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_active BOOLEAN,
    date_of_birth TIMESTAMP,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0
);

CREATE TABLE tokens (
    id SERIAL PRIMARY KEY ,
    token varchar(255) unique not null ,
    token_type varchar(50) not null ,
    revoked boolean,
    expired boolean,
    expiration_date timestamp,
    user_id int, -- FK users
    foreign key (user_id) references users(id)
)