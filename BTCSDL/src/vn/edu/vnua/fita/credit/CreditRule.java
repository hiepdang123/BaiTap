package vn.edu.vnua.fita.credit;

public interface CreditRule {
	float LEVEL_F=3.9f;
	float LEVEL_D=5f;
	float LEVEL_D_PLUS=5.5f;
	float LEVEL_C=6f;
	float LEVEL_C_PLUS=6.5f;
	float LEVEL_B=7f;
	float LEVEL_B_PLUS=8f;
	float LEVEL_A=8.5f;
	
	float calConversionMark();
	void setAttendanceMark(float attendanceMark);
	void setMidExamMark(float midExamMark);
	void setPraticeMark(float praticeMark);
	void setFinalExamMark(float finalExamMark);
	float calSubjectMark();
	String getSubjectCode();
	int getCredit();
}
