package com.momenton.test.dto;
import com.momenton.test.com.momenton.test.dto.Employee;
import com.momenton.test.com.momenton.test.dto.Organisation;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.powermock.reflect.internal.WhiteboxImpl;

import java.util.List;

/**
 * Created by Amal on 14/06/2017.
 */
public class OrganisationTest {

	Organisation  org;

   @Before
	public void setUp()
	{
		Employee emp = new Employee(1,null,"ceo");
		org= new Organisation(emp);
		org.inductEmployee(new Employee(2,1,"Jack"));
		org.inductEmployee(new Employee(3,2,"Jill"));

	}

	@Test(expected = com.momenton.test.exceptions.EmployeeDataException.class)
	public void should_throw_exception_when_employee_exists()
	{
		org.inductEmployee(new Employee(2,1,"testName"));
	}

	@Test(expected = com.momenton.test.exceptions.ManagerNotFoundException.class)
	public void should_throw_exception_when_manager_not_found()
	{
		org.inductEmployee(new Employee(4,100,"testName"));
	}

	@Test(expected = com.momenton.test.exceptions.EmployeeDataException.class)
	public void should_throw_exception_when_manager_is_null()
	{
		org.inductEmployee(new Employee(4,null,"testName"));
	}

	@Test
	public void should_induct_a_valid_employee() throws Exception {
		Employee emp = new Employee(4,2,"testName");
		org.inductEmployee(emp);
		boolean result = ((List<Employee>)WhiteboxImpl.getInternalState(org,"employees")).contains(emp);
		assertTrue(result);
	}

	@Test
	public void should_return_true_if_employee_exits() throws Exception {

		boolean result = WhiteboxImpl.invokeMethod(org,"checkIfIdAlreadyExists",2);
		assertTrue(result);
	}

	@Test
	public void should_return_false_if_employee_does_not_exit() throws Exception {

		boolean result = WhiteboxImpl.invokeMethod(org,"checkIfIdAlreadyExists",900);
		assertFalse(result);
	}

	@Test
	public void should_return_direct_subordinates()
	{
		List<Employee> subordinates = org.findDirectSubordinates(org.getCeo());
		assertThat(subordinates.size(), is(1));
		assertThat(subordinates.get(0).getId(),is(2));
	}
	@Test
	public void should_return_correct_level_separators() throws Exception {

		String result = WhiteboxImpl.invokeMethod(org,"getLevelSeparator",3);
		assertThat(result.length(),is(3));
	}
	@Test
	public void should_return_correct_level_separators_for_ceo() throws Exception {

		String result = WhiteboxImpl.invokeMethod(org,"getLevelSeparator",0);
		assertThat(result.length(),is(0));
	}
}
