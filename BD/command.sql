CREATE USER 'ubzk'@'%' IDENTIFIED BY 'ubzk2018';
grant all privileges on *.*  to 'ubzk'@'%' with grant option;
SHOW DATABASES;
status;
SHOW TABLES;
SHOW VARIABLES like '%lower_case_table_names%';
SET lower_case_table_names=1;