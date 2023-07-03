SELECT name, COUNT(project.id) AS count_project
FROM client
LEFT JOIN project ON client.id = project.client_id
GROUP BY client.id
HAVING count_project IN (
  SELECT count(id)
  FROM project
  GROUP BY client_id
  ORDER BY count(id) DESC
  LIMIT 1
);