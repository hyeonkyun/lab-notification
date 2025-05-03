package kr.pe.hyeonkyun.lab.api.admin.domain.repository;

import kr.pe.hyeonkyun.lab.api.admin.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);
}