package vn.edu.vnua.fita.credit;

import java.util.List;

import org.hibernate.Session;

import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;

public class StudentDAO {
	@SuppressWarnings("unchecked")
	public static List<Student> getAllStudent() {
//		Transaction transaction = null;
		List<Student> listStudent = null;
		try (Session session = CreditHibernateUtil.getSessionFactory().openSession();) {

//			transaction = (Transaction) session.beginTransaction();
			listStudent = session.createQuery("from SINHVIEN").getResultList();
//			transaction.commit();
		} catch (Exception e) {
//			if (transaction != null) {
//				try {
//					transaction.rollback();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
			e.printStackTrace();
		}
		return listStudent;
	}

	public static Student getStudent(String id) {
//		Transaction transaction = null;
		Student student = null;
		try (Session session = CreditHibernateUtil.getSessionFactory().openSession();) {

//			transaction = (Transaction) session.beginTransaction();
			student = session.get(Student.class,id);
//			transaction.commit();
		} catch (Exception e) {
//			if (transaction != null) {
//				try {
//					transaction.rollback();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
			e.printStackTrace();
		}
		return student;
	}
	
	public static void main(String[] args) {
		List<Student> studentList=StudentDAO.getAllStudent();
		for (Student student : studentList) {
			System.out.println(student);
		}
//		Student s=StudentDAO.getStudent("651660");
//		System.out.println(s);
	}
}
