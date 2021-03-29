CREATE DATABASE IF NOT EXISTS adlister_db;

CREATE USER adlister_user@localhost IDENTIFIED BY 'p@$$w0rd';
GRANT ALL ON adlister_db.* TO adlister_user@localhost;