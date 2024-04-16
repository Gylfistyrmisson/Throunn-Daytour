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
    customerID VarChar(1000),
    reviewCNT INT,
    reviewID VarChar(1000)
);

CREATE TABLE review (
    name VarChar(30),
    description VarChar(200),
    rating INT,
    id INT
);

CREATE TABLE booking (
    bookingID INT,
    userID INT,
    daytourID INT
);

CREATE TABLE idGenerator (
    value INT
);

// Til að pre-setta idGenerator
INSERT INTO idGenerator (value) VALUES(0);

// Mock daytour
INSERT INTO daytour
(name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID)
VALUES
('Grand Canyon Tour', 101, 150, 8, 15, 5, 2024, 'Grand Canyon', 12, 'CUST1001', 4, 'REV2001');

INSERT INTO daytour
(name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID)
VALUES
('City Lights Night Tour', 102, 75, 3, 20, 6, 2024, 'New York', 5, 'CUST1002', 2, 'REV2002');

INSERT INTO daytour
(name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID)
VALUES
('Historical Sites Tour', 103, 100, 6, 10, 4, 2024, 'Rome', 8, 'CUST1003', 3, 'REV2003');

INSERT INTO daytour
(name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID)
VALUES
('Safari Adventure', 104, 200, 12, 22, 7, 2024, 'Kenya', 10, 'CUST1004', 5, 'REV2004');


