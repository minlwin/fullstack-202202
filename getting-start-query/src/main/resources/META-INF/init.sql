-- Category
insert into category (name, logo) values ('Foods','foods.png');
insert into category (name, logo) values ('Drinks','drinks.png');
insert into category (name, logo) values ('Fashions','fashions.png');
insert into category (name, logo) values ('Beauty','beauty.png');
insert into category (name, logo) values ('Electronic','electronic.png');
insert into category (name, logo) values ('Mobiles','mobiles.png');
insert into category (name, logo) values ('Computers','computers.png');

-- Product
insert into product(name, category_id, image, price, available) values ('Potato Chips', 1, 'potato-chips.png', 500, true);
insert into product(name, category_id, image, price, available) values ('Pucci Cake', 1, 'pucci-cake.png', 500, true);
insert into product(name, category_id, image, price, available) values ('Fish Chips', 1, 'fish-chips.png', 1000, true);
insert into product(name, category_id, image, price, available) values ('Pepsi', 2, 'pepsi.png', 700, true);
insert into product(name, category_id, image, price, available) values ('Coke', 2, 'coke.png', 1000, true);
insert into product(name, category_id, image, price, available) values ('7 Up', 2, '7-up.png', 700, true);
insert into product(name, category_id, image, price, available) values ('Mac Book Pro 13 Inches', 7, 'mac-book-pro-13-inches.png', 2500000, true);
insert into product(name, category_id, image, price, available) values ('Mac Book Air Pro', 7, 'mac-book-air-pro.png', 1800000, true);

-- Product Color
insert into product_colors (product, color) values (1, 'Red');
insert into product_colors (product, color) values (1, 'Green');
insert into product_colors (product, color) values (1, 'Blue');

insert into product_colors (product, color) values (2, 'Red');
insert into product_colors (product, color) values (3, 'Red');


-- Customer
insert into customer(name, email, phone) values ('Aung Aung', 'aung-aung@gmail.com', '094-3309-1234');
insert into customer(name, email, phone) values ('Maung Maung', 'maung-maung@gmail.com', '095-1209-1009');
insert into customer(name, email, phone) values ('Thidar', 'thidar@gmail.com', '097-3456-7899');
insert into customer(name, email, phone) values ('Nilar', 'nilar@gmail.com', '098-2234-0098');
insert into customer(name, email, phone) values ('Mya Mya', 'mya-mya@gmail.com', '099-8907-1989');
insert into customer(name, email, phone) values ('Ko Ko Oo', 'ko-ko-oo@gmail.com', '091-3675-9665');

-- Township
insert into township (name, division) values ('Kamayut', 'Yangon');
insert into township (name, division) values ('Yankin', 'Yangon');
insert into township (name, division) values ('Tarmwae', 'Yangon');
insert into township (name, division) values ('Insein', 'Yangon');
insert into township (name, division) values ('Lathar', 'Yangon');
insert into township (name, division) values ('Bahan', 'Yangon');

-- Address
insert into address(id, township_id, building, street) values (1, 1, 'No 111 / 3F', 'Yagon Insein Street');
insert into address(id, township_id, building, street) values (2, 2, 'No 92', 'Yanshin Street');
insert into address(id, township_id, building, street) values (3, 3, 'No 64/ 1F', 'Min Yea Kyaw Swat Street');
insert into address(id, township_id, building, street) values (4, 1, 'No 101 A', 'Yadanarmaying Street');
insert into address(id, township_id, building, street) values (5, 1, 'No 221 B', 'Sanyeiknyein 6th Street');
insert into address(id, township_id, building, street) values (6, 2, 'No 140 A  / 5F', 'Thisar Street');

-- Sale
insert into sale (sale_date, customer_id) values ('2022-05-10', 1);
insert into sale (sale_date, customer_id) values ('2022-05-10', 2);
insert into sale (sale_date, customer_id) values ('2022-05-10', 3);
insert into sale (sale_date, customer_id) values ('2022-05-15', 1);
insert into sale (sale_date, customer_id) values ('2022-05-15', 3);
insert into sale (sale_date, customer_id) values ('2022-05-30', 2);
insert into sale (sale_date, customer_id) values ('2022-05-30', 3);
insert into sale (sale_date, customer_id) values ('2022-05-30', 4);
insert into sale (sale_date, customer_id) values ('2022-06-05', 6);
insert into sale (sale_date, customer_id) values ('2022-06-05', 5);
insert into sale (sale_date, customer_id) values ('2022-06-05', 4);
insert into sale (sale_date, customer_id) values ('2022-06-05', 3);
insert into sale (sale_date, customer_id) values ('2022-07-08', 1);
insert into sale (sale_date, customer_id) values ('2022-07-08', 3);
insert into sale (sale_date, customer_id) values ('2022-07-08', 4);
insert into sale (sale_date, customer_id) values ('2022-07-08', 6);

-- ProductSale
insert into product_sale (sale_id, product_id, quantity) values (1, 1, 2);
insert into product_sale (sale_id, product_id, quantity) values (1, 2, 1);
insert into product_sale (sale_id, product_id, quantity) values (1, 3, 3);
insert into product_sale (sale_id, product_id, quantity) values (1, 4, 3);
insert into product_sale (sale_id, product_id, quantity) values (2, 4, 5);
insert into product_sale (sale_id, product_id, quantity) values (2, 5, 5);
insert into product_sale (sale_id, product_id, quantity) values (2, 2, 3);
insert into product_sale (sale_id, product_id, quantity) values (3, 8, 1);
insert into product_sale (sale_id, product_id, quantity) values (4, 1, 5);
insert into product_sale (sale_id, product_id, quantity) values (4, 2, 2);
insert into product_sale (sale_id, product_id, quantity) values (5, 3, 2);
insert into product_sale (sale_id, product_id, quantity) values (5, 4, 2);
insert into product_sale (sale_id, product_id, quantity) values (6, 8, 1);
insert into product_sale (sale_id, product_id, quantity) values (7, 7, 1);
insert into product_sale (sale_id, product_id, quantity) values (8, 7, 1);
insert into product_sale (sale_id, product_id, quantity) values (9, 8, 1);
insert into product_sale (sale_id, product_id, quantity) values (10, 1, 1);
insert into product_sale (sale_id, product_id, quantity) values (10, 2, 1);
insert into product_sale (sale_id, product_id, quantity) values (11, 2, 1);
insert into product_sale (sale_id, product_id, quantity) values (11, 3, 1);
insert into product_sale (sale_id, product_id, quantity) values (12, 1, 2);
insert into product_sale (sale_id, product_id, quantity) values (12, 3, 2);
insert into product_sale (sale_id, product_id, quantity) values (13, 4, 3);
insert into product_sale (sale_id, product_id, quantity) values (13, 1, 4);
insert into product_sale (sale_id, product_id, quantity) values (13, 3, 4);
insert into product_sale (sale_id, product_id, quantity) values (14, 2, 2);
insert into product_sale (sale_id, product_id, quantity) values (14, 4, 2);
insert into product_sale (sale_id, product_id, quantity) values (15, 6, 1);
insert into product_sale (sale_id, product_id, quantity) values (16, 3, 1);
insert into product_sale (sale_id, product_id, quantity) values (16, 4, 5);