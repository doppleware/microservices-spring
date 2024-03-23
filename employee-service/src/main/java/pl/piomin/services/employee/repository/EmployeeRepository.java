package pl.piomin.services.employee.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import pl.piomin.services.employee.model.Employee;

public class EmployeeRepository {

	private List<Employee> employees = new ArrayList<>();
	
	public Employee add(Employee employee) {
		employee.setId((long) (employees.size()+1));
		employees.add(employee);
		return employee;
	}



	@WithSpan
	private void pullHRData(Employee employee){
		UpdateEmployeeTaxInfo(employee);
		UpdateEmployeeCompliances(employee);
		UpdateEmployeeEvaluations(employee);
	}




	@WithSpan
	public Employee findById(Long id) {
		var employee =  employees.stream()
				.filter(a -> a.getId().equals(id))
				.findFirst()
				.orElseThrow();
		pullHRData(employee);
		return employee;

	}
	
	public List<Employee> findAll() {
		return employees;
	}

	@WithSpan
	public List<Employee> findByDepartment(Long departmentId) {
		var employeeList =  employees.stream()
				.filter(a -> a.getDepartmentId().equals(departmentId))
				.toList();
		for (final Employee employee: employeeList)	{
			pullHRData(employee);
		}
		return employeeList;
	}

	@WithSpan
	public List<Employee> findByOrganization(Long organizationId) {
		var employeeList = employees.stream()
				.filter(a -> a.getOrganizationId().equals(organizationId))
				.toList();
		for (final Employee employee: employeeList)	{
			pullHRData(employee);
		}
		return employeeList;
	}


	private void UpdateEmployeeEvaluations(Employee employee) {
	}

	private void UpdateEmployeeCompliances(Employee employee) {

	}

	private void UpdateEmployeeTaxInfo(Employee employee) {

	}


}
