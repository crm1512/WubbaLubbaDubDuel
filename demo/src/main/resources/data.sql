-- Cambiar la codificación de la base de datos
ALTER DATABASE defaultdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Cambiar la codificación de las tablas
ALTER TABLE card CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE character_card CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE item_card CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE board_card CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- ---------------------------------
-- Cartas base (tabla `card`)
-- columnas: id, name, description, image, cost, rarity, type
-- ---------------------------------
