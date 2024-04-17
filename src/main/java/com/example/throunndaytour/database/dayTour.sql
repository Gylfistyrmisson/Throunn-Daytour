CREATE TABLE user (
    name VarChar(30),
    id INT,
    email VarChar(30),
    kennitala VarChar(10),
    password VarChar(30),
    daytourCNT int,
    daytours VarChar(1000)
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
    reviewID VarChar(1000),
    description VarChar(1000)
);

CREATE TABLE review (
    id int,
    title VarChar(30),
    text VarChar(200),
    rating INT,
    username VarChar(30)
);

CREATE TABLE idGenerator (
    value INT
);

//Til að pre-setta idGenerator
INSERT INTO idGenerator (value) VALUES(4);

//Setja inn gildi
INSERT INTO user (name,id,email,kennitala,password,daytourCNT, daytours) VALUES ('siggi',0,'siggi@siggi.is','1234567890','siggibesti123',2,'2,3,');
INSERT INTO user (name,id,email,kennitala,password,daytourCNT, daytours) VALUES ('balli',1,'ballibumba@gmail.is','1324567890','ballibesti123',1,'2,');


INSERT INTO daytour (name,id,price,duration,dateDay,dateMonth,dateYear,location,customerCNT,customerID,reviewCNT, reviewID, description)
VALUES ('skemmtiferð',2,100,10,1,1,2001,'reykjavik',2,'0,1,',0,'','alveg æðiselgt');
INSERT INTO daytour (name,id,price,duration,dateDay,dateMonth,dateYear,location,customerCNT,customerID,reviewCNT, reviewID, description)
VALUES ('fjöruferð',3,1000,100,2,2,2002,'keflavik',1,'0,',0,'','mökknæs');



INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Golden Circle Tour', 1, 99.99, 8, 15, 4, 2024, 'Reykjavik', 5, '1,2,3', 2, '1,2', 'A classic tour covering Gullfoss, Geysir, and Thingvellir.');
INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Northern Lights Tour', 2, 120.50, 3, 20, 11, 2024, 'Akureyri', 3, '4,5', 1, '3', 'Experience the magical Aurora Borealis in North Iceland.');
INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Whale Watching Tour', 3, 75.00, 5, 10, 7, 2024, 'Husavik', 8, '6,7,8', 4, '4,5', 'Get close to majestic whales in their natural habitat.');
INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Blue Lagoon Spa', 4, 50.00, 4, 25, 5, 2024, 'Grindavik', 10, '9,10', 5, '6,7', 'Relax in the geothermal waters of the famous Blue Lagoon.');