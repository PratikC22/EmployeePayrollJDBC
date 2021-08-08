package com.bridgelabz.employeepayrollservice;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeePayrollData {
    public int id;
    public String name;
    public double basic_Pay;
    public LocalDate start;

    public EmployeePayrollData(int id, String name, double basic_pay, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.basic_Pay = basic_pay;
        this.start = startDate;
    }

    @Override
    public String toString() {
        return "EmployeePayrollData{" + "id=" + id + ", name='" + name + '\'' + ", salary=" + basic_Pay + ", startDate="
               + start + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return id == that.id && Double.compare(that.basic_Pay, basic_Pay) == 0 && Objects.equals(name, that.name);
    }
}
