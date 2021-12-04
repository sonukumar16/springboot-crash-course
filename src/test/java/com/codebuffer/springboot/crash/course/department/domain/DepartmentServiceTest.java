package com.codebuffer.springboot.crash.course.department.domain;

import com.codebuffer.springboot.crash.course.department.domain.entity.DepartmentEntity;
import com.codebuffer.springboot.crash.course.department.domain.repository.DepartmentRepository;
import com.codebuffer.springboot.crash.course.department.v1.dto.DepartmentDto;
import com.codebuffer.springboot.crash.course.department.v1.error.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private  DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        DepartmentEntity department = DepartmentEntity.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Dubai")
                .departmentCode("IT007").build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid department name")
    public void whenValidDepartmentName_thenDepartmentShouldFound() throws DepartmentNotFoundException {
        String departmentName = "IT";
        DepartmentDto found = departmentService.getDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
}