ALTER TABLE category ALTER COLUMN id SET DEFAULT uuid_generate_v4();
ALTER TABLE product ALTER COLUMN id SET DEFAULT uuid_generate_v4();

INSERT INTO category (name) VALUES ('India');
INSERT INTO category (name) VALUES ('Brazil');
INSERT INTO category (name) VALUES ('USA');
INSERT INTO category (name) VALUES ('Italy');