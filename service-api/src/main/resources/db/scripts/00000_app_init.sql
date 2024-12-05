-- liquibase formatted sql
-- changeset devandre:init_db
CREATE SCHEMA IF NOT EXISTS furtmates_schema;

SET
    SEARCH_PATH TO furtmates_schema;

-- Role Table
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Insert default roles
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_SHELTER'),
       ('ROLE_ADOPTER');

CREATE TYPE GENRE AS ENUM ('MALE', 'FEMALE', 'OTHER');

-- Users Table
CREATE TABLE users
(
    id              SERIAL PRIMARY KEY,
    public_id       UUID                NOT NULL,
    first_name      VARCHAR(255)        NOT NULL,
    last_name       VARCHAR(255)        NOT NULL,
    username        VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    password_hash   TEXT                NOT NULL,
    phone_number    VARCHAR(20),
    address         TEXT,
    document_number VARCHAR(20) UNIQUE  NOT NULL,
    role_id         INT                 NOT NULL,
    avatar_url      VARCHAR(255),
    age             INT,
    genre           GENRE               NOT NULL,
    is_enabled      BOOLEAN             NOT NULL,
    is_adopter      BOOLEAN             NOT NULL,
    bio             TEXT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE tokens
(
    id         SERIAL PRIMARY KEY,
    user_id    INT  NOT NULL,
    token      TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE verification_code
(
    id         SERIAL PRIMARY KEY,
    user_id    INT  NOT NULL,
    code       TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE species
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by INT
);

CREATE TABLE breeds
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    species_id INT         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by INT,
    FOREIGN KEY (species_id) REFERENCES species (id)
);

CREATE TABLE shelters
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255)        NOT NULL,
    phone_number VARCHAR(20),
    address      TEXT,
    email        VARCHAR(255) UNIQUE NOT NULL,
    updated_by   INT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TYPE PET_AGES AS ENUM ('BABY', 'YOUNG', 'ADULT', 'SENIOR');
CREATE TYPE ADOPTION_STATUS AS ENUM ('ADOPTED', 'AVAILABLE', 'PENDING', 'SUSPENDED');

-- Pets Table
CREATE TABLE pets
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255)    NOT NULL,
    species_id      INT             NOT NULL,
    breed_id        INT             NOT NULL,
    age             PET_AGES        NOT NULL,
    genre           GENRE           NOT NULL,
    weight          DECIMAL(5, 2)   NOT NULL,
    description     TEXT,
    size            VARCHAR(50)     NOT NULL,
    color           VARCHAR(50)     NOT NULL,
    health_status   VARCHAR(50)     NOT NULL,
    characteristics TEXT,
    adoption_status ADOPTION_STATUS NOT NULL,
    shelter_id      INT             NOT NULL,
    arrival_date    DATE            NOT NULL,
    good_with       VARCHAR(50),
    is_spayed       BOOLEAN         NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      INT,
    FOREIGN KEY (species_id) REFERENCES species (id),
    FOREIGN KEY (breed_id) REFERENCES breeds (id),
    FOREIGN KEY (shelter_id) REFERENCES shelters (id)
);

CREATE TABLE pet_images
(
    id         SERIAL PRIMARY KEY,
    pet_id     INT          NOT NULL,
    image_url  VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pet_id) REFERENCES pets (id)
);

CREATE TABLE adoptions
(
    id            SERIAL PRIMARY KEY,
    pet_id        INT             NOT NULL,
    adopter_id    INT             NOT NULL,
    adoption_date DATE            NOT NULL,
    status        ADOPTION_STATUS NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pet_id) REFERENCES pets (id),
    FOREIGN KEY (adopter_id) REFERENCES users (id)
);

CREATE TABLE users_favorites
(
    id         SERIAL PRIMARY KEY,
    user_id    INT NOT NULL,
    pet_id     INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (pet_id) REFERENCES pets (id)
);

CREATE TYPE APPOINTMENT_STATUS AS ENUM ('PENDING', 'CONFIRMED', 'CANCELLED');

CREATE TABLE appointments
(
    id               SERIAL PRIMARY KEY,
    user_id          INT                NOT NULL,
    pet_id           INT                NOT NULL,
    appointment_date TIMESTAMP          NOT NULL,
    status           APPOINTMENT_STATUS NOT NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by       INT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (pet_id) REFERENCES pets (id)
);

CREATE TYPE NOTIFICATION_TYPE AS ENUM (
    'APPOINTMENT',
    'ADOPTION_STATUS',
    'ORDER',
    'PET_AVAILABILITY'
    );

CREATE TABLE notifications
(
    notification_id   SERIAL PRIMARY KEY,
    user_id           BIGINT            NOT NULL,
    type              NOTIFICATION_TYPE NOT NULL,
    message           TEXT              NOT NULL,
    is_read           BOOLEAN   DEFAULT FALSE,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    related_entity_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
