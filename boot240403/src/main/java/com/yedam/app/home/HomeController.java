package com.yedam.app.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	// QueryString => 커맨드객체.
	// Method는 상관없음(GET, POST).
	// Content-type : application/x-www-form-urlencoded (무조건!).
	@RequestMapping(path="comobj", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		log.info("path : /comobj");
		log.info("= employee_id : " + empVO.getEmployeeId());
		log.info("= last_name : " + empVO.getLastName());
		log.info(empVO.toString());
		return "";
	}
	
	@RequestMapping(path="reqparm", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, String lastName, 
			@RequestParam(name = "message", defaultValue = "No message", required=false) String msg) {
		log.info("path : /reqparm");
		log.info("= employee_id : " + employeeId);
		log.info("= last_name : " + lastName);
		log.info("= msg : " + msg);
		return "";
	}
	
	// 경로에 포함 => 기본타입 매개변수.
	// Method는 상관없음.
	// Content-type는 상관없음.
	@RequestMapping(path="path/{id}")
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		log.info("path : /path/{id}");
		log.info("= id : " + id);
		return "";
	}
	
	@PostMapping(path="resbody")
	@ResponseBody
	public Map<String, Object> requestBody(@RequestBody EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		map.put("path", "resbody");
		map.put("data", empVO);
		log.info("= map : " + map);
		return map;
	}
	
}
