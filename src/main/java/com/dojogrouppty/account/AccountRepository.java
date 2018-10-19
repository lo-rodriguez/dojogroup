package com.dojogrouppty.account;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findOneByEmail(String email);

	@Query(nativeQuery = true, value="select IF(count(1) > 0,1,0) from Account a where a.email = :email")
	int exists(@Param("email") String email);
        @Query(nativeQuery = true, value="select id,email,first_name,last_name,role from User a where a.date_given_low is null")
         List<Object[]>  getUserEnable();
}