package com.codebuffer.springboot.crash.course.department.domain.repository;

import com.codebuffer.springboot.crash.course.department.domain.entity.DepartmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        DepartmentEntity department = DepartmentEntity.builder()
                .departmentName("IT")
                .departmentAddress("Dubai")
                .departmentCode("IT007").build();
        entityManager.persist(department);
    }

    @Test
    @DisplayName("Get department by findById")
    public void whenFindById_thenReturnDepartment() {
        String departmentName = "IT";
        DepartmentEntity found = departmentRepository.findById(1L).get();
        assertEquals(found.getDepartmentName(), departmentName);
    }
}
