package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Department;

public interface DepartmentDao {

	int searchCount(Department condition);

	List<Department> search(@Param("dep") Department condition, @Param("begin") int begin, @Param("size") int size); 

	Department searchById(Integer id);

	int update(Department dep);

	int delete(Integer id);

	int add(Department dep);

	List<Department> searchAll();

}
