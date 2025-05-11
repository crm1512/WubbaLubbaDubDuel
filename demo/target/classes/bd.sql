-- Cartas base (tabla `card`)
-- columnas: id, name, description, image, rarity
-- ---------------------------------
INSERT INTO card (id, name, description, image, rarity) VALUES
  (1, 'Rick Sanchez',        'Científico genial, abuelo de Morty',  'https://rickandmortyapi.com/api/character/avatar/1.jpeg', 'LEGENDARY'),
  (2, 'Morty Smith',         'Nieto de Rick, valiente pero despistado', 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', 'RARE'),
  (3, 'Summer Smith',        'Hermana de Morty, lista para la aventura', 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', 'EPIC'),
  (4, 'Portal Gun',          'Dispositivo que abre portales entre dimensiones', NULL, 'RARE'),
  (5, 'Plumbus',             'Objeto de uso cotidiano en todos los hogares', NULL, 'NORMAL'),
  (6, 'Planeta Tierra C‑137', 'Tablero: el hogar original de Rick',        NULL, 'EPIC'),
  (7, 'Nave de Rick',        'Tablero: la nave espacial personal de Rick',  NULL, 'RARE');

-- ---------------------------------
-- Personajes (tabla `character_card`)
-- columnas: id, hp, attack, cost
-- ---------------------------------
INSERT INTO character_card (id, hp, attack, cost) VALUES
  (1, 120, 75, 5),  -- Rick
  (2,  90, 40, 3),  -- Morty
  (3, 100, 50, 4);  -- Summer

-- ---------------------------------
-- Items (tabla `item_card`)
-- columnas: id, effect
-- ---------------------------------
INSERT INTO item_card (id, effect) VALUES
  (4, 'Abre un portal extra para jugar una carta adicional'),
  (5, 'Añade +10 a cualquier tirada de ataque esta ronda');

-- ---------------------------------
-- Tableros (tabla `board_card`)
-- columnas: id, effect, card_condition
-- ---------------------------------
INSERT INTO board_card (id, effect, card_condition) VALUES
  (6, 'Todos los personajes recuperan 20 HP al inicio de tu turno',    'Al inicio de tu turno'),
  (7, 'Las cartas de tipo Item cuestan -1 de coste mientras este tablero esté activo', 'Mientras tengas ≥1 Personaje activo');