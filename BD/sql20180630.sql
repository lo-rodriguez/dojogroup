#SELECT FORMAT(123456789,2,'rm_CH') AS 'Format';123'456'789,00
#SELECT FORMAT(123456789,2,'es_PA') AS 'Format';
#SELECT p.*
#FROM payments p
#WHERE 
#CAST(number_of_transfer AS INTEGER) >= 10000010
#AND CAST(number_of_transfer AS INTEGER) <= 10000028
#AND ( P.type_payment =252) 
#order by p.number_of_transfer,c.system_cod_description ASC;



SELECT DATE_FORMAT(p.payday, '%d/%m/%Y'),p.number_of_transfer,c.system_cod_description,p.total_payment 
FROM payments p, system_codes c 
WHERE ( p.type_payment =c.idsystem_codes ) 
AND( CAST(number_of_transfer AS INTEGER) >= 10000004
AND CAST(number_of_transfer AS INTEGER) <= 10000028 )
AND ( P.type_payment =252 ) 
ORDER BY p.number_of_transfer,c.system_cod_description ASC