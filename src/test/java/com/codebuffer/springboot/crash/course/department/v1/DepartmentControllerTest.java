package com.codebuffer.springboot.crash.course.department.v1;

import com.codebuffer.springboot.crash.course.department.domain.DepartmentService;
import com.codebuffer.springboot.crash.course.department.domain.entity.DepartmentEntity;
import com.codebuffer.springboot.crash.course.department.v1.dto.DepartmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private DepartmentEntity department;
    private ModelMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new ModelMapper();
    }

    @BeforeEach
    void setUp() {
        department = DepartmentEntity.builder()
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
        // @Disabled
    void saveDepartment() throws Exception {
        DepartmentDto departmentTobeSaved = DepartmentDto.builder()
                .departmentName("IT")
                .departmentAddress("Dubai")
                .departmentCode("IT007")
                .build();
        DepartmentDto converted = mapper.map(department, DepartmentDto.class);
        Mockito.when(departmentService.saveDepartment(departmentTobeSaved))
                .thenReturn(converted);

        mockMvc.perform(post("/api/v1/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"IT\",\n" +
                        "\t\"departmentAddress\":\"Dubai\",\n" +
                        "\t\"departmentCode\":\"IT007\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
        //@Disabled
    void getDepartment() throws Exception {
        DepartmentDto converted = mapper.map(department, DepartmentDto.class);
        Mockito.when(departmentService.getDepartment(1L))
                .thenReturn(converted);
        mockMvc.perform(get("/api/v1/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.departmentName").
                        value(department.getDepartmentName()));
    }
}