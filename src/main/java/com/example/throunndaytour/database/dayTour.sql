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
    reviewID VarChar(1000)

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

//Til að pre-setta idGenerator
INSERT INTO idGenerator (value) VALUES(4);

//Setja inn gildi
INSERT INTO user (name,id,email,kennitala,password,daytourCNT, daytours) VALUES ('siggi',0,'siggi@siggi.is','1234567890','siggibesti123',2,'2,3,');
INSERT INTO user (name,id,email,kennitala,password,daytourCNT, daytours) VALUES ('balli',1,'ballibumba@gmail.is','1324567890','ballibesti123',1,'2,');
INSERT INTO daytour (name,id,price,duration,dateDay,dateMonth,dateYear,location,customerCNT,customerID,reviewCNT, reviewID) VALUES ('skemmtiferð',2,100,10,1,1,2001,'reykjavik',2,'0,1,',0,'');
INSERT INTO daytour (name,id,price,duration,dateDay,dateMonth,dateYear,location,customerCNT,customerID,reviewCNT, reviewID) VALUES ('fjöruferð',3,1000,100,2,2,2002,'keflavik',1,'0,',0,'');