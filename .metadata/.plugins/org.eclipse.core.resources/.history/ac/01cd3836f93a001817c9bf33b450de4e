package cn.test.ssm.service;

import java.util.List;

import cn.test.ssm.pojo.Student;

/**   
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: StudentService
 * @Description: TODO(学生信息接口) 
 * @author: GK
 * @version V1.0
 */
public interface StudentService {

	/**
	 * 查询所有学生信息
	 * @return 学生信息集合
	 */
	List<Student> findAllStudent();
	
	/**
	 * 根据id查询学生信息
	 * @param id
	 * @return
	 */
	Student queryStudent(Integer id);

	/**
	 * 删除学生信息
	 * @param id 主键ID
	 * @return 成功标志>=1，即为删除成功
	 */
	Integer removeStudent(Integer id);

	/**
	 * 保存学生信息
	 * @param stu 学生信息对象
	 * @return 成功标志>=1，即为保存成功
	 */
	Integer saveStudent(Student stu);

	/**
	 * 修改学生信息
	 * @param stu 学生信息对象
	 * @return 成功标志>=1，即为保存成功
	 */
	Integer modifyStudent(Student stu);
}
