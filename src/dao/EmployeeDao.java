package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Employee;

public interface EmployeeDao {
	public int searchCount(@Param("emp") Employee condition);

	public List<Employee> search(@Param("emp") Employee condition, @Param("begin") int begin, @Param("size") int size);

	public int add(Employee emp);

	public Employee searchById(Integer id);

	public int delete(Integer id);

	public int update(Employee emp);

	public int updateByDepId(int depId);

	public List<Employee> searchAll();

	public List<Employee> searchLazy(@Param("emp") Employee condition, @Param("begin") int begin,
			@Param("size") int size);

}
