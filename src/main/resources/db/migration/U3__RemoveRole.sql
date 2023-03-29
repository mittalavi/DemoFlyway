Alter table registration drop column Value;
Delete From flyway_schema_history where version = '3';
/*Insert into flyway_schema_history (installed_rank, version, description, type, script, installed_by, execution_time,
                                   success)
VALUES ((Select installed_rank from flyway_schema_history order by installed_rank desc limit 1)+1, 2, 'undo', 'SQL', 'U3__RemoveRole.sql', 'postgres', '12 ', 'y');*/