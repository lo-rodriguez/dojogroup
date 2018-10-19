package com.dojogrouppty.catalogs;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemCodesRepository extends JpaRepository<SystemCodes, Long> {
@Query(nativeQuery = true, value="select idsystem_codes,system_code,system_cod_description,system_cod_group,sub_group_description from system_codes where system_cod_group=:group")
 List <SystemCodes> getSystemCodesByGroup(@Param("group") String group);
 @Query(nativeQuery = true, value="select CONCAT('PROD',LPAD(COUNT(1)+1,6,'0')) from system_codes where system_cod_group='PRODUCTS'AND system_code LIKE 'PROD%'")
 String getSecuenceProduct();
 @Query(nativeQuery = true, value="select CONCAT(SUBSTRING(:subGroup,1,4),LPAD(COUNT(1)+1,6,'0')) \r\n" + 
 		"from system_codes \r\n" + 
 		"where system_cod_group='PRODUCTS'\r\n" + 
 		"AND system_code LIKE CONCAT(SUBSTRING(:subGroup,1,4),'%');")
 String getSecuenceProductBySubGroup(@Param("subGroup")String subGroup);
}