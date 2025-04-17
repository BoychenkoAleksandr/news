-- Добавляем по 10 комментариев к каждой из 20 новостей (всего 200)
INSERT INTO public.comments (text, creation_date, last_edit_date, inserted_by_id, updated_by_id, news_id) VALUES
-- Комментарии к новости 1 (10 шт)
('Отличная новость! Спасибо за информацию', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 1),
('Интересный материал, хотелось бы больше деталей', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 1),
('Полностью согласен с автором', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 1),
('Есть несколько неточностей в тексте', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 1),
('Когда будет продолжение?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 1),
('Слишком поверхностное освещение темы', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 1),
('Где можно найти первоисточник?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 1),
('Очень актуальная тема', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 1),
('Жду более подробного анализа', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 1),
('Спасибо за оперативное освещение', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 1),

-- Комментарии к новости 2 (10 шт)
('Отличный репортаж!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 2),
('Не все факты проверены', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 2),
('Интересная точка зрения', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 2),
('Можно было бы добавить статистику', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 2),
('Слишком много субъективных оценок', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 2),
('Хорошая работа журналиста', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 2),
('Где комментарии экспертов?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 2),
('Полезная информация', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 2),
('Не хватает иллюстраций', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 2),
('Буду ждать продолжения', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 2),

-- Комментарии к новости 3 (10 шт)
('Важная тема', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 3),
('Спасибо за разбор ситуации', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 3),
('Есть вопросы по некоторым моментам', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 3),
('Очень подробный анализ', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 3),
('Не согласен с выводами', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 3),
('Хорошая подборка фактов', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 3),
('Можно было бы короче', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 3),
('Информативно и полезно', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 3),
('Жду интервью с участниками', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 3),
('Слишком много воды в тексте', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 3),

-- Комментарии к новости 4 (10 шт)
('Комментарий 1 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 4),
('Комментарий 2 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 4),
('Комментарий 3 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 4),
('Комментарий 4 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 4),
('Комментарий 5 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 4),
('Комментарий 6 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 4),
('Комментарий 7 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 4),
('Комментарий 8 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 4),
('Комментарий 9 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 4),
('Комментарий 10 к новости 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 4),

-- Комментарии к новости 5 (10 шт)
('Комментарий 1 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 5),
('Комментарий 2 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 5),
('Комментарий 3 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 5),
('Комментарий 4 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 5),
('Комментарий 5 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 5),
('Комментарий 6 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 5),
('Комментарий 7 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 5),
('Комментарий 8 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 5),
('Комментарий 9 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 5),
('Комментарий 10 к новости 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 5),

-- Комментарии к новости 6 (10 шт)
('Комментарий 1 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 6),
('Комментарий 2 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 6),
('Комментарий 3 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 6),
('Комментарий 4 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 6),
('Комментарий 5 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 6),
('Комментарий 6 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 6),
('Комментарий 7 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 6),
('Комментарий 8 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 6),
('Комментарий 9 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 6),
('Комментарий 10 к новости 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 6),

-- Комментарии к новости 7 (10 шт)
('Комментарий 1 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 7),
('Комментарий 2 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 7),
('Комментарий 3 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 7),
('Комментарий 4 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 7),
('Комментарий 5 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 7),
('Комментарий 6 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 7),
('Комментарий 7 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 7),
('Комментарий 8 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 7),
('Комментарий 9 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 7),
('Комментарий 10 к новости 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 7),

-- Комментарии к новости 8 (10 шт)
('Комментарий 1 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 8),
('Комментарий 2 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 8),
('Комментарий 3 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 8),
('Комментарий 4 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 8),
('Комментарий 5 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 8),
('Комментарий 6 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 8),
('Комментарий 7 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 8),
('Комментарий 8 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 8),
('Комментарий 9 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 8),
('Комментарий 10 к новости 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 8),

-- Комментарии к новости 9 (10 шт)
('Комментарий 1 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 9),
('Комментарий 2 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 9),
('Комментарий 3 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 9),
('Комментарий 4 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 9),
('Комментарий 5 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 9),
('Комментарий 6 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 9),
('Комментарий 7 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 9),
('Комментарий 8 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 9),
('Комментарий 9 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 9),
('Комментарий 10 к новости 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 9),

-- Комментарии к новости 10 (10 шт)
('Комментарий 1 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 10),
('Комментарий 2 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 10),
('Комментарий 3 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 10),
('Комментарий 4 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 10),
('Комментарий 5 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 10),
('Комментарий 6 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 10),
('Комментарий 7 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 10),
('Комментарий 8 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 10),
('Комментарий 9 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 10),
('Комментарий 10 к новости 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 10),

-- Комментарии к новости 11 (10 шт)
('Комментарий 1 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 11),
('Комментарий 2 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 11),
('Комментарий 3 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 11),
('Комментарий 4 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 11),
('Комментарий 5 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 11),
('Комментарий 6 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 11),
('Комментарий 7 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 11),
('Комментарий 8 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 11),
('Комментарий 9 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 11),
('Комментарий 10 к новости 11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 11),

-- Комментарии к новости 12 (10 шт)
('Комментарий 1 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 12),
('Комментарий 2 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 12),
('Комментарий 3 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 12),
('Комментарий 4 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 12),
('Комментарий 5 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 12),
('Комментарий 6 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 12),
('Комментарий 7 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 12),
('Комментарий 8 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 12),
('Комментарий 9 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 12),
('Комментарий 10 к новости 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 12),

-- Комментарии к новости 13 (10 шт)
('Комментарий 1 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 13),
('Комментарий 2 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 13),
('Комментарий 3 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 13),
('Комментарий 4 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 13),
('Комментарий 5 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 13),
('Комментарий 6 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 13),
('Комментарий 7 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 13),
('Комментарий 8 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 13),
('Комментарий 9 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 13),
('Комментарий 10 к новости 13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 13),

-- Комментарии к новости 14 (10 шт)
('Комментарий 1 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 14),
('Комментарий 2 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 14),
('Комментарий 3 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 14),
('Комментарий 4 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 14),
('Комментарий 5 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 14),
('Комментарий 6 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 14),
('Комментарий 7 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 14),
('Комментарий 8 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 14),
('Комментарий 9 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 14),
('Комментарий 10 к новости 14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 14),

-- Комментарии к новости 15 (10 шт)
('Комментарий 1 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 15),
('Комментарий 2 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 15),
('Комментарий 3 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 15),
('Комментарий 4 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 15),
('Комментарий 5 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 15),
('Комментарий 6 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 15),
('Комментарий 7 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 15),
('Комментарий 8 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 15),
('Комментарий 9 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 15),
('Комментарий 10 к новости 15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 15),

-- Комментарии к новости 16 (10 шт)
('Комментарий 1 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 16),
('Комментарий 2 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 16),
('Комментарий 3 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 16),
('Комментарий 4 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 16),
('Комментарий 5 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 16),
('Комментарий 6 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 16),
('Комментарий 7 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 16),
('Комментарий 8 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 16),
('Комментарий 9 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 16),
('Комментарий 10 к новости 16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 16),

-- Комментарии к новости 17 (10 шт)
('Комментарий 1 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 17),
('Комментарий 2 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 17),
('Комментарий 3 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 17),
('Комментарий 4 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 17),
('Комментарий 5 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 17),
('Комментарий 6 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 17),
('Комментарий 7 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 17),
('Комментарий 8 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 17),
('Комментарий 9 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 17),
('Комментарий 10 к новости 17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 17),

-- Комментарии к новости 18 (10 шт)
('Комментарий 1 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 18),
('Комментарий 2 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 18),
('Комментарий 3 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 18),
('Комментарий 4 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 18),
('Комментарий 5 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 18),
('Комментарий 6 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 18),
('Комментарий 7 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 18),
('Комментарий 8 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 18),
('Комментарий 9 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 18),
('Комментарий 10 к новости 18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 18),

-- Комментарии к новости 19 (10 шт)
('Комментарий 1 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 19),
('Комментарий 2 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 19),
('Комментарий 3 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 19),
('Комментарий 4 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 19),
('Комментарий 5 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 19),
('Комментарий 6 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 19),
('Комментарий 7 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 19),
('Комментарий 8 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 19),
('Комментарий 9 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 19),
('Комментарий 10 к новости 19', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 19),

-- Комментарии к новости 20 (10 шт)
('Финальный комментарий 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 20),
('Финальный комментарий 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 20),
('Финальный комментарий 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 20),
('Финальный комментарий 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 20),
('Финальный комментарий 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 20),
('Финальный комментарий 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 20),
('Финальный комментарий 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 20),
('Финальный комментарий 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5, 20),
('Финальный комментарий 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6, 20),
('Финальный комментарий 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4, 20);