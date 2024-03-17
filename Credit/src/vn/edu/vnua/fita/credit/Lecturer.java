package vn.edu.vnua.fita.credit;

import java.util.Scanner;

public class Lecturer extends Human{
	private String password;
	
	public Lecturer() {
		
	}
	
	public Lecturer(String code, String password) {
		super(code);
		this.password=password;
	}
	
	public Lecturer(String code, String fullname, String address) {
		super(code,fullname,address);
	}
	
	public void enterInfor(Scanner sc) {
		super.enterInfor(sc);
		System.out.println("Nhập vào mật khẩu: ");
		password=sc.nextLine();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
