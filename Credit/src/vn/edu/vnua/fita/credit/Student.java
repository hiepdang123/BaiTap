package vn.edu.vnua.fita.credit;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 1SV có nhiều học kì
// Mỗi học kì có nhiều môn học
// Thêm sửa xóa môn học

public class Student extends Human {
	private String class_;
	private Map<Integer,Term> termMap=new HashMap<Integer, Term>();
	
	public Student() {
	}

	public Student(String code) {
		super(code);
	}

	public Student(String code, String fullname) {
		super(code, fullname);
	}

	public Student(String code, String fullname, String class_) {
		super(code, fullname);
		this.class_ = class_;
	}

	public Student(String code, String fullname, String class_, String address) {
		super(address, code, fullname);
		this.class_ = class_;
	}
	
	public void addTerm(int id,Term term) {
		termMap.put(id, term);
	}
	
	public void addSubject(int id,Subject subject) {
		if(termMap.get(id)!=null) {
			termMap.get(id).addSubject(subject);
		}else {
			termMap.put(id, new Term());
			termMap.get(id).addSubject(subject);
		}
	}
	
	public void editSubject(int id,Subject subject) {
		termMap.get(id).updateSubject(subject);
	}
	
	public void searchTerm(int termId) {
		for (int i: termMap.keySet()) {
			if(i==termId) {
				System.out.println("Các môn học kì "+i+": "+termMap.get(i));
			}
		}
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}
	
	public void enterInfor(Scanner sc) {
		super.enterInfor(sc);
		System.out.println("Nhập vào tên lớp: ");
		class_=sc.nextLine();
		String lc="";
		do {
			System.out.println("Nhập vào kì học: ");
			int id=sc.nextInt();
			sc.nextLine();
			String option="";
			do {
				Subject subject=new Subject();
				subject.enterInfo(sc);
				addSubject(id, subject);
				sc.nextLine();
				System.out.println("Bạn có muốn tiếp tục nhập môn học của kì "+id+" không(y-Có/n-Không)");
				option=sc.nextLine();
			} while (option.equals("y"));
			System.out.println("Bạn có muốn tiếp tục nhập kì học khác của sinh viên không(y-Có/n-Không)");
			lc=sc.nextLine();
		} while (lc.equals("y"));
	}

	@Override
	public String toString() {
		String info = super.toString() + " - " + class_;
		for(int i: termMap.keySet()) {
			info+="\n\tKì thứ "+i+termMap.get(i);
		}
		
		return info;
	}
}
