package tttt;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.talent.exam.domain.ExamAccType;
import com.talent.exam.domain.ExamContent;
import com.talent.exam.modules.admin_mng.controller.SubjectAction;
import com.talent.exam.modules.admin_mng.service.SubjectBo;

public class test {
//	private SubjectBo subjectBo;
//	
//
//	public SubjectBo getSubjectBo() {
//		return subjectBo;
//	}
//
//
//	public void setSubjectBo(SubjectBo subjectBo) {
//		this.subjectBo = subjectBo;
//	}


	@Test
	public void test() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		
		ExamContent examContent = new ExamContent();
		try {
			examContent.setExamContent("请进行一次人民币的take profit交易".getBytes("UTF-8"));
			ExamAccType examAccTypeByAccTypeNo = new ExamAccType();
			examAccTypeByAccTypeNo.setAccTypeNo("C");
			examContent.setExamAccTypeByAccTypeNo(examAccTypeByAccTypeNo);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.save(examContent);
		
//		String hql = "from ExamContent where examNo = ?";
//		ExamContent examContent = (ExamContent) session.createQuery(hql).setInteger(0, 3).uniqueResult();
//		
//		System.out.println(examContent.getExamAccTypeByAccTypeNo());		
		
//		List<ExamContent> list = null;
//		try {
//			list = subjectBo.queryByExchangeType(1);
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(ExamContent content : list){
//			System.out.println(content);
//		}
		
		
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

}
