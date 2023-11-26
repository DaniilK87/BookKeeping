package com.example.bookkeeping.repo;

import com.example.bookkeeping.entity.Grants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantRepository extends JpaRepository<Grants, Integer> {
}
