
CREATE TABLE developers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL UNIQUE,
    country VARCHAR(100),
    founded_year INT CHECK (founded_year BETWEEN 1950 AND 2100)
);

CREATE TABLE genres (
    id SERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE games (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    price NUMERIC(10,2) NOT NULL CHECK (price >= 0),
    release_year INT CHECK (release_year BETWEEN 1970 AND 2100),

    developer_id INT NOT NULL,
    genre_id INT NOT NULL,

    type VARCHAR(20) NOT NULL CHECK (type IN ('DIGITAL','PHYSICAL')),

    platform VARCHAR(100),
    download_size_gb NUMERIC(6,2),

    media_type VARCHAR(100),
    stock INT CHECK (stock >= 0),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_developer
        FOREIGN KEY (developer_id)
        REFERENCES developers(id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_genre
        FOREIGN KEY (genre_id)
        REFERENCES genres(id)
        ON DELETE RESTRICT,

    CONSTRAINT digital_fields_check CHECK (
        (type = 'DIGITAL' AND platform IS NOT NULL)
        OR (type = 'PHYSICAL')
    ),

    CONSTRAINT physical_fields_check CHECK (
        (type = 'PHYSICAL' AND stock IS NOT NULL)
        OR (type = 'DIGITAL')
    )
);

CREATE INDEX idx_games_developer ON games(developer_id);
CREATE INDEX idx_games_genre ON games(genre_id);
CREATE INDEX idx_games_type ON games(type);
CREATE INDEX idx_games_title ON games(title);
