package vn.edu.vnua.fita.credit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HRM {
	private List<Human> hrList;

	public HRM() {
		hrList = new ArrayList<Human>();
	}

	public void addHR(Human hm) {
		hrList.add(hm);
	}

	public void addHR(Scanner sc) {
		int i = 0;
		Human hr = new Human();
		System.out.println("Lựa chọn đối tượng nhập(1-Sinh viên/2-Giảng viên): ");
		do {
			i = sc.nextInt();
			sc.nextLine();
			if (i == 1) {
				hr = new Student();
			} else if (i == 2) {
				hr = new Lecturer();
			} else {
				System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại: ");
				continue;
			}
			hr.enterInfor(sc);
		} while (i != 1 && i != 2);
		hrList.add(hr);
	}

	public void printHRList() {
		int i = 1;
		for (Human human : hrList) {
			System.out.println("Người thứ " + i + ": " + human.toString());
			i++;
		}
	}

	public void printLecturerInfo() {
		int i = 1;
		for (Human human : hrList) {
			if (human instanceof Lecturer) {
				Lecturer lecturer = (Lecturer) human;
				System.out.println("Giảng viên thứ " + i + ": " + lecturer.toString());
				i++;
			}
		}
	}

	public void printStudentInfo() {
		int i = 1;
		for (Human human : hrList) {
			if (human instanceof Student) {
				Student student = (Student) human;
				System.out.println("Sinh viên viên thứ " + i + ": " + student);
				i++;
			}
		}
	}

	public String searchHuman(String code) {
		String info = "";
		for (Human human : hrList) {
			if (human.getCode().contains(code)) {
				info += human.toString();
			}
		}
		return info;
	}

	public void initDemoData() {
		Subject sj1 = new Subject("SJ1", "Subject 1", 1);
		sj1.setAttendanceMark(9);
		sj1.setMidExamMark(9);
		sj1.setFinalExamMark(10);

		Subject sj2 = new Subject("SJ2", "Subject 2", 1);
		sj2.setAttendanceMark(9);
		sj2.setMidExamMark(8);
		sj2.setFinalExamMark(10);

		Subject sj3 = new Subject("SJ3", "Subject 3", 2);
		sj3.setAttendanceMark(10);
		sj3.setMidExamMark(7);
		sj3.setFinalExamMark(10);

		Student std = new Student("st1", "Student 1", "Class 1", "Address 1");
		
		Term t1=new Term();
		Term t2=new Term();
		
		std.addTerm(1, t1);
		std.addTerm(2, t2);

		std.addSubject(1, sj1);
		std.addSubject(2, sj2);
		std.addSubject(1, sj3);
		
		std.searchTerm(1);

		Lecturer lt1 = new Lecturer("LT1", "123456");

		hrList.add(lt1);
		hrList.add(std);
	}

	public void initDemoData(Scanner sc) {
		String option="";
		do {
			addHR(sc);
			System.out.println("Bạn có tiếp tục nhập không(y-Có/n-Không): ");
			option=sc.nextLine();
		} while (option.equals("y"));
	}

	public static void main(String[] args) {
		HRM hrm=new HRM();
		Scanner sc=new Scanner(System.in);
		hrm.initDemoData();
		hrm.initDemoData(sc);
//		hrm.printHRList();
		
		hrm.printStudentInfo();
	}
}
