package com.spe.workwize.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spe.workwize.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findRoleByName(String name);

}
