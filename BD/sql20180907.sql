select CONCAT('PROD',LPAD(COUNT(1)+1,6,'0')) 
from system_codes 
where system_cod_group='PRODUCTS'
AND system_code LIKE 'PROD%';

select CONCAT(SUBSTRING('CLASES',1,4),LPAD(COUNT(1)+1,6,'0')) 
from system_codes 
where system_cod_group='PRODUCTS'
AND system_code LIKE CONCAT(SUBSTRING('CLASES',1,4),'%');