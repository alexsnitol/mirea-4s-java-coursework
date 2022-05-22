-- Author: Kozlov A.V.
CREATE TABLE roles (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(31)
);

-- Author: Slotin A.S.
CREATE TABLE house_types (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(31)
);

-- Author: Slotin A.S.
CREATE TABLE property_types (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(31)
);

-- Author: Slotin A.S.
CREATE TABLE renovation_types (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(31)
);

-- Author: Slotin A.S.
CREATE TABLE estate_object_types (
     id   SERIAL PRIMARY KEY,
     name VARCHAR(31)
);

-- Author: Slotin A.S.
CREATE TABLE cities (
     id   SERIAL PRIMARY KEY,
     name VARCHAR(31)
);

-- Author: Slotin A.S.
CREATE TABLE advertised_statuses (
     id   SERIAL PRIMARY KEY,
     name VARCHAR(31)
);

-- Author: Slotin A.S.
CREATE TABLE addresses (
     id           SERIAL PRIMARY KEY,
     city_id      BIGINT UNSIGNED NOT NULL,
     full_address VARCHAR(127),
     FOREIGN KEY (city_id) REFERENCES cities (id)
);

-- Author: Kozlov A.V.
CREATE TABLE users (
     id               SERIAL PRIMARY KEY,
     login            VARCHAR(31) NOT NULL,
     password         VARCHAR(31) NOT NULL,
     email            VARCHAR(31) NOT NULL,
     phone_number     CHAR(13) NOT NULL,

     surname          VARCHAR(31) NOT NULL,
     name             VARCHAR(31) NOT NULL,
     patronymic       VARCHAR(31),

     photo_path       VARCHAR(255),
     datetime_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     role_id          BIGINT UNSIGNED NOT NULL,
     FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Author: Slotin A.S.
CREATE TABLE estate_objects (
      id                    SERIAL PRIMARY KEY,
      area                  FLOAT(2) UNSIGNED NOT NULL,
      floor                 INT(3) UNSIGNED NOT NULL,
      max_floor             INT(3) UNSIGNED NOT NULL,
      room_size             INT(2) UNSIGNED NOT NULL,
      datetime_created      timestamp NULL,

      house_type_id         BIGINT(10) UNSIGNED NOT NULL,
      property_type_id      BIGINT(10) UNSIGNED NOT NULL,
      renovation_type_id    BIGINT(10) UNSIGNED NOT NULL,
      estate_object_type_id BIGINT(10) UNSIGNED NOT NULL,
      address_id            BIGINT(10) UNSIGNED NOT NULL,

      FOREIGN KEY (house_type_id) REFERENCES house_types (id),
      FOREIGN KEY (property_type_id) REFERENCES property_types (id),
      FOREIGN KEY (renovation_type_id) REFERENCES renovation_types (id),
      FOREIGN KEY (estate_object_type_id) REFERENCES estate_object_types (id),
      FOREIGN KEY (address_id) REFERENCES addresses (id)
);

-- Author: Slotin A.S.
CREATE TABLE vacancies (
      id                   SERIAL PRIMARY KEY,
      name                 VARCHAR(31) NOT NULL,
      keywords             VARCHAR(255) NOT NULL,
      summary              FLOAT(2) UNSIGNED NOT NULL,
      is_active            BOOLEAN NOT NULL,

      text_description     VARCHAR(511) NOT NULL,
      text_study           VARCHAR(511) NOT NULL,
      text_responsibilities     VARCHAR(511) NOT NULL,
      text_requirements    VARCHAR(511) NOT NULL,
      text_features        VARCHAR(511) NOT NULL,
      text_summary         VARCHAR(511) NOT NULL,

      admin_id             BIGINT UNSIGNED NOT NULL,
      city_id              BIGINT UNSIGNED NOT NULL,

      FOREIGN KEY (admin_id) REFERENCES users (id),
      FOREIGN KEY (city_id) REFERENCES cities (id)
);

-- Author: Slotin A.S.
CREATE TABLE advertiseds (
      id                   SERIAL PRIMARY KEY,
      description          VARCHAR(511),
      price                FLOAT(2) UNSIGNED NOT NULL,
      datetime_created     TIMESTAMP NULL,
       
      advertised_status_id BIGINT UNSIGNED NOT NULL,
      estate_objects_id    BIGINT UNSIGNED NOT NULL,
      admin_id             BIGINT UNSIGNED NOT NULL,
      user_id              BIGINT UNSIGNED NOT NULL,

      FOREIGN KEY (advertised_status_id) REFERENCES advertised_statuses (id),
      FOREIGN KEY (estate_objects_id) REFERENCES estate_objects (id),
      FOREIGN KEY (admin_id) REFERENCES users (id),
      FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Author: Kozlov A.V.
CREATE TABLE favorites (
       user_id       BIGINT(10) UNSIGNED NOT NULL,
       advertised_id BIGINT(10) UNSIGNED NOT NULL,
       FOREIGN KEY (user_id) REFERENCES users (id),
       FOREIGN KEY (advertised_id) REFERENCES advertiseds (id)
);

-- Author: Kozlov A.V.
CREATE TABLE forms (
       id                   SERIAL PRIMARY KEY,
       state_enum           VARCHAR(10) NOT NULL,
       admin_comment        VARCHAR(127),
       area                 INT(10) UNSIGNED NOT NULL,
       floor                INT(10) UNSIGNED NOT NULL,
       max_floor            INT(10) UNSIGNED NOT NULL,
       room_size            INT(10) UNSIGNED NOT NULL,
       description          VARCHAR(511),
       price                INT(13) UNSIGNED NOT NULL,
       datetime_created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

       user_id              BIGINT(10) UNSIGNED NOT NULL,
       admin_id             BIGINT(10) UNSIGNED NOT NULL,
       house_type_id        BIGINT(10) UNSIGNED NOT NULL,
       property_type_id     BIGINT(10) UNSIGNED NOT NULL,
       renovation_type_id   BIGINT(10) UNSIGNED NOT NULL,
       estate_object_type_id BIGINT(10) UNSIGNED NOT NULL,
       address_id           BIGINT(10) UNSIGNED NOT NULL,

       FOREIGN KEY (user_id) REFERENCES users (id),
       FOREIGN KEY (admin_id) REFERENCES users (id),
       FOREIGN KEY (house_type_id) REFERENCES house_types (id),
       FOREIGN KEY (property_type_id) REFERENCES property_types (id),
       FOREIGN KEY (renovation_type_id) REFERENCES renovation_types (id),
       FOREIGN KEY (estate_object_type_id) REFERENCES estate_object_types (id),
       FOREIGN KEY (address_id) REFERENCES addresses (id)
);

-- Author: Slotin A.S.
CREATE TABLE advertised_photos (
        path          VARCHAR(2047),
        advertised_id BIGINT UNSIGNED NOT NULL,
        form_id       BIGINT UNSIGNED NOT NULL,
        FOREIGN KEY (form_id) REFERENCES forms (id),
        FOREIGN KEY (advertised_id) REFERENCES advertiseds (id)
);