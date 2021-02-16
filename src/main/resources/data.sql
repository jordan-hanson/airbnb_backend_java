DELETE
FROM users;

DELETE
FROM roles;

DELETE
FROM properties;

DELETE
FROM cards;

INSERT INTO users(userid, firstname, lastname, username, primaryemail, password, created_by, created_date, last_modified_by, last_modified_date)
    VALUES (1, 'TestUser', 'Utest', 'testuser', 'testuser@email.com', 'testpassword', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'TestRenter', 'Rtest', 'testrenter', 'testrenter@email.com', 'testpassword', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 'TestOwner', 'Otest', 'testowner', 'testowner@email.com', 'testpassword', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO roles(roleid, name, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 'ADMIN', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 'USER', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 'RENTER', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (4, 'OWNER', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO properties(propertyid, title, description, street, city, state, zipcode, price, pictures, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 'Cabin Getaway', 'Cozy Cottage in the high mountains.', '100 East Maughan Road', 'Lava Hot Springs', 'Idaho', 83246, 69.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 'Couples Beach Resort', 'Romantic getaway on white sand beaches.', '300 S HoneySuckle Way', 'Liberty', 'Texas', 92475, 180.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 'House Boat Family Spot', 'Fun for the whole family, includes jet skies.', '430 BullFrog Way', 'Paige', 'Arizona', 72341, 75.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (4, 'Desert Camper Getaway', 'Rustic camper in desert area.', '50 South Hobo Camp', 'IguanaWay', 'Nevada', 32467, 99.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO cards(cardid, name, number, type, expdate, securitycode, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 'Utest TestUser', '3234567890', 'VISA', 0221, 123, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 'Rtest TestRenter', '456789012345', 'DISCOVER', 0321, 321, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 'Otest TestOwner', '345678901234', 'MASTERCARD', 4321, 432, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO propertyowners(userid, propertyid, substdate, subexpdate, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 1, CURRENT_DATE, CURRENT_DATE, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 2, CURRENT_DATE, CURRENT_DATE, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO propertyrenters(userid, propertyid, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(2, 4, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO userroles(userid, roleid, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 4, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 3, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (1, 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);