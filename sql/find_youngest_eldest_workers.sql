SELECT
  CASE
    WHEN birthday = (SELECT MIN(birthday) FROM worker) THEN 'OLDEST'
    WHEN birthday = (SELECT MAX(birthday) FROM worker) THEN 'YOUNGEST'
    ELSE ''
  END AS type,
  name,
  birthday
FROM worker
WHERE birthday = (SELECT MIN(birthday) FROM worker) OR birthday = (SELECT MAX(birthday) FROM worker)
ORDER BY birthday ASC;