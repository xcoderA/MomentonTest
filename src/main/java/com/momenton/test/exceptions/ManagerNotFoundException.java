package com.momenton.test.exceptions;

/**
 * Created by Amal on 13/06/2017.
 */
public class ManagerNotFoundException extends RuntimeException {

    public ManagerNotFoundException(Integer empId)
    {
        super("The manager assigned is invalid for employee - " + empId);
    }
}
