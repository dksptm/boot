package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	
	// 전체조회.
	public List<EmpVO> selectEmpAll();
	
	// 단건조회.
	public EmpVO selectEmp(EmpVO empVO);
	
	// 등록.
	public int insertEmp(EmpVO empVO);
	
	// 수정.
	public int updateEmp(EmpVO empVO);
	
	// 삭제.
	public int deleteEmp(@Param("eid")int employeeId);
	
	// 등록할 사원번호 조회.
	public EmpVO getEmpId();

}
