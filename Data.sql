INSERT INTO developers(name, country, founded_year)
VALUES 
('Valve', 'USA', 1996),
('Rockstar Games', 'USA', 1998),
('CD Projekt Red', 'Poland', 2002);

INSERT INTO genres(name, description)
VALUES
('Action', 'Fast-paced action games'),
('RPG', 'Role-playing games'),
('Adventure', 'Story-driven exploration games');

INSERT INTO games(
    title, price, release_year,
    developer_id, genre_id,
    type, platform, download_size_gb
)
VALUES (
    'Half-Life',
    9.99,
    1998,
    1,
    1,
    'DIGITAL',
    'PC',
    2.5
);

INSERT INTO games(
    title, price, release_year,
    developer_id, genre_id,
    type, media_type, stock
)
VALUES (
    'GTA V',
    19.99,
    2013,
    2,
    1,
    'PHYSICAL',
    'Blu-ray Disc',
    15
);


ALTER TABLE games
  ADD CONSTRAINT digital_required CHECK (
    type <> 'DIGITAL' OR (platform IS NOT NULL AND download_size_gb IS NOT NULL)
  );

ALTER TABLE games
  ADD CONSTRAINT physical_required CHECK (
    type <> 'PHYSICAL' OR (media_type IS NOT NULL AND stock IS NOT NULL)
  );

  
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = CURRENT_TIMESTAMP;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_games_updated_at
BEFORE UPDATE ON games
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
