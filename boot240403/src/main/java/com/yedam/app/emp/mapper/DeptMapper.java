package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.DeptVO;
import com.yedam.app.emp.service.EmpVO;

public interface DeptMapper {
	
	// 전체조회.
	public List<DeptVO> selectDeptAll();
	
	// 단건조회.
	public DeptVO selectDept(DeptVO deptVO);
	
	// 단건조회-사원.
	public List<EmpVO> selectDeptEmp(@Param("did")int departmentId);
	
	// 부서등록.
	public int insertDept(DeptVO deptVO);
	
	// 부서수정.
	public int updateDept(DeptVO deptVO);

}
