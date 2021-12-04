package com.codebuffer.springboot.crash.course.department.domain;

import com.codebuffer.springboot.crash.course.department.v1.error.DepartmentNotFoundException;
import com.codebuffer.springboot.crash.course.department.v1.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    public DepartmentDto saveDepartment(DepartmentDto department);

    public DepartmentDto getDepartment(Long departmentId) throws DepartmentNotFoundException;

    List<DepartmentDto> fetchDepartmentList();

    public DepartmentDto getDepartmentByName(String departmentName) throws DepartmentNotFoundException;
}
