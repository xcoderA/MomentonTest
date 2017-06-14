package com.momenton.test.com.momenton.test.dto;

import com.momenton.test.exceptions.EmployeeDataException;

/**
 * Created by Amal on 13/06/2017.
 */
public class Employee {

    private Integer id;
    private Integer managerId;
    private String name;

    public Integer getId() {
        return id;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public Employee(Integer id, Integer managerId, String name) {

        if (id == null || id < 1) {
            throw new EmployeeDataException("The EmployeeId should be greater than or equal to 1");
        }
        if (name == null || name.isEmpty()) {
            throw new EmployeeDataException("Every Employee should have a name");
        }
        this.id = id;
        this.managerId = managerId;
        this.name = name;
    }

}
