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
		city_id INT,

    PRIMARY KEY (id),
		FOREIGN KEY(city_id) REFERENCES city(id) ON DELETE RESTRICT,
    UNIQUE (id)
);