package repository;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.HibernateUtil;
import hibernate.User;

public class UserRepository {
	static User userObj;
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	public void save() {
		System.out.println(".......Hibernate Maven Example.......\n");
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			for (int i = 101; i <= 105; i++) {
				userObj = new User();
				userObj.setUserid(i);
				userObj.setUsername("Editor " + i);
				userObj.setCreatedBy("Administrator");
				userObj.setCreatedDate(new Date());

				sessionObj.save(userObj);
			}
			System.out.println("\n.......Records Saved Successfully To The Database.......\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	public void edit(int id, String username, String createdBy, Date createdDate) {
		System.out.println(".......Hibernate Maven Example.......\n");
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			userObj = new User();
			userObj.setUserid(id);
			userObj.setUsername(username);
			userObj.setCreatedBy(createdBy);
			userObj.setCreatedDate(createdDate);

			sessionObj.update(userObj);
			System.out.println("\n.......Records Updated Successfully To The Database.......\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	public void delete(int id) {
		System.out.println(".......Hibernate Maven Example.......\n");
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			sessionObj.beginTransaction();

			userObj = new User();
			userObj.setUserid(id);

			sessionObj.delete(userObj);
			System.out.println("\n.......Records Deleted Successfully To The Database.......\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
