/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  luans
 * Created: 11/03/2021
 */

CREATE DATABASE KapivaGeekdb;

USE KapivaGeekdb;

CREATE TABLE products(
	prod_id int AUTO_INCREMENT, 
	name_prod varchar(280) NOT NULL,
	long_name varchar(2000) NOT NULL,
	amount_stars int NOT NULL,
	status_prod varchar(10) NOT NULL,
	stock decimal(7,2) NOT NULL,
	price decimal(7,2) NOT NULL,
	date_register timestamp,
	PRIMARY KEY (prod_id)
);

CREATE TABLE product_img(
	img_id int AUTO_INCREMENT,
    prod_id int,
    path_img varchar(50) NOT NULL,
    status_img varchar(30) NOT null,
    date_register timestamp,
    PRIMARY KEY (img_id),
    FOREIGN KEY (prod_id) REFERENCES products(prod_id)
);

CREATE VIEW products_list as
	select prod_id,name_prod,stock,status_prod from products;
