package vn.edu.vnua.fita.credit;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 1SV có nhiều học kì
// Mỗi học kì có nhiều môn học
// Thêm sửa xóa môn học

public class Student2 extends Human {
	private String class_;
	private Map<String,Term2> termMap=new HashMap<String, Term2>();
	
	public Student2() {
	}

	public Student2(String code) {
		super(code);
	}

	public Student2(String code, String fullname) {
		super(code, fullname);
	}

	public Student2(String code, String fullname, String class_) {
		super(code, fullname);
		this.class_ = class_;
	}

	public Student2(String code, String fullname, String class_, String address) {
		super(address, code, fullname);
		this.class_ = class_;
	}
	
	public void addTerm(Term2 term) {
		termMap.put(term.getTermCode(), term);
	}
	
	public void addTerm(String termCode,Term2 term) {
		termMap.put(termCode, term);
	}
	
	public void addSubject(String termCode,Subject subject) {
		if(termMap.get(termCode)!=null) {
			termMap.get(termCode).addSubject(subject.getSubjectCode(),subject);
		}else {
			termMap.put(termCode, new Term2(termCode));
			termMap.get(termCode).addSubject(subject.getSubjectCode(),subject);
		}
	}
	
	public void editSubject(String termCode,Subject subject) {
		termMap.get(termCode).updateSubject(subject);
	}
	
	public void removeSubject(String termCode,String subjectCode) {
		termMap.get(termCode).removeSubject(subjectCode);
	}
	
	public int getSumOfCredit() {
		int sum=0;
		
		for(Term2 term:termMap.values()) {
			sum+=term.getSumOfCredit();
		}
		return sum;
	}
	
	public void searchTerm(String termId) {
		for (String i: termMap.keySet()) {
			if(i.equals(termId)) {
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
			String id=sc.nextLine();
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
		for(String i: termMap.keySet()) {
			info+="\n\tKì thứ "+i+termMap.get(i);
		}
		
		return info;
	}
}
