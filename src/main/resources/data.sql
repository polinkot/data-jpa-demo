INSERT INTO country (id, name, short_name) VALUES ('19b9fb61-114b-42c6-9000-080f2dfda1f7', 'India', 'IN');
INSERT INTO country (id, name, short_name) VALUES ('ac812959-5c02-4228-926f-d03faf4604dc', 'Brazil', 'BR');
INSERT INTO country (id, name, short_name) VALUES ('570f7920-653a-4884-8de5-338884d26fdc', 'USA', 'USA');
INSERT INTO country (id, name, short_name) VALUES ('223b0d1e-9997-41a2-be0a-dd025faddb6e', 'Italy', 'IT');

INSERT INTO city (name, country_id) VALUES ('Rome', '223b0d1e-9997-41a2-be0a-dd025faddb6e');
INSERT INTO city (name, country_id) VALUES ('Milan', '223b0d1e-9997-41a2-be0a-dd025faddb6e');
INSERT INTO city (name, country_id) VALUES ('Bari', '223b0d1e-9997-41a2-be0a-dd025faddb6e');

INSERT INTO category (id, name) VALUES ('19b9fb61-114b-42c6-9000-080f2dfda1f8', 'category1');
INSERT INTO category (id, name) VALUES ('ac812959-5c02-4228-926f-d03faf4604d8', 'category2');

INSERT INTO product (name, category_id) VALUES ('product1', '19b9fb61-114b-42c6-9000-080f2dfda1f8');
INSERT INTO product (name, category_id) VALUES ('product2', '19b9fb61-114b-42c6-9000-080f2dfda1f8');
INSERT INTO product (name, category_id) VALUES ('product3', 'ac812959-5c02-4228-926f-d03faf4604d8');