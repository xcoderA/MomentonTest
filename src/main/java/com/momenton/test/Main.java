package com.momenton.test;

import com.momenton.test.com.momenton.test.dto.Employee;
import com.momenton.test.com.momenton.test.dto.Organisation;

/**
 * Created by Amal on 13/06/2017.
 */
public class Main {

    public static void main(String[] args) {
        Employee ceo =  new Employee(150,null,"Jamie");
        Organisation org = new Organisation(ceo);
        org.inductEmployee(new Employee(100, 150, "Alan"));
        org.inductEmployee(new Employee(220, 100, "Martin"));
        org.inductEmployee(new Employee(275, 100, "Alex"));
        org.inductEmployee(new Employee(400, 150, "Steve"));
        org.inductEmployee(new Employee(190, 400, "David"));
        org.printOrganisationStructure();

    }
}
