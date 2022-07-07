-- category data
insert into category (name, logo) values ('Foods','foods.png');
insert into category (name, logo) values ('Drinks','drinks.png');
insert into category (name, logo) values ('Fashions','fashions.png');
insert into category (name, logo) values ('Beauty','beauty.png');
insert into category (name, logo) values ('Electronic','electronic.png');
insert into category (name, logo) values ('Mobiles','mobiles.png');
insert into category (name, logo) values ('Computers','computers.png');

-- product data
insert into product(name, category_id, image, price, available) values ('Potato Chips', 1, 'potato-chips.png', 500, true);
insert into product(name, category_id, image, price, available) values ('Pucci Cake', 1, 'pucci-cake.png', 500, true);
insert into product(name, category_id, image, price, available) values ('Fish Chips', 1, 'fish-chips.png', 1000, true);
insert into product(name, category_id, image, price, available) values ('Pepsi', 2, 'pepsi.png', 700, true);
insert into product(name, category_id, image, price, available) values ('Coke', 2, 'coke.png', 1000, true);
insert into product(name, category_id, image, price, available) values ('7 Up', 2, '7-up.png', 700, true);
insert into product(name, category_id, image, price, available) values ('Mac Book Pro 13 Inches', 7, 'mac-book-pro-13-inches.png', 2500000, true);
insert into product(name, category_id, image, price, available) values ('Mac Book Air Pro', 7, 'mac-book-air-pro.png', 1800000, true);

-- customer data