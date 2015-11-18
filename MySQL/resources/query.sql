DROP DATABASE IF EXISTS ONLINE_AUCTIONS;

DROP SCHEMA IF EXISTS ONLINE_AUCTIONS;

CREATE SCHEMA IF NOT EXISTS ONLINE_AUCTIONS DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE ONLINE_AUCTIONS;

DROP DATABASE IF EXISTS ONLINE_AUCTIONS;

CREATE DATABASE ONLINE_AUCTIONS;

USE ONLINE_AUCTIONS;

CREATE TABLE USERS(
User_ID int NOT NULL AUTO_INCREMENT,
Name varchar(255),
Surname varchar(255),
Email varchar(255) NOT NULL UNIQUE,
Phone int,
Login varchar(255) NOT NULL UNIQUE,
Pass varchar(255) NOT NULL,
Account int,
Address varchar(255),
Town varchar(255),
ZipCode varchar(255),
PRIMARY KEY (User_ID)
);

CREATE TABLE AUCTIONS(
Auciton_ID int NOT NULL AUTO_INCREMENT,
User_ID int NOT NULL,
Description varchar(255) NOT NULL,
Start_Date datetime DEFAULT NOW(),
End_Date datetime,
Price float NOT NULL,
PRIMARY KEY (Auciton_ID),
FOREIGN KEY (User_ID) REFERENCES USERS(User_ID)
);

CREATE TABLE IMAGES(
ID int PRIMARY KEY AUTO_INCREMENT,
Image blob NOT NULL,
Name varchar(255) NOT NULL
);

CREATE TABLE TYPE_PAYMENT(
Type_payment varchar(255)
);

CREATE TABLE PRODUCT_CONDITION(
Product_Condition varchar(255)
);

CREATE TABLE TYPE_SHIPMENT(
Type_Shipment varchar(255)
);

CREATE TABLE PAYMENT(
Payment_ID int NOT NULL AUTO_INCREMENT,
Auction_ID int,
Type_Payment varchar(255),
PRIMARY KEY(Payment_ID),
FOREIGN KEY (Auction_ID) REFERENCES AUCTIONS(Auciton_ID)
);

CREATE TABLE SHIPMENT(
Shipment_ID int NOT NULL AUTO_INCREMENT,
Auction_ID int,
Type_Shipment varchar(255),
PRIMARY KEY(Shipment_ID),
FOREIGN KEY (Auction_ID) REFERENCES AUCTIONS(Auciton_ID)
);

CREATE TABLE BIDDING(
Bidding_ID int NOT NULL AUTO_INCREMENT,
Shipping_Adress varchar(255),
Type_Payment varchar(255),
Auction_ID int,
User_ID int,
Price int,
Date_Bidding date,
PRIMARY KEY (Bidding_ID),
FOREIGN KEY (Auction_ID) REFERENCES AUCTIONS(Auciton_ID),
FOREIGN KEY (User_ID) REFERENCES USERS(User_ID)
);

CREATE TABLE COMMENT_a(
Comment_ID int NOT NULL AUTO_INCREMENT,
Sender_ID int,
Rating int,
Add_Date datetime NOT NULL DEFAULT NOW(),
Auction_ID int,
Addressee_ID int,
Comment_a LONGTEXT,
PRIMARY KEY (Comment_ID),
FOREIGN KEY (Auction_ID) REFERENCES AUCTIONS(Auciton_ID),
FOREIGN KEY (Sender_ID) REFERENCES USERS(User_ID),
FOREIGN KEY (Addressee_ID) REFERENCES USERS(User_ID)
);

CREATE TABLE MESSAGE(
Message_ID int NOT NULL AUTO_INCREMENT,
Sender_ID int,
Addressee_ID int,
Message LONGTEXT,
Auction_ID int,
Add_Date datetime NOT NULL DEFAULT NOW(),
PRIMARY KEY (Message_ID),
FOREIGN KEY (Auction_ID) REFERENCES AUCTIONS(Auciton_ID),
FOREIGN KEY (Sender_ID) REFERENCES USERS(User_ID),
FOREIGN KEY (Addressee_ID) REFERENCES USERS(User_ID)
);

CREATE TABLE ACCOUNT_NUMBER(
Acc_num int NOT NULL AUTO_INCREMENT,
User_ID int,
PRIMARY KEY (Acc_num),
FOREIGN KEY (User_ID) REFERENCES AUCTIONS(Auciton_ID)
);
