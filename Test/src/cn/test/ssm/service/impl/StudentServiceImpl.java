package cn.test.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.test.ssm.mapper.StudentMapper;
import cn.test.ssm.pojo.Student;
import cn.test.ssm.service.StudentService;

/**   
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: StudentServiceImpl
 * @Description: TODO(学生信息接口实现类) 
 * @author: GK
 * @version V1.0
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public List<Student> findAllStudent() {
		return studentMapper.findAllStudent();
	}

	@Override
	public Student queryStudent(Integer id) {
		return studentMapper.queryStudent(id);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer removeStudent(Integer id) {
		return studentMapper.removeStudent(id);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer saveStudent(Student stu) {
		return studentMapper.saveStudent(stu);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Integer modifyStudent(Student stu) {
		return studentMapper.modifyStudent(stu);
	}
}
