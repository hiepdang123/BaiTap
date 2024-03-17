package vn.edu.vnua.fita.credit;

import java.util.HashMap;
import java.util.Map;

// 1SV có nhiều học kì
// Mỗi học kì có nhiều môn học
// Thêm sửa xóa môn học

public class Student extends Human {
	private String class_;
	private Map<String,Term> termMap=new HashMap<String, Term>();
	
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
	
	public void addTerm(Term term) {
		termMap.put(term.getTermCode(), term);
	}
	
	public void addTerm(String termCode,Term term) {
		termMap.put(termCode, term);
	}
	
	public void addSubject(String termCode,CreditRule subject) {
		if(termMap.get(termCode)!=null) {
			termMap.get(termCode).addSubject(subject.getSubjectCode(),subject);
		}else {
			termMap.put(termCode, new Term(termCode));
			termMap.get(termCode).addSubject(subject.getSubjectCode(),subject);
		}
	}
	
	public void editSubject(String termCode,CreditRule subject) {
		termMap.get(termCode).updateSubject(subject);
	}
	
	public void removeSubject(String termCode,String subjectCode) {
		termMap.get(termCode).removeSubject(subjectCode);
	}
	
	public int getSumOfCredit() {
		int sum=0;
		
		for(Term term:termMap.values()) {
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

	@Override
	public String toString() {
		String info = super.toString() + " - " + class_;
		for(String i: termMap.keySet()) {
			info+="\n\tKì thứ "+i+termMap.get(i);
		}
		
		return info;
	}
}
