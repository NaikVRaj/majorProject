package com.spe.workwize.repository;
import com.spe.workwize.bean.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<Risk,Long> {
}
