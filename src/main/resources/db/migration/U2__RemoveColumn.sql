Alter table registration drop column username;
Delete From flyway_schema_history where version = '2';
/*Insert into flyway_schema_history (installed_rank, version, description, type, script, installed_by, execution_time,
                                   success)
VALUES ((Select installed_rank from flyway_schema_history order by installed_rank desc limit 1)+1, 2, 'undo', 'SQL', 'U2__RemoveColumn.sql', 'postgres', '12 ', 'y');*/