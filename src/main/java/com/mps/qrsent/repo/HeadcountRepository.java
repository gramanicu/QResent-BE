package com.mps.qrsent.repo;

import com.mps.qrsent.model.Headcount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadcountRepository extends JpaRepository<Headcount, String> {
}