package com.spe.workwize.repository;

import com.spe.workwize.bean.FunctionalRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionalRequirementRepository extends JpaRepository<FunctionalRequirement,Long> {
}
