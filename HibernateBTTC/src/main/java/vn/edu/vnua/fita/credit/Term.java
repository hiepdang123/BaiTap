package vn.edu.vnua.fita.credit;

import java.util.HashMap;
import java.util.Map;

public class Term {
	// Tối ưu hơn nên dùng hashmap(key,value), key: mã môn học
	private String termCode;
	private Map<String,CreditRule> mapSubject;
	
	public Term(String termCode) {
		this.termCode=termCode;
		mapSubject = new HashMap<String,CreditRule>();
	}
	
	public String getTermCode() {
		return termCode;
	}
	
	public void setTermCode(String termCode) {
		this.termCode=termCode;
	}

	public Map<String,CreditRule> getmapSubject() {
		return mapSubject;
	}
	
	public void addSubject(String subjectCode,CreditRule subject) {
		mapSubject.put(subjectCode,subject);
	}

	public float calTermAvageMark() {
		float ts = 0;
		float ms = 0;
		for (CreditRule subject : mapSubject.values()) {
			ts += subject.calConversionMark() * subject.getCredit();
			ms += subject.getCredit();
		}
		return ts / ms;
	}

	// Xóa môn
	public void removeSubject(String subjectCode) {
		mapSubject.remove(subjectCode);
	}
	
	// Sửa môn (ko cho sửa mã)
	public void updateSubject(CreditRule subject) {
		mapSubject.replace(subject.getSubjectCode(), subject);
	}
	
	public int getSumOfCredit() {
		int sum=0;
		for(CreditRule s:mapSubject.values()) {
			sum+=s.getCredit();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		StringBuilder info=new StringBuilder();
		for (CreditRule subject : mapSubject.values()) {
			info.append("\n\t\t" + subject.toString());
		}
		return info.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Term term = (Term) obj;
		float d = Math.abs(this.calTermAvageMark() - term.calTermAvageMark());
		return d < 0.3;
	}
}
