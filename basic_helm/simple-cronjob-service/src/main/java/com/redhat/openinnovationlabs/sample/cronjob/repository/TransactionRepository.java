package com.redhat.openinnovationlabs.sample.cronjob.repository;

import com.redhat.openinnovationlabs.sample.cronjob.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
