package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@GetMapping("menu")
	public String empMain() {
		return "emp/menu";
	}
	
	// 전체조회.
	@GetMapping("empList")
	public String empList(Model model) {
		List<EmpVO> list = empService.empList();
		model.addAttribute("empList", list);
		return "emp/list";
	}
	
	// 단건조회.
	@GetMapping("empInfo") 
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "emp/info";
	}
	
	// 등록 - 페이지 => GET
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		EmpVO empVO = empService.getEmpId();
		model.addAttribute("empVO", empVO);
		return "emp/insert";
	}
	
	// 등록 - 처리 => POST
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eId = empService.empInsert(empVO);
		String uri = null;
		
		if (eId > -1) {
			uri = "redirect:empInfo?employeeId=" + eId;
		} else {
			uri = "empList"; 
		}
		return uri;
	}
	
	// 수정 - 페이지
	@GetMapping("empUpdate")
	public String empUpdateForm(@RequestParam Integer employeeId, Model model) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "emp/update";
	}
	
	// 수정 - 처리 방법1: AJAX => QueryString
	@PostMapping("empUpdate")
	@ResponseBody
	public Map<String, Object> empUpdateProcess(EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	// 수정 - 처리 방법2: AJAX => JSON
	//@PostMapping("empUpdate")
	@ResponseBody
	public Map<String, Object> empUpdateProcessAjax(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	// 삭제 - 처리
	@GetMapping("empDelete")
	public String empDelete(EmpVO empVO) {
		empService.empDelete(empVO);
		return "redirect:empList";
	}
	

}
