package cn.test.ssm.mapper;

import java.util.List;


import cn.test.ssm.pojo.Student;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: StudentMapper
 * @Description: TODO(学生信息数据库操作接口)
 * @author: GK
 * @version V1.0
 */
public interface StudentMapper {

	/**
	 * 查询所有学生信息
	 * @return
	 */
	List<Student> findAllStudent();
	
	/**
	 * 根据id查询学生信息
	 * @param id 主键Id
	 * @return
	 */
	Student queryStudent(Integer id);

	/**
	 * 删除学生信息
	 * @param id 主键ID
	 * @return
	 */
	Integer removeStudent(Integer id);

	/**
	 * 保存学生信息
	 * @param stu 学生信息对象
	 * @return
	 */
	Integer saveStudent(Student stu);

	/**
	 * 修改学生信息
	 * @param stu 学生信息对象
	 * @return
	 */
	Integer modifyStudent(Student stu);
}
