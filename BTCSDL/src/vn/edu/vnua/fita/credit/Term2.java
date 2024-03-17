package vn.edu.vnua.fita.credit;

import java.util.HashMap;
import java.util.Map;

public class Term2 {
	// Tối ưu hơn nên dùng hashmap(key,value), key: mã môn học
	private String termCode;
	private Map<String,Subject> mapSubject;
	
	public Term2(String termCode) {
		this.termCode=termCode;
		mapSubject = new HashMap<String,Subject>();
	}
	
	public String getTermCode() {
		return termCode;
	}
	
	public void setTermCode(String termCode) {
		this.termCode=termCode;
	}

	public Map<String,Subject> getmapSubject() {
		return mapSubject;
	}
	
	public void addSubject(String subjectCode,Subject subject) {
		mapSubject.put(subjectCode,subject);
	}

	public float calTermAvageMark() {
		float ts = 0;
		float ms = 0;
		for (Subject subject : mapSubject.values()) {
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
	public void updateSubject(Subject subject) {
		mapSubject.replace(subject.getSubjectCode(), subject);
	}
	
	public int getSumOfCredit() {
		int sum=0;
		for(Subject s:mapSubject.values()) {
			sum+=s.getCredit();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		StringBuilder info=new StringBuilder();
		for (Subject subject : mapSubject.values()) {
			info.append("\n\t\t" + subject.toString());
		}
		return info.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Term2 term = (Term2) obj;
		float d = Math.abs(this.calTermAvageMark() - term.calTermAvageMark());
		return d < 0.3;
	}
}
