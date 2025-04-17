-- Добавляем 20 новостей с разными темами и авторами
INSERT INTO public.news (title, text, creation_date, last_edit_date, inserted_by_id, updated_by_id)
WITH news_data AS (
    SELECT
        'Новость ' || n || ': ' ||
        CASE
            WHEN n % 5 = 0 THEN 'Экстренное сообщение'
            WHEN n % 4 = 0 THEN 'Эксклюзивное интервью'
            WHEN n % 3 = 0 THEN 'Аналитический обзор'
            WHEN n % 2 = 0 THEN 'Последние новости'
            ELSE 'Важные события'
            END AS title,
        'Полный текст новости ' || n || '. ' ||
        CASE
            WHEN n % 3 = 0 THEN 'Эксперты считают, что ситуация требует внимания. '
            WHEN n % 2 = 0 THEN 'Наши корреспонденты выяснили подробности. '
            ELSE 'Официальные представители прокомментировали ситуацию. '
            END ||
        'Материал подготовлен при участии специалистов.' AS text,
        CURRENT_TIMESTAMP - (n * INTERVAL '2 hour') AS creation_date,
        CASE WHEN n % 2 = 0 THEN 2 ELSE 3 END AS journalist_id -- Чередуем журналистов
    FROM generate_series(1, 20) AS n
)
SELECT
    title,
    text,
    creation_date,
    creation_date AS last_edit_date,
    journalist_id,
    journalist_id
FROM news_data;