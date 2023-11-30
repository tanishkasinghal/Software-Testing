package com.speMajor.demo.service.Department;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.speMajor.demo.exception.ResourceNotFoundException;
import com.speMajor.demo.model.Department;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.payload.DepartmentDTO;
import com.speMajor.demo.repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DepartmentServiceImplDiffblueTest {
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test:  {@link DepartmentServiceImpl#addDepartment(DepartmentDTO)}
     */
    @Test
    void testAddDepartment() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class,
                () -> departmentServiceImpl.addDepartment(new DepartmentDTO(1L, "Dept Name")));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Department>>any());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#addDepartment(DepartmentDTO)}
     */
    @Test
    void testAddDepartment2() {
        Department department = mock(Department.class);
        doNothing().when(department).setDeptName(Mockito.<String>any());
        doNothing().when(department).setEmployeeList(Mockito.<List<Employee>>any());
        doNothing().when(department).setId(anyLong());
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        when(departmentRepository.save(Mockito.<Department>any())).thenReturn(department);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(null);
        DepartmentDTO actualAddDepartmentResult = departmentServiceImpl.addDepartment(new DepartmentDTO(1L, "Dept Name"));
        verify(department).setDeptName(Mockito.<String>any());
        verify(department).setEmployeeList(Mockito.<List<Employee>>any());
        verify(department).setId(anyLong());
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Department>>any());
        verify(departmentRepository).save(Mockito.<Department>any());
        assertNull(actualAddDepartmentResult);
    }

    /**
     * Method under test:
     * {@link DepartmentServiceImpl#updateDepartmentDetails(DepartmentDTO, Long)}
     */
    @Test
    void testUpdateDepartmentDetails() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);
        when(departmentRepository.save(Mockito.<Department>any())).thenReturn(department2);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Dept Name");

        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any())).thenReturn(departmentDTO);
        DepartmentDTO actualUpdateDepartmentDetailsResult = departmentServiceImpl
                .updateDepartmentDetails(new DepartmentDTO(1L, "Dept Name"), 1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        verify(departmentRepository).save(Mockito.<Department>any());
        assertSame(departmentDTO, actualUpdateDepartmentDetailsResult);
    }

    /**
     * Method under test:
     * {@link DepartmentServiceImpl#updateDepartmentDetails(DepartmentDTO, Long)}
     */
    @Test
    void testUpdateDepartmentDetails2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);
        when(departmentRepository.save(Mockito.<Department>any())).thenReturn(department2);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class,
                () -> departmentServiceImpl.updateDepartmentDetails(new DepartmentDTO(1L, "Dept Name"), 1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        verify(departmentRepository).save(Mockito.<Department>any());
    }

    /**
     * Method under test:
     * {@link DepartmentServiceImpl#updateDepartmentDetails(DepartmentDTO, Long)}
     */
    @Test
    void testUpdateDepartmentDetails3() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);

        Department department2 = new Department();
        department2.setDeptName("Dept Name");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(1L);
        when(departmentRepository.save(Mockito.<Department>any())).thenReturn(department2);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class,
                () -> departmentServiceImpl.updateDepartmentDetails(new DepartmentDTO(1L, "Dept Name"), 1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        verify(departmentRepository).save(Mockito.<Department>any());
    }

//    /**
//     * Method under test:
//     * {@link DepartmentServiceImpl#updateDepartmentDetails(DepartmentDTO, Long)}
//     */
//    @Test
//    void testUpdateDepartmentDetails4() {
//        Optional<Department> emptyResult = Optional.empty();
//        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
//        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
//                .thenReturn(new DepartmentDTO(1L, "Dept Name"));
//        assertThrows(ResourceNotFoundException.class,
//                () -> departmentServiceImpl.updateDepartmentDetails(new DepartmentDTO(1L, "Dept Name"), 1L));
//        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
//        verify(departmentRepository).findById(Mockito.<Long>any());
//    }

    /**
     * Method under test: {@link DepartmentServiceImpl#getDepartmentById(Long)}
     */
    @Test
    void testGetDepartmentById() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Dept Name");

        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any())).thenReturn(departmentDTO);
        DepartmentDTO actualDepartmentById = departmentServiceImpl.getDepartmentById(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        assertSame(departmentDTO, actualDepartmentById);
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#getDepartmentById(Long)}
     */
    @Test
    void testGetDepartmentById2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> departmentServiceImpl.getDepartmentById(1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:  {@link DepartmentServiceImpl#getAllDepartments()}
     */
    @Test
    void testGetAllDepartments() {
        when(departmentRepository.findAll()).thenReturn(new ArrayList<>());
        List<DepartmentDTO> actualAllDepartments = departmentServiceImpl.getAllDepartments();
        verify(departmentRepository).findAll();
        assertTrue(actualAllDepartments.isEmpty());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#getAllDepartments()}
     */
    @Test
    void testGetAllDepartments2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        ArrayList<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        when(departmentRepository.findAll()).thenReturn(departmentList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
                .thenReturn(new DepartmentDTO(1L, "Dept Name"));
        List<DepartmentDTO> actualAllDepartments = departmentServiceImpl.getAllDepartments();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findAll();
        assertEquals(1, actualAllDepartments.size());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#getAllDepartments()}
     */
    @Test
    void testGetAllDepartments3() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        Department department2 = new Department();
        department2.setDeptName("42");
        department2.setEmployeeList(new ArrayList<>());
        department2.setId(2L);

        ArrayList<Department> departmentList = new ArrayList<>();
        departmentList.add(department2);
        departmentList.add(department);
        when(departmentRepository.findAll()).thenReturn(departmentList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
                .thenReturn(new DepartmentDTO(1L, "Dept Name"));
        List<DepartmentDTO> actualAllDepartments = departmentServiceImpl.getAllDepartments();
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findAll();
        assertEquals(2, actualAllDepartments.size());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#getAllDepartments()}
     */
    @Test
    void testGetAllDepartments4() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);

        ArrayList<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        when(departmentRepository.findAll()).thenReturn(departmentList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class, () -> departmentServiceImpl.getAllDepartments());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        verify(departmentRepository).findAll();
    }

    @Test
    public void testGetDepartmentByIdWithInvalidId() {
        // Arrange
        Long invalidDepartmentId = 123L; // Assuming this departmentId is invalid

        // Mock the behavior of departmentRepository.findById to return null
        when(departmentRepository.findById(invalidDepartmentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            departmentServiceImpl.getDepartmentById(invalidDepartmentId);
        });

        // Verify that departmentRepository.findById is called with the correct argument
        verify(departmentRepository, times(1)).findById(invalidDepartmentId);
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        ArrayList<Employee> employeeList = new ArrayList<>();
        department.setEmployeeList(employeeList);
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        doNothing().when(departmentRepository).delete(Mockito.<Department>any());
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        departmentServiceImpl.deleteDepartment(1L);
        verify(departmentRepository).delete(Mockito.<Department>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
        assertEquals(employeeList, departmentServiceImpl.getAllDepartments());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment2() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        Optional<Department> ofResult = Optional.of(department);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L)).when(departmentRepository)
                .delete(Mockito.<Department>any());
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> departmentServiceImpl.deleteDepartment(1L));
        verify(departmentRepository).delete(Mockito.<Department>any());
        verify(departmentRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment3() {
        Optional<Department> emptyResult = Optional.empty();
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> departmentServiceImpl.deleteDepartment(1L));
        verify(departmentRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link DepartmentServiceImpl#dtoToDepartment(DepartmentDTO)}
     */
    @Test
    void testDtoToDepartment() {
        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Department>>any())).thenReturn(department);
        Department actualDtoToDepartmentResult = departmentServiceImpl.dtoToDepartment(new DepartmentDTO(1L, "Dept Name"));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Department>>any());
        assertSame(department, actualDtoToDepartmentResult);
    }

    /**
     * Method under test:  {@link DepartmentServiceImpl#dtoToDepartment(DepartmentDTO)}
     */
    @Test
    void testDtoToDepartment2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Department>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));
        assertThrows(ResourceNotFoundException.class,
                () -> departmentServiceImpl.dtoToDepartment(new DepartmentDTO(1L, "Dept Name")));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Department>>any());
    }

    /**
     * Method under test: {@link DepartmentServiceImpl#DepartmentTodto(Department)}
     */
    @Test
    void testDepartmentTodto() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Dept Name");

        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any())).thenReturn(departmentDTO);

        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        DepartmentDTO actualDepartmentTodtoResult = departmentServiceImpl.DepartmentTodto(department);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
        assertSame(departmentDTO, actualDepartmentTodtoResult);
    }

    /**
     * Method under test:  {@link DepartmentServiceImpl#DepartmentTodto(Department)}
     */
    @Test
    void testDepartmentTodto2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42L));

        Department department = new Department();
        department.setDeptName("Dept Name");
        department.setEmployeeList(new ArrayList<>());
        department.setId(1L);
        assertThrows(ResourceNotFoundException.class, () -> departmentServiceImpl.DepartmentTodto(department));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<DepartmentDTO>>any());
    }
}
