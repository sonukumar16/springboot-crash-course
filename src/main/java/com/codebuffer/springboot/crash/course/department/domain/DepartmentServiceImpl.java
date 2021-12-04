package com.codebuffer.springboot.crash.course.department.domain;

import com.codebuffer.springboot.crash.course.department.domain.repository.DepartmentRepository;
import com.codebuffer.springboot.crash.course.department.v1.error.DepartmentNotFoundException;
import com.codebuffer.springboot.crash.course.department.domain.entity.DepartmentEntity;
import com.codebuffer.springboot.crash.course.department.v1.dto.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
//    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init(){
        modelMapper = new ModelMapper();;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        DepartmentEntity department = modelMapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartment(Long departmentId) throws DepartmentNotFoundException {
        Optional<DepartmentEntity> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return modelMapper.map(department.get(), DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> fetchDepartmentList() {
        List<DepartmentEntity> departments = departmentRepository.findAll();
        List<DepartmentDto> mappedDepartments = departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
        return mappedDepartments;
    }

    @Override
    public DepartmentDto getDepartmentByName(String departmentName) throws DepartmentNotFoundException {
        Optional<DepartmentEntity> department = Optional.ofNullable(departmentRepository.findByDepartmentNameIgnoreCase(departmentName));
        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return modelMapper.map(department.get(), DepartmentDto.class);
    }
}
