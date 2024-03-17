package vn.edu.vnua.fita.credit;

import java.util.Scanner;

public abstract class AbstractSubject {
	private String subjectCode;
	private String subjectName;
	private int credit;

	public AbstractSubject() {

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

	public AbstractSubject(String subjectCode, String subjectName, int credit) {
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.credit = credit;
	}

	public abstract float calSubjectMark();

	public float calConversionMark() {
		float subjectMark = calSubjectMark();
		float conversionMark = -1;
		if (subjectMark < CreditRule.LEVEL_F && subjectMark >= 0) {
			conversionMark = 0;
		} else if (subjectMark < CreditRule.LEVEL_D) {
			conversionMark = 1;
		} else if (subjectMark < CreditRule.LEVEL_D_PLUS) {
			conversionMark = 1.5f;
		} else if (subjectMark < CreditRule.LEVEL_C) {
			conversionMark = 2;
		} else if (subjectMark < CreditRule.LEVEL_C_PLUS) {
			conversionMark = 2.5f;
		} else if (subjectMark < CreditRule.LEVEL_B) {
			conversionMark = 3;
		} else if (subjectMark < CreditRule.LEVEL_B_PLUS) {
			conversionMark = 3.5f;
		} else if (subjectMark <= CreditRule.LEVEL_A) {
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

	
	
	public void enterInfo(Scanner sc) {
		System.out.println("Nhập vào mã môn học: ");
		subjectCode=sc.nextLine();
		System.out.println("Nhập vào tên môn học: ");
		subjectName=sc.nextLine();
		System.out.println("Nhập vào tín chỉ: ");
		credit=sc.nextInt();
//		System.out.println("Nhập vào điểm chuyên cần: ");
//		setAttendanceMark(sc.nextFloat());
//		System.out.println("Nhập vào điểm giữa kì: ");
//		setMidExamMark(sc.nextFloat());
//		System.out.println("Nhập vào điểm cuối kì: ");
//		setFinalExamMark(sc.nextFloat());
	}

	@Override
	public String toString() {
		return subjectCode + " - " + subjectName + " - "+credit;
	}
}
