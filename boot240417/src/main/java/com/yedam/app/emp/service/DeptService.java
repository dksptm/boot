package com.yedam.app.emp.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	
	// 부서 전체조회.
	public List<DeptVO> deptList();
	
	// 부서 단건조회.
	public Map<String, Object> deptInfo(DeptVO deptVO);
	
	// 부서 등록.
	public int deptInsert(DeptVO deptVO);
	
	// 부서 수정.
	public Map<String, Object> deptUpdate(DeptVO deptVO);

}
