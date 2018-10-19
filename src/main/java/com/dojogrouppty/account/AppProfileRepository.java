package com.dojogrouppty.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppProfileRepository extends JpaRepository<AppProfile, Long> {

    AppProfile findOneBy(Long idProfile);


}