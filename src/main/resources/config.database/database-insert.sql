INSERT INTO roles(name)
VALUES ('admin'), ('user');

INSERT INTO users(login, password, email, phone_number, surname, username, patronymic, photo_path)
VALUES ('admin', 'admin', 'admin@ineri.ru', '900 000-00-00', 'admin_surname', 'admin_name', 'admin_patronymic', '');

INSERT INTO cities(name)
VALUES ('Москва');

INSERT INTO addresses(city_id, full_address)
VALUES (1, 'ул. Партизанская, д. 10, кв. 1');

INSERT INTO vacancies(name, keywords, summary, is_active, text_description, text_study, text_responsibilities, text_requirements, text_features, text_summary, admin_id, city_id)
VALUES ('Агент по продаже жилой недвижимости',
        'Ведение переговоров;Консультирование клиентов;Подбор и организация показов;Сопровождение клиента;Умение вести переговоры;Уверенный пользователь ПК;',
        'от 50 000 руб. до 200 000 руб. в месяц',
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
        )