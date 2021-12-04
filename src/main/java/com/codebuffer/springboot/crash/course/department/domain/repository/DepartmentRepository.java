package com.codebuffer.springboot.crash.course.department.domain.repository;

import com.codebuffer.springboot.crash.course.department.domain.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    DepartmentEntity findByDepartmentName(String department);
    DepartmentEntity findByDepartmentNameIgnoreCase(String department);
}
