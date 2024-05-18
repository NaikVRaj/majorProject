package com.spe.workwize.repository;

import com.spe.workwize.bean.NonFunctionalRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonFunctionalRequirementRepository extends JpaRepository<NonFunctionalRequirement,Long> {
}
