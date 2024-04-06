package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.emp.service.DeptService;
import com.yedam.app.emp.service.DeptVO;

@Controller
public class DeptController {
	
	DeptService deptService;

	@Autowired
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	// 부서 전체조회.
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
		model.addAttribute("deptList", list);
		return "dept/list";
	}
	
	// 부서 단건조회.
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		Map<String, Object> map = deptService.deptInfo(deptVO);
		model.addAttribute("deptInfo", map.get("findVO"));
		model.addAttribute("empList", map.get("empList"));
		return "dept/info";
	}
	
	// 부서 등록 - 페이지.
	@GetMapping("deptInsert")
	public String deptInsertForm(Model model) {
		model.addAttribute("deptVO", new DeptVO());
		return "dept/deptInsert";
	}
	
	// 부서 등록 - 처리.
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int dId = deptService.deptInsert(deptVO);
		String uri = null;
		if(dId > -1) {
			uri = "redirect:deptInfo?departmentId=" + dId;
		} else {
			uri = "dept/list";			
		}
		return uri;
	}
	
}
