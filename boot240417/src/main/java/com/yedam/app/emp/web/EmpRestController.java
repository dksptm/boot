package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@RestController // @Controller + 모든 메소드에 @ResponseBody를 적용
public class EmpRestController {
	
	@Autowired
	EmpService empService;
	
	/**
	 * 메소드 리턴타입에 페이지가 아니라 반환하고자하는 데이터 타입. 
	 * 모델을 사용하지 않는다.
	 * uri : 자원에 대한 부분을 uri로 표시한다.(ex.emps)
	 **/
	
	// 전체조회 : GET
	@GetMapping("emps") 
	public List<EmpVO> empList() {
		return empService.empList();
	}
	// => http://localhost:8080/emps 하면 데이터가 화면에 뿌려짐.
	
	// 단건조회 : GET
	@GetMapping("emps/{id}") // 주의: 접속할때 반드시 {} 개수만큼 어떤 값이라도 반드시 있어야 함
	public EmpVO empInfo(@PathVariable Integer id) { 
		// or @PathVariable(name = "id") Integer employeeId
		// emps
		// emps/100
		// emps/100/Hong
		// emps/100/Hong/20 
		// => 모두 경로가 다르다! ?뒤는 데이터지만 /는 경로!
		// 그래서 전체조회 중 검색은 pathVariable를 사용하지 않는다.
		EmpVO findVO = new EmpVO();
		findVO.setEmployeeId(id);
		return empService.empInfo(findVO);
	}
	
	// 브라우저에서 보내는.. 무조건 get방식이다.(조회, 빈페이지)
	//-> 브라우저에서 경로입력하는걸로는 post못함
	
	// 등록 : POST
	@PostMapping("emps")
	public int empInsert(@RequestBody EmpVO empVO) { 
		// 보통 값을 받을때 json으로 많이 받음 -> @RequestBody
		// @RequestBody -> 무조건 json포맷으로 받아야 한다..(헤더에서 무조건 json보내야-컨텐트타입)
		// ObjectMapper의 readValue()메소드..
		return empService.empInsert(empVO);
	}
	
	// 수정 : PUT (단건조회 + 등록)
	@PutMapping("emps/{id}")
	public Map<String, Object> empUpdate(@PathVariable(name = "id")
											Integer employeeId,
										 @RequestBody EmpVO empVO) {
		empVO.setEmployeeId(employeeId); // 단건조회 + 등록
		return empService.empUpdate(empVO); 
	}
	
	// 삭제 : DELETE
	@DeleteMapping("emps/{id}")
	public Map<String, Object> empDelete(@PathVariable(name = "id")
											Integer employeeId) {
		EmpVO findVO = new EmpVO();
		findVO.setEmployeeId(employeeId);
		return empService.empDelete(findVO); 
	}
	

}
