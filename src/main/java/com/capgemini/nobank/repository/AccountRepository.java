package com.capgemini.nobank.repository;

import com.capgemini.nobank.domain.persistence.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
