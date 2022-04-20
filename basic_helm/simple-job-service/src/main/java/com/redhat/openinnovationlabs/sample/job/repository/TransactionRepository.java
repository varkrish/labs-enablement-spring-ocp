package com.redhat.openinnovationlabs.sample.job.repository;

import com.redhat.openinnovationlabs.sample.job.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
