CREATE TABLE IF NOT EXISTS city (
    id bigserial NOT NULL,
		name VARCHAR(20) NOT NULL,
		citizens INT NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS students (
    id bigserial NOT NULL,
    name VARCHAR(20) NOT NULL,
    family_name VARCHAR(20) NOT NULL,
		age INT NOT NULL,
		contact VARCHAR(50) NULL,
		city_id INT REFERENCES city (id),

    PRIMARY KEY (id),
    UNIQUE (id)
);