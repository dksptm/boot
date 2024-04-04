package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot240403ApplicationTests {
	
	@Autowired
	EmpMapper empMapper;

	//@Test
	void contextLoads() {
	}

	@Test
	void empAllList() {
		List<EmpVO> list = empMapper.selectEmpAll();
		assertTrue(!list.isEmpty());
	}
	
	@Test
	void empInsert() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("hong");
		empVO.setEmail("kdHong@google.com");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.insertEmp(empVO);
		assertEquals(1, result);
	}
	
	@Test
	void empUpdate() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Jong");
		empVO.setJobId("AD_VP");
		empVO.setEmployeeId(315);
		
		int result = empMapper.updateEmp(empVO);
		assertEquals(1, result);	
	}
	
}
