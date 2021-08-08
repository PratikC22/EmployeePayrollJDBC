package com.bridgelabz.employeepayrollservicetest;

import com.bridgelabz.employeepayrollservice.EmployeePayrollDBService;
import com.bridgelabz.employeepayrollservice.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.EmployeePayrollException;
import com.bridgelabz.employeepayrollservice.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeePayrollDBServiceTest {
    EmployeePayrollDBService employeePayrollDBService = EmployeePayrollDBService.getInstance();

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.readData();
        Assertions.assertEquals(6, employeePayrollDataList.size());
    }

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldNotMatchEmployeeCount() {
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.readData();
        Assertions.assertNotSame(4, employeePayrollDataList.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService
                .readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalary("Teressa", 3000000.00);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Teressa");
        Assertions.assertTrue(result);
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDBUsingPreparedStatement() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService
                .readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalaryUsingPreparedStatement("Teressa", 5000000.00);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Teressa");
        Assertions.assertTrue(result);
    }

    @Test
    public void givenDataRangeWhenRetrievedShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollService
                .readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        List<EmployeePayrollData> employeePayrollData = employeePayrollService
                .retrieveEmployeesForGivenDataRange("2000-01-01", "2000-09-08");
        Assertions.assertEquals(2, employeePayrollData.size());
    }
}
