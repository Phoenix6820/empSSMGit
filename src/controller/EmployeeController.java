package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import entity.Department;
import entity.Employee;
import service.DepartmentService;
import service.EmployeeService;

import util.Constant;
import util.Pagination;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	private static final String path = "employee/";
	@Autowired
	EmployeeService empService;
	@Autowired
	DepartmentService depService;

	@RequestMapping("search")
	public ModelAndView search(Employee condition, Integer ye) {
		ModelAndView mv = new ModelAndView(path + "list");
		if (ye == null) {
			ye = 1;
		}
		int count = empService.searchCount(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

		List<Employee> list = empService.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Department> depList = depService.searchAll();
		mv.addObject("p", p);
		mv.addObject("depList", depList);
		mv.addObject("c", condition);
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping("searchLazy")
	public ModelAndView searchLazy(Employee condition, Integer ye) {
		String str = "ds123fazddfd";
		ModelAndView mv = new ModelAndView(path + "list");
		if (ye == null) {
			ye = 1;
		}
		int count = empService.searchCount(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

		List<Employee> list = empService.searchLazy(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Department> depList = depService.searchAll();
		mv.addObject("p", p);
		mv.addObject("depList", depList);
		mv.addObject("c", condition);
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping("searchAll")
	public ModelAndView searchAll() {
		ModelAndView mv = new ModelAndView(path + "list");

		List<Employee> list = empService.searchAll();
		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "showAdd")
	public ModelAndView showAdd() {

		ModelAndView mv = new ModelAndView(path + "add");

		List<Department> depList = depService.searchAll();
		mv.addObject("depList", depList);

		return mv;

	}

	@RequestMapping(value = "add")
	public String add(Employee emp, @RequestParam(value = "file") MultipartFile[] myfiles) {

		String path = "c:/test06/pic";

		String photoName = "";

		for (MultipartFile myfile : myfiles) {
			if (!myfile.isEmpty()) {
				String oldName = myfile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				photoName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."));
				try {
					myfile.transferTo(new File(path + "/" + photoName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		emp.setPhoto(photoName);
		boolean flag = empService.add(emp);

		// show(request, response);

		return "redirect:search.do";
	}

	@RequestMapping(value = "showAdd2")
	public ModelAndView showAdd2() {
		ModelAndView mv = new ModelAndView(path + "add2");

		List<Department> depList = depService.searchAll();
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(@RequestParam(value = "file") MultipartFile[] myfiles) {

		String path = "c:/test06/pic";

		String photoName = "";

		for (MultipartFile myfile : myfiles) {
			if (!myfile.isEmpty()) {
				String oldName = myfile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				photoName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."));
				try {
					myfile.transferTo(new File(path + "/" + photoName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return photoName;
	}

	@RequestMapping(value = "add2")
	public String add2(Employee emp) {
		boolean flag = empService.add(emp);

		return "redirect:search.do";
	}

	@RequestMapping(value = "showUpdate")
	public ModelAndView showUpdate(Integer id) {
		ModelAndView mv = new ModelAndView(path + "update");

		Employee emp = empService.searchById(id);
		List<Department> depList = depService.searchAll();
		mv.addObject("depList", depList);
		mv.addObject("emp", emp);
		return mv;
	}

	@RequestMapping(value = "update")
	public String update(Employee emp) {

		boolean flag = empService.update(emp);

		return "redirect:search.do";

	}

	@RequestMapping(value = "delete")
	public String delete(Integer id) {
		boolean flag = empService.delete(id);

		return "redirect:search.do";

	}
}
