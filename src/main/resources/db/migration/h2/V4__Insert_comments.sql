-- Добавляем по 10 комментариев к каждой новости (всего 200)
INSERT INTO public.comments (text, creation_date, last_edit_date, inserted_by_id, updated_by_id, news_id)
WITH comment_texts AS (
    SELECT unnest(ARRAY[
                      'Отличная статья!',
                  'Спасибо за информацию',
                  'Не согласен с автором',
                  'Где первоисточник?',
                  'Ожидал больше деталей',
                  'Очень актуально!',
                  'Когда будет продолжение?',
                  'Есть неточности',
                  'Лучший материал за день',
                  'Почему так мало цифр?'
                      ]) AS text
),
     subscribers AS (
         SELECT id FROM users WHERE role = 'ROLE_SUBSCRIBER' ORDER BY id LIMIT 3
    )
SELECT
    ct.text,
    n.creation_date + (c.num * INTERVAL '5 minutes'),
    n.creation_date + (c.num * INTERVAL '5 minutes'),
    s.id,
    s.id,
    n.id
FROM
    (SELECT id, creation_date FROM news ORDER BY id) AS n,
    (SELECT generate_series(1, 10) AS num) AS c,
    comment_texts AS ct,
    subscribers AS s
ORDER BY n.id, c.num
    LIMIT 200; -- 20 новостей × 10 комментариев