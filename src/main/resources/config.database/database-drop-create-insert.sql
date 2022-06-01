DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS advertised_photos;
DROP TABLE IF EXISTS advertiseds;
DROP TABLE IF EXISTS vacancies;
DROP TABLE IF EXISTS estate_objects;
DROP TABLE IF EXISTS forms;
DROP TABLE IF EXISTS property_types;
DROP TABLE IF EXISTS estate_object_types;
DROP TABLE IF EXISTS house_types;
DROP TABLE IF EXISTS renovation_types;
DROP TABLE IF EXISTS advertised_statuses;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;


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
                       username         VARCHAR(31) NOT NULL,
                       password         VARCHAR(255) NOT NULL,
                       email            VARCHAR(31) NOT NULL,
                       phone_number     CHAR(13) NOT NULL,

                       surname          VARCHAR(31) NOT NULL,
                       name             VARCHAR(31) NOT NULL,
                       patronymic       VARCHAR(31),

                       photo_path       VARCHAR(255),
                       datetime_created TIMESTAMP
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
                           name                 VARCHAR(63) NOT NULL,
                           keywords             VARCHAR(255) NOT NULL,
                           summary              VARCHAR(63) NOT NULL,
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
                             estate_object_id    BIGINT UNSIGNED NOT NULL,
                             admin_id             BIGINT UNSIGNED NOT NULL,
                             user_id              BIGINT UNSIGNED NOT NULL,

                             FOREIGN KEY (advertised_status_id) REFERENCES advertised_statuses (id),
                             FOREIGN KEY (estate_object_id) REFERENCES estate_objects (id),
                             FOREIGN KEY (admin_id) REFERENCES users (id),
                             FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Author: Kozlov A.V.
CREATE TABLE favorites (
                           id            SERIAL PRIMARY KEY,
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
                                   id            SERIAL PRIMARY KEY,
                                   path          VARCHAR(2047),
                                   advertised_id BIGINT UNSIGNED,
                                   form_id       BIGINT UNSIGNED,
                                   FOREIGN KEY (form_id) REFERENCES forms (id),
                                   FOREIGN KEY (advertised_id) REFERENCES advertiseds (id)
);

-- Author: Kozlov A.V.
CREATE TABLE user_roles (
                            id      SERIAL PRIMARY KEY,
                            user_id BIGINT UNSIGNED NOT NULL,
                            role_id BIGINT UNSIGNED NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users (id),
                            FOREIGN KEY (role_id) REFERENCES roles (id)
);


INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users(username, password, email, phone_number, surname, name, patronymic, photo_path, datetime_created)
VALUES ('admin', '$2a$10$iEbws11e8wvaXGLm2KCK/.R4/OdAsyAKae/0DY5mdi2rvli7tmAhK', 'admin@ineri.ru', '900 000-00-00', 'admin_surname', 'admin_name', 'admin_patronymic', '', '2022-05-31 18:29:06'),
       ('user', '$2a$10$aCFZS/n29qRx7P/kIaing.j99x.BbNi41bTT6vMKnwepPHjs1.dj.', 'ivan@gmail.com', '921 167-69-55', 'Иванов', 'Иван', 'Иванович', '', '2022-05-31 18:29:06'),
       ('evgeniy37', '$2a$10$OSHx6uMHEDMFxal3tdTp8.SbWZ2D2HHpnI4clLnrLJ48JjY2I8RTS', 'evgeniy37@gmail.com', '921 167-69-55', 'Керимбаев', 'Евгений', 'Егорович', '', '2022-05-31 18:29:06'),
       ('prohor03111985', '$2a$10$/OuSc87VCFihFLei7ytNYO0.QDgzXzYxr/h/cxzd/ESd4b9Il8ywC', 'prohor03111985@rambler.ru', '954 883-56-98', 'Зуев', 'Прохор', 'Иннокентиевич', '', '2022-05-31 18:29:06'),
       ('mila.naberejneva', '$2a$10$3PXAZuuW9Qv/vkeJGYrAtelyMVh9MqE0OIUHVJ9.jaXULH9T7PECi', 'mila.naberejneva@hotmail.com', '973 762-51-24', 'Набережнева', 'Мила', 'Евгеньевна', '', '2022-05-31 18:29:06'),
       ('maryamna09011968', '$2a$10$9T0aJhgnn2NYmxurRqpPfuunVD1l8fMpH4CL3oYdmhNDM5Yn69ZHm', 'maryamna09011968@gmail.com', '946 950-24-24', 'Мерзлякова', 'Марьямна', 'Егоровна', '', '2022-05-31 18:29:06'),
       ('serafim1988', '$2a$10$t.kMaPYpuQ.ROtjfZ8a/W.bLxAOZrS5/oHMSvHc28qReZyS.WnYGy', 'serafim1988@yandex.ru', '926 965-36-70', 'Баева', 'Серафим', 'Павлович', '', '2022-05-31 18:29:06'),
       ('feliks94', '$2a$10$sdoWiTBH8.uo2rb.s3DRkem87ZbFZVpt7RqO1JEeKachBf7Onlnsi', 'feliks94@ya.ru', '976 719-41-64', 'Ягунов', 'Феликс', 'Александрович', '', '2022-05-31 18:29:06'),
       ('semen23', '$2a$10$LI6KU5sZfRmrzehWf2ADreM.7.zE9YuojBCnO6bkvWaxCfwLbF5LC', 'semen23@gmail.com', '971 602-76-38', 'Лобачёв', 'Семен', 'Евгениевич', '', '2022-05-31 18:29:06'),
       ('marianna17121965', '$2a$10$YvOkqA.biHJT/wVi1eMAJe2FZbfSTGdUyaeisP25pfXDhsO6JEC1O', 'marianna17121965@rambler.ru', '940 487-33-86', 'Юшакова', 'Марианна', 'Климентьевна', '', '2022-05-31 18:29:06'),
       ('trofim5535', '$2a$10$vUjAp42bLkhbg4Mx5MErve7IiHCsjAws1YnUbcFgthl9Fm8O61WCS', 'trofim5535@hotmail.com', '984 410-13-82', 'Волошин', 'Трофим', 'Федорович', '', '2022-05-31 18:29:06'),
       ('tamara1977', '$2a$10$FzVPAxsmDqHFbLOGGZkygehA2hEImnx4ehgd2Z8Y0ttf6DUXVbn9C', 'tamara1977@hotmail.com', '971 769-76-16', 'Хитрово', 'Тамара', 'Кирилловна', '', '2022-05-31 18:29:06'),
       ('viktoriya1996', '$2a$10$3B5d3ADfolMKM5wqJYsD6uZ5nHwwd7QEmkVI1vrqgj4LUp5gCt5ry', 'viktoriya1996@hotmail.com', '975 654-14-87', 'Эйлер', 'Виктория', 'Константиновна', '', '2022-05-31 18:29:06');


INSERT INTO user_roles(user_id, role_id)
VALUES  (1, 1),
        (1, 2),
        (2, 2),
        (3, 2),
        (4, 2),
        (5, 2),
        (6, 2),
        (7, 2),
        (8, 2),
        (9, 2),
        (10, 2),
        (11, 2),
        (12, 2),
        (13, 2);


INSERT INTO cities(name)
VALUES ('Москва'), ('Санкт-Петербург'), ('Мытищи'), ('Королёв'), ('Балашиха'), ('Подольск'), ('Красногорск'), ('Химки'), ('Люберцы'), ('Одинцово');


INSERT INTO addresses(city_id, full_address)
VALUES  (1, 'ул. Партизанская, д. 10, кв. 1'),
        (1, 'ул. Космонавтов, 11, кв. 50'),
        (1, 'пер. Ломоносова, 98, кв. 312'),
        (1, 'ул. Космонавтов, 60, кв. 5'),
        (2, 'въезд Будапештсткая, 63, кв. 12'),
        (2, 'пл. Ладыгина, 91, кв. 13');

INSERT INTO vacancies(name, keywords, summary, is_active, text_description, text_study, text_responsibilities, text_requirements, text_features, text_summary, admin_id, city_id)
VALUES ('Агент по продаже жилой недвижимости',
        'Ведение переговоров;Консультирование клиентов;Подбор и организация показов;Сопровождение клиента;Умение вести переговоры;Уверенный пользователь ПК;',
        'от 50 000 руб. до 200 000 руб.',
        1,
        'Если вы человек самоорганизованный, настроены на результат, это деятельность вам подойдет. Компания ведет деятельность в направлении приобретения объектов представляющих наибольший инвестиционный интерес.',
        '- Анализировать стоимость и ликвидность объектов недвижимости, а также состояние рынка в целом
- Оценивать рентабельность и инвестиционные риски
- Работать со 127 ф. з.',
        '- Ведение переговоров и консультирование клиентов по вопросам покупки/продаже/аренде недвижимости
- Подбор и организация показов объектов недвижимости
- Сопровождение клиента на всех этапах сделки',
        '- Умение вести переговоры (эмпатия)
- Активная жизненная позиция
- Самоорганизация
- Работать на результат
- Позитивное мышление
- Уверенный пользователь ПК',
        '- Обучение для начинающих специалистов проводится и персональный наставник
- Прекрасную атмосферу в коллективе, поддержка коллег и руководителей
- Обеспечение заявками и лидами
- Карьерный рост
- Гибкий график работы
- Высокий % с каждой закрытой сделки, З/П без «потолка»',
        'От 50 000 до 200 000 руб. в месяц. Заработная плата будет зависеть напрямую от вашей организованности и систематичности.',
        1,
        1
       );

INSERT INTO vacancies(name, keywords, summary, is_active, text_description, text_study, text_responsibilities, text_requirements, text_features, text_summary, admin_id, city_id)
VALUES ('Брокер по недвижимости',
        'Ведение переговоров;Умение вести переговоры;Уверенный пользователь ПК;',
        'от 110 000 руб.',
        1,
        'Приглашаем Вас для работы в нашем агентстве недвижимости.
У нас заключены договоры с ведущими застройщиками Москвы. Мы реализуем более 100 проектов, большинство из которых расположены в пределах ТТК.
Среди всех агентств по недвижимости мы обладаем самым мощным потоком входящих заявок – от 5000 в месяц. Благодаря собственному колл-центру качество заявок настолько высоко, что именно это обеспечит вам возможность закрыть 1-2 сделки уже в первые недели работы.',
        '',
        '- Обработка входящих заявок по стандартам компании, консультация по телефону и на встречах
- Выявление потребности клиента, а так же подбор идеального варианта объекта с учетом всех пожеланий
- Презентация объекта недвижимости с выгодами и преимуществами
- Организация показа между потенциальным покупателем и Застройщиком
- Полное ведение клиента от звонка до сделки
- Ведение CRM системы (АМО)',
        '- Опыт работы с новостройками Москвы и МО
- Опыт продаж первичной и вторичной недвижимости
- Знание рынка первичной недвижимости Москвы',
        '',
        '- Доход состоит из процента с каждой сделки в размере от 30% до 40%
- Гибкий график работы, предполагает дежурства в офисе
- Обеспечение потоком входящих заявок (от 100 лидов в первый месяц работы), база застройщиков.
- При эффективной работе возможность брать дополнительные заявки.',
        1,
        1
       );



INSERT INTO vacancies(name, keywords, summary, is_active, text_description, text_study, text_responsibilities, text_requirements, text_features, text_summary, admin_id, city_id)
VALUES ('Оператор call-центра',
        'Консультирование клиентов;Сопровождение клиента;Уверенный пользователь ПК;',
        'от 45 000 руб.',
        0,
        'Приглашаем Вас для работы в нашем агентстве недвижимости.
Эта вакансия для тебя, если ты:
Любишь и умеешь общаться с людьми;
Всегда открыт, готов выслушать и помочь;
Имеешь тех. оснащение, а именно: компьютер или ноутбук, наушники с микрофоном и стабильный интернет.',
        '- Анализировать стоимость и ликвидность объектов недвижимости, а также состояние рынка в целом
- Оценивать рентабельность и инвестиционные риски
- Работать со 127 ф. з.',
        '- Звонки клиентам по вопросам актуальности услуги. Мы работаем в сфере недвижимости и оказываем консультативные услуги. Нет продаж!
- Минимальная отчетность.',
        '- Грамотная и четкая речь
- Наличие компьютера, гарнитуры и стабильного интернета
- Возможность обеспечить тишину в течение рабочего дня
- Желание много зарабатывать!',
        '- Стандартная рабочая неделя 5/2, сб-вс - выходные.
- Если вы хотите зарабатывать, то можете дополнительно работать в выходные дни!
- Полный рабочий день 8 часов. При этом, есть возможность скорректировать график под себя, так как наш КЦ работает с 9 утра до 9 вечера.
- Возможна частичная занятость от 5 часов в день.
- Оплата состоит из оклада и бонуса за каждый перевод. Особо эффективным кандидатам дополнительные премии.
- Работа полностью удаленная',
        'От 50 000 до 200 000 руб. в месяц. Заработная плата будет зависеть напрямую от вашей организованности и систематичности.',
        1,
        1
       );

INSERT INTO advertised_statuses(name)
VALUES ('Опубликованно'), ('Закрыто'), ('Скрыто');

INSERT INTO house_types(name)
VALUES ('Кирпичный'), ('Деревянный'), ('Монолитный'), ('Панельный'), ('Блочный'), ('Кирпично-монолитный'), ('Сталинский');

INSERT INTO property_types(name)
VALUES ('Жилое'), ('Коммерческое');

INSERT INTO renovation_types(name)
VALUES ('Без ремонта'), ('Косметический'), ('Евроремонт'), ('Дизайнерский');

INSERT INTO estate_object_types(name)
VALUES ('Новостройка'), ('Вторичка'), ('Дом'), ('Участок'), ('Офис'), ('Помещение'), ('Склад');


INSERT INTO estate_objects(area, floor, max_floor, room_size, datetime_created,
                           house_type_id, property_type_id, renovation_type_id, estate_object_type_id, address_id)
VALUES  (50, 4, 30, 3, '2022-01-01 12:00:00', 2, 1, 2, 2, 1),
        (112, 36, 50, 4, '2022-02-01 11:59:00', 3, 1, 1, 1, 2),
        (314, 2, 6, 3, '2022-03-14 10:39:54', 6, 2, 3, 5, 3),
        (32, 4, 5, 2, '2022-02-01 19:30:14', 4, 1, 1, 1, 4),
        (84, 6, 12, 2, '2022-02-01 14:14:56', 5, 1, 1, 1, 5),
        (215, 0, 2, 6, '2021-12-29 11:59:00', 3, 1, 1, 3, 6);

INSERT INTO advertiseds(description, price, datetime_created, advertised_status_id, estate_object_id, admin_id, user_id)
VALUES  ('Проспект Мира и ВДНХ: это традиции и преемственность поколений. Четыре парка рядом с вами.
Жизнь в нескольких минутах от центра города в месте, утопающем в высоких зеленых деревьях
это жизнь в "Триколоре", где слагаемых комфорта, здоровья и окружения для вас и вашей семьи
даже больше, чем три причины вашего счастья. Собственная инфраструктура, подземный паркинг
здесь учтены и реализованы все решения уровня бизнес-класса в недвижимости.',
         8500199, '2021-12-29 11:59:00', 1, 1, 1, 1),
        ('Описание',
         18999899, '2022-02-01 14:14:56', 1, 2, 1, 1),
        ('Описание',
         54521499, '2022-02-01 19:30:14', 3, 3, 1, 1),
        ('Описание',
         7154349, '2022-03-14 10:39:54', 1, 4, 1, 1),
        ('Описание',
         9954349, '2022-03-14 10:39:54', 2, 5, 1, 1),
        ('Описание',
         114578199, '2022-03-27 17:39:54', 1, 6, 1, 1);

INSERT INTO favorites(user_id, advertised_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (1, 3),
       (1, 1);

INSERT INTO forms(state_enum, admin_comment, area, floor, max_floor, room_size, description, price, datetime_created, user_id, admin_id, house_type_id, property_type_id, renovation_type_id, estate_object_type_id, address_id)
VALUES (0, 'test0',
        39, 9, 15, 2,
        'Описание',
        5899999, '2021-12-29 11:59:00',
        2, 1, 1, 1, 1, 2, 1),
       (-1, 'test-1',
        23, 9, 15, 1,
        'Описание',
        12546142, '2021-11-28 19:13:00',
        2, 1, 1, 1, 1, 2, 1),
       (1, 'test1',
        23, 9, 15, 1,
        'Описание',
        29546142, '2021-11-28 19:13:00',
        2, 1, 1, 1, 1, 2, 1);

INSERT INTO advertised_photos(path, advertised_id, form_id)
VALUES ('/images/userdata/1-0.jpg',
        1, 1),
       ('/images/userdata/1-1.jpg',
        1, 1),
       ('/images/userdata/1-2.jpg',
        1, 1),
       ('/images/userdata/1-3.jpg',
        1, 1),
       ('/images/userdata/1-4.jpg',
        1, 1),
       ('/images/userdata/1-5.jpg',
        1, 1),
       ('/images/userdata/1-6.jpg',
        1, 1),
       ('/images/userdata/1-7.jpg',
        1, 1),
       ('/images/userdata/1-8.jpg',
        1, 1),
       ('/images/userdata/1-9.jpg',
        1, 1),
       ('/images/userdata/2-0.jpg',
        2, 1),
       ('/images/userdata/2-1.jpg',
        2, 1),
       ('/images/userdata/2-2.jpg',
        2, 1),
       ('/images/userdata/2-3.jpg',
        2, 1),
       ('/images/userdata/2-4.jpg',
        2, 1),
       ('/images/userdata/2-5.jpg',
        2, 1),
       ('/images/userdata/2-6.jpg',
        2, 1),
       ('/images/userdata/2-7.jpg',
        2, 1);