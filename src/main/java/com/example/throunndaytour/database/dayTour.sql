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

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Golden Circle Extended', 5, 13986, 9, 16, 4, 2024, 'Reykjavik', 7, '11,12', 3, '8,9', 'Explore the beauty of Gullfoss, Geysir, and Thingvellir with an extended itinerary.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Aurora Viewing Night', 6, 16870, 4, 21, 11, 2024, 'Akureyri', 4, '13', 2, '10', 'Night tour to witness the Northern Lights in the comfort of our luxury coach.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Whale Encounter Adventure', 7, 10500, 6, 11, 7, 2024, 'Husavik', 6, '14,15', 3, '11,12', 'An adventurous day on the sea watching whales with expert guides.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Hot Springs Relaxation Day', 8, 7000, 5, 26, 5, 2024, 'Grindavik', 12, '16,17', 6, '13,14', 'Spend a day relaxing in the natural hot springs near the Blue Lagoon.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Volcano and Lava Tour', 9, 11200, 7, 15, 6, 2024, 'Reykjavik', 8, '18,19', 2, '15,16', 'Explore Iceland’s volcanic landscape with a guided tour of active and dormant sites.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Historic Vik Village Tour', 10, 9800, 8, 18, 8, 2024, 'Vik', 5, '20', 1, '17', 'Discover the charming village of Vik and its surrounding natural wonders.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Glacier Hiking Experience', 11, 12600, 5, 12, 3, 2024, 'Skaftafell', 9, '21,22', 3, '18,19', 'Hike on one of Iceland’s most famous glaciers with experienced guides.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Puffin Watching Tour', 12, 8400, 4, 14, 7, 2024, 'Westman Islands', 10, '23,24', 4, '20,21', 'A boat tour to see puffins and other seabirds on the Westman Islands.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Icelandic Horseback Riding', 13, 11900, 3, 22, 9, 2024, 'Hafnarfjordur', 3, '25', 1, '22', 'Experience the unique gait of the Icelandic horse in beautiful countryside.');


INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Lava Tunnel Exploration', 15, 12600, 6, 28, 4, 2024, 'Reykjanes Peninsula', 7, '27,28', 3, '24,25', 'Explore the natural lava tunnels formed by volcanic activity.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Snæfellsnes Peninsula Tour', 16, 16800, 9, 20, 3, 2024, 'Snæfellsnes', 6, '29,30', 2, '26,27', 'A comprehensive tour of the mystical Snæfellsnes Peninsula and its dramatic landscapes.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Ice Cave and Waterfall Tour', 17, 15400, 8, 23, 11, 2024, 'Vatnajokull', 4, '31', 1, '28', 'Discover stunning ice caves and waterfalls in Vatnajokull National Park.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('East Fjords Panorama', 18, 16100, 7, 15, 8, 2024, 'East Fjords', 9, '32,33', 4, '29,30', 'Experience the unique beauty of the East Fjords with panoramic views.');

INSERT INTO daytour (name, id, price, duration, dateDay, dateMonth, dateYear, location, customerCNT, customerID, reviewCNT, reviewID, description)
VALUES ('Geothermal Fields Exploration', 19, 11200, 5, 30, 1, 2024, 'Myvatn', 11, '34,35', 5, '31,32', 'Tour the active geothermal fields of Myvatn, known for their stunning beauty.');


INSERT INTO review (id, title, text, rating, username)
VALUES (8, 'Amazing Sights', 'The extended itinerary allowed us to enjoy more time at each landmark. Highly recommended!', 5, 'JohnDoe');

INSERT INTO review (id, title, text, rating, username)
VALUES (10, 'Magical Night', 'Seeing the Northern Lights was a breathtaking experience. The tour was well-organized.', 5, 'AliceM');

INSERT INTO review (id, title, text, rating, username)
VALUES (11, 'Whale Spectacle', 'The guides were knowledgeable and we saw several whales. A must-do for nature lovers.', 4, 'BobSmith');

INSERT INTO review (id, title, text, rating, username)
VALUES (13, 'Relaxing Day', 'The hot springs were exactly what I needed to unwind. The location is perfect.', 5, 'ClaraBelle');

INSERT INTO review (id, title, text, rating, username)
VALUES (15, 'Volcanic Wonders', 'Exploring the volcanic sites was incredible. The landscapes are like another planet!', 5, 'DerekZ');

INSERT INTO review (id, title, text, rating, username)
VALUES (17, 'Charming Vik', 'Vik is beautiful and the tour covers all the must-see spots. Great day out!', 4, 'EvaG');

INSERT INTO review (id, title, text, rating, username)
VALUES (18, 'Glacier Adventure', 'Hiking the glacier was exhilarating and educational. The guides ensured everyone was safe.', 5, 'FrankP');

INSERT INTO review (id, title, text, rating, username)
VALUES (20, 'Puffin Fun', 'The puffins were adorable and the tour guide was very funny and informative.', 5, 'GraceH');

INSERT INTO review (id, title, text, rating, username)
VALUES (22, 'Horseback Joy', 'Riding the Icelandic horses was a unique experience. They are gentle and the scenery was stunning.', 5, 'HannahJ');

INSERT INTO review (id, title, text, rating, username)
VALUES (24, 'Lava Exploration', 'The lava tunnel tour was fascinating. Learned a lot about geology and volcanic activity.', 4, 'IanK');

INSERT INTO review (id, title, text, rating, username)
VALUES (26, 'Peninsula Wonders', 'Snæfellsnes is gorgeous, with landscapes that are vivid and diverse. Well worth the trip.', 5, 'JennaL');

INSERT INTO review (id, title, text, rating, username)
VALUES (28, 'Ice Cave Magic', 'The ice caves are spectacular, and the waterfalls add to the beauty of this tour.', 5, 'KyleM');

INSERT INTO review (id, title, text, rating, username)
VALUES (29, 'Stunning Fjords', 'The East Fjords are breathtaking. Each view was better than the last. Fantastic photography spots.', 5, 'LauraN');

INSERT INTO review (id, title, text, rating, username)
VALUES (31, 'Geothermal Wonder', 'The geothermal fields at Myvatn are a must-see. The natural beauty and steam vents are incredible.', 5, 'MikeO');
