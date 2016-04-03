Does :

Checkin date and checkout date should be in format (yyyy-MM-dd) 

Table Entries :

CUSTOMER TABLE :
================
CREATE TABLE `world`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_no` INT NOT NULL,
  `billing_addr` VARCHAR(45) NOT NULL,
  `billing_city` VARCHAR(45) NOT NULL,
  `billing_state` VARCHAR(45) NOT NULL,
  `billing_zip` VARCHAR(45) NOT NULL,
  `checkin_date` VARCHAR(45) NOT NULL,
  `checkout_date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customer_id`));
  
  
ROOM TABLE:
===========
CREATE TABLE `world`.`room` (
  `room_number` INT NOT NULL,
  `room_type` VARCHAR(45) NOT NULL,
  `room_price` VARCHAR(45) NOT NULL,
  `current_occupant` VARCHAR(45) NOT NULL);
  
insert into room (room_number, room_type, room_price, current_occupant) values (101, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (102, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (103, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (104, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (105, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (106, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (107, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (108, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (109, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (110, "Single", "100$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (111, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (112, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (113, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (114, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (115, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (116, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (117, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (118, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (119, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (120, "Single", "100$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (121, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (122, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (123, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (124, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (125, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (126, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (127, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (128, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (129, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (130, "Single", "100$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (131, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (132, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (133, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (134, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (135, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (136, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (137, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (138, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (139, "Single", "100$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (140, "Single", "100$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (201, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (202, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (203, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (204, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (205, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (206, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (207, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (208, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (209, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (210, "Double", "150$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (211, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (212, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (213, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (214, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (215, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (216, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (217, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (218, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (219, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (220, "Double", "150$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (221, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (222, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (223, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (224, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (225, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (226, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (227, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (228, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (229, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (230, "Double", "150$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (231, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (232, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (233, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (234, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (235, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (236, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (237, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (238, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (239, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (240, "Double", "150$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (241, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (242, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (243, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (244, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (245, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (246, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (247, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (248, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (249, "Double", "150$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (250, "Double", "150$", "Empty");

insert into room (room_number, room_type, room_price, current_occupant) values (301, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (302, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (303, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (304, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (305, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (306, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (307, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (308, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (309, "Presidential", "300$", "Empty");
insert into room (room_number, room_type, room_price, current_occupant) values (310, "Presidential", "300$", "Empty");

  
Transactions Table:
===================
CREATE TABLE `world`.`transactions` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `amount` VARCHAR(45) NOT NULL,
  `credit_card` VARCHAR(45) NOT NULL,
  `exp_date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`transaction_id`));