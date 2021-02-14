DELETE
FROM users;

DELETE
FROM roles;

DELETE
FROM properties;

DELETE
FROM cards;

INSERT INTO users(userid, firstName, lastName, username, primaryemail, password, created_by, created_date, last_modified_by, last_modified_date)
    VALUES (1, 'TestUser', 'Utest', 'testuser', 'testuser@email.com', 'testpassword', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'TestRenter', 'Rtest', 'testrenter', 'testrenter@email.com', 'testpassword', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 'TestOwner', 'Otest', 'testowner', 'testowner@email.com', 'testpassword', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO roles(roleid, name, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 'ADMIN', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (1, 'USER', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (1, 'RENTER', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (1, 'OWNER', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO properties(propertyid, title, description, street, city, state, zipcode, price, pictures, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 'Cabin Getaway', 'Cozy Cottage in the high mountains.', '100 East Maughan Road', 'Lava Hot Springs', 'Idaho', 83246, 69.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 'Couples Beach Resort', 'Romantic getaway on white sand beaches.', '300 S HoneySuckle Way', 'Liberty', 'Texas', 92475, 180.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 'House Boat Family Spot', 'Fun for the whole family, includes jet skies.', '430 BullFrog Way', 'Paige', 'Arizona', 72341, 75.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (4, 'Desert Camper Getaway', 'Rustic camper in desert area.', '50 South Hobo Camp', 'IguanaWay', 'Nevada', 32467, 99.00, 'pictures', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO cards(cardid, name, number, type, expDate, securityCode, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 'Utest TestUser', 1234567890, 'VISA', 0221, 123, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (2, 'Rtest TestRenter', 4567890123, 'DISCOVER', 0321, 321, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
          (3, 'Otest TestOwner', 3456789012, 'MASTERCARD', 4321, 432, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO propertyowners(owner, property, subStDate, subExpDate, created_by, created_date, last_modified_by, last_modified_date)
    VALUES(1, 1, CURRENT_DATE, CURRENT_DATE, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP)