CREATE TABLE user (
    name VarChar(30),
    id INT,
    email VarChar(30),
    kennitala VarChar(10),
    password VarChar(30),
    isAdmin INT
    );

CREATE TABLE daytour (
    name VarChar(30),
    id INT,
    price INT,
    duration INT,
    dateDay INT,
    dateMonth INT,
    dateYear INT,
    location VarChar(30),
    customerCNT INT,
    customerID VarChar(100),
    reviewCNT INT,
    reviewID VarChar(100)
    );

CREATE TABLE review (
    name VarChar(30),
    description VarChar(200),
    rating INT,
    id INT
    );

CREATE TABLE idGenerator (
    value INT
    );

