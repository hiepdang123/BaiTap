package vn.edu.vnua.fita.credit;

import java.util.Scanner;

public class Subject {
	private String subjectCode;
	private String subjectName;
	private int credit;
	private float attendanceMark;
	private float midExamMark;
	private float finalExamMark;

	public Subject() {

	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Subject(String subjectCode, String subjectName, int credit) {
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.credit = credit;
	}

	public float calSubjectMark() {
		return attendanceMark * 0.1f + midExamMark * 0.4f + finalExamMark * 0.5f;
	}

	public float calConversionMark() {
		float subjectMark = calSubjectMark();
		float conversionMark = -1;
		if (subjectMark < 4 && subjectMark >= 0) {
			conversionMark = 0;
		} else if (subjectMark < 5) {
			conversionMark = 1;
		} else if (subjectMark < 5.5) {
			conversionMark = 1.5f;
		} else if (subjectMark < 6.5) {
			conversionMark = 2;
		} else if (subjectMark < 7) {
			conversionMark = 2.5f;
		} else if (subjectMark < 7.5) {
			conversionMark = 3;
		} else if (subjectMark < 8.5) {
			conversionMark = 3.5f;
		} else if (subjectMark <= 10) {
			conversionMark = 4;
		} else {
			conversionMark=-1;
		}
		return conversionMark;
	}

	public float calConversionMark(String grade) {
		float conversionMark = -1;
		switch (grade) {
		case "A": {
			conversionMark = 4;
			break;
		}
		case "B+": {
			conversionMark = 3.5f;
			break;
		}
		case "B": {
			conversionMark = 3;
			break;
		}
		case "C+": {
			conversionMark = 2.5f;
			break;
		}
		case "C": {
			conversionMark = 2;
			break;
		}
		case "D+": {
			conversionMark = 1.5f;
			break;
		}
		case "D": {
			conversionMark = 1;
			break;
		}
		case "F": {
			conversionMark = 0;
			break;
		}
		default:
			System.out.println("Diem khong hop le!");
			conversionMark=-1;
		}
		return conversionMark;
	}

	public String calGrade() {
		float subjectMark = calSubjectMark();
		String grade = "";
		if (subjectMark < 4 && subjectMark >= 0) {
			grade = "F";
		} else if (subjectMark < 5) {
			grade = "D";
		} else if (subjectMark < 5.5) {
			grade = "D+";
		} else if (subjectMark < 6.5) {
			grade = "C";
		} else if (subjectMark < 7) {
			grade = "C+";
		} else if (subjectMark < 7.5) {
			grade = "B";
		} else if (subjectMark < 8.5) {
			grade = "B+";
		} else if (subjectMark <= 10) {
			grade = "A";
		} else {
			grade = "Error";
		}
		return grade;
	}

	public int getCredit() {
		return credit;
	}

	public void setAttendanceMark(float attendanceMark) {
		if(attendanceMark<=10&&attendanceMark>=0) {
			this.attendanceMark = attendanceMark;
		}else {
			System.out.println("Lỗi điểm chuyên cần");
			System.exit(0);
		}
	}

	public void setMidExamMark(float midExamMark) {
		if(midExamMark<=10&&midExamMark>=0) {
			this.midExamMark = midExamMark;
		}else {
			System.out.println("Lỗi điểm giữa kì");
			System.exit(0);
		}
	}

	public void setFinalExamMark(float finalExamMark) {
		if(finalExamMark<=10&&finalExamMark>=0) {
			this.finalExamMark = finalExamMark;
		}else {
			System.out.println("Lỗi điểm cuối kì");
			System.exit(0);
		}
	}
	
	public void enterInfo(Scanner sc) {
		System.out.println("Nhập vào mã môn học: ");
		subjectCode=sc.nextLine();
		System.out.println("Nhập vào tên môn học: ");
		subjectName=sc.nextLine();
		System.out.println("Nhập vào tín chỉ: ");
		credit=sc.nextInt();
		System.out.println("Nhập vào điểm chuyên cần: ");
		setAttendanceMark(sc.nextFloat());
		System.out.println("Nhập vào điểm giữa kì: ");
		setMidExamMark(sc.nextFloat());
		System.out.println("Nhập vào điểm cuối kì: ");
		setFinalExamMark(sc.nextFloat());
	}

	@Override
	public String toString() {
		return subjectCode + " - " + subjectName + " - "+credit+" - " + attendanceMark+" - "+midExamMark+" - "+finalExamMark;
	}
}
