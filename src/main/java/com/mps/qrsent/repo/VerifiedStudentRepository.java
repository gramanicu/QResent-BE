package com.mps.qrsent.repo;

import com.mps.qrsent.model.VerifiedStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifiedStudentRepository extends JpaRepository<VerifiedStudent, Long> {
}