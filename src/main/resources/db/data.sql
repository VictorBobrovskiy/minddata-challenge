INSERT INTO Engine (model, isHyper, power) VALUES
  ('Ion Drive', true,  10000),
  ('Hyperdrive', true, 20000),
  ('Sublight Drive', false, 5000),
  ('Light Freighter Engine', false, 3000),
  ('Escort Cruiser Drive', true, 15000);
  ('DeathStar Engine', true, 500000);


INSERT INTO StarShip (name, maxSpeed, weight, size, shieldStrength, engine_id, dtype) VALUES

  ('Millennium Falcon', 1200, 30000, 35, 300, 1, 'ArtifactShip'),  -- Light Freighter
  ('X-wing', 1300, 9740, 12, 250, 3, 'WarShip'),  -- Starfighter
  ('Y-wing', 1100, 24000, 21, 200, 2, 'WarShip'),  -- Bomber
  ('A-wing', 1600, 8500, 9, 150, 3, 'WarShip'),  -- Interceptor
  ('B-wing', 850, 34500, 32, 300, 2, 'WarShip'),  -- Assault Gunboat
  ('Mon Calamari Cruiser', 900, 1000000, 1200, 500, 4, 'WarShip'),  -- Star Cruiser
  ('Corellian Corvette', 1100, 300000, 400, 400, 4, 'WarShip'),  -- Multi-role Corvette

  ('Death Star', 1000, 10000000, 10000000, Integer.MAX_VALUE, 6, 'ArtifactShip'),  -- Battle Station
  ('TIE Fighter', 1100, 1250, 6, 100, 3, 'WarShip'),  -- Starfighter
  ('TIE Interceptor', 1300, 2100, 9, 150, 3, 'WarShip'),  -- Interceptor
  ('Star Destroyer', 1000, 15000000, 1600, 600, 5, 'WarShip'),  -- Destroyer
  ('Victory-class Star Destroyer', 950, 6000000, 1200, 500, 5, 'WarShip'),  -- Destroyer
  ('Gozanti Cruiser', 600, 850000, 400, 300, 4, 'CargoVessel'),  -- Cruiser (Cargo)
  ('Lambda-class Shuttle', 800, 20000, 20, 150, 2, 'CargoVessel'),  -- Shuttle (Cargo)
  ('Corellian Freighter', 1000, 75000, 400, 250, 1, 'CargoVessel'),
  ('YT-1300 Light Freighter', 900, 30000, 35, 200, 1, 'CargoVessel'),
  ('Nebulon-B Frigate', 850, 700000, 300, 350, 4, 'CargoVessel'),  -- Frigate (Cargo variant)
  ('Acclamator-class Assault Ship', 800, 800000, 800, 400, 4, 'WarShip'),
  ('Venator-class Star Destroyer', 900, 10000000, 1200, 500, 5, 'WarShip'),
  ('LAAT/i Gunship', 600, 15000, 15, 200, 3, 'WarShip'),  -- Gunship
  ('Corellian Scout Ship', 1200, 20000, 25, 180, 3, 'CargoVessel'),
  ('Gauntlet Fighter', 1000, 8000, 10, 120, 3, 'WarShip'),  -- Mandalorian Fighter
  ('Naboo Royal Starship', 1100, 80000, 50, 350, 4, 'CargoVessel'),
  ('H-type Hutt freighter', 800, 150000, 400, 200, 1, 'CargoVessel'),
  ('Providence-class Dreadnought', 600, 6000000, 1000, 550, 5, 'WarShip'),
  ('Munificent-class Star Frigate', 850, 500000, 800, 400, 4, 'WarShip'),
  ('Droid Gunship', 500, 20000, 20, 150, 2, 'WarShip'),
  ('Xi'an  Nightcrawler', 950, 40000, 30, 220, 1, 'CargoVessel'),
  ('Virago', 800, 25000, 28, 180, 1, 'CargoVessel'),
  ('YT-2400 Freighter', 1050, 70000, 45, 280, 1, 'CargoVessel');

