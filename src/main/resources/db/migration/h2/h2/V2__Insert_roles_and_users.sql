-- Добавляем роли
INSERT INTO public.users (username, password, name, surname, parent_name, creation_date, last_edit_date, role)
VALUES
-- Администратор
('admin', '$2a$10$xVCH4IAHwYr3NiEDj1TJ8uZgDS/JwQoYQvJYVjH6YdZPv6vL7Lf7a', 'Иван', 'Иванов', 'Иванович', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_ADMIN'),
-- Журналисты
('journalist1', '$2a$10$xVCH4IAHwYr3NiEDj1TJ8uZgDS/JwQoYQvJYVjH6YdZPv6vL7Lf7a', 'Петр', 'Петров', 'Петрович', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_JOURNALIST'),
('journalist2', '$2a$10$xVCH4IAHwYr3NiEDj1TJ8uZgDS/JwQoYQvJYVjH6YdZPv6vL7Lf7a', 'Сергей', 'Сергеев', 'Сергеевич', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_JOURNALIST'),
-- Подписчики
('subscriber1', '$2a$10$xVCH4IAHwYr3NiEDj1TJ8uZgDS/JwQoYQvJYVjH6YdZPv6vL7Lf7a', 'Алексей', 'Алексеев', 'Алексеевич', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_SUBSCRIBER'),
('subscriber2', '$2a$10$xVCH4IAHwYr3NiEDj1TJ8uZgDS/JwQoYQvJYVjH6YdZPv6vL7Lf7a', 'Дмитрий', 'Дмитриев', 'Дмитриевич', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_SUBSCRIBER'),
('subscriber3', '$2a$10$xVCH4IAHwYr3NiEDj1TJ8uZgDS/JwQoYQvJYVjH6YdZPv6vL7Lf7a', 'Анна', 'Аннова', 'Анновна', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_SUBSCRIBER');