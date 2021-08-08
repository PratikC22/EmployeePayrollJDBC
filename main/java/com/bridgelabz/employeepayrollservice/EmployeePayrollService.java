package com.bridgelabz.employeepayrollservice;

import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {

    public enum IOService {
        DB_IO
    }

    private List<EmployeePayrollData> employeePayrollDataList;
    EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollDataList) {
        this.employeePayrollDataList = employeePayrollDataList;
    }

    public EmployeePayrollService() {
        this.employeePayrollDataList = new ArrayList<>();
    }

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        this.employeePayrollDataList = employeePayrollDBService.readData();
        return employeePayrollDataList;
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        return this.employeePayrollDataList.stream()
                .filter(employeePayrollData -> employeePayrollData.name.equals(name)).findFirst().orElse(null);
    }

    public void updateEmployeeSalary(String name, double salary) {
        int result = employeePayrollDBService.updateEmployeeData(name, salary);
        if (result == 0)
            return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null)
            employeePayrollData.salary = salary;
    }

    public void updateEmployeeSalaryUsingPreparedStatement(String name, double salary) {
        int result = employeePayrollDBService.updateEmployeeDataUsingStatement(name, salary);
        if (result == 0)
            return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null)
            employeePayrollData.salary = salary;
    }
}