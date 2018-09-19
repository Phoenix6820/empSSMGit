package service;

import java.util.List;

import entity.Employee;

public interface EmployeeService {
	public int searchCount(Employee condition);

	public List<Employee> search(Employee condition, int begin, int size);

	public boolean add(Employee emp);

	public Employee searchById(Integer id);

	public boolean delete(Integer id);

	public boolean update(Employee emp);

	public List<Employee> searchAll();

	public List<Employee> searchLazy(Employee condition, int begin, int empNumInPage);

}
