create database online_shopping;

use online_shopping;

CREATE TABLE admin (
	id int NOT NULL AUTO_INCREMENT,
    email varchar(200),
    password varchar(45),
    PRIMARY KEY(id)
);

CREATE TABLE supplier(
	sid int NOT NULL,
    sname varchar(100),
    semail varchar(100),
    spassword varchar(100),
    sphone varchar(15),
    saddress1 text,
    saddress2 text,
    PRIMARY KEY(sname)
);

CREATE TABLE user(
	uid int NOT NULL AUTO_INCREMENT,
    uname varchar(200),
    uemail varchar(100),
    upassword varchar(100),
    uphone varchar(15),
    usecqus text,
    uans text,
    uaddress1 text,
    uaddress2 text,
    PRIMARY KEY(uid)
);

CREATE TABLE category(
	cid int NOT NULL,
    cname Varchar(200),
    cdesc text,
    PRIMARY KEY(cname),
    UNIQUE KEY cid_UNIQUE (cid)
);

CREATE TABLE purchase(
	id int NOT NULL AUTO_INCREMENT,
    uid int,
    uname varchar(200),
    uphone varchar(15),
    pid int,
    product_name varchar(200),
    qty int,
    price double,
    total double,
    p_date varchar(20),
    uaddress text,
    receive_date varchar(20),
    supplier varchar(20),
    status varchar(45),
    PRIMARY KEY (id),
    KEY fk_user_uid (uid),
    KEY fk_supplier_name (supplier),
    CONSTRAINT fk_supplier_name FOREIGN KEY (supplier) REFERENCES supplier (sname) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_uid FOREIGN KEY (uid) REFERENCES user (uid) ON DELETE CASCADE ON UPDATE CASCADE
);
    
CREATE TABLE product(
	pid int NOT NULL AUTO_INCREMENT,
    pname varchar(200),
    cname varchar(200),
    pqty int,
    pprice double,
    PRIMARY KEY (pid),
    KEY fk_category_name (cname),
    CONSTRAINT fk_category_name FOREIGN KEY (cname) REFERENCES category (cname) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from online_shopping.admin;
    