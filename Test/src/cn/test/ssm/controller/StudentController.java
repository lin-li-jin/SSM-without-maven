package cn.test.ssm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.test.ssm.pojo.Student;
import cn.test.ssm.service.StudentService;

/**
 * Copyright © 2018 Talent Information Solutions Ltd. All rights reserved.
 * 
 * @ClassName: StudentController
 * @Description: TODO(学生信息控制器)
 * @author: GK
 * @version V1.0
 */
@Controller
@RequestMapping("/")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	/**
	 * 查询所有学生
	 */
	@RequestMapping("/studentList.action")
	public String studentList(Model model) {
		List<Student> students = studentService.findAllStudent();
		model.addAttribute("studentList", students);
		return "jsp/student/studentList";
	}
	
	/**
	 * 转向新增界面
	 */
	@RequestMapping("/toAddStudent.action")
	public String toAddStudent() {
		return "jsp/student/studentAdd";
	}

	/**
	 * 根据页面输入的信息添加学生
	 */
	@RequestMapping("/addStudent.action")
	public String addStudent(Student stu, Model model) {
		studentService.saveStudent(stu);
		return "redirect:/studentList.action";
	}
	
	/**
	 * 转向修改界面
	 * @param id 主键ID
	 */
	@RequestMapping("/toModifyStudent.action")
	public String toModifyStudent(Integer id, Model model) {
		model.addAttribute("student", studentService.queryStudent(id));
		return "jsp/student/studentModify";
	}

	/**
	 * 根据页面输入的信息修改学生
	 */
	@RequestMapping("/modifyStudent.action")
	public String modifyStudent(Student stu, Model model) {
		studentService.modifyStudent(stu);
		return "redirect:/studentList.action";
	}

	/**
	 * 根据页面输入的信息删除学生
	 */
	@RequestMapping("/removeStudent.action")
	public String removeStudent(Integer id, Model model) {
		studentService.removeStudent(id);
		return "redirect:/studentList.action";
	}
}
