package com.momenton.test.dto;

import com.momenton.test.com.momenton.test.dto.Employee;

import org.junit.Test;

/**
 * Created by Amal on 14/06/2017.
 */
public class EmployeeTest {

	@Test(expected = com.momenton.test.exceptions.EmployeeDataException.class)
	public void should_throw_exception_if_id_is_null() {
		new Employee(null, 1, "testName");
	}

	@Test(expected = com.momenton.test.exceptions.EmployeeDataException.class)
	public void should_throw_exception_if_id_is_less_than_1() {
		new Employee(0, 1, "testName");
	}

	@Test
	public void should_create_a_valid_employee() {
		new Employee(2, 1, "testName");
	}

	@Test(expected = com.momenton.test.exceptions.EmployeeDataException.class)
	public void should_throw_exception_if_name_is_null() {
		new Employee(10, null, null);
	}

	@Test(expected = com.momenton.test.exceptions.EmployeeDataException.class)
	public void should_throw_exception_if_name_is_empty() {
		new Employee(10, null, "");
	}

}
