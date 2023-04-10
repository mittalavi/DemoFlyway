UPDATE registration
SET value = value / 1.1;
Delete From flyway_schema_history where version = '5';
Insert Into undo_schema_history (version,date) VALUES (5,CAST(CURRENT_DATE AS DATE));