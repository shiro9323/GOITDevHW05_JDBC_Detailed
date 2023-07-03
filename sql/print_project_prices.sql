SELECT pw.project_id AS Id,
SUM(TIMESTAMPDIFF(MONTH, p.start_date, p.finish_date) * w.salary) AS overall_price
FROM project_worker pw
LEFT JOIN worker w ON pw.worker_id = w.id
LEFT JOIN project p ON pw.project_id = p.id
GROUP BY pw.project_id
ORDER BY overall_price DESC;