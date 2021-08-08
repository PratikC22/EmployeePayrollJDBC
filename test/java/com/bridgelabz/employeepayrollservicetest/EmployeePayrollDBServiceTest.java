package com.bridgelabz.employeepayrollservicetest;

import com.bridgelabz.employeepayrollservice.EmployeePayrollDBService;
import com.bridgelabz.employeepayrollservice.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.EmployeePayrollException;
import com.bridgelabz.employeepayrollservice.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeePayrollDBServiceTest {
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.readData();
        Assertions.assertEquals(6, employeePayrollDataList.size());
    }

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldNotMatchEmployeeCount() {
        EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.readData();
        Assertions.assertNotSame(4, employeePayrollDataList.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalary("Teressa", 3000000.00);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Teressa");
        Assertions.assertTrue(result);
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDBUsingPreparedStatement() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalaryUsingPreparedStatement("Teressa", 5000000.00);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Teressa");
        Assertions.assertTrue(result);
    }
}
