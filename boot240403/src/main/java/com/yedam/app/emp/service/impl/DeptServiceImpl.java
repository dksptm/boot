package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.DeptMapper;
import com.yedam.app.emp.service.DeptService;
import com.yedam.app.emp.service.DeptVO;
import com.yedam.app.emp.service.EmpVO;

@Service
public class DeptServiceImpl implements DeptService {
	
	DeptMapper deptMapper;
	
	@Autowired
	public DeptServiceImpl(DeptMapper deptMapper) {
		this.deptMapper = deptMapper;
	}

	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public Map<String, Object> deptInfo(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		DeptVO findVO = deptMapper.selectDept(deptVO);
		List<EmpVO> empList = deptMapper.selectDeptEmp(findVO.getDepartmentId());
		map.put("findVO", findVO);
		map.put("empList", empList);
		return map;
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDept(deptVO);
		
		if(result == 1) {
			return deptVO.getDepartmentId();
		} else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = deptMapper.updateDept(deptVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", deptVO);
		
		return map;
	}

}
