package com.codebuffer.springboot.crash.course.department.v1;

import com.codebuffer.springboot.crash.course.department.domain.DepartmentService;
import com.codebuffer.springboot.crash.course.department.v1.error.DepartmentNotFoundException;
import com.codebuffer.springboot.crash.course.department.v1.dto.DepartmentDto;
import com.codebuffer.springboot.crash.course.respone.handler.Response;
import com.codebuffer.springboot.crash.course.respone.handler.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * Returns credit card list
     */

//    @PostMapping
//    public ResponseEntity<DepartmentDto> saveDepartment(@Valid @RequestBody DepartmentDto department){
//        LOGGER.info("Inside saveDepartment of DepartmentController");
//        return ResponseEntity.ok(departmentService.saveDepartment(department));
//    }


    @PostMapping
    public ResponseEntity<Response> saveDepartment(@Valid @RequestBody DepartmentDto department){
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return ResponseEntity.ok(
                Response.builder().status(ResponseStatus.SUCCESS).data(departmentService.saveDepartment(department)).build());
    }

    @GetMapping("/")
    public ResponseEntity<Response> fetchDepartmentList() {
        LOGGER.info("Inside fetchDepartmentList of DepartmentController");
        return ResponseEntity.ok(
                Response.builder().status(ResponseStatus.SUCCESS).data(departmentService.fetchDepartmentList()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getDepartment(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return ResponseEntity.ok(
                Response.builder().status(ResponseStatus.SUCCESS).data(departmentService.getDepartment(departmentId)).build());
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<Response> fetchDepartmentByName(@PathVariable("name") String departmentName) throws DepartmentNotFoundException {
        LOGGER.info("Inside fetchDepartmentByName of DepartmentController");
        return ResponseEntity.ok(
                Response.builder().status(ResponseStatus.SUCCESS).data(departmentService.getDepartmentByName(departmentName)).build());
    }
}
