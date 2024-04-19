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
	public String empList(Model model) { // Model = Request + Response.
		// 1) 해당 기능 수행 -> Service.
		List<EmpVO> list = empService.empList();
		// 2) 클라이언트에 전달할 데이터 담기.
		model.addAttribute("empList", list);
		// 3) 데이터를 출력할 페이지 선택.
		return "emp/list";
		// prefix => classpath:/templates/ (-> "/emp/list" 이렇게하면 안된다.)
		// subfix => .html
	}
	
	// 단건조회.
	@GetMapping("empInfo") // 커맨드 객체 => QueryString
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "emp/info";
	}
	
	// 등록 - 페이지 => GET
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		//model.addAttribute("empVO", new EmpVO());
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
			uri = "empList"; // -> 다시 확인해야함
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
