INSERT INTO gallery (id, username, description, target_date, done)
VALUES (10001, 'milesd', 'Gallery 1 description', CURRENT_DATE, false),
       (10002, 'milesd', 'Gallery 2 description', CURRENT_DATE, false),
       (10003, 'milesd', 'Gallery 3 description', CURRENT_DATE, false),
       (10004, 'milesd', 'Gallery 4 description', CURRENT_DATE, false),
       (10005, 'milesd', 'Gallery 5 description', CURRENT_DATE, false);



INSERT INTO picture (id, gallery_id, name, height, width, url)
VALUES (20001, 10001, 'picture 1', 100, 100, 'url');
