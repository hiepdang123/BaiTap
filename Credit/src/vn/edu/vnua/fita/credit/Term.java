package vn.edu.vnua.fita.credit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Term {
	// Tối ưu hơn nên dùng hashmap(key,value), key: mã môn học
	private List<Subject> listSubject = new ArrayList<Subject>();
	
	public Term() {
		
	}
	
	public List<Subject> getListSubject() {
		return listSubject;
	}
	
	public void addSubject(Subject subject) {
		listSubject.add(subject);
	}

	public float calTermAvageMark() {
		float ts = 0;
		float ms = 0;
		for (Subject subject : listSubject) {
			ts += subject.calConversionMark() * subject.getCredit();
			ms += subject.getCredit();
		}
		return ts / ms;
	}
	
	@Override
	public String toString() {
		int i = 1;
		String info="";
		for (Subject subject : listSubject) {
			info += "\n\t\t" + subject.toString();
			i++;
		}
		return info;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Term term = (Term) obj;
		float d = Math.abs(this.calTermAvageMark() - term.calTermAvageMark());
		return d < 0.3;
	}

	// Xóa môn
	public void removeSubject(String subjectCode) {
		for (Subject subject : listSubject) {
			if(subject.equals(subjectCode)) {
				listSubject.remove(subject);
			}
		}
	}
	
	// Sửa môn (ko cho sửa mã)
	public void updateSubject(Subject subject) {
		int i=0;
		for(Subject s:listSubject) {
			if(s.getSubjectCode().equals(subject.getSubjectCode())) {
				listSubject.set(i, subject);
			}
			i++;
		}
	}
}
