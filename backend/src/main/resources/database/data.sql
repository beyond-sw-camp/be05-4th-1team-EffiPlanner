INSERT INTO category (category_id, category_name)
SELECT * FROM (
                  SELECT 1 AS id, '업무' AS name
                  UNION ALL
                  SELECT 2 AS id, '개인' AS name
                  UNION ALL
                  SELECT 3 AS id, '긴급' AS name
                  UNION ALL
                  SELECT 4 AS id, '기타' AS name
              ) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM category WHERE id IN (1, 2, 3, 4)
);
