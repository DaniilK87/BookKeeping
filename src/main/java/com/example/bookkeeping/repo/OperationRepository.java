package com.example.bookkeeping.repo;

import com.example.bookkeeping.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
