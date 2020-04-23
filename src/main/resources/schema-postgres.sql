ALTER TABLE category ALTER COLUMN id SET DEFAULT uuid_generate_v4();
ALTER TABLE product ALTER COLUMN id SET DEFAULT uuid_generate_v4();