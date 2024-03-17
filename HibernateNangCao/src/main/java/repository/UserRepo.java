package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.User;
import utils.HibernateUtils;

public class UserRepo {
	public List<User> getAllUser(){
		List<User> list=null;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "FROM user";
		    list = session.createQuery(hql, User.class).list();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi get all user: "+e.getMessage());
		}
		return list;
	}
	
	public User getById(int id) {
		User user=null;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "FROM user AS u WHERE u.id = :id";
		    user = session.createQuery(hql, User.class).setParameter("id", 1L).uniqueResult();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getById: "+e.getMessage());
		}
		return user;
	}
	
	public String selectById(int id) {
		String username="";
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "SELECT u.username FROM user u WHERE u.id = :id";
		    username = session.createQuery(hql, String.class).setParameter("id", 1L).uniqueResult();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi selectById: "+e.getMessage());
		}
		return username;
	}
	
	public List<User> getAllUserByCreatedAt(){
		List<User> list=null;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "FROM user u WHERE month(u.createdAt) = month(sysdate())";
		    list = session.createQuery(hql, User.class).list();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getAllUserByCreatedAt: "+e.getMessage());
		}
		return list;
	}
	
	public List<User> orderUserByCreatedAt(){
		List<User> list=null;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "FROM user u WHERE month(u.createdAt) = month(sysdate()) ORDER BY u.createdAt DESC, u.username ASC";
		    list = session.createQuery(hql, User.class).list();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getAllUserByCreatedAt: "+e.getMessage());
		}
		return list;
	}
	
	public List<Object[]> groupByUserByCreatedAt(){
		List<Object[]> list=null;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "SELECT month(createdAt) AS month, COUNT(id) AS numberOfUser FROM user WHERE year(createdAt) = year(sysdate()) GROUP BY month(createdAt) HAVING COUNT(id) > 3";
		    list = session.createQuery(hql).list();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getAllUserByCreatedAt: "+e.getMessage());
		}
		return list;
	}
	
	public boolean updateUser(int id,String fullname,String password){
		int count=0;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "UPDATE user SET fullname = :fullname, password = :password WHERE id = :id";
		    Query query = session.createQuery(hql);
		    query.setParameter("fullname", fullname);
		    query.setParameter("password", password);
		    query.setParameter("id", id);
		    count=query.executeUpdate();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getAllUserByCreatedAt: "+e.getMessage());
		}
		return count>0;
	}
	
	public boolean deleteUser(String month){
		int count=0;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "DELETE FROM user WHERE month(createdAt) = :month";
		    Query query = session.createQuery(hql);
		    query.setParameter("month", month);
		    count=query.executeUpdate();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getAllUserByCreatedAt: "+e.getMessage());
		}
		return count>0;
	}
	
	public boolean insertUser(){
		int count=0;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "INSERT INTO user(fullname, username, password, createdAt, modifiedAt) SELECT fullname, CONCAT('copyOf', username) , password, sysdate(), sysdate() FROM user";
		    Query query = session.createQuery(hql);
		    count=query.executeUpdate();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getAllUserByCreatedAt: "+e.getMessage());
		}
		return count>0;
	}
	
	public List<User> pagingUser(){
		List<User> list=null;
		try (Session session = HibernateUtils.getSessionFactory().openSession();) {

		    // Begin a unit of work
		    session.beginTransaction();
		 
		    // HQL
		    String hql = "FROM user ORDER BY id";
		    Query query = session.createQuery(hql, User.class);
		    query.setFirstResult(2);
		    query.setMaxResults(5);
		    list = query.list();
		    
		    session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Loi getAllUserByCreatedAt: "+e.getMessage());
		}
		return list;
	}
}
