package com.spe.workwize.repository;

import com.spe.workwize.bean.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByProjectId(Long id);

    @Query(value = "SELECT * FROM project P WHERE P.project_id IN (SELECT UP.project_id FROM project_user UP WHERE UP.user_id=?1) ",nativeQuery = true)
    List<Project> getAllProjectByUserId(Long id);


}
