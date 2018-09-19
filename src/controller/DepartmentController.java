package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Department;
import service.DepartmentService;
import util.Constant;
import util.Pagination;

@Controller
@RequestMapping("dep")
public class DepartmentController {
	private static final String path = "department/";
	@Autowired
	DepartmentService depService;

	@RequestMapping("search")
	public ModelAndView search(Department condition, Integer ye) {
		ModelAndView mv = new ModelAndView(path + "list");
		if (ye == null) {
			ye = 1;
		}
		int count = depService.searchCount(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

		List<Department> list = depService.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		mv.addObject("p", p);
		mv.addObject("c", condition);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "showAdd")
	public ModelAndView showAdd() {

		ModelAndView mv = new ModelAndView(path + "add");
		return mv;

	}

	@RequestMapping(value = "add")
	public String add2(Department dep) {

		boolean flag = depService.add(dep);

		return "redirect:search.do";
	}

	@RequestMapping(value = "showUpdate")
	public ModelAndView showUpdate(Integer id) {
		ModelAndView mv = new ModelAndView(path + "update");

		Department dep = depService.searchById(id);
		mv.addObject("dep", dep);
		return mv;
	}

	@RequestMapping(value = "update")
	public String update(Department dep) {

		boolean flag = depService.update(dep);

		return "redirect:search.do";

	}

	@RequestMapping(value = "delete")
	public String delete(Integer id) {
		try {
			boolean flag = depService.delete(id);
		} catch (Exception e) {

		}
		return "redirect:search.do";

	}
}
