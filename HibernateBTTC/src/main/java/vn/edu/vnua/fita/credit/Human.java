package vn.edu.vnua.fita.credit;

import java.util.Scanner;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Human {
	@Id
	@Column(name = "MaSV")
	private String code;
	
	@Column(name = "DiaChi")
	private String address;
	
	@Column(name = "HoTen")
	private String fullname;
	
	public Human() {
		
	}
	
	public Human(String code) {
		this.code = code;
	}

	public Human(String code, String fullname) {
		this.code = code;
		this.fullname = fullname;
	}

	public Human(String address, String code, String fullname) {
		this.address = address;
		this.code = code;
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public void enterInfor(Scanner sc) {
		System.out.println("Nhập vào tên: ");
		fullname=sc.nextLine();
		System.out.println("Nhập vào mã: ");
		code=sc.nextLine();
		System.out.println("Nhập vào địa chỉ: ");
		address=sc.nextLine();
	}
	
	@Override
	public String toString() {
		return code+" - "+fullname+" - "+address;
	}
}
