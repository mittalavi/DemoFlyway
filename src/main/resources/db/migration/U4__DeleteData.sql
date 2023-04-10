DELETE from registration where id = 1;
DELETE from registration where id = 2;
Delete From flyway_schema_history where version = '4';
Insert Into undo_schema_history (version,date) VALUES (4,CAST(CURRENT_DATE AS DATE));