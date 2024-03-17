package vn.edu.vnua.fita.credit;

public class OOPSubject extends AbstractSubject implements CreditRule{
	private float attendanceMark;
	private float midExamMark;
	private float practiceMark;
	private float finalExamMark;
	
	public OOPSubject(String subjectCode, String subjectName, int credit) {
		super(subjectCode,subjectName,credit);
	}

	public void setAttendanceMark(float attendanceMark) {
		if (attendanceMark <= 10 && attendanceMark >= 0) {
			this.attendanceMark = attendanceMark;
		} else {
			System.out.println("Lỗi điểm chuyên cần");
			System.exit(0);
		}
	}

	public void setMidExamMark(float midExamMark) {
		if (midExamMark <= 10 && midExamMark >= 0) {
			this.midExamMark = midExamMark;
		} else {
			System.out.println("Lỗi điểm giữa kì");
			System.exit(0);
		}
	}

	public void setPraticeMark(float practiceMark) {
		if (practiceMark <= 10 && practiceMark >= 0) {
			this.practiceMark = practiceMark;
		} else {
			System.out.println("Lỗi điểm cuối kì");
			System.exit(0);
		}
	}

	public void setFinalExamMark(float finalExamMark) {
		if (finalExamMark <= 10 && finalExamMark >= 0) {
			this.finalExamMark = finalExamMark;
		} else {
			System.out.println("Lỗi điểm cuối kì");
			System.exit(0);
		}
	}

	@Override
	public float calSubjectMark() {
		return attendanceMark * 0.1f + midExamMark * 0.2f + practiceMark * 0.3f + finalExamMark * 0.4f;
	}

	@Override
	public String toString() {
		return super.toString()+" - " + attendanceMark + " - " + midExamMark + " - " + practiceMark + " - " + finalExamMark+" - "+calSubjectMark();
	}
}
