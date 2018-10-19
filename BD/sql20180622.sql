ALTER TABLE detail_payments AUTO_INCREMENT=10000000;
ALTER TABLE payments AUTO_INCREMENT=20000000;

SELECT concat('DROP TABLE IF EXISTS `', table_name, '`;')
FROM information_schema.tables
WHERE table_schema = 'dbbzk';

SET FOREIGN_KEY_CHECKS=0; -- to disable them
SET FOREIGN_KEY_CHECKS=1; -- to re-enable them
---
SELECT * FROM dbbzk.payments;
SELECT * FROM dbbzk.detail_payments where id_payment= 179;

 ALTER TABLE payments DROP id_payment;
 
 SET FOREIGN_KEY_CHECKS=0; -- to disable them
 
 DELETE FROM dbbzk.detail_payments where id_payment>0;
 DELETE FROM dbbzk.payments where id_payment>0;
 
 ALTER TABLE payments MODIFY id_payment INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST;,
   ADD PRIMARY KEY (id_payment);
   
   ALTER TABLE `payments` CHANGE COLUMN `id_payment` `id_payment` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT FIRST;