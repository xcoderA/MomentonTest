package com.momenton.test.com.momenton.test.dto;

import com.momenton.test.exceptions.EmployeeDataException;
import com.momenton.test.exceptions.ManagerNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Amal on 13/06/2017.
 */
public class Organisation {

	private List<Employee> employees = new ArrayList<>();
	private Employee ceo;

	public Organisation(Employee ceo) {
		//Assuming that the organisation cannot be created without the CEO
		if(ceo == null)
		{
			throw new RuntimeException("CEO cant be null");
		}
		if (ceo.getManagerId() != null) {
			throw new RuntimeException("The CEO cannot have a manager");
		}
		this.ceo = ceo;
		employees.add(ceo);
	}

	/**
	 * Induct an employee to the organisation.
	 * @param emp
	 */
	public void inductEmployee(Employee emp) {
		if(emp == null)
		{
			throw new EmployeeDataException("Cannot induct a null employee");
		}
		if (checkIfIdAlreadyExists(emp.getId())) {
			throw new EmployeeDataException("The employee Id should be unique. Please give a unique id to employee -  " + emp.getName());
		}
		if(emp.getManagerId() == null){
			throw new EmployeeDataException("All employees should have a manager. Please assign a manager to employee - " + emp.getName());
		}
		Optional<Employee> manager = employees.stream().filter((e) -> emp.getManagerId().equals(e.getId())).findAny();
		if (!manager.isPresent()) {
			throw new ManagerNotFoundException(emp.getId());
		}
		employees.add(emp);
	}

	private boolean checkIfIdAlreadyExists(Integer id) {
		Optional<Employee> emp = employees.stream().filter((e) -> e.getId().equals(id)).findAny();
		return emp.isPresent();
	}

	/**
	 * Finds the subordinates of an Employee.
	 *
	 * @param emp the Employee.
	 * @return The list of subordinates.
	 */
	public List<Employee> findDirectSubordinates(Employee emp) {
		return employees.stream().filter((e) -> {
			if (e.getManagerId() == null) return false;
			return e.getManagerId().equals(emp.getId());
		}).collect(Collectors.toList());
	}

	/**
	 * Prints the organisation structure.
	 */
	public void printOrganisationStructure() {
		System.out.println("**************************************************************************************");
		prettyPrintSubordinates(ceo, 0);
		System.out.println("**************************************************************************************");
	}

	/**
	 * Pretty prints organisation structure with the given employee as root.
	 *
	 * @param emp   - The root employee.
	 * @param level - The level of the employee from the top.
	 */
	private void prettyPrintSubordinates(Employee emp, int level) {
		System.out.println(getLevelSeparator(level) + emp.getName());
		if (!findDirectSubordinates(emp).isEmpty()) {
			for (Employee subordinate : findDirectSubordinates(emp)) {
				prettyPrintSubordinates(subordinate, level + 1);
			}
		}

	}

	/**
	 * This method computes the number of spaces to print before the employee's name while pretty printing.
	 *
	 * @param level - the level of the employee from top.
	 * @return - String of spaces.
	 */
	private String getLevelSeparator(int level) {
		if (level == 0) return "";
		StringBuilder separator = new StringBuilder();
		for (int i = 0; i < level; i++) {
			separator.append("\t");
		}
		return separator.toString();
	}

	public Employee getCeo() {
		return ceo;
	}
}
